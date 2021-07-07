package FRAME;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SpectatorAction extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu menuOption; 
	private JMenuItem op1, op2; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SpectatorAction(Spectator param) {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Information du client
		initFrame(param);
		
		// Titre
		JLabel smallTitleLabel = new JLabel("Bonjour :");
		smallTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		smallTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		smallTitleLabel.setBounds(180, 15, 100, 30);
		contentPane.add(smallTitleLabel);
		
		// Sous titre
		JLabel subTitelLabel = new JLabel("Que voulez-vous faire ?");
		subTitelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subTitelLabel.setBounds(200, 80, 170, 30);
		contentPane.add(subTitelLabel);
		
		// Contenu global
		
		// Option client
		optionFrame(param);
		
		// Afficher spectacles
		btnDisplayShowFrame(param);
		
		// Affcher spectacles reservés
		btnDisplayReservationFrame(param);
		
		// Quitter programme
		btnExitFrame();
				
	}
	
	private void initFrame(Spectator param) {
		JLabel nameClientLabel = new JLabel(param.getName() + " - " + param.getFirstName());
		nameClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameClientLabel.setBounds(280, 15, 140, 30);
		contentPane.add(nameClientLabel);
	}
	
	private void optionFrame(Spectator param) {
		JMenuBar optionBar = new JMenuBar();  
        menuOption = new JMenu("Options"); 
        
        // Edition
        op1 = new JMenuItem("Editer");  
        op1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        op1.addActionListener(new ActionListener(){ // JMenuItem ==> Utilisation d'un ActionListener !!!
        	  public void actionPerformed(ActionEvent evt){
        		UserEditAccount frame = new UserEditAccount(param); 
          		frame.setLocationRelativeTo(null);
       			frame.setVisible(true);  
       			dispose();      
  			}
        });
        
        // Deconnexion
        op2 = new JMenuItem("Deconnexion");      
        op2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        op2.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent evt){
        		UserMainMenu frame = new UserMainMenu();				
  				frame.setLocationRelativeTo(null);
  				frame.setVisible(true);  
  				dispose();     	 
  			}
        });
				
		 menuOption.add(op1);
	     menuOption.add(op2);
        
        optionBar.add(menuOption);
        contentPane.add(optionBar);
        optionBar.setBounds(0, 0, 50, 20);
        contentPane.setSize(400, 400);
        contentPane.setLayout(null);
        contentPane.setVisible(true);  
	}
	
	private void btnDisplayShowFrame(Spectator cli) {
		JButton btnDispalyShow = new JButton("Spectacles");
		btnDispalyShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDispalyShow.setBounds(230, 140, 120, 40);
		contentPane.add(btnDispalyShow);
	}
	
	private void btnDisplayReservationFrame(Spectator cli) {
		JButton btnDisplayReservation = new JButton("Commandes");
		btnDisplayReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDisplayReservation.setBounds(230, 190, 120, 40);
		contentPane.add(btnDisplayReservation);
	}
	
	private void btnExitFrame() {
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(230, 240, 120, 40);
		contentPane.add(btnExit);
	}
}
