package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FAQInterface extends AbstractInterface {
	
	private static final String FAQ_PATH = "src/ressources/html/faq.html";
	private JEditorPane editorPane;
	private JScrollPane scrollPane; 
	
	

	@Override
	public void createInterface() {
		
        workInterface.add(scrollPane, BorderLayout.CENTER);
        workInterface.setBackground(secondaryColor);
		
	}

	@Override
	protected void createView() {
		workInterface= new JPanel();
		
		editorPane = new JEditorPane();
	    editorPane.setContentType("text/html");
	    editorPane.setBackground(secondaryColor);
	    editorPane.setEditable(false);
	    
	    try {
            URL url = getClass().getClassLoader().getResource(FAQ_PATH);
            editorPane.setPage(url);
        } catch (IOException e) {
            //
        }
	    scrollPane = new JScrollPane(editorPane);
	    scrollPane.setPreferredSize(new Dimension(300,300));
		
	}

	@Override
	void createController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void createModel() {
		// TODO Auto-generated method stub
		
	}

}
