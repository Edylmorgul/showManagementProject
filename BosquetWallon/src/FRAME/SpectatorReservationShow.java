package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Representation;
import POJO.Show;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class SpectatorReservationShow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Representation> jListRepresentation;
	private DefaultListModel<Representation> listModelRep = new DefaultListModel<>();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SpectatorReservationShow(Spectator cli, Show show) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Reservation");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 585, 50);
		contentPane.add(titleLabel);		
				
		// Sous titre
		JLabel subTitleLabel = new JLabel("Veuillez choisir une repr\u00E9sentation :");
		subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTitleLabel.setBounds(165, 65, 250, 20);
		contentPane.add(subTitleLabel);
		
		// Contenu global
		
		// Afficher representation
		displayRepresentationFrame(show);
		
		// Choisir representation
		btnTakeRepresentationFrame(cli, show);
		
		// Retour menu client
		btnBackClientFrame(cli);		
	}
	
	private void displayRepresentationFrame(Show show) {
		show.getListRepresentationByShow();
		jListRepresentation = new JList<>();
		
		for(Representation rep : show.getRepresentationList())
			listModelRep.addElement(rep);
		
		jListRepresentation.setVisibleRowCount(3);
		jListRepresentation.setModel(listModelRep);
		jListRepresentation.setBounds(50, 300, 150, 100);
        
		scrollPane = new JScrollPane(jListRepresentation, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(41, 110, 500, 130);
		contentPane.add(scrollPane);   
	}
	
	private void btnTakeRepresentationFrame(Spectator cli, Show show) {
		JButton btnRepresentation = new JButton("Choisir");
		btnRepresentation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jListRepresentation.getSelectedValue() == null) 
            		JOptionPane.showMessageDialog(null, "Veuillez choisir une representation !");   
				else {
					Representation representation = jListRepresentation.getSelectedValue();					
					SpectatorChooseCategory frame = new SpectatorChooseCategory(cli, show, representation);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);  
				    dispose();
				}				
			}
		});
		btnRepresentation.setBounds(230, 320, 120, 40);
		contentPane.add(btnRepresentation);
	}
	
	private void btnBackClientFrame(Spectator cli) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpectatorAction frame = new SpectatorAction(cli);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnBack.setBounds(230, 370, 120, 40);
		contentPane.add(btnBack);
	}
}
