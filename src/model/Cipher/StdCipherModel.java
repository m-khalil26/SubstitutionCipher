package model.Cipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.Normalizer;
import model.Alphabet;
import util.Contract;
/**
 * Classe permettant le chiffrement et déchiffrement d'un message ou du='un fichier par substition
 *
 */
public class StdCipherModel implements ICipher {

	public static final int LEVEL1 = 1;
	public static final int LEVEL2 = 2;
	public static final int LEVEL3 = 3;

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

	public StdCipherModel() {
		alphabet = new Alphabet(true, true, true);
		key = new Alphabet(true, true, true);
		seed = generateRandomSeed();
		key.setAlphabet(computeKeyFromSeed(seed));
	}

	// ***** REQUETES *****
	/**
	 * Renvoie la clé ( Le tableau de permutations)
	 */
	public Alphabet getKey() {
		return key;
	}

	/**
	 * Renvoie l'alphabet utilisé
	 * @return
	 */
	public Alphabet getAlphabet() {
		return new Alphabet(alphabet.alphabet());
	}

	/**
	 * Renvoie la graine utilisée pour la création de la clé
	 */
	public String getSeed() {
		return seed;
	}
	
	/**
	 * Renvoie le message clair
	 */
	public String getClearMessage() {
		return clearMessage;
	}
	
	/**
	 * Renvoie le message chiffré 
	 */
	public String getCypheredMessage() {
		Contract.checkCondition(hasAlreadyBeenCyphered);

		return cypheredMessage;
	}
	
	/**
	 * Renvoie le niveau séléctionné
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Indique si un fichier est séléctionné
	 */
	public boolean isFileSet() {
		return file != null;
	}

	// ***** COMMANDES *****
	/**
	 * Permet de définir l'alphabet
	 * @param a
	 */
	public void setAlphabet(Alphabet a) {
		Contract.checkCondition(a != null);

		alphabet = a;
		key.setAlphabet(computeKeyFromSeed(seed));
	}
	
	/**
	 * Permet de définir une clé
	 * @param a
	 */
	public void setKey(Alphabet a) {
		Contract.checkCondition(a != null);
		key.setAlphabet(a.alphabet());;
	}

	/**
	 * Permet de définir un niveau
	 * 1 : Chiffrement simple
	 * 2 : Ponctuation supprimée
	 * 3 : Ponctuation et espaces supprimés
	 * @param lvl
	 */
	public void setLevel(int lvl) {
		Contract.checkCondition(1 <= lvl && lvl <= 3, "Le level doit être compris entre 1 et 3 compris");

		level = lvl;
	}
	
	/**
	 * Permet de définir une graine
	 * @param seed
	 */
	public void setSeed(String seed) {
		Contract.checkCondition(seed != null && !seed.equals(""));

		this.seed = seed;
		key.setAlphabet(computeKeyFromSeed(seed));
	}
	
	/**
	 * Permet de definir une graine aléatoire
	 */
	public void setRandomSeed() {
		seed = generateRandomSeed();
		key.setAlphabet(computeKeyFromSeed(seed));
	}

	/**
	 * Permet de definir le message clair
	 * @param message
	 */
	public void setClearMessage(String message) {
		Contract.checkCondition(message != null);

		clearMessage = message;
		hasAlreadyBeenCyphered = false;
	}
	
	/**
	 * Permet de définir le message chiffré
	 * @param message
	 */
	public void setCypheredMessage(String message) {
		Contract.checkCondition(message != null);

		cypheredMessage = message;
		hasAlreadyBeenCyphered = true;
	}
	
	/**
	 * Permet de définir un fichier comme source d'opérations
	 * @param f
	 * @param op
	 */
	public void setFile(File f, int op) throws IOException {
		Contract.checkCondition(f != null && f.length() != 0);
		Contract.checkCondition(op == CHIFFREMENT || op == DECHIFFREMENT);

		file = f;
		if (op == DECHIFFREMENT) {
			setCypheredMessage(getFileContent(f));
		} else {
			setClearMessage(getFileContent(f));
		}
	}
	
