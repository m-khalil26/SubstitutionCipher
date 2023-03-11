package model;

import java.io.IOException;

public interface ICipher {

	
	// ***** CONSTANTES *****
	
	
	final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz!#$%&()*+-/<=>?@[]^_{|}~ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	final int SEED_SIZE = 9;
	final int NB_BYTES_INT = 4;
	final int CHIFFREMENT = 1;
	final int DECHIFFREMENT = 0;

	
	// ***** REQUETES *****
	
	
	/**
	 *  Renvoie la graîne
	 */
	public String getSeed();
	
	/**
	 *  Renvoie la clé associée à la graîne
	 */
	public Alphabet getKey();
	
	/**
	 *  Renvoie le message clair
	 */
	public String getClearMessage();
	
	/**
	 *  Renvoie le message chiffré
	 *  @pre: 
	 *  	hasAlreadyBeenCiphered()
	 */
	public String getCypheredMessage();
	
	/**
	 *  Renvoie le niveau de chiffrement
	 */
	public int getLevel();
	
	
	// ***** COMMANDES *****
	
	
	/**
	 *  @pre: 
	 *  	1 <= lvl <= 3
	 */
	public void setLevel(int lvl);
	
	/**
	 *  @pre: 
	 *  	seed != null && !seed.equals("")
	 */
	public void setSeed(String seed);
	
	/**
	 *  @pre: 
	 *  	message != null && !message.equals("")
	 */
	public void setClearMessage(String message);
	
	/**
	 *  @pre: 
	 *  	message != null && !message.equals("")
	 */
	public void setCypheredMessage(String message);
	
	/**
	 *  @pre: 
	 *  	f != null
	 */
	public void getClearFromFile ()throws IOException;
	
	/**
	 *  @pre: 
	 *  	f != null
	 */
	public void getCypheredFromFile () throws IOException;
}
