package gui.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.utils.Chart;
import gui.utils.Piano;
import model.Attack;
import model.Cipher.StdCipherModel;

/**
 * Classe qui permet la construction de l'interface d'analyse.
 */

public class AttackInterface extends AbstractInterface {
	
	//Attributs
	private JTextField input;
	private JButton  fromFile;
	private JLabel fileName;
	private JButton removeFileButton;
	private JButton buttonGoAttack;
	private Chart chartModifiable;
	private Chart chartDefault;
	private JComboBox<String> language;
	private Attack model;
	private JTextPane result;
	private JTextPane tips;
	private JScrollPane spResult;
	private JScrollPane spTips;
	private String message;
	private String previousMessage;
	private JLabel fileLanguageName;
	private JButton removeFileLanguageButton;
	private JCheckBox majuscules;
	private JCheckBox minuscules;
	private JCheckBox chiffres;
	JCheckBox[] checkBoxes;
	private Piano pianoCreator;
	private JPanel piano;
	private JButton goBack;
	private JButton calcButton;
	private JScrollPane jsp;
	private static final String ENGLISH_TIPS = "src/ressources/tips/englishTips.txt";
	private static final String FRENCH_TIPS = "src/ressources/tips/frenchTips.txt";
	private static final String ESPAGNOL_TIPS = "src/ressources/tips/espagnolTips.txt";
	

	
	/**
	 * Instancie tout les composants nécéssaires
	 * au bon fonctionnement de l'application
	 */
	protected void createView() {
		
		
		workInterface= new JPanel();
		
		buttonGoAttack=  new JButton("Analyser");
		buttonGoAttack.setPreferredSize(new Dimension(87, 60));
		buttonGoAttack.setBackground(secondaryColor);
		buttonGoAttack.setForeground(textColor);
		buttonGoAttack.setBorder(null);
		
	
		
		input = new JTextField();
		input.setBorder(new LineBorder(Color.DARK_GRAY));
		input.setBackground(secondaryColor);
		input.setPreferredSize(new Dimension(500, 30));
		input.setForeground(textColor);
		
		fileName= new JLabel("");
		fileName.setForeground(textColor);

		fromFile = new JButton();
		fromFile.setPreferredSize(new Dimension(30, 30));
		fromFile.setBackground(secondaryColor);
		fromFile.setBorder(null);
		fromFile.setIcon(new ImageIcon("src/ressources/icons/file30.png"));
		
		removeFileButton = new JButton();
		removeFileButton.setPreferredSize(new Dimension(30, 30));
		removeFileButton.setBackground(secondaryColor);
		removeFileButton.setBorder(null);
		removeFileButton.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
		removeFileButton.setVisible(false);
		
		Border b = new LineBorder(textColor);
		Border b1 = BorderFactory.createTitledBorder(b, "Graphique de référence ",0, 0, null, textColor);
		
		chartDefault= new Chart();
		chartDefault.setBackground(secondaryColor);
		chartDefault.setBarColor(effectColor);
		chartDefault.setVisible(false);
		chartDefault.setBorder(b1);
		
		b1 =BorderFactory.createTitledBorder(b, "Fréquences des lettres dans le texte",0, 0, null, textColor);
		chartModifiable= new Chart();
		chartModifiable.setBackground(secondaryColor);
		chartModifiable.setBarColor(effectColor);
		chartModifiable.setVisible(false);
		chartModifiable.setBorder(b1);
		
		b1 =BorderFactory.createTitledBorder(b, "Résultat",0, 0, null, textColor);
		result = new JTextPane();
		result.setBackground(Color.BLACK);
		result.setEditable(false);
		result.setForeground(effectColor);
		result.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
		
		tips = new JTextPane();
		tips.setBackground(Color.BLACK);
		tips.setEditable(false);
		tips.setForeground(effectColor);
		tips.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
		
		
		language = new JComboBox<String> ();
		language.setBackground(secondaryColor);
		language.setForeground(textColor);
		language.addItem(Attack.NONE);
		language.addItem(Attack.ENGLISH);
		language.addItem(Attack.FRENCH);
		language.addItem(Attack.SPANISH);
		language.addItem(Attack.OTHER);
		
		pianoCreator = new Piano(alphabetModel, true);
		piano = pianoCreator.getPiano();
		piano.setVisible(false);
		
		goBack=new JButton();
		goBack.setPreferredSize(new Dimension(30, 30));
		goBack.setBackground(secondaryColor);
		goBack.setBorder(null);
		goBack.setIcon(new ImageIcon("src/ressources/icons/goBack.png"));
		goBack.setVisible(false);
		
		majuscules = new JCheckBox ("Majuscules");
		majuscules.setBackground(secondaryColor);
		majuscules.setForeground(textColor);
		majuscules.setSelected(true);
		majuscules.setEnabled(false);
		
		minuscules = new JCheckBox ("Minuscules");
		minuscules.setBackground(secondaryColor);
		minuscules.setForeground(textColor);
		minuscules.setSelected(true);
		minuscules.setEnabled(false);
		
		
		chiffres = new JCheckBox ("Chiffres");
		chiffres.setBackground(secondaryColor);
		chiffres.setForeground(textColor);
		chiffres.setSelected(true);
		chiffres.setEnabled(false);
		
		checkBoxes = new JCheckBox[] {majuscules,minuscules,chiffres};
		
		
		
		fileLanguageName = new JLabel();
		fileLanguageName.setBackground(secondaryColor);
		fileLanguageName.setForeground(textColor);
		fileLanguageName.setVisible(false);
		
		removeFileLanguageButton = new JButton();
		removeFileLanguageButton.setPreferredSize(new Dimension(30, 30));
		removeFileLanguageButton.setBackground(secondaryColor);
		removeFileLanguageButton.setBorder(null);
		removeFileLanguageButton.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
		removeFileLanguageButton.setVisible(false);
		
		
		calcButton = new JButton();
		calcButton.setPreferredSize(new Dimension(50, 50));
		calcButton.setBackground(secondaryColor);
		calcButton.setBorder(null);
		calcButton.setText("Calquer les données des graphes");
		calcButton.setForeground(textColor);
		calcButton.setIcon(new ImageIcon("src/ressources/icons/calcIcon.png"));
		calcButton.setVisible(false);

	
	}
	
	
	
