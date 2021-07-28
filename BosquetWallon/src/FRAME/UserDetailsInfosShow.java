package FRAME;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.Artist;
import POJO.Representation;
import POJO.Show;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UserDetailsInfosShow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> listModelArt = new DefaultListModel<>();
	private DefaultListModel<Representation> listModelRep = new DefaultListModel<>();
	private JScrollPane scrollPane;
	private JList<String> jListArt;
	private JList<Representation> jListRepresentation;
	private JButton btnClose;
	private JLabel lblNewLabel_3;
	JLabel lblConfig;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UserDetailsInfosShow(Show show) {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("Informations compl\u00E9mentaires");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 664, 50);
		contentPane.add(lblNewLabel);
		initFrame(show);
		
		// Sous-titre
		JLabel lblNewLabel_1 = new JLabel("Artiste(s) : ");
		lblNewLabel_1.setBounds(80, 110, 90, 20);
		contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_5 = new JLabel("Configuration :");
        lblNewLabel_5.setBounds(445, 110, 90, 20);
        contentPane.add(lblNewLabel_5);   
        
        JLabel lblNewLabel_2 = new JLabel("Representation(s) :");
        lblNewLabel_2.setBounds(80, 280, 150, 20);
        contentPane.add(lblNewLabel_2);
        
        // Contenu global
        		
		// Affichage des artistes
		displayArtistFrame(show);
		
		// Affichage des representations
		displayRepresentationFrame(show);
		
		// Affichage de la configuration
		initConfigFrame(show);
		
		// Bouton fermer pop-up
		btnClosePopUpFrame();
	}
	
	private void initFrame(Show param) {	
		lblNewLabel_3 = new JLabel(param.toString());
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_3.setBounds(181, 50, 300, 30);
        contentPane.add(lblNewLabel_3);
	}
	
	private void initConfigFrame(Show param) {		
		lblConfig = new JLabel(param.getConfig().toString());		
		lblConfig.setBounds(350, 150, 275, 20);
        contentPane.add(lblConfig);
	}
	
	private void displayArtistFrame(Show param) {
		param.getListArtistByShow();
		jListArt = new JList<String>();		
        
		if(!param.getArtistList().isEmpty()) {
			for (Artist obj : param.getArtistList())
	            listModelArt.addElement(obj.getPseudo());
	        
	        jListArt.setVisibleRowCount(3);
	        jListArt.setModel(listModelArt);
	        jListArt.setBounds(50, 150, 150, 100);
	        
	        scrollPane = new JScrollPane(jListArt, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(50, 140, 150, 100);
	        contentPane.add(scrollPane);   
		}
		
		else {
			JLabel lblNewLabel_6 = new JLabel("Aucun artistes présents !");
	        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_6.setBounds(10, 135, 320, 45);
			contentPane.add(lblNewLabel_6);
		}                
	}
	
	private void displayRepresentationFrame(Show param) {
		param.getListRepresentationByShow();
		jListRepresentation = new JList<>();
		
		if(!param.getRepresentationList().isEmpty()) {
			for(Representation rep : param.getRepresentationList())
				listModelRep.addElement(rep);
		
			jListRepresentation.setVisibleRowCount(3);
			jListRepresentation.setModel(listModelRep);
			jListRepresentation.setBounds(50, 300, 150, 100);
        
			scrollPane = new JScrollPane(jListRepresentation, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(50, 310, 500, 130);
			contentPane.add(scrollPane);   
		}

		else {
			JLabel lblNewLabel_7 = new JLabel("Aucune représentations présentes !");
	        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_7.setBounds(30, 360, 610, 45);
			contentPane.add(lblNewLabel_7);
		}
	}
	
	private void btnClosePopUpFrame() {
		btnClose = new JButton("Fermer");
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose(); // Ferme la frame actuelle ==> Pop-up
        	}
        });
        btnClose.setBounds(280, 490, 120, 40);
        contentPane.add(btnClose);                 
	}
}
