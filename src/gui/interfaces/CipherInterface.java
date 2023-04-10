package gui.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.utils.Piano;
import model.Alphabet;
import model.Cipher.ICipher;
import model.Cipher.StdCipherModel;

/**
 * Classe qui permet la construction de l'interface de chiffrement.
 */

public class CipherInterface extends AbstractInterface {
	

	private JButton buttonGo;
	@SuppressWarnings("unused")
	private JPanel resultPanel;
	private JTextPane Result;
	private JScrollPane scrollPane;
	private JTextField Input;
	private ButtonGroup Config;
	private JRadioButton fromKey;
	private JRadioButton fromPerms;
	private JRadioButton removePonct;
	private JTextField fromKeyInput;
	@SuppressWarnings("unused")
	private ButtonGroup AlphabetConfig;
	private JRadioButton majuscules;
	private JRadioButton minuscules;
	private JRadioButton chiffres;
	private JButton fromFile;
	private JLabel fileName;
	private JButton removeFileButton;
	private JButton removeFileButtonAttack;
	private JPanel piano;
	
	private JRadioButton level1;
	private JRadioButton level2;
	private JRadioButton level3;
	private ButtonGroup levels;
	
	private JButton copy;


	@Override
	public void createInterface() {
		workInterface.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(10, 80));
		{
			JPanel q = new JPanel();
			q.setPreferredSize(new Dimension(800, 40));
			q.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel x = new JLabel("Insérez le texte à chiffrer  ");
				x.setForeground(textColor);
				q.add(x);
				q.add(Input);
				q.add(fromFile);
			}
			q.setBackground(secondaryColor);
			p.setBackground(secondaryColor);
			p.add(q, BorderLayout.NORTH);
			q = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{
				q.add(fileName);
				q.add(removeFileButton);	
			}
			q.setBackground(secondaryColor);
			p.add(q,BorderLayout.CENTER);
			