	@Override
	/**
	 * Place tout les composants sur la frame
	 */
	public void createInterface() {
		workInterface.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(10, 80));
		{ //--
			JPanel q = new JPanel();
			q.setPreferredSize(new Dimension(800, 40));
			q.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{ //--
				JLabel x = new JLabel("Analyse");
				x.setForeground(textColor);
				q.add(x);
				q.add(input);
				q.add(fromFile);
			} //--
			q.setBackground(secondaryColor);
			p.setBackground(secondaryColor);
			p.add(q, BorderLayout.CENTER);
			q = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{ //--
				q.add(fileName);
				q.add(removeFileButton);	
			} //--
			q.setBackground(secondaryColor);
			p.add(q,BorderLayout.SOUTH);
			
		workInterface.add(p, BorderLayout.NORTH);
		} //--
		p = new JPanel();
		p.setLayout(new GridLayout(0, 1, 0, 0));
		{ //--
			p.add(buttonGoAttack);
		} //--
		p.setBackground(secondaryColor);
		workInterface.add(p, BorderLayout.SOUTH);
			
		JPanel z = new JPanel(new BorderLayout());
		z.setBackground(secondaryColor);
		{ //--
			p = new JPanel(new BorderLayout());
			p.setBackground(secondaryColor);
			{ //--	
				JPanel q = new JPanel();
				q.setBackground(secondaryColor);
				{ //--
					q.add(language);
					q.add(majuscules);
					q.add(minuscules);
					q.add(chiffres);
				} //--
				p.add(q,BorderLayout.CENTER);
				q = new JPanel();
				q.setBackground(secondaryColor);
				{ //--
					q.add(fileLanguageName);
					q.add(removeFileLanguageButton);
				} //--
				p.add(q,BorderLayout.SOUTH);
			} //--
			z.add(p,BorderLayout.NORTH);
			p = new JPanel(new GridLayout(1,2));
			{ //--
				
				JPanel q = new JPanel(new BorderLayout());
				{ //--
					JPanel r = new JPanel(new GridLayout(2,1));
					r.setBackground(secondaryColor);
					{	
						r.add(chartDefault);
						r.add(chartModifiable);
					}
					q.add(r,BorderLayout.CENTER);
					q.add(calcButton,BorderLayout.SOUTH);
				} //--
				q.setBackground(secondaryColor);
				p.add(q);
				q = new JPanel();
				q.setLayout(new GridLayout(2,1));
				{ //--	
					JPanel r = new JPanel(new GridLayout(1,0));
					{ //--
						spResult= new JScrollPane(result);
						Border b = new LineBorder(textColor);
						Border b1 = BorderFactory.createTitledBorder(b, "Résultats",0, 0, null, textColor);
						spResult.setBorder(b1);
						spResult.setBackground(secondaryColor);
						spResult.setVisible(false);
						r.setBackground(secondaryColor);
						r.add(spResult);
					} //--
					
					q.add(r);
					r = new JPanel(new GridLayout(1,0));
					{ //--
						spTips= new JScrollPane(tips);
						Border b = new LineBorder(textColor);
						Border b1 = BorderFactory.createTitledBorder(b, "Remarques",0, 0, null, textColor);
						spTips.setBorder(b1);
						spTips.setBackground(secondaryColor);
						r.setBackground(secondaryColor);
						spTips.setVisible(false);
						r.add(spTips);
					} //--
					q.add(r);
				} //--
				q.setBackground(secondaryColor);
				p.add(q);
			} //--
			p.setBackground(secondaryColor);
			z.add(p,BorderLayout.CENTER);
			
			jsp = new JScrollPane();
			jsp.setVisible(false);
			p = new JPanel();
			p.setBackground(secondaryColor);
			{ //--
				JPanel q = new JPanel(new GridLayout(0,1));
				q.setBackground(secondaryColor);
				{
					q.add(pianoCreator.getGraduations());
					q.add(piano);
					pianoCreator.getGraduations().setVisible(piano.isVisible());
				}
				p.add(q);
				p.add(goBack);
				
			} //--
			jsp.setViewportView(p);
			jsp.setBackground(secondaryColor);
			z.add(jsp,BorderLayout.SOUTH);
			
		} //--
		
