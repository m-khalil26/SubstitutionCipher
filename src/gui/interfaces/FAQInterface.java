package gui.interfaces;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * Classe qui permet la construction de l'interface de FAQ
 *
 */
public class FAQInterface extends AbstractInterface {
	
	private static final String FAQ_PATH = "src/ressources/html/faq.html";
	private JScrollPane scrollPane; 
	private JTextPane textPane;
	private JPanel mainPanel;
	
	

	@Override
	public void createInterface() {
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		loadHtmlFile(FAQ_PATH);
	}

	@Override
	protected void createView() {
		 mainPanel = new JPanel(new BorderLayout());
		 textPane = new JTextPane();
		 textPane.setBackground(secondaryColor);
		 textPane.setForeground(textColor);
		 textPane.setEditable(false);
		 scrollPane = new JScrollPane(textPane); 
		 scrollPane.getVerticalScrollBar().setValue(0);
		
	}

	@Override
	void createController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void createModel() {
		// TODO Auto-generated method stub
		
	}
	
	public JPanel getInterface() {
		return mainPanel;
	}
	
	/**
	 * Méthode qui,permet le chargement d'un fichier HTML
	 * et de le placer sur un conteneur de texte. 
	 */
	private void loadHtmlFile(String filename) {
		StringBuilder htmlBuilder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while ((line = reader.readLine()) != null) {
				htmlBuilder.append(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textPane.setContentType("text/html");
		textPane.setText(htmlBuilder.toString());
	}

}