		workInterface.add(p, BorderLayout.NORTH);
		}
		
		p = new JPanel();
		p.setLayout(new GridLayout(0, 1, 0, 0));
		{
			p.add(buttonGo);
		}
		p.setBackground(secondaryColor);
		workInterface.add(p, BorderLayout.SOUTH);
		
		p = new JPanel(new GridLayout(1,2));
		{
			JPanel q = new JPanel(new GridLayout(4,1));
			{
				JPanel r = new JPanel();
				{
					//r.add(new JLabel(ChiffrementSource),BorderLayout.NORTH);
					JPanel s = new JPanel(new GridLayout(3,1));
					s.setPreferredSize(new Dimension(300, 150));
					{
						s.add(fromKey);
						s.add(fromKeyInput);
						s.add(fromPerms);
						
					}
					s.setBackground(secondaryColor);
					r.setBackground(secondaryColor);
					r.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					r.add(s);
				}
				LineBorder b = new LineBorder(textColor);
				TitledBorder b1 = BorderFactory.createTitledBorder(b, "Source du chiffrement",0, 0, null, textColor);
				r.setBorder(b1);
				r.setBackground(secondaryColor);
				q.add(r);
				
				
				r = new JPanel();
				{
					piano.setVisible(false);
					r.add(piano);
				}
				
				r.setBorder( new LineBorder(textColor));
				r.setBackground(secondaryColor);
				q.add(r);
				
				r = new JPanel();
				{
					//r.add(new JLabel(AlphabetSource),BorderLayout.NORTH);
					JPanel s = new JPanel();
					s.setPreferredSize(new Dimension(300, 150));
					s.setLayout(new GridLayout(4,1));
					{
						s.add(majuscules);
						s.add(minuscules);
						s.add(chiffres);
					}
					s.setBackground(secondaryColor);
					r.setBackground(secondaryColor);
					r.add(s,BorderLayout.CENTER);
				}
				b = new LineBorder(textColor);
				b1 = BorderFactory.createTitledBorder(b, "Choisissez l'alphabet",0, 0, null, textColor);
				r.setBorder(b1);
				r.setBackground(secondaryColor);
				q.add(r);
				
				
				r = new JPanel();
				{
					JPanel s = new JPanel();
					s.setPreferredSize(new Dimension(300, 150));
					s.setLayout(new GridLayout(4,1));
					{
						s.add(level1);
						s.add(level2);
						s.add(level3);
					}
					s.setBackground(secondaryColor);
					r.setBackground(secondaryColor);
					r.add(s,BorderLayout.CENTER);
				}
				b = new LineBorder(textColor);
				b1 = BorderFactory.createTitledBorder(b, "Niveau de difficulté",0, 0, null, textColor);
				r.setBorder(b1);
				r.setBackground(secondaryColor);
				q.add(r);
			}
			p.add(q);
			q = new JPanel(new BorderLayout()); 
			{
				LineBorder b = new LineBorder(textColor);
				TitledBorder b1 = BorderFactory.createTitledBorder(b, "Résultat",0, 0, null, textColor);
				scrollPane.setBorder(b1);
				q.add(scrollPane, BorderLayout.CENTER);
				q.add(copy,BorderLayout.SOUTH);
			}
			p.add(q);
		}
		p.setBackground(secondaryColor);
		workInterface.add(p);
		
		
	}
	
	@Override
	protected void createView() {
		workInterface = new JPanel();
		
		buttonGo = new JButton("Chiffrer");
		buttonGo.setPreferredSize(new Dimension(87, 60));
		buttonGo.setBackground(secondaryColor);
		buttonGo.setForeground(textColor);
		buttonGo.setBorder(null);
		
		Result = new JTextPane();
		Result.setSelectedTextColor(Color.BLUE);
		Result.setBorder(new LineBorder(Color.DARK_GRAY));
		Result.setBackground(Color.BLACK);
		Result.setForeground(effectColor);
		Result.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
		Result.setEditable(false); 
		
		scrollPane = new JScrollPane(Result);
		scrollPane.setBackground(secondaryColor);
		Input = new JTextField();
		Input.setBorder(new LineBorder(Color.DARK_GRAY));
		Input.setBackground(secondaryColor);
		Input.setPreferredSize(new Dimension(500, 30));
		Input.setForeground(textColor);
		
		Config = new ButtonGroup(); 
		fromKey =  new JRadioButton ("À partir d'une clé");
		fromKey.setForeground(textColor);
		fromKey.setBackground(secondaryColor);
		fromPerms = new JRadioButton ("Choisir les permutations");
		fromPerms.setBackground(secondaryColor);
		fromPerms.setForeground(textColor);
		Config.add(fromKey);
		Config.add(fromPerms);
		
		removePonct = new JRadioButton("Supprimer la ponctuation");
		removePonct.setBackground(secondaryColor);
		removePonct.setForeground(textColor);
		
		fromKeyInput = new JTextField();
		fromKeyInput.setPreferredSize(new Dimension(5, 10));
		fromKeyInput.setBorder(new LineBorder(Color.DARK_GRAY));
		fromKeyInput.setBackground(secondaryColor);
		fromKeyInput.setForeground(textColor);
		fromKeyInput.setEnabled(false);
		
		
		
			
		AlphabetConfig = new ButtonGroup();
		majuscules = new JRadioButton ("Majuscules");
		majuscules.setBackground(secondaryColor);
		majuscules.setForeground(textColor);
		majuscules.setSelected(true);
		
		minuscules = new JRadioButton ("Minuscules");
		minuscules.setBackground(secondaryColor);
		minuscules.setForeground(textColor);
		minuscules.setSelected(true);
		chiffres = new JRadioButton ("Chiffres");
		chiffres.setBackground(secondaryColor);
		chiffres.setForeground(textColor);
		chiffres.setSelected(true);
		
		
		
		
		fromFile = new JButton();
		fromFile.setPreferredSize(new Dimension(30, 30));
		fromFile.setBackground(secondaryColor);
		fromFile.setBorder(null);
		fromFile.setIcon(new ImageIcon("src/ressources/icons/file30.png"));
		fileName = new JLabel("");
		fileName.setPreferredSize(new Dimension(500, 30));
		
		
		
		removeFileButton = new JButton();
		removeFileButton.setPreferredSize(new Dimension(30, 30));
		removeFileButton.setBackground(secondaryColor);
		removeFileButton.setBorder(null);
		removeFileButton.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
		removeFileButton.setVisible(false);
		
		
		
		removeFileButtonAttack = new JButton();
		removeFileButtonAttack.setPreferredSize(new Dimension(30, 30));
		removeFileButtonAttack.setBackground(secondaryColor);
		removeFileButtonAttack.setBorder(null);
		removeFileButtonAttack.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
		removeFileButtonAttack.setVisible(false);
		
		piano = new Piano(alphabetPiano).getPiano();
		
		level1 = new JRadioButton("Chiffrement simple");
		level1.setBackground(secondaryColor);
		level1.setForeground(textColor);
		
		level2 = new JRadioButton("Ponctuation supprimée");
		level2.setBackground(secondaryColor);
		level2.setForeground(textColor);
		
		level3 = new JRadioButton("Ponctuation et espace supprimés");
		level3.setBackground(secondaryColor);
		level3.setForeground(textColor);
		
		levels = new ButtonGroup();
		levels.add(level1);
		levels.add(level2);
		levels.add(level3);
		
		copy = new JButton("Copier le résultat");
		copy.setBackground(secondaryColor);
		copy.setForeground(textColor);
		copy.setBorder(null);
		copy.setEnabled(false);
		copy.setPreferredSize(new Dimension(30,30));
		
	}
	
	@Override
	void createController() {
		
		fromKey.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				fromKeyEnabled();
			}
			
		});
		
		fromPerms.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				fromPermsEnabled();
			}
			
		});
		
		majuscules.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshPiano();
					alphabetModel.refreshAlphabet(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
					Alphabet tmp = new Alphabet(true,true,true);
					tmp.setAlphabet(alphabetModel.alphabet());
					model.setAlphabet(alphabetModel);
					alphabetPiano.setAlphabet(tmp.alphabet());
				}catch(AssertionError exc) {
					//
					return;
				}
				
			}
			
		});
		
		minuscules.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshPiano();
					alphabetModel.refreshAlphabet(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
					Alphabet tmp = new Alphabet(true,true,true);
					tmp.setAlphabet(alphabetModel.alphabet());
					model.setAlphabet(alphabetModel);
					alphabetPiano.setAlphabet(tmp.alphabet());
				}catch(AssertionError exc) {
					//
					return;
				}
				
			}
			
		});
		
		chiffres.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshPiano();
					alphabetModel.refreshAlphabet(minuscules.isSelected(),majuscules.isSelected(),chiffres.isSelected());
					Alphabet tmp = new Alphabet(true,true,true);
					tmp.setAlphabet(alphabetModel.alphabet());
					model.setAlphabet(alphabetModel);
					alphabetPiano.setAlphabet(tmp.alphabet());
				}catch(AssertionError exc) {
					//
					return;
				}
				
			}
			
		}); 
		
		alphabetPiano.addChangeListener(new ChangeListener() {
	
			@Override
			public void stateChanged(ChangeEvent e) {
				model.setKey(alphabetPiano);
			}
			
		});
			
		
		removeFileButton.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				fileName.setText("");
				Input.setEnabled(true);
				removeFileButton.setVisible(false);
				model.removeFile();
				
			}
		});
		
		fromFile.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = selectLoadFile();
				
				if(f != null) 
				{
					if(!isTextFile(f)) {
						displayError("Veuillez choisir un fichier .txt");
						return;
					}
					fileName.setText(f.getName());
					fileName.setForeground(textColor);
					Input.setText("");
					Input.setEnabled(false);
					removeFileButton.setVisible(true);
					try {
						model.setFile(f, ICipher.CHIFFREMENT);
					} catch (IOException e1) {
						displayError("Problème lors de la lecture du fichier");
						return;
					}
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
		
		
		
		Input.addMouseListener(new MouseListener() {
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
				if(Input.isEnabled()) {
					Input.setBackground(effectColor);
				}
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				Input.setBackground(secondaryColor);
			}
		});
		
		
		fromKeyInput.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//tabbedPane.setSelectedIndex(3);
				
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
				if(fromKeyInput.isEnabled()) {
					fromKeyInput.setBackground(effectColor);
				}
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				fromKeyInput.setBackground(secondaryColor);
				
			}
		});
		
		Input.getDocument().addDocumentListener(new DocumentListener() {
	
			@Override
			public void insertUpdate(DocumentEvent e) {
				model.setClearMessage(Input.getText());
				
			}
	
			@Override
			public void removeUpdate(DocumentEvent e) {
				model.setClearMessage(Input.getText());
				
			}
	
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		buttonGo.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				boolean random =false;
				if (!doVerifications()) {
					return;
				}
				setTheLevel();
				copy.setEnabled(true);
					
				if(fromKey.isSelected()) {
					if(fromKeyInput.getText() == null || fromKeyInput.getText().isBlank()){
						random = showOptionPane("Voulez vous générer une clé aléatoire ?");
						if(random) {
							model.setRandomSeed();
						}else {
							return;
						}
					}else {
						model.setSeed(fromKeyInput.getText());
					}
				}
				
				alphabetPiano.setAlphabet(model.getKey().alphabet());
				model.encoder_message(StdCipherModel.CHIFFREMENT);
				
				fmt = new model.Formatter();
				fmt.setSeed(model.getSeed());
				fmt.setFromKey(fromKey.isSelected());
				fmt.setKey(model.getKey().alphabet());
				fmt.setResult(model.getCypheredMessage());
				fmt.setClearMessage(model.getClearMessage());
				FontMetrics fm =Result.getFontMetrics(Result.getFont());
				
				fmt.setScreenSize(Result.getWidth()/fm.charWidth('0'));
				
				Result.setStyledDocument(fmt.getFormated());
				
				
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
				buttonGo.setBackground(effectColor);
				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				buttonGo.setBackground(secondaryColor);
			}
		});
		
		copy.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(copy.isEnabled()) {
				StringSelection stringSelection = new StringSelection(model.getCypheredMessage());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				showMessageDialog("Message copié le presse-papier");
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
				if(copy.isEnabled()) {
					copy.setBackground(effectColor);
				}
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				copy.setBackground(secondaryColor);
				
			}
			
		});
	}
	
	@Override
	void createModel() {
		model = new StdCipherModel ();
		
	}
	
	
	//---------------------------OUTILS --------------------------------------
	
	/**
	 * Permet de definir le comportement du boutton
	 * fromKey ( à partir d'une clé)
	 */
	private void fromKeyEnabled() {
		if(fromKey.isSelected()) {
			fromKeyInput.setEnabled(true);
			piano.setVisible(false);
			return;
		}
	}
	
	/**
	 * Permet de definir le comportement du boutton
	 * fromPerms ( à partir d'un tableau de permutations)
	 */
	private void fromPermsEnabled() {
		if(fromPerms.isSelected()) {
			fromKeyInput.setEnabled(false);
			fromKeyInput.setText("");
			refreshPiano();
			return;
		}
	}
	
	/**
	 * Permet de rafraichir le clavier de permutations
	 */
	private void refreshPiano() {
		if(!fromPerms.isSelected()) {
			piano.setVisible(false);
			return;
		}
		if(!(chiffres.isSelected()|| majuscules.isSelected() || minuscules.isSelected())){
			piano.setVisible(false);
		}else {
			piano.setVisible(true);
		}
		
	}
	

	/**
	 * Vérifie l'entrée du client
	 * @return
	 */	
	private boolean doVerifications() {
		if((Input.getText() == null || (Input.getText()).isBlank() ) && !model.isFileSet()) {
			displayError("Veuillez insérer un message à chiffrer ou choisir un fichier");
			return false;
		}
		
		if(!(fromKey.isSelected() || fromPerms.isSelected())){
			displayError("Veuillez choisir une source de chiffrement");
			return false;
		}
		
		if(!(chiffres.isSelected() || minuscules.isSelected()  || majuscules.isSelected())){
			displayError("Veuillez choisir au moins un alphabet");
			return false;
		}
		if(!level1.isSelected() && !level2.isSelected() && !level3.isSelected()) {
			displayError("Veuillez selectioner un niveau");
			return false;
		}
		return true;
	}
	
	/**
	 * Permet de définir le niveau de chiffrement
	 */
	private void setTheLevel() {
		if(level1.isSelected()) {
			model.setLevel(StdCipherModel.LEVEL1);
		}
		if(level2.isSelected()) {
			model.setLevel(StdCipherModel.LEVEL2);
			}
		if(level3.isSelected()) {
			model.setLevel(StdCipherModel.LEVEL3);
		}
	}

}
