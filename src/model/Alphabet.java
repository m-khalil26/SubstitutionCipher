package model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import util.Contract;

public class Alphabet {
	
//-----ATTRIBUTS STATIQUES--------------------------------------------------------
	static final String MINUSCULES = "abcdefghijklmnopqrstuvwxyz";
	static final String MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String CHIFFRES = "0123456789";
	
//-----ATTRIBUTS------------------------------------------------------------------	
	private char[] alphabet;
	private ChangeEvent event;
	private EventListenerList listenerList;
	
	
//----CONSTRUCTEURS---------------------------------------------------------------	
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
	}
	
	public Alphabet(char[] alphabet) {
		Contract.checkCondition( alphabet != null, "Null pointer");
		this.alphabet = alphabet.clone();
	}
	
//--------REQUETES----------------------------------------------------------------	
	
	public int size(){
		return alphabet.length;
	}
	
	public char charAtIndex(int i) {
		Contract.checkCondition(i >= 0 && i < size());
		return alphabet[i];
	}
	
	public char[] alphabet() {
		return alphabet.clone();
	}
	
	public ChangeListener[] getChangeListeners() {
		return listenerList.getListeners(ChangeListener.class);
	}
	
//-------COMMANDES-----------------------------------------------------------------
	
	public void setAlphabet(char[] newAlpha) {
		Contract.checkCondition( alphabet != null, "Null pointer");
		this.alphabet = newAlpha.clone();
		fireStateChanged();
	}
	
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
	
	public void addChangeListener(ChangeListener lst) {
		Contract.checkCondition(lst!=null);
		listenerList.add(ChangeListener.class, lst);
	}
	
	public void removeChangeListener(ChangeListener lst) {
		Contract.checkCondition(lst!=null);
		listenerList.remove(ChangeListener.class, lst);
	}
	
//--------OUTILS------------------------------------------------------------------

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