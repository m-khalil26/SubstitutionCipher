package gui.interfaces;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Alphabet;
import model.Formatter;
import model.Cipher.StdCipherModel;

/**
 *  Une classe abstraite de qui permet de definir le comportement par 	
 *  défaut d’une interface de notre application.	
 *  
 *  Elle définit les variables et composants et communs à toutes les 
 *  interfaces et dont nous aurons besoin pour le bon fonctionement de
 *  notre application.
 */


public abstract class AbstractInterface {
	
	protected JPanel workInterface;
	
	
	/* 
	 * ATTRIBUTS
	 */
	
	protected final Color mainColor = new Color(29,25,28) ;
	protected final Color secondaryColor = new Color(30,40,44);
	protected final Color textColor = new Color(154,153,150);
	protected final Color textColor2 = new Color(254, 176, 77);
	protected final Color effectColor = new Color(54,81,207);
	protected final int FONT_SIZE = 16;
	
	protected Formatter fmt;
	protected StdCipherModel model;
	Alphabet alphabetModel;
	Alphabet alphabetPiano;
	
	/*
	 * CONSTRUCTEUR.
	 * 
	 */
	
	public AbstractInterface(){
		fmt = new Formatter();
		alphabetModel = new Alphabet(true,true,true);
		alphabetPiano = new Alphabet(true,true,true);
		createModel();
		createView();
		createInterface();
		createController();
	}
	
	//---------------------------Méthodes abstraites-----------------------
	
	/**
	 * Instancie tout les composants nécéssaires
	 * au bon fonctionnement de l'application
	 */
	protected abstract void createView();
	
	/**
	 * Place tout les composants sur la frame
	 */
	public abstract void createInterface();
	
	/**
	 * Crée le controlleur de cette application 
	 */
	abstract void createController();
	
	/**
	 * Crée le modèle de cette application 
	 */
	abstract void createModel();
	
	
	//---------------------------Méthodes concrètes-------------------------
	
	
	/**
	 * Commande qui retourne l'interface.
	 */
	
	public JPanel getInterface() {
		return workInterface;
	}
	
	/**
	 * Affiche une boîte dialogue mentionnant une erreur
	 * @param message
	 */
	protected static void displayError(String message) {
	    JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Affiche une fenêtre de choix de fichier, et renvoie le fichier séléctionné
	 * Renvoie null si aucun fichier n'est séléctionné ou si le fichier ne contient pas que du texte.
	 */
	protected static File selectLoadFile() {
		String projectPath = System.getProperty("user.dir");
		JFileChooser x = new JFileChooser(projectPath);
		if (x.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File f = x.getSelectedFile();
			if(!f.exists() || !f.isFile()) {
				displayError("Cet élément ne correspond pas à un fichier existant");
				return null;
			}
			if(!isTextFile(f)) {
				displayError("Veuillez choisir un fichier : .txt, .c, .java, .py,.md ou .ml");
				return null;
			}
				return f;
		}
		return null;
	}
	
	
	/**
	 * Affiche une fenêtre de choix à l'utilisateur, avec OUI et NON.
	 * Le texte affiché est le texte donné en entrée
	 * @param message
	 */
	protected boolean showOptionPane(String message) {
	    int choix = JOptionPane.showOptionDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    return choix == JOptionPane.YES_OPTION;
	}
	
	/**
	 * Affiche une fenêtre d'information.
	 * @param message
	 */
	protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
	
	/**
	 * Vérifie si un fichier est pris en charge par l'application
	 * Les formats pris en charge sont les suivants:
	 * 			-.txt
	 * 			-.c
	 * 			-.java
	 * 			-.py
	 * 			-.md
	 * 			-.ml 
	 * @param file
	 * @return
	 */
	public static boolean isTextFile(File file) {
		if (file.isFile()) {
			String name = file.getName();
			int lastDotIndex = name.lastIndexOf('.');
			if (lastDotIndex != -1) {
				String extension = name.substring(lastDotIndex);
				return extension.equals(".txt")||extension.equals(".c")||extension.equals(".java")||extension.equals(".py")||extension.equals(".md")||extension.equals(".ml");
			}
		}
		return false;
	}
	

}
