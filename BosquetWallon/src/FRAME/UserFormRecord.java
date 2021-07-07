package FRAME;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Global;
import POJO.Organizer;
import POJO.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JComboBox;

public class UserFormRecord extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField firstNameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private String[] typeUserTab = {"Client", "Organisateur"};
	private String typeUser;
	private JComboBox<String> typeUserCombo;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UserFormRecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Formulaire");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 580, 50);
		contentPane.add(titleLabel);
		
		// Contenu global
		JLabel lblNewLabel_1 = new JLabel("Nom : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(40, 100, 90, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(40, 140, 90, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("T\u00E9l\u00E9phone : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(40, 180, 90, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Email :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(300, 100, 90, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Mot de passe : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(300, 140, 90, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Type compte :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(300, 180, 90, 20);
		contentPane.add(lblNewLabel_3);
		
		nameField = new JTextField();
		nameField.setBounds(140, 100, 90, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(140, 140, 90, 20);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setToolTipText("Ex : 0494 64 78 74");
		phoneField.setBounds(140, 180, 90, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(400, 100, 90, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Ex : Billy123");
		passwordField.setBounds(400, 140, 90, 20);
		contentPane.add(passwordField);
		
		typeUserCombo = new JComboBox(typeUserTab);
		typeUserCombo.setBounds(400, 180, 90, 20);
		contentPane.add(typeUserCombo);
		
		// Valider l'enregistrement en tant que client ou organisateur (Gestionnaire ==> Admin, présent en dur dans la database)
		registerFormFrame();
	    
	    // Bouton retour vers menu
	    btnBackMenuFrame();
	}
	
	private void registerFormFrame() {
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().isEmpty() || firstNameField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");	
				else if(!nameField.getText().matches(Global.getLetterPattern()) || !firstNameField.getText().matches(Global.getLetterPattern()))
					JOptionPane.showMessageDialog(null, "Veuillez entrer un nom et prénom correct !");
				else if(!phoneField.getText().matches(Global.getPhonePattern()))
					JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de téléphone valide !");
				else if(!emailField.getText().matches(Global.getEmailPattern()))
					JOptionPane.showMessageDialog(null, "Veuillez entrer un email correct !");
				else if(!passwordField.getText().matches(Global.getPasswordPattern()))						
					JOptionPane.showMessageDialog(null, "Veuillez entrer un mot de passe correct ! (Minuscule + majuscule + nombre + 4 caractères minimums)");
				else if(Person.checkEmail(emailField.getText())) {
					JOptionPane.showMessageDialog(null, "Cet email est déjà présent !");
				}
				else {
					typeUser = typeUserCombo.getSelectedItem().toString();			
					if(typeUser.equals("Client")) {
						Spectator cli = new Spectator(nameField.getText(), firstNameField.getText(), phoneField.getText(), emailField.getText(), passwordField.getText());
						if(cli.create()) {
							JOptionPane.showMessageDialog(null, "Enregistrement réussi !");
							UserMainMenu frame = new UserMainMenu();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							dispose();
						}
					}
					
					else if(typeUser.equals("Organisateur")) {
						Organizer org = new Organizer(nameField.getText(), firstNameField.getText(), phoneField.getText(), emailField.getText(), passwordField.getText());
						if(org.create()) {
							JOptionPane.showMessageDialog(null, "Enregistrement réussi !");
							UserMainMenu frame = new UserMainMenu();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							dispose();
						}
					}
										
					else
						JOptionPane.showMessageDialog(null, "Enregistrement échoué !");					
				}																		
			}
		});
		btnValidate.setBounds(380, 290, 120, 40);
		contentPane.add(btnValidate);
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
		btnBack.setBounds(90, 290, 120, 40);
		contentPane.add(btnBack);
	}
}
