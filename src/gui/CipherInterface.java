package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Cipher;


public class CipherInterface extends AbstractInterface {
	

	private JButton buttonGoCipher;
	@SuppressWarnings("unused")
	private JPanel resultPanelCipher;
	private JTextArea cipherResult;
	private JTextField cipherInput;
	private ButtonGroup cipherConfig;
	private JRadioButton fromKeyCipher;
	private JRadioButton fromPermsCipher;
	private JRadioButton removePonct;
	private JTextField fromKeyInputCipher;
	@SuppressWarnings("unused")
	private ButtonGroup cipherAlphabetConfig;
	private JRadioButton majusculesCipher;
	private JRadioButton minusculesCipher;
	private JRadioButton chiffresCipher;
	private JButton fromFileCipher;
	private JLabel fileNameCipher;
	private JButton removeFileButtonCipher;
	private JButton removeFileButtonAttack;
	


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
			JLabel x = new JLabel("Chiffrement");
			x.setForeground(textColor);
			q.add(x);
			q.add(cipherInput);
			q.add(fromFileCipher);
		}
		q.setBackground(secondaryColor);
		p.setBackground(secondaryColor);
		p.add(q, BorderLayout.NORTH);
		q = new JPanel(new FlowLayout(FlowLayout.CENTER));
		{
			q.add(fileNameCipher);
			q.add(removeFileButtonCipher);	
		}
		q.setBackground(secondaryColor);
		p.add(q,BorderLayout.CENTER);
		
	workInterface.add(p, BorderLayout.NORTH);
	}
	
	p = new JPanel();
	p.setLayout(new GridLayout(0, 1, 0, 0));
	{
		p.add(buttonGoCipher);
	}
	p.setBackground(secondaryColor);
	workInterface.add(p, BorderLayout.SOUTH);
	
	p = new JPanel(new GridLayout(1,2));
	{
		JPanel q = new JPanel(new GridLayout(2,1));
		{
			JPanel r = new JPanel();
			{
				//r.add(new JLabel(ChiffrementSource),BorderLayout.NORTH);
				JPanel s = new JPanel(new GridLayout(3,1));
				s.setPreferredSize(new Dimension(300, 150));
				{
					s.add(fromKeyCipher);
					s.add(fromKeyInputCipher);
					s.add(fromPermsCipher);
					
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
				//r.add(new JLabel(AlphabetSource),BorderLayout.NORTH);
				JPanel s = new JPanel();
				s.setPreferredSize(new Dimension(300, 150));
				s.setLayout(new GridLayout(4,1));
				{
					s.add(majusculesCipher);
					s.add(minusculesCipher);
					s.add(chiffresCipher);
					s.add(removePonct);
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
		}
		p.add(q);
		q = new JPanel();
		q.setLayout(new GridLayout(0, 1, 0, 0));
		{
			q.add(cipherResult);
			LineBorder b = new LineBorder(textColor);
			TitledBorder b1 = BorderFactory.createTitledBorder(b, "Résultat",0, 0, null, textColor);
			q.setBorder(b1);
		}
		q.setBackground(secondaryColor);
		p.add(q);
	}
	p.setBackground(secondaryColor);
	workInterface.add(p);
	
	
}

@Override
protected void createView() {
	workInterface = new JPanel();
	
	buttonGoCipher = new JButton("Chiffrer");
	buttonGoCipher.setPreferredSize(new Dimension(87, 60));
	buttonGoCipher.setBackground(secondaryColor);
	buttonGoCipher.setForeground(textColor);
	buttonGoCipher.setBorder(null);
	
	cipherResult = new JTextArea();
	cipherResult.setSelectedTextColor(Color.BLUE);
	cipherResult.setBorder(new LineBorder(Color.DARK_GRAY));
	cipherResult.setPreferredSize(new Dimension(200, 200));
	cipherResult.setBackground(Color.BLACK);
	cipherResult.setForeground(effectColor);
	cipherResult.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
	cipherResult.setEditable(false);
	
	cipherInput = new JTextField();
	cipherInput.setBorder(new LineBorder(Color.DARK_GRAY));
	cipherInput.setBackground(secondaryColor);
	cipherInput.setPreferredSize(new Dimension(500, 30));
	cipherInput.setForeground(textColor);
	
	cipherConfig = new ButtonGroup(); 
	fromKeyCipher =  new JRadioButton ("À partir d'une clé");
	fromKeyCipher.setForeground(textColor);
	fromKeyCipher.setBackground(secondaryColor);
	fromPermsCipher = new JRadioButton ("Choisir les permutations");
	fromPermsCipher.setBackground(secondaryColor);
	fromPermsCipher.setForeground(textColor);
	cipherConfig.add(fromKeyCipher);
	cipherConfig.add(fromPermsCipher);
	
	removePonct = new JRadioButton("Supprimer la ponctuation");
	removePonct.setBackground(secondaryColor);
	removePonct.setForeground(textColor);
	
	fromKeyInputCipher = new JTextField();
	fromKeyInputCipher.setPreferredSize(new Dimension(5, 10));
	fromKeyInputCipher.setBorder(new LineBorder(Color.DARK_GRAY));
	fromKeyInputCipher.setBackground(secondaryColor);
	fromKeyInputCipher.setForeground(textColor);
	fromKeyInputCipher.setEnabled(false);
	
	
	
		
	cipherAlphabetConfig = new ButtonGroup();
	majusculesCipher = new JRadioButton ("Majuscules");
	majusculesCipher.setBackground(secondaryColor);
	majusculesCipher.setForeground(textColor);
	
	minusculesCipher = new JRadioButton ("Minuscules");
	minusculesCipher.setBackground(secondaryColor);
	minusculesCipher.setForeground(textColor);
	chiffresCipher = new JRadioButton ("Chiffres");
	chiffresCipher.setBackground(secondaryColor);
	chiffresCipher.setForeground(textColor);
	
	
	
	
	fromFileCipher = new JButton();
	fromFileCipher.setPreferredSize(new Dimension(30, 30));
	fromFileCipher.setBackground(secondaryColor);
	fromFileCipher.setBorder(null);
	fromFileCipher.setIcon(new ImageIcon("src/ressources/icons/file30.png"));
	fileNameCipher = new JLabel("");
	fileNameCipher.setPreferredSize(new Dimension(500, 30));
	
	
	
	removeFileButtonCipher = new JButton();
	removeFileButtonCipher.setPreferredSize(new Dimension(30, 30));
	removeFileButtonCipher.setBackground(secondaryColor);
	removeFileButtonCipher.setBorder(null);
	removeFileButtonCipher.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
	removeFileButtonCipher.setVisible(false);
	
	
	
	removeFileButtonAttack = new JButton();
	removeFileButtonAttack.setPreferredSize(new Dimension(30, 30));
	removeFileButtonAttack.setBackground(secondaryColor);
	removeFileButtonAttack.setBorder(null);
	removeFileButtonAttack.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
	removeFileButtonAttack.setVisible(false);
	
	
}

@Override
void createController() {
	fromKeyCipher.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			refreshRadioButtonsCipher();
		}
		
	});
	
	fromPermsCipher.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			refreshRadioButtonsCipher();
		}
		
	});
	
	removeFileButtonCipher.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			fileNameCipher.setText("");
			cipherInput.setEnabled(true);
			removeFileButtonCipher.setVisible(false);
			//model.setFile();
			
		}
		
	});
	
	fromFileCipher.addMouseListener(new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			File f = selectLoadFile();
			if(f != null) 
			{
				fileNameCipher.setText(f.getName());
				fileNameCipher.setForeground(textColor);
				cipherInput.setText("");
				cipherInput.setEnabled(false);
				removeFileButtonCipher.setVisible(true);
				model.setFile(f);
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
			fromFileCipher.setBackground(effectColor);				
		}

		@Override
		public void mouseExited(MouseEvent e) {
			fromFileCipher.setBackground(secondaryColor);
			
		}
	});
	
	
	
	cipherInput.addMouseListener(new MouseListener() {
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
			if(cipherInput.isEnabled()) {
				cipherInput.setBackground(effectColor);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			cipherInput.setBackground(secondaryColor);
		}
	});
	
	fromKeyInputCipher.addMouseListener(new MouseListener() {
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
			if(fromKeyInputCipher.isEnabled()) {
				fromKeyInputCipher.setBackground(effectColor);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			fromKeyInputCipher.setBackground(secondaryColor);
			
		}
	});
	
	buttonGoCipher.addMouseListener(new MouseListener() {
		
		public void mouseClicked(MouseEvent e) {
			
			String message = cipherInput.getText();
			boolean random =false;
			
			if((message == null || message.isBlank()) &&!model.isFileSelected()) {
				displayError("Veuillez insérer un message à chiffrer ou choisir un fichier");
				return;
			}
			if(!(fromKeyCipher.isSelected() || fromPermsCipher.isSelected())){
				displayError("Veuillez choisir une source de chiffrement");
				return;
			}
			try {
				model.setAlphabet(minusculesCipher.isSelected(),majusculesCipher.isSelected(),chiffresCipher.isSelected());
			}catch(AssertionError exc) {
				displayError("Veuillez selectionner au moins un alphabet");
				return;
			}
			
			String key = fromKeyInputCipher.getText();

				
			if(fromKeyCipher.isSelected()) {
				if(key == null || key.isBlank()){
					random = showOptionPane("Voulez vous générer une clé aléatoire ?");
					if(!random) {
						return;
					}
				}
				
				if(!random) {
					model.setSeed(key);
				}
				
			}
			
			if(fromPermsCipher.isSelected()) {
				
			}
			
			model.setAlphabet(minusculesCipher.isSelected(),majusculesCipher.isSelected(),chiffresCipher.isSelected());
			if(model.isFileSelected()) {
				try {
					model.getClearFromFile();
				} catch (IOException e1) {
					displayError("Le fichier contient des charactères non pris en charge");
					return;
				}
			}
			model.setClearMessage(message);
			model.encoder_message(Cipher.CHIFFREMENT);
			
			fmt = new Formatter();
			fmt.setSeed(model.getSeed());
			fmt.setKey(model.getKey().toString());
			fmt.setResult(model.getCypheredMessage());
			
			FontMetrics fm =cipherResult.getFontMetrics(cipherResult.getFont());
			
			fmt.setScreenSize(cipherResult.getWidth()/fm.charWidth('0'));
			
			AfficheurEnTempsReel af = new AfficheurEnTempsReel(fmt.getFormated(),cipherResult);
			af.execute();
			
			
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
			buttonGoCipher.setBackground(effectColor);
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			buttonGoCipher.setBackground(secondaryColor);
		}
	});
	
}

@Override
void createModel() {
	model = new Cipher();
	
}
private void refreshRadioButtonsCipher() {
	if(fromKeyCipher.isSelected()) {
		fromKeyInputCipher.setEnabled(true);
	}else {
		fromKeyInputCipher.setEnabled(false);
		fromKeyInputCipher.setText("");
		
	}
}

}
