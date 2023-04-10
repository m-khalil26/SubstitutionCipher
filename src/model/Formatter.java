package model;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import util.Contract;

/**
 * Classe permettant le formattage d'un texte.
 * Classe principalement utilisée pour définir la manière dont
 * un resultat (Message clair, graine, clé, message chiffré)
 * s'affiche sur un JTextPane, son style, sa police d'écriture, 
 * et sa justification par rapport à la taille de la fenêtre
 */

public class Formatter {
	
	//Attributs
	private String seed;
	private char[] key;
	private String result;
	private int indent;
	private String clear;
	private boolean fromKey;
	
	//Constructeur
	public Formatter() {
		seed =null;
		key = null;
		result = null;
		indent = 0;
	}
	
	/**
	 * Définis la le champ graine du texte à renvoyer.
	 */
	public void setSeed(String seed) {
		this.seed = seed;
	}
	
	/**
	 * Définis la le champ graine du texte à renvoyer.
	 */
	public void setKey(char[] key) {
		this.key = key;
	}
	
	/**
	 * Définis la le champ résultat du texte à renvoyer.
	 */
	public void setResult(String result) {
		this.result=result;
	}
	
	/**
	 * Permet de définir la taille actuelle de la fenêtre.
	 */
	public void setScreenSize(int n) {
		indent =n;
	}
	
	/**
	 * Définis la le champ message clair du texte à renvoyer.
	 */
	public void setClearMessage(String clear) {
		this.clear = clear;
 	}
	
	/**
	 * Permet de préciser si le chiffrement s'est fait 
	 * à partir d'une cléou d'un tableau de permutations
	 */
	public void setFromKey(boolean b) {
		fromKey = b;
	}
	
	/**
	 * Permet de formatter le texte après avoir initialiser tout les champs
	 * et de renvoyer un document stylisé pour un affichage clair et lisible,
	 * qui pourr être intégrer dans un JTextPane.
	 * @pre
	 * 		seed != null;
	 * 		key != null;
	 *		result!= null;
	 *		clear!= null
	 *		indent >=1 ;
	 * 
	 */
	
	public StyledDocument getFormated() {
		Contract.checkCondition(seed != null);
		Contract.checkCondition(key != null);
		Contract.checkCondition(result!= null);
		Contract.checkCondition(clear!= null);
		Contract.checkCondition(indent >=1 );
		
		StyledDocument doc = new DefaultStyledDocument();

        SimpleAttributeSet blue = new SimpleAttributeSet();
        StyleConstants.setForeground(blue, new Color(54,81,207));
        StyleConstants.setFontFamily(blue, "Arial");
        StyleConstants.setFontSize(blue, 15);

        SimpleAttributeSet orange = new SimpleAttributeSet();
        StyleConstants.setForeground(orange, new Color(254, 176, 77));

        
        SimpleAttributeSet green = new SimpleAttributeSet();
        StyleConstants.setForeground(green, new Color(0, 100, 0));
        
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);
        

        try {
        	doc.insertString(doc.getLength(),buildSeparator(), green);
        	
            doc.insertString(doc.getLength(), addIndent("\nMessage: "), blue);
            doc.insertString(doc.getLength(), addIndent(clear + "\n\n"), orange);
            
            doc.insertString(doc.getLength(),buildSeparator(), green);
            
            if (fromKey) {
                doc.insertString(doc.getLength(),addIndent("\nClé utilisée: " ), blue);
                doc.insertString(doc.getLength(), addIndent(seed + "\n\n"), orange);
            }
            
            doc.insertString(doc.getLength(),buildSeparator(), green);

            doc.insertString(doc.getLength(), addIndent("\nTableau de permutations utilisé:\n "), blue);
            doc.insertString(doc.getLength(), addIndent(String.valueOf(key) + "\n\n"), orange);
            
            doc.insertString(doc.getLength(),buildSeparator(), green);

            doc.insertString(doc.getLength(), addIndent("\nRésultat: "), blue);
            doc.insertString(doc.getLength(), addIndent(result + "\n\n"),red);
            
            doc.insertString(doc.getLength(),buildSeparator(), green);

        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        
        return doc;
	}
	
	/**
	 * Permet de retourner un texte àdapté à la taille de la fenêtre
	 * @param input
	 */
	
	private String addIndent(String input) {
			
	    if (input == null || input.isEmpty() || indent < 1) {
	        return input;
	    }

	    StringBuilder sb = new StringBuilder(input);
	    int count = 0;

	    for (int i = 0; i < sb.length(); i++) {
	        if (sb.charAt(i) != '\n') {
	            count++;
	        }
	        if (sb.charAt(i) == '\n') {
	            count=0;
	        }

	        if (count == indent) {
	            sb.insert(i + 1, '\n');
	            count = 0;
	        }
	    }

	    return sb.toString();
	}
	
	/**
	 * Permet la construction d'une chaine caractères 
	 * representant un separateur entre les différents champs
	 * du résultat
	 * @return
	 */
	private String buildSeparator() {
		StringBuilder sb = new StringBuilder("");
		for(int i=0; i <indent*3 ; i++) {
			if(i==indent || i==indent*2) {
				sb.append('\n');
			}
			sb.append('-');
		}
		sb.append('\n');
		return sb.toString();
	}


}
	
	
	