		workInterface.add(z,BorderLayout.CENTER);
		
	}

	

	@Override
	
	/**
	 * Crée le controlleur de cette application 
	 */
	void createController() {
		
		/**
		 * Permet la selection d'un fichier
		 */
		fromFile.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = selectLoadFile();
				if(f != null) 
				{
					if(!isTextFile(f)) {
						displayError("Fichier non pris en charge");
						return;
					}
					fileName.setText(f.getName());
					fileName.setForeground(textColor);
					input.setText("");
					input.setEnabled(false);
					removeFileButton.setVisible(true);
					model.setClientFile(f);
				}
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
				fromFile.setBackground(effectColor);				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				fromFile.setBackground(secondaryColor);
				
			}
		});
		
		/**
		 * Permet la suppression du fichier séléctionné
		 */
		removeFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileName.setText("");
				input.setEnabled(true);
				removeFileButton.setVisible(false);
				
			}
			
		});
		
		/**
		 * Permet le choix de la langue et la mise à jour de tout les composants
		 */
		language.addActionListener(new ActionListener() {
			
			String lastSelection = "";

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String selected = (String) language.getSelectedItem();
				if (!lastSelection.equals(selected)) {					
					if(language.getSelectedItem()== Attack.OTHER) {
						File f = selectLoadFile();
						if(f == null) {
							language.setSelectedItem(Attack.ENGLISH);
							return;
						}
						model.setFileForRef(f);
						fileLanguageName.setText(f.getName());
						fileLanguageName.setVisible(true);
						removeFileLanguageButton.setVisible(true);
						tips.setText("");
					}
					model.setLangage(selected);
					lastSelection = new String(selected);
				}
				try {
					refreshCharts();
					appendTips();
				}
			catch(NullPointerException s) {
				//
			}
			}
		});
		
		
		for(JCheckBox cb : checkBoxes) {
			cb.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					updateSelectedOptions();
					
					
				}
				
			});
		} 
		
		input.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				if(input.isEnabled()) {
					input.setBackground(effectColor);
				}
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				input.setBackground(secondaryColor);
			}
		});
		
		input.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				model.setMessage(input.getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				model.setMessage(input.getText());
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		/**
		 * Définis le comportement du boutton de lancement d'analyse.
		 */
		
	buttonGoAttack.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(chartModifiable.isVisible()) {
					if(!showOptionPane("Voulez vous lancer une nouvelle analyse ?")) {
						return;
					}
				}
				if(!doVerifications()) {
					return;
				}
				
				for(JCheckBox b : checkBoxes) {
					b.setEnabled(true);
				}  
				jsp.setVisible(true);
				calcButton.setVisible(true);

				result.setText(model.getMessage());
				message =model.getMessage();
				chartDefault.setVisible(true);
				goBack.setVisible(true);
				model.setMessageData();
				refreshCharts();
				alphabetModel.refreshAlphabet(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
				piano.setVisible(true);
				pianoCreator.getGraduations().setVisible(true);
				spResult.setVisible(true);
				spTips.setVisible(true);
				appendTips();
				previousMessage =null;
				
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
				buttonGoAttack.setBackground(effectColor);
				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				buttonGoAttack.setBackground(secondaryColor);
			}
		});
	
		alphabetModel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				previousMessage = message;
				if (minuscules.isSelected() && !majuscules.isSelected() || !minuscules.isSelected() && majuscules.isSelected()) {
					message = StdCipherModel.substituteWithoutCaseSensitivity(pianoCreator.gradAlphabet().alphabet(), alphabetModel.alphabet(), model.getMessage());
				} else {
					message = StdCipherModel.substitute(pianoCreator.gradAlphabet().alphabet(), alphabetModel.alphabet(), model.getMessage());
				}
				result.setText(message);
				
			}
			
		});
		
		goBack.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(previousMessage !=null) {
					String temp = previousMessage;
					previousMessage=message;
					message =temp;
					result.setText(message);
					pianoCreator.modelToLastAlpha();
				}
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
				if(previousMessage != null) {
					goBack.setBackground(effectColor);
				}else {
					goBack.setBackground(Color.RED);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				goBack.setBackground(secondaryColor);
				
			}
			
		});
		
		removeFileLanguageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeFileLanguageButton.setVisible(false);
				fileLanguageName.setVisible(false);
				language.setSelectedItem(Attack.ENGLISH);
				
			}
			
		});
		
		
		calcButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String[] modNames = chartModifiable.getNames();
				String[] defaultNames = chartDefault.getNames();
				char[] alphabetResult = alphabetModel.getBaseAlphabet();
				
				for(int i = 0; i<defaultNames.length ; i++) {
					char c =modNames[i].charAt(0);
					int index = alphabetModel.indexOfinBaseAlphabet(c);
					if(index == -1) {
						continue;
					}
					alphabetResult[index]=defaultNames[i].charAt(0);
				}
				alphabetModel.setAlphabet(alphabetResult);
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
				calcButton.setBackground(effectColor);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				calcButton.setBackground(secondaryColor);
				
			}
			
		});

		
	}

	@Override
	void createModel() {
		model = new Attack(alphabetModel);
	}
	
	
	//-------------------------------Outils-------------------------------
	
	
	/**
	 * Vérifie l'entrée du client
	 * @return
	 */
	
	private boolean doVerifications() {
		if((input.getText() == null || (input.getText()).isBlank() )) {
			if(!model.isFileSet()) {
			displayError("Veuillez insérer un message à chiffrer ou choisir un fichier");
			return false;
			}
		}
		if(language.getSelectedItem().equals(Attack.NONE)) {
			displayError("Veuillez choisir une langue");
			return false;
		}
		if(!(minuscules.isSelected()|| majuscules.isSelected() || chiffres.isSelected())) {
			displayError("Veuillez choisir un alphabet");
			return false;
		}
		return true;
	}
	
	/**
	 * Méthode outils qui permtet de convertir une collection en tableau
	 * @param c
	 * @return
	 */
	
	private double[] collectionToArray(Collection<Double> c) {
		double[] ret = new double[c.size()];
		int i =0;
		for(Double d : c) {
			ret[i]=d;
			i++;
		}
		return ret;
	}
	
	private String[] fromObjArrayToCharArray(Object[] c) {
		String[] ret = new String[c.length];
		int i =0;
		for(Object d : c) {
			ret[i]=d+"";
			i++;
		}
		return ret;
		
	}
	
	/**
	 * Méthode permettant de mettre à jour les diagrammes.
	 */
	private void refreshCharts() {
		refreshModifiableChart();
		refreshDefaultChart();
	}
	
	/**
	 * Méthode permettant de mettre à jour le diagramme
	 * représentant le texte chiffré.
	 */
	
	private void refreshModifiableChart() {
		chartModifiable.setData(collectionToArray(model.getData()));
		chartModifiable.setNames(fromObjArrayToCharArray(model.getNames().toArray()));
		chartModifiable.repaint();
		chartModifiable.validate();
		chartModifiable.setVisible(true);
		
	}
	
	/**
	 * Permet la mise à jour du diagramme de référence.
	 */
	private void refreshDefaultChart() {
		chartDefault.setData(collectionToArray(model.getRefData()));
		chartDefault.setNames(fromObjArrayToCharArray(model.getRefNames().toArray()));
		chartDefault.repaint();
		chartDefault.validate();
	}
	
	/**
	 * Permet la mise à jours de tout les composants après 
	 * un changement d'alphabet
	 */
	private void updateSelectedOptions(){
		try {
			if(!(minuscules.isSelected() || majuscules.isSelected() || chiffres.isSelected())){
				displayError("Au moins un alphabet doit être choisi");
			}
			pianoCreator.replaceGrad(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
			alphabetModel.refreshAlphabet(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
			pianoCreator.setLastAlpha(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
			model.setMode(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
			model.setAlphabet(alphabetModel);
			refreshCharts();
		}catch(Exception exc) {
			//
			return;
		}
		
	}
	/**
	 * Affiche les astuces
	 */
	private void appendTips() {
		try {
			if(language.getSelectedItem() == Attack.ENGLISH) {
				String content = "La lettre "+chartModifiable.getMostFrequentLetter()+
						  " correspond probalement à la la lettre "+Attack.getFileContent(new File(ENGLISH_TIPS)); 
				tips.setText(content);
			}
		  if(language.getSelectedItem() == Attack.FRENCH) {
			 	String content = "La lettre "+chartModifiable.getMostFrequentLetter()+
			 			" correspond probalement à la la lettre "+Attack.getFileContent(new File(FRENCH_TIPS)); 
				tips.setText(content);
		  }
		  if(language.getSelectedItem() == Attack.SPANISH) {
				  String content = "La lettre "+chartModifiable.getMostFrequentLetter()+
							" correspond probalement à la la lettre "+Attack.getFileContent(new File(ESPAGNOL_TIPS)); 
				  tips.setText(content);
		  }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			
		}
	}
	
	
}
