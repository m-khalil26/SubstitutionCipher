package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.Normalizer;

import util.Contract;

public class Cipher implements ICipher {
		
	// ***** ATTRIBUTS *****

	private boolean hasAlreadyBeenCyphered;
	private int level;
	private String seed;
	private String clearMessage;
	private String cypheredMessage;
	private Alphabet alphabet;
	private Alphabet key;
	private File file;

	// ***** CONSTRUCTEUR *****

	public Cipher() {
			alphabet = new Alphabet(true,true,true);
			key = new Alphabet(true, true,true);
			seed = generateRandomSeed();
			key.setAlphabet(computeKeyFromSeed(seed));
		}

	// ***** REQUETES *****

	public Alphabet getKey() {
		return key;
	}

	public Alphabet getAlphabet() {
		return new Alphabet(alphabet.alphabet());
	}

	public String getSeed() {
		return seed;
	}

	public String getClearMessage() {
		return clearMessage;
	}

	public String getCypheredMessage() {
		Contract.checkCondition(hasAlreadyBeenCyphered);

		return cypheredMessage;
	}

	public int getLevel() {
		return level;
	}
	
	public boolean isFileSelected() {
		return file != null;
	}
	
	




	// ***** COMMANDES *****

	public void setAlphabet(Alphabet a) {
		Contract.checkCondition(a != null);

		alphabet = a;
		key.setAlphabet(computeKeyFromSeed(seed));
	}
	
	public void setFile(File f) {
		Contract.checkCondition(f !=null,"Fichier null");
		file = f;
	}
	
	public void removeFile() {
		file =null;
	}
	
	public void setAlphabet(boolean min , boolean maj, boolean chiffres) {
		//Contract.checkCondition(a != null);
		Contract.checkCondition(min || maj || chiffres, "Aucun alphabet sélectionné");

		alphabet = new Alphabet(min,maj,chiffres);
		key.setAlphabet(computeKeyFromSeed(seed));
	}

	public void setLevel(int lvl) {
		Contract.checkCondition(1 <= lvl && lvl <= 3, "Le level doit être compris entre 1 et 3 compris");

		level = lvl;
	}

	public void setSeed(String seed) {
		Contract.checkCondition(seed != null && !seed.equals(""));

		this.seed = seed;
		key.setAlphabet(computeKeyFromSeed(seed));
	}

	public void setClearMessage(String message) {
		Contract.checkCondition(message != null && !message.equals(""));

		clearMessage = message;
		hasAlreadyBeenCyphered = false;
	}

	public void setCypheredMessage(String message) {
		Contract.checkCondition(message != null && !message.equals(""));

		cypheredMessage = message;
		hasAlreadyBeenCyphered = true;
	}


