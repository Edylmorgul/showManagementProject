package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Organizer;
import POJO.Planning;
import POJO.Booking;
import java.awt.Color;

public class OrganizerSummaryReservation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbtnPayAdvancePayment;
	private JRadioButton rdbtnPayTotalPrice;
	private JRadioButton rdbtnBancontact;
	private JRadioButton rdbtnPaypal;
	private JRadioButton rdbtnVisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OrganizerSummaryReservation(Booking reservation, Organizer org) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Confirmation reservation");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 785, 50);
		contentPane.add(titleLabel);
		
		// Sous titre
		JLabel subTitelLabel = new JLabel("Rappel :");
		subTitelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTitelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subTitelLabel.setBounds(300, 60, 170, 30);
		contentPane.add(subTitelLabel);
		
		// Contenu global		
		JLabel lblNewLabel = new JLabel("Acompte : " + reservation.getDeposit());
		lblNewLabel.setBounds(40, 120, 150, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Afin de valider la reservation, veuillez payer l'acompte ou la totalit\u00E9 : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(300, 120, 400, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel2 = new JLabel("Location salle : " + reservation.getPrice());
		lblNewLabel2.setBounds(40, 160, 150, 20);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("Total à payer : " + reservation.getBalance());
		lblNewLabel3.setBounds(40, 200, 150, 20);
		contentPane.add(lblNewLabel3);
		
		JLabel lblNewLabel_2 = new JLabel("Comment voulez-vous payer ?");
		lblNewLabel_2.setBounds(330, 260, 164, 20);
		contentPane.add(lblNewLabel_2);
		
		rdbtnPayAdvancePayment = new JRadioButton("Payer acompte");
		rdbtnPayAdvancePayment.setSelected(true);
		rdbtnPayAdvancePayment.setBounds(470, 160, 120, 25);
		contentPane.add(rdbtnPayAdvancePayment);
		
		rdbtnPayTotalPrice = new JRadioButton("Payer totalit\u00E9");
		rdbtnPayTotalPrice.setBounds(470, 200, 120, 25);
		contentPane.add(rdbtnPayTotalPrice);
		
		rdbtnBancontact = new JRadioButton("Bancontact");
		rdbtnBancontact.setSelected(true);
		rdbtnBancontact.setBounds(350, 300, 109, 23);
		contentPane.add(rdbtnBancontact);
		
		rdbtnPaypal = new JRadioButton("Paypal");
		rdbtnPaypal.setBounds(350, 330, 109, 23);
		contentPane.add(rdbtnPaypal);
		
		rdbtnVisa = new JRadioButton("Visa");
		rdbtnVisa.setBounds(350, 360, 109, 23);
		contentPane.add(rdbtnVisa);
		
		//Group the radio buttons price method.
	    ButtonGroup groupPrice = new ButtonGroup();
	    groupPrice.add(rdbtnPayAdvancePayment);
	    groupPrice.add(rdbtnPayTotalPrice);
	    
	    //Group the radio buttons price method.
	    ButtonGroup groupMeansOfPayment = new ButtonGroup();
	    groupMeansOfPayment.add(rdbtnBancontact);
	    groupMeansOfPayment.add(rdbtnPaypal);
	    groupMeansOfPayment.add(rdbtnVisa);
	    
	    // Valider reservation
	 	btnValidateReservationFrame(org, reservation);
	 		
	 	// Retour
	 	btnCancelFrame(org);	
	}
	
	private void btnValidateReservationFrame(Organizer org, Booking res) {
		JButton btnBack = new JButton("Valider");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Acompte
				if(rdbtnPayAdvancePayment.isSelected()) {
					//res.setStatut(1);
					//res.calculateSolde(res.getAcompte(), "-");
										
					if(rdbtnBancontact.isSelected()) {
						res.setStatus(1);
						res.calculateBalance(res.getDeposit(), "-");
						createReservationFrame(org, res);
					}					
					else if(rdbtnPaypal.isSelected()) {
						res.setStatus(1);
						res.calculateBalance(res.getDeposit(), "-");
						createReservationFrame(org, res);
					}					
					else {
						res.setStatus(1);
						res.calculateBalance(res.getDeposit(), "-");
						createReservationFrame(org, res);
					}
				}									
				// Total
				else {
					//res.setStatut(2);
					//res.calculateSolde(res.getSolde(), "-");
					
					if(rdbtnBancontact.isSelected()) {
						res.setStatus(2);
						res.calculateBalance(res.getBalance(), "-");
						createReservationFrame(org, res);
					}					
					else if(rdbtnPaypal.isSelected()) {
						res.setStatus(2);
						res.calculateBalance(res.getBalance(), "-");
						createReservationFrame(org, res);
					}					
					else {
						res.setStatus(2);
						res.calculateBalance(res.getBalance(), "-");
						createReservationFrame(org, res);
					}
				}
			}
		});
		btnBack.setBounds(320, 430, 120, 40);
		contentPane.add(btnBack);
	}
	
	private void createReservationFrame(Organizer org, Booking res) {
		if(res.create()) {
			for(Planning plan : res.getPlanningList()) {
				plan.setReservation(res);
				plan.setAvailable(true);
				plan.update();
			}
			JOptionPane.showMessageDialog(null, "Reservation confirmée !");
			OrganizerAction frame = new OrganizerAction(org);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);  
			dispose();
		}
		else 
			JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de votre reservation !");	
	}
	
	private void btnCancelFrame(Organizer param) {
		JButton btnBack = new JButton("Annuler");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrganizerAction frame = new OrganizerAction(param);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnBack.setBounds(320, 480, 120, 40);
		contentPane.add(btnBack);
	}
}
