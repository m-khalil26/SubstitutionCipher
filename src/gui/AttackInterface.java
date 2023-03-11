package gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AttackInterface extends AbstractInterface {

	
	private JTextField input;
	private JButton  fromFile;
	private JLabel fileName;
	private JButton removeFileButton;
	private JButton buttonGoAttack; 
	private Chart chartModifiable;
	private Chart chartDefault;
	
	
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
				JLabel x = new JLabel("Analyse");
				x.setForeground(textColor);
				q.add(x);
				q.add(input);
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
			p.add(buttonGoAttack);
		}
		p.setBackground(secondaryColor);
		workInterface.add(p, BorderLayout.SOUTH);
		
		p = new JPanel(new GridLayout(2,1));
		{
			JPanel q = new JPanel(new GridLayout(2,1));
			{
				q.add(chartModifiable);
			}
			p.add(q);
			q = new JPanel();
			q.setLayout(new GridLayout(0, 1, 0, 0));
			{
				q.add(chartDefault);
			}
			q.setBackground(secondaryColor);
			p.add(q);
		}
		p.setBackground(secondaryColor);
		workInterface.add(p);
		
	}

	@Override
	protected void createView() {
workInterface= new JPanel();
		
		buttonGoAttack=  new JButton("Déchiffrer");
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
		
		chartDefault= new Chart();
		chartDefault.setBackground(secondaryColor);
		
		chartModifiable= new Chart();
		chartModifiable.setBackground(secondaryColor);
		
	}

	@Override
	void createController() {

		removeFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileName.setText("");
				input.setEnabled(true);
				removeFileButton.setVisible(false);
				
			}
			
		});
		
		
		fromFile.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File f = selectLoadFile();
				if(f != null) 
				{
					fromFile.setText(f.getName());
					fromFile.setForeground(textColor);
					input.setText("");
					input.setEnabled(false);
					removeFileButton.setVisible(true);
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
				fromFile.setBackground(effectColor);				
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				fromFile.setBackground(secondaryColor);
				
			}
		});
		
		
		
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
		
	buttonGoAttack.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				  EventQueue.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new CustomEvent(this));
	                    }
	                });
				
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
		
		
	}

	@Override
	void createModel() {
		// TODO Auto-generated method stub
		
	}
	
	public class CustomEvent extends AWTEvent {
	    public static final int CUSTOM_EVENT_TYPE = AWTEvent.RESERVED_ID_MAX + 1;

	    public CustomEvent(Object source) {
	        super(source, CUSTOM_EVENT_TYPE);
	    }
	}

}