	public void getClearFromFile() throws IOException {
		Contract.checkCondition(isFileSelected());
		File f = file;
		
		FileReader fr = new FileReader(f);
		try (BufferedReader br = new BufferedReader(fr)) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (containsNonSupportedAlphabet(line)) {
					throw new IOException();
				}
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String fileContent = sb.toString();
			br.close();
			fr.close();

			clearMessage = fileContent;
		}
	}

	public void getCypheredFromFile() throws IOException {
		Contract.checkCondition(isFileSelected());
		File f = file;

		FileReader fr = new FileReader(f);
		try (BufferedReader br = new BufferedReader(fr)) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (containsNonSupportedAlphabet(line)) {
					throw new IOException();
				}
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String fileContent = sb.toString();
			br.close();
			fr.close();

			cypheredMessage = fileContent;
		}
	}

	// ***** OUTILS *****

	/**
	 * Génère une graîne aléatoire
	 */
	private String generateRandomSeed() {
		SecureRandom rd = new SecureRandom();
		StringBuilder randomTokens = new StringBuilder();
		for (int i = 0; i < SEED_SIZE; i++) {
			randomTokens.append(CHARACTERS.charAt(rd.nextInt(CHARACTERS.length())));
		}
		return randomTokens.toString();
	}

	/**
	 * Renvoie la clé à partir de la graîne
	 */
	private char[] computeKeyFromSeed(String seed) {
		Contract.checkCondition(seed != null && !seed.isEmpty());

		return hexToChar(sha256(seed));
	}

	/**
	 * Supprime les espaces de la chaîne de caractères s
	 */
	private String removeSpaces(String s) {
		assert s != null;
		assert !s.isEmpty();

		return s.replaceAll("\s", "");
	}

	/**
	 * Supprime les accents de la chaîne de caractères s
	 */
	private String removeAccents(String s) {
		assert s != null;
		assert !s.isEmpty();

		// décompose les caractères accentués (ex: é -> e + ')
		String stringNormalized = Normalizer.normalize(s, Normalizer.Form.NFD);

		// Supprime les caractères accentués (\p{M})
		return stringNormalized.replaceAll("\\p{M}", "");
	}

	/**
	 * Supprime les accents de la chaîne de caractères s
	 */
	private String removePunct(String s) {
		assert s != null;
		assert !s.isEmpty();

		// Supprime la ponctuation (\p{Punct})
		return s.replaceAll("\\p{Punct}", "");
	}

	public void encoder_message(int op) {
		assert op == CHIFFREMENT || op == DECHIFFREMENT;
		assert !clearMessage.isEmpty() ; // des asserts ????? methode publique 
		
		StringBuffer tmp_m;
		if (op == CHIFFREMENT) {
			// On supprime les accents du clearMessage
			String s = clearMessage;
			s = removeAccents(s);
			if (level > 1) {
				s = removeSpaces(s);
				if (level == 3) {
					s = removePunct(s);
				}
			}
			// On crée une copie du clearMessage
			tmp_m = new StringBuffer(s);

			// On chiffre les caractères
			for (int i = 0; i < s.length(); i++) {
				int ind = String.valueOf(alphabet.alphabet()).indexOf(tmp_m.charAt(i));
				if (ind != -1) {
					tmp_m.setCharAt(i, key.charAtIndex(ind));
				}
			}

			cypheredMessage = tmp_m.toString();
			hasAlreadyBeenCyphered = true;
		} else {
			tmp_m = new StringBuffer(cypheredMessage);
			for (int i = 0; i < cypheredMessage.length(); i++) {
				int ind = String.valueOf(key.alphabet()).indexOf(tmp_m.charAt(i));
				if (ind != -1) {
					tmp_m.setCharAt(i, alphabet.charAtIndex(ind));
				}
			}

			clearMessage = tmp_m.toString();
		}
	}


	/**
	 * Encode la chaîne de caractères data avec l'algorithme SHA256
	 */
	public String sha256(String data) {
		assert data != null;
		assert !data.isEmpty();

		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes("UTF-8"));
			return bytesToHex(hash);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Transforme le hash (en hexadécimal) en tableau de caractère
	 */
	private char[] hexToChar(String hash) {
		assert hash != null;
		assert !hash.isEmpty();

		StringBuffer tmp_alphabet = new StringBuffer(String.valueOf(alphabet.alphabet()));
		StringBuffer tab_permutation = new StringBuffer();
		String tmp_hash = hash;

		for (int i = alphabet.size(); i != 0; --i) {
			if (tmp_hash.length() == NB_BYTES_INT) {
				tmp_hash = sha256(tmp_hash);
			}
			int index = Integer.parseInt(tmp_hash.substring(0, NB_BYTES_INT), HEX_ARRAY.length) % i;
			char c = tmp_alphabet.charAt(index);
			tab_permutation.append(c);
			tmp_alphabet.deleteCharAt(index);
			tmp_hash = tmp_hash.substring(NB_BYTES_INT);
		}
		return tab_permutation.toString().toCharArray();
	}

	/**
	 * Convertit le hash(en bytes) en hexadécimal.
	 */
	private String bytesToHex(byte[] hash) {
		assert hash != null;
		assert !hash.toString().isEmpty();

		char[] hexChars = new char[hash.length * 2];
		for (int j = 0; j < hash.length; j++) {
			int v = hash[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	public boolean containsNonSupportedAlphabet(String line) {
		for (int i = 0; i < line.length(); ++i) {
			if (String.valueOf(alphabet.alphabet()).indexOf(line.charAt(i)) == -1) {
				return true;
			}
		}
		return false;
	}
	
	
	// MAIN

	/*public static void main(String[] args) {
		/*
		 * Scanner sn = new Scanner(System.in);
		 * System.out.print("Rentrez le message à traiter\n"); String m = sn.nextLine();
		 * 
		 * System.out.print("Rentrez la graine pour le cypher\n"); String g =
		 * sn.nextLine();
		 * 
		 * System.out.
		 * print("Indiquez l'opération voulue \n 1: CHIFFREMENT \n 0: DECHIFFREMENT\n");
		 * int op = Integer.valueOf(sn.nextLine());
		 * 
		 * sn.close();
		 * 
		 * Cypher cy = new Cypher(g); cy.setMessage(m); cy.encoder_message(op);
		 * System.out.println("Voici le résultat: " + cy.getCypheredMessage());
		 * 
		 */
		/*
		Cipher cy = new Cipher();
		Alphabet a2 = new Alphabet(true, true, true);
		cy.setSeed("toto");
		cy.setAlphabet(a2);

		// LEVEL 1
		System.out.println("***** Level 1: ***** [ACCENTS]");
		cy.setClearMessage(
				"L'envoi de fleurs est charmant. Quel plaisir de recevoir un joli bouquet ? Mais c'est encore mieux quand il est accompagner d'une carte avec un mot adorable ou touchant.\n"
						+ "Pour choisir le bon message, nous vous proposons des centaines d'exemples répartis en plusieurs catégories. A vous de choisir en fonction du motif de l'envoi : anniversaire, naissance, amour,...\n"
						+ "Plus le message sera personnel, plus la personne sera touchée. Alors n'hésitez pas à modifier nos messages avec votre surnom, un souvenir partagé, une citation que vous aimez...");
		cy.setLevel(1);
		cy.encoder_message(CHIFFREMENT);
		System.out.println(cy.getCypheredMessage());
		System.out.print("\n");
		cy.encoder_message(DECHIFFREMENT);
		System.out.println(cy.getClearMessage());

		System.out.print("\n");
		System.out.print("\n");

		// LEVEL 2
		System.out.println("***** Level 2: ***** [ACCENTS + ESPACES]");
		cy.setClearMessage(
				"L'envoi de fleurs est charmant. Quel plaisir de recevoir un joli bouquet ? Mais c'est encore mieux quand il est accompagner d'une carte avec un mot adorable ou touchant.\n"
						+ "Pour choisir le bon message, nous vous proposons des centaines d'exemples répartis en plusieurs catégories. A vous de choisir en fonction du motif de l'envoi : anniversaire, naissance, amour,...\n"
						+ "Plus le message sera personnel, plus la personne sera touchée. Alors n'hésitez pas à modifier nos messages avec votre surnom, un souvenir partagé, une citation que vous aimez...");
		cy.setLevel(2);
		cy.encoder_message(CHIFFREMENT);
		System.out.println(cy.getCypheredMessage());
		System.out.print("\n");
		cy.encoder_message(DECHIFFREMENT);
		System.out.println(cy.getClearMessage());

		System.out.print("\n");
		System.out.print("\n");

		// LEVEL 3
		System.out.println("***** Level 3: ***** [ACCENTS + ESPACES + PONCTUATION]");
		cy.setClearMessage(
				"L'envoi de fleurs est charmant. Quel plaisir de recevoir un joli bouquet ? Mais c'est encore mieux quand il est accompagner d'une carte avec un mot adorable ou touchant.\n"
						+ "Pour choisir le bon message, nous vous proposons des centaines d'exemples répartis en plusieurs catégories. A vous de choisir en fonction du motif de l'envoi : anniversaire, naissance, amour,...\n"
						+ "Plus le message sera personnel, plus la personne sera touchée. Alors n'hésitez pas à modifier nos messages avec votre surnom, un souvenir partagé, une citation que vous aimez...");
		cy.setLevel(3);
		cy.encoder_message(CHIFFREMENT);
		System.out.println(cy.getCypheredMessage());
		System.out.print("\n");
		cy.encoder_message(DECHIFFREMENT);
		System.out.println(cy.getClearMessage());

	}
*/
}