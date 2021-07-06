package FRAME;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Gestionnaire;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;


public class ManagerAction extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu menuOption; 
	private JMenuItem op1; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public ManagerAction(Gestionnaire param) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Administration");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 580, 50);
		contentPane.add(titleLabel);
		
		// Sous titre
		JLabel subTitelLabel = new JLabel("Que voulez-vous faire ?");
		subTitelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subTitelLabel.setBounds(215, 70, 170, 30);
		contentPane.add(subTitelLabel);
		
		// Contenu global
		JLabel lblNewLabel_1 = new JLabel("Ins\u00E9rer date : ");
		lblNewLabel_1.setBounds(100, 130, 90, 20);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser dateDebut = new JDateChooser();
		dateDebut.setBounds(230, 120, 120, 40);
		contentPane.add(dateDebut);
		
		// Options manager
		optionFrame();
		
		// Création du planning date
		btnCreatePlanningFrame(dateDebut, param);
		
		// Afficher spectacle
		btnDisplayShowFrame(param);
		
		// Afficher les utilisateurs
		btnDisplayUserFrame(param);
		
		// Quitter programme
		btnExitFrame();    
	}
	
	private void optionFrame() {
		JMenuBar optionBar = new JMenuBar();  
        menuOption = new JMenu("Options"); 
        
        // Deconnexion
        op1 = new JMenuItem("Deconnexion");      
        op1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        op1.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent evt){
        		UserMainMenu frame = new UserMainMenu();				
  				frame.setLocationRelativeTo(null);
  				frame.setVisible(true);  
  				dispose();     	 
  			}
        });
				
		 menuOption.add(op1);
        
        optionBar.add(menuOption);
        contentPane.add(optionBar);
        optionBar.setBounds(0, 0, 50, 20);
        contentPane.setSize(400, 400);
        contentPane.setLayout(null);
        contentPane.setVisible(true);
  
	}
	
	private void btnDisplayUserFrame(Gestionnaire param) {
		JButton btnUser = new JButton("Utilisateurs");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerDisplayUser frame = new ManagerDisplayUser(param);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnUser.setBounds(230, 170, 120, 40);
		contentPane.add(btnUser);
	}
	
	private void btnDisplayShowFrame(Gestionnaire param) {
		JButton btnSpect = new JButton("Spectacles");
		btnSpect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSpect.setBounds(230, 220, 120, 40);
		contentPane.add(btnSpect);
	}
	
	private void btnExitFrame() {
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(230, 270, 120, 40);
		contentPane.add(btnExit);
	}	
}
