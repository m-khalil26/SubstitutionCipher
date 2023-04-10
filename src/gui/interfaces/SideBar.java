package gui.interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Interface permettant de créer le menu latéral et 
 * de gérer la navigation entre les différentes interfaces.
 * 
 * Menu composé de 4 bouttons:
 * 	-Chiffrement
 * 	-Déchiffrement
 *	-Analyse
 *	-FAQ
 */
public class SideBar extends AbstractInterface {
	
	public static final String INTRODUCTION_INTERFACE = "Interface d'accueil";
	public static final String CIPHER_INTERFACE = "Interface de chiffrement";
	public static final String UNCIPHER_INTERFACE = "Interface de déchiffrement";
	public static final String FAQ_INTERFACE = "Interface de renseignements";
	public static final String ATTACK_INTERFACE = "Interface d'analyse et d'attaque";
	
	
	private JButton buttonChooseCiphering;
	private JButton buttonChooseUnciphering;
	private JButton buttonChooseAttack;
	private JButton faqButton;
	private JPanel root;
	
	public SideBar(JPanel p) {
		super();
		root = p;
	}
	
	@Override
	protected void createView() {
		workInterface= new JPanel();
		workInterface.setPreferredSize(new Dimension(70,10));
		
		buttonChooseCiphering = new JButton();
		buttonChooseCiphering.setBounds(10, 51, 50, 50);
		buttonChooseCiphering.setPreferredSize(new Dimension(50, 50));
		buttonChooseCiphering.setBackground(mainColor);
		buttonChooseCiphering.setBorder(null);
		buttonChooseCiphering.setIcon(new ImageIcon("src/ressources/icons/lock30.png"));
		
		
		buttonChooseUnciphering = new JButton();
		buttonChooseUnciphering.setBounds(10, 131, 50, 50);
		buttonChooseUnciphering.setPreferredSize(new Dimension(50, 50));
		buttonChooseUnciphering.setBackground(mainColor);
		buttonChooseUnciphering.setBorder(null);
		buttonChooseUnciphering.setIcon(new ImageIcon("src/ressources/icons/unlock30.png"));
		
		buttonChooseAttack = new JButton();
		buttonChooseAttack.setBounds(10, 218, 50, 50);
		buttonChooseAttack.setPreferredSize(new Dimension(50, 50));
		buttonChooseAttack.setBackground(mainColor);
		buttonChooseAttack.setBorder(null);
		buttonChooseAttack.setIcon(new ImageIcon("src/ressources/icons/bolt30.png"));
		
		faqButton = new JButton();
		faqButton.setPreferredSize(new Dimension(50, 50));
		faqButton.setBackground(mainColor);
		faqButton.setBorder(null);
		faqButton.setIcon(new ImageIcon(("src/ressources/icons/faq30.png")));
		
	}

	@Override
	public void createInterface() {
		workInterface.setLayout(new BorderLayout(0, 0));
		
		JPanel p = new JPanel(); 
		p.setPreferredSize(new Dimension(70, 100));
		{
			p.add(new JLabel(new ImageIcon("src/ressources/icons/logoUrCrop.png") ));
		}
		p.setBackground(mainColor);
		workInterface.add(p,BorderLayout.NORTH);
		
		p = new JPanel();
		{
			p.add(buttonChooseCiphering);
			p.add(buttonChooseUnciphering);
			p.add(buttonChooseAttack);
		}
		p.setBackground(mainColor);
		workInterface.add(p,BorderLayout.CENTER);
		
		p = new JPanel();
		{
			p.add(faqButton);
		}
		p.setBackground(mainColor);
		workInterface.add(p,BorderLayout.SOUTH);
		
		
	}

	

	@Override
	void createController() {
		
		
		buttonChooseCiphering.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				 CardLayout cl = (CardLayout)(root.getLayout());
				 cl.show(root, CIPHER_INTERFACE);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				buttonChooseCiphering.setBackground(effectColor);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buttonChooseCiphering.setBackground(mainColor);
				
			}
			
		});
	
	
		buttonChooseUnciphering.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 CardLayout cl = (CardLayout)(root.getLayout());
				 cl.show(root, UNCIPHER_INTERFACE);
				
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonChooseUnciphering.setBackground(effectColor);
				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				buttonChooseUnciphering.setBackground(mainColor);
				
			}
		});
	
		buttonChooseAttack.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(root.getLayout());
				cl.show(root, ATTACK_INTERFACE);
				
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonChooseAttack.setBackground(effectColor);				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				buttonChooseAttack.setBackground(mainColor);
				
			}
		});
	
		
		faqButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(root.getLayout());
				 cl.show(root, FAQ_INTERFACE);
				
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseEntered(MouseEvent e) {
				faqButton.setBackground(effectColor);				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				faqButton.setBackground(mainColor);
				
			}
		});
		
	}

	@Override
	void createModel() {
		// TODO Auto-generated method stub
		
	}

}
