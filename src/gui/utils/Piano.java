package gui.utils;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Alphabet;
import util.Contract;

/**
 * Classe qui permet de créer le "clavier" virtuel qui permute les lettres dans un alphabet
 * lors des manipulations par les utilisateurs, ainsi que des "graduations" qui composent en fait
 * un clavier non cliquable en une ligne qui vient se mettre au dessus du clavier cliquable, cela étant
 * disponible uniquement dans l'interface d'attaque.
 */
public class Piano {
	
	private static final int ATTACK = 1;
	private static final int DEFAULT = 0;

	protected final Color mainColor = new Color(29,25,28) ;
	protected final Color secondaryColor = new Color(30,40,44);
	protected final Color textColor = new Color(154,153,150);
	protected final Color effectColor = new Color(54,81,207);
	
	
	private JPanel panel;
	private JPanel graduations;
	private JPanel[] subpanels;
	private final Alphabet model;
	private final Alphabet lastAlphabet;
	private final Alphabet gradAlphabet;
	private int workMode;
	private boolean replaceGrad;
	
	/**
	 * Construit un créateur de clavier à partir d'un alphabet a et d'une valeur booléenne déterminant sa forme
	 * @param a - L'alphabet à utiliser pour le clavier
	 * @param attack - true si le clavier est placé sur l'interface d'attaque, faux sinon
	 */
	public Piano(Alphabet a, boolean attack) {
		Contract.checkCondition(a!=null,"erreur piano alphabet is null");
		model = a;
		if (attack) {
			workMode = ATTACK;
		}
		lastAlphabet = new Alphabet(true,true,true);
		lastAlphabet.setAlphabet(a.alphabet());
		gradAlphabet = new Alphabet(true,true,true);
		createView();
		placeComponents();
		createController();
		replaceGrad = true;
	}
	
	/**
	 * Construit un créateur de clavier qui n'est pas pour l'interface d'attaque à partir d'un alphabet a
	 * @param a L'alphabet à utiliser pour le clavier
	 */
	public Piano(Alphabet a) {
		this(testAlph(a), false);
	}
	
	/**
	 * Construit un créateur de clavier à partir  d'un alphabet par défaut et qui n'est pas pour l'interface d'attaque
	 */
	public Piano() {
		this(new Alphabet(true,true,true),false);
		createView();
		placeComponents();
		createController();
	}
	
	
	// Initialise les composants graphiques selon l'interface où on veut poser le clavier
	private void createView() {
		if (workMode == DEFAULT) {
			panel = new JPanel(new GridLayout(0,13,1,1));
		}
		else {
			panel = new JPanel(new GridLayout(1,0,1,1));
			graduations = new JPanel(new GridLayout(1,0,0,1));
		}
		panel.setBackground(secondaryColor);
	}
	
	// Place les composants graphiques
	private void placeComponents() {
		subpanels = new JPanel[model.size()];
		JPanel p;
		for (int i = 0; i<model.size(); i+=1) {
			p = new JPanel();
			p.setBorder(BorderFactory.createLineBorder(textColor));
			JLabel l = new JLabel(String.valueOf(model.charAtIndex(i)));
			l.setForeground(textColor);
			p.add(l);
			subpanels[i] = p;
			p.setBackground(secondaryColor);
			panel.add(p);
		}
		placeGrad();
	}
	
	// Place les "graduations"
	private void placeGrad() {
	    if (workMode == ATTACK && replaceGrad) {
	        graduations.removeAll();
	        for (int i = 0; i < gradAlphabet.size(); i += 1) {
	            JPanel p = new JPanel();
	            JLabel l = new JLabel(String.valueOf(gradAlphabet.charAtIndex(i)));
	            l.setForeground(textColor);
	            p.add(l);
	            p.setBackground(secondaryColor);
	            p.setBorder(BorderFactory.createEtchedBorder()); // ajout de cette instruction
	            graduations.add(p);
	        }
	        replaceGrad = false;
	    }
	}
	
