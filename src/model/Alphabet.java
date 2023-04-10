package model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import util.Contract;

/**
 * Classe définissant un alphabet et des opérations permettant de le manipuler.
 * Les mises à jours de ses instances sont observables via des ChangeListeners
 */
public class Alphabet {
	
//-----ATTRIBUTS STATIQUES--------------------------------------------------------
	private static final String MINUSCULES = "abcdefghijklmnopqrstuvwxyz";
	private static final String MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String CHIFFRES = "0123456789";
	
//-----ATTRIBUTS------------------------------------------------------------------	
	private char[] alphabet;
	private ChangeEvent event;
	private EventListenerList listenerList;
	private char[] baseAlphabet;
	
//----CONSTRUCTEURS---------------------------------------------------------------	
	
	/**
	 * Crée un alphabet contenant au moins un des trois suivants: minuscules, majuscules et chiffres.
	 * @param min - true pour ajouter les minuscules, faux sinon
	 * @param maj - true pour ajouter les majuscules, faux sinon
	 * @param chiffre - true pour ajouter les chiffres, faux sinon
	 * 
	 * @pre min || maj || chiffre
	 */
	public Alphabet(boolean min, boolean maj, boolean chiffre) {
		Contract.checkCondition(min || maj || chiffre, "Aucun alphabet sélectionné");
		
		listenerList = new EventListenerList();
		StringBuffer alpha = new StringBuffer();
		
		if (min) {
			alpha.append(MINUSCULES);
		}
		if (maj) {
			alpha.append(MAJUSCULES);
		}
		if (chiffre) {
			alpha.append(CHIFFRES);
		}
		
		alphabet = alpha.toString().toCharArray();
		baseAlphabet = alpha.toString().toCharArray();
	}
	
	/**
	 * Crée un alphabet à partir d'un tableau de caractères passé en paramètre. 
	 * L'alphabet contiendra alors tous les caractères du tableau passé en paramètre
	 * 
	 * @param alphabet - un tableau de caractères contenant les caractères qui appartiendront à l'alphabet créé
	 * 
	 * @pre alphabet != null
	 */
	public Alphabet(char[] alphabet) {
		Contract.checkCondition( alphabet != null, "Null pointer");
		this.alphabet = alphabet.clone();
		baseAlphabet = alphabet.toString().toCharArray();
	}
	
//--------REQUETES----------------------------------------------------------------	
	/**
	 * @return La taille de l'alphabet
	 */
	public int size(){
		return alphabet.length;
	}
	
	/**
	 * @param i - L'indice du caractère voulu
	 * @pre i >= 0 && i < size()
	 * @return le caractère à la position i+1 dans l'alphabet
	 */
	public char charAtIndex(int i) {
		Contract.checkCondition(i >= 0 && i < size());
		return alphabet[i];
	}
	
	/**
	 * Renvoie l'indice de c dans l'alphabet
	 * @param c - le caractère recherché
	 * @return -1 si le caractère n'est pas contenu dans l'alphabet, l'indice du caractère dans l'alphabet sinon
	 */
	public int getIndexOf(char c) {
		int i = 0;
		for (char x: alphabet) {
			if (x == c) {
				return i;
			}
			i += 1;
		}
		return -1;
	}
	
	/**
	 * @return L'alphabet sous forme d'un tableau de caractères
	 */
	public char[] alphabet() {
		return alphabet.clone();
	}
	
	/**
	 * @return La liste des écouteurs associés à l'alphabet
	 */
	public ChangeListener[] getChangeListeners() {
		return listenerList.getListeners(ChangeListener.class);
	}
	
	/**
	 * @return L'alphabet de base associé à cet alphabet, c'est à dire dans l'ordre de base
	 */
	public char[] getBaseAlphabet() {
		return baseAlphabet.clone();
	}
	
	/**
	 * Renvoie l'indice d'un caractère dans l'alphabet de base
	 * @param c - le caractère voulu
	 * @return L'indice de c dans l'alphabet de base s'il est trouvé, -1 sinon
	 */
	public int indexOfinBaseAlphabet(char c) {
		int i=0;
		for(char a : baseAlphabet) {
			if(a==c) {
				return i;
			}
			i+=1;
		}
		return -1; // jamais
	}
	
//-------COMMANDES-----------------------------------------------------------------
	
	/**
	 * Modifie l'alphabet de sorte à ce qu'il soit conforme à l'alphabet décrit par le tableau passé en paramètre
	 * @param newAlpha - un tableau de caractères qui représente un alphabet
	 */
	public void setAlphabet(char[] newAlpha) {
		Contract.checkCondition( alphabet != null, "Null pointer");
		this.alphabet = newAlpha.clone();
		fireStateChanged();
	}
	
	/**
	 * Echange les positions des caractères à la position i+1 et j+1 dans l'alphabet
	 * @param i - un entier décrivant la position du premier caractere
	 * @param j - un entier décrivant la position du deuxieme caractere
	 * 
	 * @pre 
	 * 		i<alphabet.length && j<alphabet.length
	 * 		i>=0 && j>=0
	 */
	public void exchangePositions(int i, int j) {
		Contract.checkCondition(i<alphabet.length && j<alphabet.length);
		Contract.checkCondition(i>=0 && j>=0);
		
		if (i == j) {
			return;
		}
		
		char tmp = alphabet[i];
		alphabet[i] = alphabet[j];
		alphabet[j] = tmp;
		fireStateChanged();
	}
	
	/**
	 * Met à jour l'alphabet selon le fait que les minuscules, majuscules et chiffres doivent appartenir à l'alphabet modifié.
	 * Remet l'alphabet dans son ordre naturel
	 * 
	 * @param min - true pour ajouter les minuscules, faux sinon
	 * @param maj - true pour ajouter les majuscules, faux sinon
	 * @param chiffre - true pour ajouter les chiffres, faux sinon
	 * 
	 * @pre min || maj || chiffre
	 */
	public void refreshAlphabet(boolean min, boolean maj, boolean chiffre) {
		Contract.checkCondition(min || maj || chiffre, "Aucun alphabet sélectionné");
		
		alphabet = new Alphabet(min,maj,chiffre).alphabet();
		baseAlphabet = new Alphabet(min,maj,chiffre).alphabet();
		fireStateChanged();
	}
	
	/**
	 * Ajoute un écouteur de changement d'état interne à l'alphabet
	 * @param lst - un écouteur de changement d'état interne
	 * @pre lst!=null
	 */
	public void addChangeListener(ChangeListener lst) {
		Contract.checkCondition(lst!=null);
		listenerList.add(ChangeListener.class, lst);
	}
	
	/**
	 * Retire un écouteur de changement d'état interne à l'alphabet
	 * @param lst - un écouteur de changement d'état interne
	 * @pre lst!=null
	 */
	public void removeChangeListener(ChangeListener lst) {
		Contract.checkCondition(lst!=null);
		listenerList.remove(ChangeListener.class, lst);
	}
	
//--------OUTILS------------------------------------------------------------------
	
	// Notifie les changements d'état interne
	private void fireStateChanged() {
    	Object[] listeners = listenerList.getListenerList();
    	for (int i = listeners.length-2; i >= 0; i-=2) {
    		if (listeners[i] == ChangeListener.class) {
    			if (event == null) {
    				event = new ChangeEvent(this);
    			}
    			((ChangeListener) listeners[i+1]).stateChanged(event);
    		}
    	}
    }
}