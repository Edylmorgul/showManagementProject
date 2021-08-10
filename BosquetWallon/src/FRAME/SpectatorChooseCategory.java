package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Representation;
import POJO.Show;
import java.awt.Color;

public class SpectatorChooseCategory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel[] tabLabel;
	private JSpinner[] tabSpinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SpectatorChooseCategory(Spectator cli, Show show, Representation representation) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Categorie");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 785, 50);
		contentPane.add(titleLabel);		
						
		// Sous titre
		JLabel subTitleLabel = new JLabel("Veuillez choisir une ou plusieurs cat\u00E9gories :");
		subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTitleLabel.setBounds(10, 100, 290, 20);
		contentPane.add(subTitleLabel);
		
		JLabel subTitleLabel_1 = new JLabel("Veuillez choisir votre nombre de places :");
		subTitleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		subTitleLabel_1.setBounds(450, 100, 290, 20);
		contentPane.add(subTitleLabel_1);
		
		JLabel subTitleLabel_2 = new JLabel("Limite de places par commande : " + show.getTicketPerPerson());
		subTitleLabel_2.setForeground(Color.RED);
		subTitleLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		subTitleLabel_2.setBounds(250, 50, 290, 20);
		contentPane.add(subTitleLabel_2);
		
		// Contenu global
		
		// Affichage des categorie d'un spectacle
		displayCategorieFrame(cli, show, representation);
		
		// Retour client action
		btnBackClientFrame(cli);
	}
	
	private void displayCategorieFrame(Spectator cli, Show show, Representation representation) {	
		show.getListCategorieByShow();	
		switch ((int)show.getConfig().getId()) {
			case 1:
				displayCatFrame(show);
				btnValidateCategorieFrame(cli, show, representation, tabSpinner);
				break;
			case 2:
				displayCatFrame(show);
				btnValidateCategorieFrame(cli, show, representation, tabSpinner);
				break;
			case 3:
				displayCatFrame(show);
				btnValidateCategorieFrame(cli, show, representation, tabSpinner);
				break;
		}
	}
	
	private void displayCatFrame(Show show) {
		int taille = show.getCategoryList().size();
		int y = 140;
		tabLabel = new JLabel[taille];
		tabSpinner = new JSpinner[taille];
		for(int i = 0; i < taille; i++) {		
			// Ajout des catégories
			tabLabel[i] = new JLabel("Categorie : " + show.getCategoryList().get(i).getType() + " - " +  " prix : " + show.getCategoryList().get(i).getPrix() + " - " + " places disponible : " + show.getCategoryList().get(i).getNbrPlaceDispo());
			tabLabel[i].setHorizontalAlignment(SwingConstants.LEFT);
			tabLabel[i].setBounds(20, y, 350, 20);
			contentPane.add(tabLabel[i]);
			
			// Ajout des spinners
			tabSpinner[i] = new JSpinner();
			tabSpinner[i].setBounds(550, y, 45, 20);
			contentPane.add(tabSpinner[i]);	
			y += 40;
		}
	}
	
	// On diminue déjà le nombre de place dispo dans catégorie ==> Si plusieurs personnes font des commandes en même temps, peut y avoir des problèmes de places disponibles si on attend la fin de la commande pour diminuer les places
	// ==> Cela s'applique aussi pour la reservation des plannings coté organisateur ==> A corriger plus tard ==> Même si reservation pas terminée ==> Déjà reserver les dates pour éviter problèmes si plusieurs organisateurs prennent les mêmes dates
	// ==> Si annulation ==> On fait simplement un retour en arrière des valeurs
	private void btnValidateCategorieFrame(Spectator cli, Show show, Representation representation, JSpinner[] tabSpinner) {
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkNegativeValueFrame(tabSpinner))
					JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides !");
				else {
					int total = sumPlaceFrame(tabSpinner);
					if(checkNumberPlaceFrame(show, total))
						JOptionPane.showMessageDialog(null, "Veuillez respecter la limite de places !");
					else if(total <= 0)
						JOptionPane.showMessageDialog(null, "Veuillez choisir un montant de place valide !");
					else {															
						boolean error = decreasePlaceFrame(show, tabSpinner);
							
						if(error)
							JOptionPane.showMessageDialog(null, "Une erreur est survenue pour la mise à jour des places disponibles !");
											
						else {
							SpectatorFormOrder frame = new SpectatorFormOrder(cli, show, representation, tabSpinner);
						    frame.setLocationRelativeTo(null);
						    frame.setVisible(true);  
						    dispose();
						}												
					}
				}			
			}
		});
		btnValidate.setBounds(330, 330, 120, 40);
		contentPane.add(btnValidate);
	}
	
	private boolean decreasePlaceFrame(Show show, JSpinner[] tabSpinner) {
		for(int i = 0; i< tabSpinner.length ; i++) {
			show.getCategoryList().get(i).decreasePlace(Integer.parseInt(tabSpinner[i].getValue().toString()));
			if(!show.getCategoryList().get(i).update())
				return true;
		}
		
		return false;
	}
	
	private int sumPlaceFrame(JSpinner[] tabSpinner) {
		int sum = 0;
		
		for(int i = 0; i< tabSpinner.length ; i++) {
			sum += Integer.parseInt(tabSpinner[i].getValue().toString());
		}
		
		return sum;
	}
	
	private boolean checkNegativeValueFrame(JSpinner[] tabSpinner) {
		
		for(int i = 0; i< tabSpinner.length; i++) {
			if(Integer.parseInt(tabSpinner[i].getValue().toString()) < 0){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkNumberPlaceFrame(Show show, int total) {
		
		if(show.getTicketPerPerson() == 0) // Pas de limite
			return false;
		
		if(total > show.getTicketPerPerson()) // Si nombre de places choisies par client trop haut ==> Limite
			return true;
		
		else
			return false; // Aucun problème
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
		btnBack.setBounds(330, 380, 120, 40);
		contentPane.add(btnBack);
	}
}
