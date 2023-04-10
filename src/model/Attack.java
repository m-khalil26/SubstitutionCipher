package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import util.Contract;

/**
 * Classe permettant de réaliser une analyse de fréquences sur un message 
 * ou un fichier.
 * @author bpkhalil
 *
 */
public class Attack {

	// FICHIERS POUR RÉFÉRENCES
	
	private static final String testFileForEnglish = "src/ressources/languageFiles/english.txt";
	private static final String testFileForSpanish= "src/ressources/languageFiles/espagnol.txt";
	private static final String testFileForFrench = "src/ressources/languageFiles/french.txt";
	
	// LANGAGES DETECTÉS
	
	public static final String ENGLISH = "english";
	public static final String SPANISH = "spanish";
	public static final String FRENCH = "french";
	public static final String OTHER = "other";
	public static final String NONE = "";
	
	public static final String MAJUSCULES="majuscules";
	public static final String MINUSCULES="minuscules";
	public static final String MIX="mix";

	// ATTRIBUTS 
	
	private Map<Character, Double> frequences;
	private Map<Character, Double> refFreq;
	private String langage;
	private File clientFile;
	private File languageFile; 
	private String message;
	private Alphabet alphabet;
	private String mode;
	
	public Attack(Alphabet a) {
		alphabet = a;
		setLangage(ENGLISH);
	}
	
	//REQUETES

	/**
	 * Vérifie si un fichier est défini.
	 * @return
	 */
	public boolean isFileSet() {
		return clientFile != null;
	}
	
	
	/**
	 * Renvoie les données de l'analyse sur le fichier ou
	 * le message du client.
	 * Renvoie une Collection de Double
	 * @return
	 */
	public Collection<Double> getData() {
		Contract.checkCondition(!frequences.isEmpty());
		return frequences.values();
	}
	
	/**
	 * Renvoie les lettres de l'analyse
	 * @return
	 */
	public Set<Character> getNames() {
		Contract.checkCondition(!frequences.isEmpty());
		return frequences.keySet();
	}
	
	/**
	 * Renvoie les données de l'analyse sur
	 * le fichier de référence de la langue séléctionnée.
	 * Renvoie une Collection de Double
	 * @return
	 */
	public Collection<Double> getRefData() {
		return refFreq.values();
	}
	
	/**
	 * Renvoie les lettres de l'analyse du fichier séléctionné
	 * @return
	 */
	public Set<Character> getRefNames(){
		return refFreq.keySet();
		
	}
	
	/**
	 * Renvoie le message donné en entrée
	 * @return
	 */
	public String getMessage() {
		return new String(message);
	}
	
	// ***** COMMANDES *****
	
	/**
	 * Permet de definir un langage, et de mettre à jour les fichiers 
	 * de référence par conséquent.
	 * @param l
	 */
	public void setLangage (String l) {
		langage = l;
		if (langage == ENGLISH) {
			setFileForRef(testFileForEnglish);
		
		} 
		if (langage == FRENCH) {
			setFileForRef(testFileForFrench);
		
		} 
		if (langage == SPANISH) {
			setFileForRef(testFileForSpanish);
		}
		setRefFrequencies();
		
	}
	/**
	 * Permet de définir l'alphabet.
	 * @param a
	 */
	public void setAlphabet(Alphabet a) {
		this.alphabet=a;
		setRefFrequencies();
		setMessageData();
		
	}
	
	/**
	 * Permet de définir un mode d'analyse
	 *  -- minuscules, majuscules, ou mixe.
	 *  
	 * @param min
	 * @param maj
	 * @param chiffres
	 */
	public void setMode(boolean min,boolean maj, boolean chiffres) {
		if(maj && !min) {
			this.mode = MAJUSCULES;
			return;
		}
		if(min && !maj) {
			this.mode = MINUSCULES;
			return;
		}
		mode=MIX;
	}
	
	/**
	 * Permet de calculer les fréquences de référence.
	 */
	public void setRefFrequencies() {
		String s="";
		try {
			s = getFileContent(languageFile);
		} catch (IOException e1) {
		}
		
		refFreq = FrequenceLettres.calculerFrequence(s,alphabet,mode);
	}
	
	/**
	 * Permet de définir le message du client
	 * @param msg
	 */
	public void setMessage(String msg) {
		message = msg;
	}
	
	/**
	 * Permet calculer les fréquences du message du client
	 */
	public void setMessageData() {
		Contract.checkCondition(message != null);
		frequences = FrequenceLettres.calculerFrequence(message,alphabet,mode);
	}
	
	/**
	 * Permet de définir le fichier du client
	 * @param f
	 */
	public void setClientFile(File f) {
		clientFile=f;
		try {
			message = getFileContent(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//
		}
	}
	
	
	// ***** OUTILS *****
	
	/**
	 * Permet la lecture d'un fichier, et le transfert de toute les données
	 * dans une chaine de caractères.
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static String getFileContent(File f) throws IOException {
		
		FileReader fr = new FileReader(f);
		StringBuilder sb;
		String line;
		try (BufferedReader br = new BufferedReader(fr)) {
			sb = new StringBuilder();
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			br.close();
			fr.close();	
		}
		
		return sb.toString();
	}
	
	/**
	 * Permet de définir un fichier de référence à partir 
	 * d'un chemin
	 * @param path
	 */
	private void setFileForRef(String path) {
		languageFile = new File(path);
	}
	
	/**
	 * Permet de définir un fichier de référence à partir
	 * d'un File.
	 * @param f
	 */
	public void setFileForRef(File f) {
		languageFile = f;
	}
	
	// **** MAIN *****
	
}
