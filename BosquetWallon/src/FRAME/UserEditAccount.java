package FRAME;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Global;
import POJO.Organizer;
import POJO.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class UserEditAccount extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nameField;
	private JTextField firstNameField;
	private JTextField phoneField;
	private JTextField passwordField;
	private JCheckBox deleteAccountCheckBox;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UserEditAccount(Person param) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Edition de compte");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
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
				
		JLabel lblNewLabel_5 = new JLabel("Mot de passe : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(300, 100, 90, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Supprimer compte :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(300, 140, 120, 20);
		contentPane.add(lblNewLabel_3);
				
		nameField = new JTextField();
		nameField.setBounds(140, 100, 90, 20);
		nameField.setText(param.getName());
		contentPane.add(nameField);
		nameField.setColumns(10);
				
		firstNameField = new JTextField();
		firstNameField.setBounds(140, 140, 90, 20);
		firstNameField.setText(param.getFirstName());
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);
				
		phoneField = new JTextField();
		phoneField.setToolTipText("Ex : 0494 64 78 74");
		phoneField.setBounds(140, 180, 90, 20);
		phoneField.setText(param.getPhoneNumber());
		contentPane.add(phoneField);
		phoneField.setColumns(10);
				
		passwordField = new JTextField();
		passwordField.setToolTipText("Ex : Billy123");
		passwordField.setText(param.getPassword());
		passwordField.setBounds(400, 100, 90, 20);
		contentPane.add(passwordField);
		
		deleteAccountCheckBox = new JCheckBox("");
		deleteAccountCheckBox.setBounds(420, 140, 30, 20);
		contentPane.add(deleteAccountCheckBox);
		
		// Editer compte
		editAccountFormFrame(param);
		
		// Retour
		btnBackActionFrame(param);		
	}
	
	private void editAccountFormFrame(Person param) {
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deleteAccountCheckBox.isSelected() == true) {
					if(param.delete()) {
						JOptionPane.showMessageDialog(null, "Suppression effectuée !");
						UserMainMenu frame = new UserMainMenu();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);  
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Suppression échouée !");
				}
				
				else {
					if(nameField.getText().isEmpty() || firstNameField.getText().isEmpty() || phoneField.getText().isEmpty() || passwordField.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs !");	
					else if(!nameField.getText().matches(Global.getLetterPattern()) || !firstNameField.getText().matches(Global.getLetterPattern()))
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nom et prénom correct !");
					else if(!phoneField.getText().matches(Global.getPhonePattern()))
						JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de téléphone valide !");
					else if(!passwordField.getText().matches(Global.getPasswordPattern()))						
						JOptionPane.showMessageDialog(null, "Veuillez entrer un mot de passe correct ! (Minuscule + majuscule + nombre + 4 caractères minimums)");
					else {
						 param.setName(nameField.getText());		
						 param.setFirstName(firstNameField.getText());
						 param.setPhoneNumber(phoneField.getText());
						 param.setPassword(passwordField.getText());
						 
						 if(param.update()) {
							JOptionPane.showMessageDialog(null, "Mise à jour de vos informations effectuées !");
							
							if(param instanceof Spectator) {
								SpectatorAction frame = new SpectatorAction((Spectator) param);
								frame.setLocationRelativeTo(null);
								frame.setVisible(true);  
								dispose();
							}
							
							else {
								OrganizerAction frame = new OrganizerAction((Organizer) param);
								frame.setLocationRelativeTo(null);
								frame.setVisible(true);  
								dispose();
							}						
						 }
						 else
							 JOptionPane.showMessageDialog(null, "Edition de compte échouée !");
					}	
				}																					
			}
		});
		btnValidate.setBounds(380, 290, 120, 40);
		contentPane.add(btnValidate);
	}
	
	private void btnBackActionFrame(Person param) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(param instanceof Spectator) {
					SpectatorAction frame = new SpectatorAction((Spectator) param);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}
				
				else {
					OrganizerAction frame = new OrganizerAction((Organizer) param);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);  
					dispose();
				}				
			}
		});
		btnBack.setBounds(90, 290, 120, 40);
		contentPane.add(btnBack);
	}
}
