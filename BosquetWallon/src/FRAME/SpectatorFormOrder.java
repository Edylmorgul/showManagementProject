package FRAME;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Order;
import POJO.Ticket;
import POJO.Representation;
import POJO.Show;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SpectatorFormOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String modePayement;
	private String modeLivraison;
	private JRadioButton rdbtnPaypal;
	private JRadioButton rdbtnVisa;
	private JRadioButton rdbtnSepa;
	private JRadioButton rdbtnTakeOnPlace;
	private JRadioButton rdbtnDelivery;
	private JRadioButton rdbtnSecureDelivery;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SpectatorFormOrder(Spectator cli, Show show, Representation rep, JSpinner[] tabSpinner) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("Commande");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 740, 50);
		contentPane.add(lblNewLabel);
		
		// Sous-titre
		JLabel lblNewLabel_1 = new JLabel("Mode paiement : ");
		lblNewLabel_1.setBounds(190, 100, 130, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mode de livraison : ");
		lblNewLabel_2.setBounds(400, 100, 130, 20);
		contentPane.add(lblNewLabel_2);
		
		// Contenu global
		rdbtnVisa = new JRadioButton("Visa");
		rdbtnVisa.setSelected(true);
		rdbtnVisa.setBounds(190, 140, 120, 20);
		contentPane.add(rdbtnVisa);
		
		rdbtnPaypal = new JRadioButton("Paypal");
		rdbtnPaypal.setBounds(190, 180, 120, 20);
		contentPane.add(rdbtnPaypal);
		
		rdbtnSepa = new JRadioButton("Vir.SEPA");
		rdbtnSepa.setBounds(190, 220, 120, 20);
		contentPane.add(rdbtnSepa);
		
		rdbtnTakeOnPlace = new JRadioButton("Sur place");
		rdbtnTakeOnPlace.setSelected(true);
		rdbtnTakeOnPlace.setBounds(400, 140, 120, 20);
		contentPane.add(rdbtnTakeOnPlace);
		
		rdbtnDelivery = new JRadioButton("Livraison");
		rdbtnDelivery.setBounds(400, 180, 120, 20);
		contentPane.add(rdbtnDelivery);
		
		rdbtnSecureDelivery = new JRadioButton("Livraison s\u00E9curis\u00E9 : Sup. 10 euros");
		rdbtnSecureDelivery.setBounds(400, 220, 215, 20);
		contentPane.add(rdbtnSecureDelivery);
		
		//Group the radio buttons.
	    ButtonGroup groupPayement = new ButtonGroup();
	    groupPayement.add(rdbtnVisa);
	    groupPayement.add(rdbtnPaypal);
	    groupPayement.add(rdbtnSepa);
	    
	    //Group the radio buttons.
	    ButtonGroup groupDelivery = new ButtonGroup();
	    groupDelivery.add(rdbtnTakeOnPlace);
	    groupDelivery.add(rdbtnDelivery);
	    groupDelivery.add(rdbtnSecureDelivery);
		
		// Bouton vers confirmation de reservation
		btnValidateFrame(cli, show, rep, tabSpinner);
		
		// Bouton annuler commande ==> Retour client
		btnCancelOrder(cli, show, tabSpinner);
		
	}
	
	private void btnValidateFrame(Spectator cli, Show show, Representation rep, JSpinner[] tabSpinner) {
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mode de paiement
				if(rdbtnVisa.isSelected()) {
                    modePayement = "Visa";
                }
                if(rdbtnPaypal.isSelected()) {
                    modePayement = "Paypal";
                }
                if(rdbtnSepa.isSelected()) { // Attention à la date(
                    modePayement = "Sepa";
                }

                // Mode de livraison
                if(rdbtnTakeOnPlace.isSelected()) {
                    modeLivraison = "Sur place";
                }
                if(rdbtnDelivery.isSelected()) {
                    modeLivraison = "Prior";
                }
                if(rdbtnSecureDelivery.isSelected()) { // Supplément prix
                    modeLivraison = "Securise";
                }
             
                // On renvoi le tout pour confirmation de livraison mais sans insertions DB tant que client n'a pas confirmer son achat
                Order order = new Order(modePayement, modeLivraison, cli);
                List<Ticket> ticketList = new LinkedList<>();
                
                // Calcul du prix pour chaque place
                for(int i = 0; i< tabSpinner.length ; i++) {
                	for(int j = 0; j <Integer.parseInt(tabSpinner[i].getValue().toString()) ; j++){
                		Ticket place = new Ticket(order, rep);
                    	place.calculateTicketPrice(show.getConfig(), show.getCategoryList().get(i));
                    	ticketList.add(place);
                    	//System.out.println(show.getListCategorie().get(i).toString() + place.toString());
                	}               	
        		}  
                
                // Calcul du tatal de la commande
                order.setTicketList(ticketList);
                order.calculateTotalOrder(modeLivraison);
                
                // Redirection vers le résumé de la reservation des places pour validation
                SpectatorSummaryOrder frame = new SpectatorSummaryOrder(cli, order, show, tabSpinner);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                dispose();
             }			
		});
		btnValidate.setBounds(286, 273, 120, 40);
		contentPane.add(btnValidate);
	}
	
	private boolean increasePlaceFrame(Show show, JSpinner[] tabSpinner) {
		for(int i = 0; i< tabSpinner.length ; i++) {
			show.getCategoryList().get(i).increasePlace(Integer.parseInt(tabSpinner[i].getValue().toString()));
			if(!show.getCategoryList().get(i).update())
				return true;
		}
		
		return false;
	}
	
	private void btnCancelOrder(Spectator cli, Show show, JSpinner[] tabSpinner) {
		JButton btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {						
			public void actionPerformed(ActionEvent e) {
				// Annulation des places ==> Retour au valeur de départ
				boolean error = increasePlaceFrame(show, tabSpinner);
				
				if(error)
					JOptionPane.showMessageDialog(null, "Une erreur est survenue pour la mise à jour des places disponibles !");
								
				else {
					SpectatorAction frame = new SpectatorAction(cli);
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	                dispose();
				}							
			}
		});
		btnCancel.setBounds(286, 330, 120, 40);
		contentPane.add(btnCancel);
	}	
}