	/**
	 * Permet de supprimer le fichier
	 */
	public void removeFile() {
		Contract.checkCondition(isFileSet());

		file = null;
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
		Contract.checkCondition(seed != null && !seed.isBlank());

		return hexToChar(sha256(seed));
	}

	/**
	 * Supprime les espaces de la chaîne de caractères s
	 */
	private String removeSpaces(String s) {
		assert s != null;
		assert !s.isEmpty();

		return s.replaceAll("\\s", "");
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

		// \p{Punct} =
		return s.replaceAll("\\p{Punct}", "");
	}
	
	/**
	 * S'occupe de chiffrer ou de déchiffrer le message
	 * op peut être égal à CHIFFREMENT ou DECHIFFREMENT
	 * @param op
	 */
	public void encoder_message(int op) {
		assert op == CHIFFREMENT || op == DECHIFFREMENT;
		assert !clearMessage.isEmpty();

		String f = null;
		StringBuffer tmp_m;
		String check = checkA();
		if (op == CHIFFREMENT) {

			String s = f == null ? clearMessage : f;
			s = removeAccents(s);
			if (level > 1) {
				s = removePunct(s);
				if (level == 3) {
					s = removeSpaces(s);
				}
			}
			tmp_m = new StringBuffer(s);
			for (int i = 0; i < s.length(); i++) {
				char c = tmp_m.charAt(i);
				if (check.compareTo("m") == 0 && Character.isUpperCase(c)) {
					c = Character.toLowerCase(c);
					int ind = String.valueOf(alphabet.alphabet()).indexOf(c);
					if (ind != -1) {
						tmp_m.setCharAt(i, Character.toUpperCase(key.charAtIndex(ind)));
					}
				} else if (check.compareTo("M") == 0 && Character.isLowerCase(c)) {
					c = Character.toUpperCase(c);
					int ind = String.valueOf(alphabet.alphabet()).indexOf(c);
					if (ind != -1) {
						tmp_m.setCharAt(i, Character.toLowerCase(key.charAtIndex(ind)));
					}
				} else {
					int ind = String.valueOf(alphabet.alphabet()).indexOf(c);
					if (ind != -1) {
						tmp_m.setCharAt(i, key.charAtIndex(ind));
					}
				}
			}

			cypheredMessage = tmp_m.toString();
			hasAlreadyBeenCyphered = true;
		} else {
			String c_m = f == null ? cypheredMessage : f;
			tmp_m = new StringBuffer(c_m);
			for (int i = 0; i < tmp_m.length(); i++) {
				char c = tmp_m.charAt(i);
				if (check.compareTo("m") == 0 && Character.isUpperCase(c)) {
					c = Character.toLowerCase(c);
					int ind = String.valueOf(key.alphabet()).indexOf(c);
					if (ind != -1) {
						tmp_m.setCharAt(i, Character.toUpperCase(alphabet.charAtIndex(ind)));
					}
				} else if (check.compareTo("M") == 0 && Character.isLowerCase(c)) {
					c = Character.toUpperCase(c);
					int ind = String.valueOf(key.alphabet()).indexOf(c);
					if (ind != -1) {
						tmp_m.setCharAt(i, Character.toLowerCase(alphabet.charAtIndex(ind)));
					}
				} else {
					int ind = String.valueOf(key.alphabet()).indexOf(c);
					if (ind != -1) {
						tmp_m.setCharAt(i, alphabet.charAtIndex(ind));
					}
				}
			}

			clearMessage = tmp_m.toString();
		}
	}

