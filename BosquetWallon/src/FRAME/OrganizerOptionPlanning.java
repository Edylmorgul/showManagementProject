package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Organizer;
import POJO.Planning;
import POJO.Booking;

public class OrganizerOptionPlanning extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Planning> listPlan;
	private DefaultListModel<Planning> listModel = new DefaultListModel<>();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OrganizerOptionPlanning(Organizer org, Booking res) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Gestion planning");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setBounds(0, 0, 580, 50);
		contentPane.add(titleLabel);
		
		// Afficher les dates de reservation
		displayPlanningFrame(res);
		
		// Bouton vers creation spectacle
		btnCreateShowFrame(org, res);
		
		// Retour
		btnBackOrganizerFrame(org);
	}
	
	private void displayPlanningFrame(Booking res) {		
		listPlan = new JList<>();
		res.getListPlanningByReservation();
		for (Planning plan : res.getPlanningList())
			listModel.addElement(plan);  
			
	     listPlan.setVisibleRowCount(3);
	     listPlan.setModel(listModel);
	     listPlan.setBounds(98, 66, 285, 139);
	        
	     scrollPane = new JScrollPane(listPlan, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     scrollPane.setBounds(28, 85, 532, 217);
	     contentPane.add(scrollPane);
	}
	
	private void btnCreateShowFrame(Organizer org, Booking res) {
		JButton btnCreate = new JButton("Creer spectacle");
        btnCreate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {   
            	Planning plan = listPlan.getSelectedValue();
            	if(plan == null) 
            		JOptionPane.showMessageDialog(null, "Veuillez choisir une date avant de continuer !");
            	else if(plan.getShow().getId()!= 0)
            		JOptionPane.showMessageDialog(null, "Un spectacle est déjà présent pour cette date !");
            	else {
                                    
            	}                   
            }
        });
        btnCreate.setBounds(240, 430, 120, 40);
        contentPane.add(btnCreate);
	}
	
	private void btnBackOrganizerFrame(Organizer org) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrganizerAction frame = new OrganizerAction(org);
					  frame.setLocationRelativeTo(null);
					  frame.setVisible(true);  
					  dispose();
			}
		});
		btnBack.setBounds(240, 480, 120, 40);
		contentPane.add(btnBack);
	}
}
