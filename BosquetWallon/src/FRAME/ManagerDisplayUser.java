package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Manager;
import POJO.Organizer;

public class ManagerDisplayUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Spectator> listClient;
	private List<Organizer> listOrganizer;
	private JList<Spectator> listObjClient;
	private JList<Organizer> listObjOrganizer;
	private DefaultListModel<Spectator> listModelClient = new DefaultListModel<>();
	private DefaultListModel<Organizer> listModelOrganizer = new DefaultListModel<>();
	private JScrollPane scrollPaneClient;
	private JScrollPane scrollPaneOrganizer;
	private String[] typeUserTab = {"Client", "Organisateur"};
	private JComboBox<String> typeUserCombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public ManagerDisplayUser(Manager param) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Gestion des utilisateurs");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 785, 50);
		contentPane.add(titleLabel);
		
		// Sous titre
		JLabel lblNewLabel_1 = new JLabel("Type compte :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(280, 80, 90, 20);
		contentPane.add(lblNewLabel_1);
		
		// Contenu global
		typeUserCombo = new JComboBox(typeUserTab);
		typeUserCombo.setBounds(400, 80, 90, 20);
		contentPane.add(typeUserCombo);
		
		// Afficher utilisateur choisi
		displayUserFrame();
		
		// => On verra plus tard pour d'autres options à ajouter à l'admin mais pas important
		
		// Retour
		btnBackManagerFrame(param);
	}
	
	private void displayUserFrame() {
		displayClientFrame();
		displayOrganizerFrame();
		
		typeUserCombo.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent e){
	        	if(e.getItem().equals("Client")) {  
	        		scrollPaneClient.setVisible(true);
	        		scrollPaneOrganizer.setVisible(false);
	        	}	        			
	        	else {
	        		scrollPaneClient.setVisible(false);
	        		scrollPaneOrganizer.setVisible(true);
	        	}	        			
	        }        	
	    });
	}
	
	private void displayClientFrame() {		
		listObjClient = new JList<Spectator>();		
		listClient = new LinkedList<Spectator>();
		listModelClient.removeAllElements(); // Refresh manuelle ==> myJlist.updateUi ne fonctionne pas
        listClient = Spectator.getAll();  
        
        for (Spectator obj : listClient) {       	
            listModelClient.addElement(obj);
        }
        
        listObjClient.setVisibleRowCount(3);
        listObjClient.setModel(listModelClient);
        listObjClient.setBounds(98, 66, 285, 139);
        
        scrollPaneClient = new JScrollPane(listObjClient, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneClient.setBounds(50, 130, 690, 260);
        contentPane.add(scrollPaneClient);
	}
	
	private void displayOrganizerFrame() {
		listObjOrganizer = new JList<Organizer>();		
		listOrganizer = new LinkedList<Organizer>();
		listModelOrganizer.removeAllElements();
        listOrganizer = Organizer.getAll();                   
        
        for (Organizer obj : listOrganizer) {
            listModelOrganizer.addElement(obj);
        }
        
        listObjOrganizer.setVisibleRowCount(3);
        listObjOrganizer.setModel(listModelOrganizer);
        listObjOrganizer.setBounds(98, 66, 285, 139);
        
        scrollPaneOrganizer = new JScrollPane(listObjOrganizer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneOrganizer.setBounds(50, 130, 690, 260);
        contentPane.add(scrollPaneOrganizer);
	}
	
	private void btnBackManagerFrame(Manager param) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerAction frame = new ManagerAction(param);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);  
				dispose();
			}
		});
		btnBack.setBounds(340, 480, 120, 40);
		contentPane.add(btnBack);
	}
}