	/**
	 * Encode la chaîne de caractères data avec l'algorithme SHA256
	 */
	private String sha256(String data) {
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

	/**
	 * Renvoie le contenu du fichier
	 */
	private String getFileContent(File f) throws IOException {
		Contract.checkCondition(isFileSet());

		StringBuilder sb = new StringBuilder();
		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {

			String line;
			sb = new StringBuilder();
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return sb.toString();
	}

	private String checkA() {
		String res = "";
		Alphabet a = getAlphabet();
		if (String.valueOf(a.alphabet()).contains("abcdefghijklmnopqrstuvwxyz")) {
			res += 'm';
		}
		if (String.valueOf(a.alphabet()).contains("ABCDEFGHIJKLMNOPQRSTUVWXYZ")) {
			res += 'M';
		}
		return res.toString();
	}
	
	/**
	 * Permet de substituer les caractères d'une chaîne de caracères
	 * en fonction de son image dans le tableau alpha2, par rapport au tableau alpha1
	 * qui representent des alphabets, sans porter attention à la casse
	 * @param alpha1 - un tableau de caractères représentant l'alphabet de base
	 * @param alpha2 - un tableau de caractères représentant l'alphabet permuté
	 * @param s - une chaine de caractère à modifier selon les permutations entre alpha1 et alpha2
	 * @return Une chaine de caractère résultant de la permutation des caractères dans s
	 */
	public static String substituteWithoutCaseSensitivity(char[] alpha1, char[] alpha2, String s) {
	    // On vérifie que les deux tableaux ont la même taille
	    if (alpha1.length != alpha2.length) {
	       System.out.println("Souci : les tailles des alphabets");
	       return "";
	    }
	    
	    // On convertit la chaîne en tableau de caractères pour pouvoir la modifier
	    char[] result = s.toCharArray();
	    
	    // On parcourt chaque caractère de la chaîne
	    for (int i = 0; i < result.length; i++) {
	        // On parcourt chaque élément des deux tableaux pour chercher une correspondance
	        boolean charFound = false;
	        for (int j = 0; j < alpha1.length; j++) {
	            if (Character.toLowerCase(result[i]) == Character.toLowerCase(alpha1[j])) {
	                // On remplace le caractère correspondant dans la chaîne par celui du 2ème tableau
	                if (Character.isLowerCase(result[i])) {
	                    result[i] = Character.toLowerCase(alpha2[j]);
	                } else {
	                    result[i] = Character.toUpperCase(alpha2[j]);
	                }
	                charFound = true;
	                break; // On sort de la boucle dès qu'on a trouvé une correspondance
	            }
	        }
	        // Si aucune correspondance n'a été trouvée, on garde le caractère tel quel
	        if (!charFound) {
	            result[i] = result[i];
	        }
	    }
	    
	    // On renvoie la chaîne modifiée
	    return new String(result);
	}
	
	/**
	 * Permet de substituer les caractères d'une chaîne de caracères
	 * en fonction de son image dans le tableau alpha2, par rapport au tableau alpha1
	 * qui representent des alphabets.
	 * @param alpha1 - un tableau de caractères représentant l'alphabet de base
	 * @param alpha2 - un tableau de caractères représentant l'alphabet permuté
	 * @param s - une chaine de caractère à modifier selon les permutations entre alpha1 et alpha2
	 * @return Une chaine de caractère résultant de la permutation des caractères dans s
	 */
	public static String substitute(char[] alpha1, char[] alpha2, String s) {
	    // On vérifie que les deux tableaux ont la même taille
	    if (alpha1.length != alpha2.length) {
	       System.out.println("Souci : les tailles des alphabets");
	       return "";
	    }
	    
	    // On convertit la chaîne en tableau de caractères pour pouvoir la modifier
	    char[] result = s.toCharArray();
	    
	    // On parcourt chaque caractère de la chaîne
	    for (int i = 0; i < result.length; i++) {
	        // On parcourt chaque élément des deux tableaux pour chercher une correspondance
	        boolean charFound = false;
	        for (int j = 0; j < alpha1.length; j++) {
	            if (result[i] == alpha1[j]) {
	                // On remplace le caractère correspondant dans la chaîne par celui du 2ème tableau
	                result[i] = alpha2[j];
	                break; // On sort de la boucle dès qu'on a trouvé une correspondance
	            }
	        }
	        // Si aucune correspondance n'a été trouvée, on garde le caractère tel quel
	        if (!charFound) {
	            result[i] = result[i];
	        }
	    }
	    
	    // On renvoie la chaîne modifiée
	    return new String(result);
	}

}
