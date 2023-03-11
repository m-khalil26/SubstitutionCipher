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


public class UncipherInterface extends AbstractInterface {
	
	
	private JButton buttonGoUncipher;
	@SuppressWarnings("unused")
	private JPanel resultPanelUncipher;
	private JTextArea uncipherResult;
	private JTextField uncipherInput;
	
	private ButtonGroup uncipherConfig;
	private JRadioButton fromKeyUncipher;
	private JRadioButton fromPermsUncipher;
	private JTextField fromKeyInputUncipher;
	
	@SuppressWarnings("unused")
	private ButtonGroup uncipherAlphabetConfig;
	private JRadioButton majusculesUncipher;
	private JRadioButton minusculesUncipher;
	private JRadioButton chiffresUncipher;
	
	private JButton fromFileUncipher;
	private JLabel fileNameUncipher;
	private JButton removeFileButtonUncipher;
	

	@Override
	
	protected void createView() {
		workInterface= new JPanel();
		
		buttonGoUncipher=  new JButton("Déchiffrer");
		buttonGoUncipher.setPreferredSize(new Dimension(87, 60));
		buttonGoUncipher.setBackground(secondaryColor);
		buttonGoUncipher.setForeground(textColor);
		buttonGoUncipher.setBorder(null);
		
		uncipherResult = new JTextArea();;
		uncipherResult.setSelectedTextColor(Color.BLUE);
		uncipherResult.setBorder(new LineBorder(Color.DARK_GRAY));
		uncipherResult.setPreferredSize(new Dimension(200, 200));
		uncipherResult.setBackground(Color.BLACK);
		uncipherResult.setForeground(effectColor);
		uncipherResult.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
		uncipherResult.setEditable(false);
		
		uncipherInput = new JTextField();
		uncipherInput.setBorder(new LineBorder(Color.DARK_GRAY));
		uncipherInput.setBackground(secondaryColor);
		uncipherInput.setPreferredSize(new Dimension(500, 30));
		uncipherInput.setForeground(textColor);
		
		uncipherConfig = new ButtonGroup();
		
		fromKeyUncipher = new JRadioButton ("À partir d'une clé");
		fromKeyUncipher.setForeground(textColor);
		fromKeyUncipher.setBackground(secondaryColor);
		
		fromPermsUncipher = new JRadioButton ("Choisir les permutations");
		fromPermsUncipher.setBackground(secondaryColor);
		fromPermsUncipher.setForeground(textColor);
		
		uncipherConfig.add(fromKeyUncipher);
		uncipherConfig.add(fromPermsUncipher);
		
		fromKeyInputUncipher = new JTextField();
		fromKeyInputUncipher.setPreferredSize(new Dimension(5, 10));
		fromKeyInputUncipher.setBorder(new LineBorder(Color.DARK_GRAY));
		fromKeyInputUncipher.setBackground(secondaryColor);
		fromKeyInputUncipher.setForeground(textColor);
		fromKeyInputUncipher.setEnabled(false);
		
		uncipherAlphabetConfig = new ButtonGroup();;
		majusculesUncipher =  new JRadioButton ("Majuscules");
	 	minusculesUncipher = new JRadioButton ("Minuscules");
	 	majusculesUncipher.setBackground(secondaryColor);
		majusculesUncipher.setForeground(textColor);
		minusculesUncipher.setBackground(secondaryColor);
		minusculesUncipher.setForeground(textColor);
	 	
		chiffresUncipher = new JRadioButton ("Chiffres");
		chiffresUncipher.setBackground(secondaryColor);
		chiffresUncipher.setForeground(textColor);
		
		fromFileUncipher = new JButton();
		
		fileNameUncipher= new JLabel("");
		fileNameUncipher.setForeground(textColor);

		fromFileUncipher = new JButton();
		fromFileUncipher.setPreferredSize(new Dimension(30, 30));
		fromFileUncipher.setBackground(secondaryColor);
		fromFileUncipher.setBorder(null);
		fromFileUncipher.setIcon(new ImageIcon("src/ressources/icons/file30.png"));
		
		removeFileButtonUncipher = new JButton();
		removeFileButtonUncipher.setPreferredSize(new Dimension(30, 30));
		removeFileButtonUncipher.setBackground(secondaryColor);
		removeFileButtonUncipher.setBorder(null);
		removeFileButtonUncipher.setIcon(new ImageIcon("src/ressources/icons/cross30.png"));
		removeFileButtonUncipher.setVisible(false);
	}

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
				JLabel x = new JLabel("Déchiffrement");
				x.setForeground(textColor);
				q.add(x);
				q.add(uncipherInput);
				q.add(fromFileUncipher);
			}
			q.setBackground(secondaryColor);
			p.setBackground(secondaryColor);
			p.add(q, BorderLayout.NORTH);
			q = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{
				q.add(fileNameUncipher);
				q.add(removeFileButtonUncipher);	
			}
			q.setBackground(secondaryColor);
			p.add(q,BorderLayout.CENTER);
			
		workInterface.add(p, BorderLayout.NORTH);
		}
		
		p = new JPanel();
		p.setLayout(new GridLayout(0, 1, 0, 0));
		{
			p.add(buttonGoUncipher);
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
						s.add(fromKeyUncipher);
						s.add(fromKeyInputUncipher);
						s.add(fromPermsUncipher);
						
					}
					s.setBackground(secondaryColor);
					r.setBackground(secondaryColor);
					r.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					r.add(s);
				}
				LineBorder b = new LineBorder(textColor);
				TitledBorder b1 = BorderFactory.createTitledBorder(b, "Source du déchiffrement",0, 0, null, textColor);
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
						s.add(majusculesUncipher);
						s.add(minusculesUncipher);
						s.add(chiffresUncipher);
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
				q.add(uncipherResult);
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
	public void createController() {
		fromKeyUncipher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshRadioButtonsUncipher();
			}
			
		});
		
		fromPermsUncipher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshRadioButtonsUncipher();
			}
			
		});
		
		removeFileButtonUncipher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileNameUncipher.setText("");
				uncipherInput.setEnabled(true);
				removeFileButtonUncipher.setVisible(false);
				//model.setFile();
				
			}
			
		});
		
		
		fromFileUncipher.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = selectLoadFile();
				if(f != null) 
				{
					fileNameUncipher.setText(f.getName());
					fileNameUncipher.setForeground(textColor);
					uncipherInput.setText("");
					uncipherInput.setEnabled(false);
					removeFileButtonUncipher.setVisible(true);
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
				fromFileUncipher.setBackground(effectColor);				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				fromFileUncipher.setBackground(secondaryColor);
				
			}
		});
		
		
		
		uncipherInput.addMouseListener(new MouseListener() {
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
				if(uncipherInput.isEnabled()) {
					uncipherInput.setBackground(effectColor);
				}
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				uncipherInput.setBackground(secondaryColor);
			}
		});
		
		fromKeyInputUncipher.addMouseListener(new MouseListener() {
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
				if(fromKeyInputUncipher.isEnabled()) {
					fromKeyInputUncipher.setBackground(effectColor);
				}
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				fromKeyInputUncipher.setBackground(secondaryColor);
				
			}
		});
		
		buttonGoUncipher.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				String message = uncipherInput.getText();
				
				if((message == null || message.isBlank()) &&!model.isFileSelected()) {
					displayError("Veuillez insérer un message à chiffrer ou choisir un fichier");
					return;
				}
				if(!(fromKeyUncipher.isSelected() || fromPermsUncipher.isSelected())){
					displayError("Veuillez choisir une source de chiffrement");
					return;
				}
				
				try {
					model.setAlphabet(minusculesUncipher.isSelected(),majusculesUncipher.isSelected(),chiffresUncipher.isSelected());
				}catch(AssertionError exc) {
					displayError("Veuillez selectionner au moins un alphabet");
					return;
				}
				
				String key = fromKeyInputUncipher.getText();

					
				if(fromKeyUncipher.isSelected()) {
					if(key == null || key.isBlank()){
						displayError("Veuillez selectionner une clé");
						return;
					}
					model.setSeed(key);
				}
				
				if(fromPermsUncipher.isSelected()) {
					
				}
				
				model.setAlphabet(minusculesUncipher.isSelected(),majusculesUncipher.isSelected(),chiffresUncipher.isSelected());
				
				if(model.isFileSelected()) {
					try {
						model.getCypheredFromFile();
						message = model.getCypheredMessage();
					} catch (IOException e1) {
						displayError("Le fichier contient des charactères non pris en charge");
					}
				}
				model.setCypheredMessage(message);
				model.encoder_message(Cipher.DECHIFFREMENT);
				
				fmt = new Formatter();
				fmt.setSeed(model.getSeed());
				fmt.setKey(model.getKey().toString());
				fmt.setResult(model.getClearMessage());
				
				FontMetrics fm =uncipherResult.getFontMetrics(uncipherResult.getFont());
				
				fmt.setScreenSize(uncipherResult.getWidth()/fm.charWidth('0'));
				
				AfficheurEnTempsReel af = new AfficheurEnTempsReel(fmt.getFormated(),uncipherResult);
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
				buttonGoUncipher.setBackground(effectColor);
				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				buttonGoUncipher.setBackground(secondaryColor);
			}
		});
		
	
		
	}

	@Override
	void createModel() {
		model = new Cipher();
		
	}
	
	private void refreshRadioButtonsUncipher() {
		if(fromKeyUncipher.isSelected()) {
			fromKeyInputUncipher.setEnabled(true);
		}else {
			fromKeyInputUncipher.setEnabled(false);
			fromKeyInputUncipher.setText("");
			
		}
	}

	
}
