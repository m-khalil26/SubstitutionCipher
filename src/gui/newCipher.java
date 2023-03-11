package gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.AttackInterface.CustomEvent;

public class newCipher {
	
	private JFrame mainFrame;
	private JPanel root;
	private JPanel sideBar;
	private JPanel cipherInterface;
	private JPanel uncipherInterface; 
	private JPanel introductionInterface;
	private JPanel faqInterface;
	private JPanel attackInterface;
	
	private newCipher() {
		createView();
		placeComponents();
		createController();
		
		
	}

	/**
	 * Affiche la fenêtre principale.
	 */
	public void display() {
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	private void createView() {
		
		mainFrame = new JFrame("Application de cryptographie");
		mainFrame.setPreferredSize(new Dimension(750, 650));
		root = new JPanel();
		
		
	}
	
	
	private void placeComponents() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root.setLayout(new CardLayout());
		
		createSideBar();
		createIntroductionInterface();
		createCipherInterface();
		createUncipherInterface();
		createFAQInterface();
		createAttackInterface();
		
		root.add(introductionInterface, SideBar.INTRODUCTION_INTERFACE);
		root.add(cipherInterface, SideBar.CIPHER_INTERFACE);
		root.add(uncipherInterface, SideBar.UNCIPHER_INTERFACE);
		root.add(faqInterface,SideBar.FAQ_INTERFACE);
		root.add(attackInterface,SideBar.ATTACK_INTERFACE);
		
		mainFrame.getContentPane().add(root);
		mainFrame.getContentPane().add(sideBar,BorderLayout.WEST);
		
	}
	
	private void createCipherInterface() {
		CipherInterface CInterface = new CipherInterface();
		cipherInterface = CInterface.getInterface();
	}
	
	private void createUncipherInterface() {
		UncipherInterface unInterface = new UncipherInterface();
		uncipherInterface =unInterface.getInterface();
		
	}
	
	private void createSideBar() {
		SideBar sideBar = new SideBar(root);
		this.sideBar =sideBar.getInterface();
	}
	
	private void createIntroductionInterface() {
		introductionInterface = new JPanel();
	}
	
	private void createFAQInterface() {
		FAQInterface faq = new FAQInterface();
		faqInterface =faq.getInterface();
	}
	private void createAttackInterface() {
		AttackInterface atk = new AttackInterface();
		attackInterface =atk.getInterface();
	}
	
	
	private void createController() {
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue() {
	         protected void dispatchEvent(AWTEvent event) {
	             if (event instanceof CustomEvent) {
	                 // Traiter l'événement personnalisé ici
	                 mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	             }
	             super.dispatchEvent(event);
	         }
	     });
		
	}
	 

	
	

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new newCipher().display();
			}
		});
	}
	
}
