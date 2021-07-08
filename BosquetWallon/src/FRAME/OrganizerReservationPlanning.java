package FRAME;

import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.Booking;
import POJO.Organizer;
import POJO.Planning;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Color;

public class OrganizerReservationPlanning extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Planning> listModel = new DefaultListModel<>();
	private JList<Planning> listDateAvailable;
	private List<Planning> listPlan = new LinkedList<>();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OrganizerReservationPlanning(Organizer param) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Reservation salle");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 685, 50);
		contentPane.add(titleLabel);
		
		// Sous titre
		JLabel subTitelLabel = new JLabel("Veuillez choisir vos dates de reservation : ");
		subTitelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subTitelLabel.setBounds(200, 60, 300, 30);
		contentPane.add(subTitelLabel);
		
		// Contenu global
		JLabel lblNewLabel = new JLabel("Frais garantie de d\u00E9gats : 4000 euros");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 100, 250, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Frais garantie de reservation : 1000 euros");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(10, 120, 250, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tarif dégréssif en cas de plusieurs jours");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(10, 140, 250, 20);
		contentPane.add(lblNewLabel_2);
		
		// Affichage liste des dates disponibles
		displayDateFrame(param);
				     
        // Buton retour vers menu
        btnBackOrganizerFrame(param); 
	}
	
	private void displayDateFrame(Organizer param) {
		listDateAvailable = new JList<>();
        List<Planning> listPlan = new LinkedList<Planning>();
        listPlan = Planning.getDateAvailable();
        
        if(listPlan.isEmpty()) {
        	JLabel lblNewLabel_5 = new JLabel("Il n'y pas de dates disponibles actuellement !");
            lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
    		lblNewLabel_5.setBounds(0, 200, 685, 45);
    		contentPane.add(lblNewLabel_5);
    		btnAddReservationFrame(param, false);
    		btnValidateReservationFrame(param,false);
        }
        
        else {  
        	btnAddReservationFrame(param,true);
        	btnValidateReservationFrame(param,true);
        	
        	for (Planning plan : listPlan) {
        		plan.rentalPrice();
        		listModel.addElement(plan);
        	}
                          
            listDateAvailable.setModel(listModel);
            listDateAvailable.setBounds(49, 170, 338, 150);
            
            scrollPane = new JScrollPane(listDateAvailable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBounds(110, 200, 470, 180);
            contentPane.add(scrollPane);
        }
	}
	
	private void btnAddReservationFrame(Organizer param, boolean visible) {
		JButton btnReservation = new JButton("Reserver");
		btnReservation.setVisible(visible);
        btnReservation.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {   
            	if(listDateAvailable.getSelectedValue() == null) 
            		JOptionPane.showMessageDialog(null, "Veuillez choisir une date à ajouter !");           	           	
            	else {
            		Planning plan = listDateAvailable.getSelectedValue(); 
            		if(plan.checkPlanning(listPlan,plan))
                		JOptionPane.showMessageDialog(null, "Vous avez déjà reservé cette date !"); 
                	else {
                		listPlan.add(plan);   
                    	JOptionPane.showMessageDialog(null, "Souhaitez-vous ajouter une autre date ?"); 
                	}           		        		
            	}                   
            }
        });
        btnReservation.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReservation.setBounds(290, 390, 120, 40);
        contentPane.add(btnReservation);
	}
	
	private void btnValidateReservationFrame(Organizer param, boolean visible) {
		JButton btnContinue = new JButton("Continuer");
		btnContinue.setVisible(visible);
        btnContinue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {   
            	if(listDateAvailable.getSelectedValue() == null) 
            		JOptionPane.showMessageDialog(null, "Veuillez choisir une date avant de continuer !"); 
            	else if(listPlan.isEmpty())
            		JOptionPane.showMessageDialog(null, "Veuillez réserver une date avant de continuer !");
            	else {
            		Booking res = new Booking(5000,1, param);
            		res.setOrganizer(param);
            		res.setPlanningList(listPlan);
            		res.calculateTotalPrice();
                    OrganizerSummaryReservation frame = new OrganizerSummaryReservation(res, param);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    dispose();              
            	}                   
            }
        });
        btnContinue.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnContinue.setBounds(290, 440, 120, 40);
        contentPane.add(btnContinue);
	}
	
	private void btnBackOrganizerFrame(Organizer param) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrganizerAction frame = new OrganizerAction(param);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnBack.setBounds(290, 490, 120, 40);
		contentPane.add(btnBack);
	}
}
