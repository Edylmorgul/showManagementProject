package FRAME;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Manager;
import POJO.Global;
import POJO.Organizer;
import POJO.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Connexion");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 580, 50);
		contentPane.add(titleLabel);
		
		// Contenu global
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(120, 100, 90, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(120, 150, 90, 20);
		contentPane.add(lblNewLabel_2);
		
		emailField = new JTextField();
		emailField.setBounds(310, 100, 90, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 150, 90, 20);
		contentPane.add(passwordField);
		
		// Connexion
		loginFrame();
		
		// Retour vers menu
		btnBackMenuFrame();			    	    
	}
	
	private void loginFrame() {	   		
		JButton btnValide = new JButton("Valider");
		btnValide.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(emailField.getText().isEmpty() || passwordField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");				
				else if(!emailField.getText().matches(Global.getEmailPattern()))
					JOptionPane.showMessageDialog(null, "Veuillez entrer un email correct !");
				else if(!passwordField.getText().matches(Global.getPasswordPattern()))						
					JOptionPane.showMessageDialog(null, "Veuillez entrer un mot de passe correct ! (Minuscule + majuscule + nombre + 4 caractères minimums)");
				else {
					Person pers = new Person();
					pers = pers.login(emailField.getText(), passwordField.getText());
					if(pers != null) {
						if(pers.checkTypeUser() instanceof Spectator) {
							Spectator spec = (Spectator) pers.checkTypeUser();
							SpectatorAction frame = new SpectatorAction(spec);				
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
						}					
						else if(pers.checkTypeUser() instanceof Organizer) {
							Organizer org = (Organizer) pers.checkTypeUser();
							OrganizerAction frame = new OrganizerAction(org);				
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
						}
						else {
							Manager manager = (Manager) pers.checkTypeUser();
							ManagerAction frame = new ManagerAction(manager);				
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
						}
					}					
					else
						JOptionPane.showMessageDialog(null, "Email ou mot de passe invalide !");	
				}
			}
		});
		btnValide.setBounds(230, 250, 120, 40);
		contentPane.add(btnValide);
	}
	
	private void btnBackMenuFrame() {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu frame = new UserMainMenu();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnBack.setBounds(230, 300, 120, 40);
		contentPane.add(btnBack);
	}
}
