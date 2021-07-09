package FRAME;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.Organizer;
import POJO.Planning;
import POJO.Booking;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class OrganizerDisplayReservation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Booking> listReservation;
	private DefaultListModel<Booking> listModel = new DefaultListModel<>();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OrganizerDisplayReservation(Organizer param) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Gestion de vos reservations");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 785, 50);
		contentPane.add(titleLabel);
		
		// Contenu global
		
		// Affichage des réservations
		displayReservationFrame(param);
		
		// Modifier spectacle ==> Option
		btnSelectReservationFrame(param);
		
		// Payer le restant de la reservation
		btnPayReservationFrame(param);
		
		// Supprimer reservation
		btnDeleteReservationFrame(param);
		
		// Retour
		btnBacOrganizerFrame(param);
	}
	
	private void displayReservationFrame(Organizer param) {
		
		listReservation = new JList<>();
		param.getListBookingByOrganizer();
		if(!param.getBookingList().isEmpty()) {
			for (Booking res : param.getBookingList())
				listModel.addElement(res);            
	        
	        listReservation.setModel(listModel);
	        listReservation.setBounds(45, 90, 690, 230);
	        
	        scrollPane = new JScrollPane(listReservation, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(45, 90, 690, 230);
	        contentPane.add(scrollPane);	        	    
		}
		
		else {
			JLabel lblNewLabel_5 = new JLabel("Vous n'avez aucune reservation !");
	        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_5.setBounds(0, 200, 785, 45);
			contentPane.add(lblNewLabel_5);
		}      
	}
	
	private void btnSelectReservationFrame(Organizer param) {
		JButton btnSelectReservation = new JButton("Choisir");
		btnSelectReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listReservation.getSelectedValue() == null) 
            		JOptionPane.showMessageDialog(null, "Veuillez choisir une reservation !");           	           	
            	else {
            		JOptionPane.showMessageDialog(null, "Choisi !");
            	}
			}
		});
		btnSelectReservation.setBounds(340, 330, 120, 40);
		contentPane.add(btnSelectReservation);
	}
	
	private void btnPayReservationFrame(Organizer param) {
		JButton btnPayReservation = new JButton("Payer");
		btnPayReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking res = listReservation.getSelectedValue();
				if(listReservation.getSelectedValue() == null) 
            		JOptionPane.showMessageDialog(null, "Veuillez choisir une reservation !");  
				else if(res.getBalance() == 0) 
					JOptionPane.showMessageDialog(null, "Totalité de la somme déjà payée !");				
            	else {            	
            		res.calculateBalance(res.getBalance(), "-");
            		if(res.update()) {
            			JOptionPane.showMessageDialog(null, "Transaction effectuée!"); 
            			listModel.clear(); // Refresh 
            			preventUpdateJlistFrame(param);
            		}            			
            		else
            			JOptionPane.showMessageDialog(null, "Transaction echouée !"); 
            	}
			}
		});
		btnPayReservation.setBounds(340, 380, 120, 40);
		contentPane.add(btnPayReservation);
	}
	
	private void btnDeleteReservationFrame(Organizer param) {
		JButton btnDeleteReservation = new JButton("Annuler");
		btnDeleteReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDeleteReservation.setBounds(340, 430, 120, 40);
		contentPane.add(btnDeleteReservation);
	}
	
	// Solution pour éviter la perte du focus après modification élément dans JLIST
	private void preventUpdateJlistFrame(Organizer param) {
		param.getListBookingByOrganizer();
		if(!param.getBookingList().isEmpty()) {
			for (Booking res : param.getBookingList())
				listModel.addElement(res);            
	        
	        listReservation.setModel(listModel);
	    }
		
		else {
			JLabel lblNewLabel_5 = new JLabel("Vous n'avez aucune reservation !");
	        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_5.setBounds(0, 200, 785, 45);
			contentPane.add(lblNewLabel_5);
		}  
	}
	
	private void btnBacOrganizerFrame(Organizer param) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrganizerAction frame = new OrganizerAction(param);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnBack.setBounds(340, 480, 120, 40);
		contentPane.add(btnBack);
	}
}
