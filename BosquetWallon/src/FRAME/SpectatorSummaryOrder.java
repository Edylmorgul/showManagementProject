package FRAME;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Order;
import POJO.Ticket;
import POJO.Show;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SpectatorSummaryOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPaiment;
	private JLabel lblLivraison;
	private JLabel lblCoutTotal;
	private JLabel[] tabLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SpectatorSummaryOrder(Spectator cli, Order commande, Show show, JSpinner[] tabSpinner, List<Ticket> listPlace) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("R\u00E9sum\u00E9 commande ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 635, 50);
		contentPane.add(lblNewLabel);
		
		// Sous-titre
		JLabel lblNewLabel_1 = new JLabel("Mode de paiement : ");
		lblNewLabel_1.setBounds(330, 80, 130, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mode de livraison : ");
		lblNewLabel_2.setBounds(330, 140, 130, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total :");
		lblNewLabel_3.setBounds(330, 200, 130, 20);
		contentPane.add(lblNewLabel_3);
		
		// Contenu global
		lblPaiment = new JLabel("");
		lblPaiment.setBounds(470, 80, 160, 20);
		contentPane.add(lblPaiment);
		
		lblLivraison = new JLabel("");
		lblLivraison.setBounds(470, 140, 160, 20);
		contentPane.add(lblLivraison);
		
		lblCoutTotal = new JLabel("");
		lblCoutTotal.setBounds(470, 200, 150, 20);
		contentPane.add(lblCoutTotal);
		
		initFrame(commande);
		
		// Afficher place + prix
		displayPlaceFrame(listPlace);
		
		// Validation reservation place
		btnConfirmFrame(commande, listPlace);
		
		// Annulation reservation place
		btnCancelOrder(cli, show, tabSpinner);
	}
	
	private void initFrame(Order commande) {
		lblPaiment.setText(commande.getPaymentMethod());
		lblLivraison.setText(commande.getDeliveryMethod());
		lblCoutTotal.setText(commande.getTotal()+"");
	}
	
	private void displayPlaceFrame(List<Ticket> listPlace) {
		int y = 80;
		int taille = listPlace.size();
		tabLabel = new JLabel[taille];
		for(int i = 0 ; i< taille; i++ ) {
			tabLabel[i] = new JLabel("Ticket n° " + (i+1) + " - " + listPlace.get(i).toString());
			tabLabel[i].setHorizontalAlignment(SwingConstants.LEFT);
			tabLabel[i].setBounds(20, y, 350, 20);
			contentPane.add(tabLabel[i]);
			y += 30;
		}			
	}
	
	private void btnConfirmFrame(Order commande, List<Ticket> listPlace) {
		JButton btnNewButton = new JButton("Confirmer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				// Création de la commande
				if(commande.create()) {
					// Création des places
					for(Ticket place : listPlace) {
						if(!place.create())
							error = true;
					}
										
					if(error) {
						JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la reservation de vos places !");
						commande.delete();
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Reservation validée !");
						SpectatorAction frame = new SpectatorAction(commande.getSpectator());
		                frame.setLocationRelativeTo(null);
		                frame.setVisible(true);
		                dispose();
					}
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la création de votre commande !");
				}
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(250, 280, 120, 40);
		contentPane.add(btnNewButton);
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
		btnCancel.setBounds(250, 330, 120, 40);
		contentPane.add(btnCancel);
	}
}
