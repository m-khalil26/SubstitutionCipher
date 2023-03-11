package gui;

import java.awt.Color;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import model.Cipher;

public abstract class AbstractInterface {
	
	protected JPanel workInterface;
	
	
	protected final Color mainColor = new Color(29,25,28) ;
	protected final Color secondaryColor = new Color(30,40,44);
	protected final Color textColor = new Color(154,153,150);
	protected final Color effectColor = new Color(54,81,207);
	
	protected Formatter fmt;
	protected Cipher model;
	
	public AbstractInterface(){
		fmt = new Formatter();
		createModel();
		createView();
		createInterface();
		createController();
	}
	
	
	
	
	
	
	
	public abstract void createInterface();
	protected abstract void createView();
	abstract void createController();
	abstract void createModel();
	
	public JPanel getInterface() {
		return workInterface;
	}
	
	protected static void displayError(String message) {
	    JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	
	protected File selectLoadFile() {
		JFileChooser x = new JFileChooser();
		if (x.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File f = x.getSelectedFile();
			if(!f.exists() || !f.isFile()) {
				displayError("Cet élément ne correspond pas à un fichier existant");
				return null;
			}
				return f;
		}
		return null;
	}
	
	
	
	
	protected class AfficheurEnTempsReel extends SwingWorker<Void, Character> {
	    
	    private String texte;
	    private JTextArea container;
	    
	    public AfficheurEnTempsReel(String texte, JTextArea container) {
	        this.texte = texte;
	        this.container = container;
	    }
	    
	    @Override
	    protected Void doInBackground() throws Exception {
	        for (int i = 0; i < texte.length(); i++) {
	            char c = texte.charAt(i);
	            publish(c); // publier le caractère pour le traiter sur le thread de l'EDT
	            Thread.sleep(50); // attendre 50 millisecondes avant d'afficher le caractère suivant
	        }
	        return null;
	    }
	    
	    @Override
	    protected void process(List<Character> chunks) {
	        // afficher les caractères publiés sur le thread de l'EDT
	        for (Character c : chunks) {
	            container.append(c+"");
	        }
	    }
	}
	

	
	
	
	
	static boolean showOptionPane(String message) {
	    int choix = JOptionPane.showOptionDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    return choix == JOptionPane.YES_OPTION;
	}
	
}