	/**
	 * Renvoie le clavier cliquable créé
	 * @return un JPanel contenant le clavier
	 */
	public JPanel getPiano() {
		return panel;
	}
	
	/**
	 * Met à jour les "graduations"
	 * @param min - true si les minuscules doivent être activées, false sinon
	 * @param maj - true si les majuscules doivent être activées, false sinon
	 * @param chiffres - true si les chiffres doivent être activées, false sinon
	 */
	public void replaceGrad(boolean min, boolean maj, boolean chiffres) {
		gradAlphabet.refreshAlphabet(min, maj, chiffres);
		replaceGrad = true;
		placeGrad();
	}
	
	/**
	 * Les graduations pour l'interface d'attaque
	 * @pre L'instance a été construite avec le paramètre ATTACK à true
	 * @return Un JPanel contenant les "graduations"
	 */
	public JPanel getGraduations() {
		Contract.checkCondition(workMode == ATTACK);
		return graduations;
	}
	
	// Met à jour le clavier lors des changements dans son alphabet interne
	private void createController() {
		model.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				refreshPiano();
			}
			
		});
		
		putPanelListeners();
	}
	
	// Méthode privée permettant de poser les écouteurs de clic sur chaque touche du clavier
	private void putPanelListeners() {
		MouseListener ml = new MouseListener() {
			
			boolean alreadySelected = false;
			int selectedIndex =  -1;

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				JPanel p = (JPanel) e.getSource();
				JLabel l = (JLabel) (p.getComponent(0));
				char c = l.getText().charAt(0);
				int ind = model.getIndexOf(c);
				
				if (!alreadySelected){
					alreadySelected = true;
					selectedIndex = ind;
					p.setBackground(Color.BLUE);
				} else {
					if (selectedIndex != ind) {
						lastAlphabet.setAlphabet(model.alphabet());
						char[] alphabet = model.alphabet();
						alphabet[ind] = alphabet[selectedIndex];
						alphabet[selectedIndex] = c;
						model.setAlphabet(alphabet);
					} 
					alreadySelected = false;
					selectedIndex =  -1;
					p.setBackground(secondaryColor);
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//rien
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JPanel p = (JPanel) e.getSource();
				if(!alreadySelected || alreadySelected && p != subpanels[selectedIndex]) {
					p.setBackground(effectColor);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JPanel p = (JPanel) e.getSource();
				if(!alreadySelected || alreadySelected && p != subpanels[selectedIndex]) {
					p.setBackground(secondaryColor);
				}
			}
			
		};
		
		for (JPanel p: subpanels) {
			p.addMouseListener(ml);
		}
	}
	
	/**
	 * Définit l'alphabet utilisé précédemment par le clavier
	 * @param min - true si les minuscules doivent être activées, false sinon
	 * @param maj - true si les majuscules doivent être activées, false sinon
	 * @param chiffres - true si les chiffres doivent être activées, false sinon
	 */
	public void setLastAlpha(boolean min, boolean maj, boolean chif) {
		lastAlphabet.refreshAlphabet(min, maj, chif);
	}
	
	/**
	 * Fait utiliser le dernier alphabet utilisé par le clavier
	 */
	public void modelToLastAlpha() {
		model.setAlphabet(lastAlphabet.alphabet());
	}
	
	// Met à jour l'affichage du clavier
	private void refreshPiano() {
		panel.removeAll();
		placeComponents();
		putPanelListeners();
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * Renvoie l'alphabet utilisé pour les graduations
	 * @return une instance de Alphabet utilisée par graduations
	 */
	public Alphabet gradAlphabet() {
		return new Alphabet(gradAlphabet.alphabet());
	}
	
	/**
	 * Renvoie l'alphabet utilisé par le clavier
	 * @return une instance de Alphabet utilisée par le clavier
	 */
	public Alphabet getAlphabet() {
		return model;
	}
	
	// Permet de tester si l'alphabet a est valide
	private static Alphabet testAlph(Alphabet a) {
		Contract.checkCondition(a!=null);
		return a;
	}
	
}
