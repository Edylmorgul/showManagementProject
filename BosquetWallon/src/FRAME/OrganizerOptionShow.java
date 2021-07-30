package FRAME;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Artist;
import POJO.Organizer;
import POJO.Planning;
import POJO.Representation;
import POJO.Show;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class OrganizerOptionShow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private List<Artist> listArt = new LinkedList<>();
	private JCheckBox chckbx1;
	private JCheckBox chckbx2;
	private JCheckBox chckbx3;
	private JCheckBox chckbx4;
	private JTextField pseudoArtistField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OrganizerOptionShow(Organizer org, Show show, Planning plan) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("Options suppl\u00E9mentaires ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(0, 0, 665, 50);
        contentPane.add(lblNewLabel);
        
        // Sous-titre
        JLabel lblNewLabel_2 = new JLabel("S\u00E9lectionnez les repr\u00E9sentations :");
        lblNewLabel_2.setBounds(340, 90, 200, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_1 = new JLabel("Ajouter les artistes : ");
        lblNewLabel_1.setBounds(50, 90, 120, 20);
        contentPane.add(lblNewLabel_1);  
        
        JLabel lblNewLabel_4 = new JLabel(plan.toString());
        lblNewLabel_4.setBounds(150, 55, 370, 20);
        contentPane.add(lblNewLabel_4);
		
		// Contenu global
        chckbx1 = new JCheckBox("14h-16h");
        chckbx1.setSelected(true);
        chckbx1.setBounds(340, 135, 90, 20);
        contentPane.add(chckbx1);

        chckbx2 = new JCheckBox("18h-20h");
        chckbx2.setBounds(440, 135, 90, 20);
        contentPane.add(chckbx2);
        
        chckbx3 = new JCheckBox("08h-10h");
        chckbx3.setBounds(340, 175, 90, 20);
        contentPane.add(chckbx3);

        chckbx4 = new JCheckBox("10h-12h");
        chckbx4.setBounds(440, 175, 90, 20);
        contentPane.add(chckbx4);
        
        JLabel lblNewLabel_3 = new JLabel("Pseudo : ");
		lblNewLabel_3.setBounds(20, 150, 90, 20);
		contentPane.add(lblNewLabel_3);
        
        pseudoArtistField = new JTextField();
		pseudoArtistField.setBounds(120, 150, 90, 20);
		contentPane.add(pseudoArtistField);
		pseudoArtistField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("1er jour :");
        lblNewLabel_5.setBounds(255, 135, 80, 20);
        contentPane.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("2e jour :");
        lblNewLabel_6.setBounds(255, 175, 80, 20);
        contentPane.add(lblNewLabel_6);
                     
        // Formulaire artiste ==> A refaire si temps possible car insertion de données avec valeur vide dans personne ==> Pas beau
		btnAddArtistFrame(show);
        
        // Bouton validation des options du spectacles avec artistes
        btnValidateOptionFrame(show, org, plan);
     	
     	// Bouton retour 
        btnBackOrganizerFrame(org);
	}
	
	private void btnAddArtistFrame(Show show) {
		JButton btnAddArtist = new JButton("Ajouter");
		btnAddArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pseudoArtistField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Veuillez indiquer un pseudo !");
				else {
					Artist art = new Artist("Artiste", "", "", "", "",  pseudoArtistField.getText(), show);
					listArt.add(art);
					pseudoArtistField.setText("");
					JOptionPane.showMessageDialog(null, "Un autre artiste ?");
				}	
			}
		});
		btnAddArtist.setBounds(50, 200, 120, 40);
		contentPane.add(btnAddArtist);
	}
	
	private void btnValidateOptionFrame(Show show, Organizer org, Planning plan) {
		JButton btnValidate = new JButton("Valider");
        btnValidate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(listArt.isEmpty())
        			JOptionPane.showMessageDialog(null, "Veuillez ajouter un artiste !");        	
        		else {
        			int statut = 0;
        			// Jour 1
            		if(chckbx1.isSelected()) {
                      statut =  defineHourFrame(14, 16, show, plan.getStartDate());
                    }
            		if(chckbx2.isSelected()) {
                      statut =  defineHourFrame(18, 20, show, plan.getStartDate());
                    }
            		// Jour 2
                    if(chckbx3.isSelected()) {
                       statut = defineHourFrame(8, 10, show, plan.getEndDate());
                    }
                    if(chckbx4.isSelected()) {
                       statut = defineHourFrame(10, 12, show, plan.getEndDate());
                    } 
                    
                    if(statut == 1) {
                    	JOptionPane.showMessageDialog(null, "Mise à jour de votre spectacle effectué !");                     	
                    	for(Artist art : listArt)
                    		art.create();                   	
                    		                   	
                    	OrganizerAction frame = new OrganizerAction(org);
        			    frame.setLocationRelativeTo(null);
        			    frame.setVisible(true);  
        			    dispose();
                    }
                    
                    else
                    	JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la création de vos représentation !"); 
        		}     		               
        	}
        });
        btnValidate.setBounds(280, 250, 120, 40);
        contentPane.add(btnValidate);
	}
	
	private int defineHourFrame(int startHour, int endHour, Show show, String date) {
        Calendar c = Calendar.getInstance(); // Debut
        Calendar t = Calendar.getInstance(); // Fin
        Date d1;
        Date d2;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
        String strStartHour;
        String strEndHour;

        c.set( Calendar.HOUR_OF_DAY, startHour );
        c.set( Calendar.MINUTE, 0 );
        c.set( Calendar.SECOND, 0 );
        c.set( Calendar.MILLISECOND, 0 );
        
        t.set( Calendar.HOUR_OF_DAY, endHour );
        t.set( Calendar.MINUTE, 0 );
        t.set( Calendar.SECOND, 0 );
        t.set( Calendar.MILLISECOND, 0 );
        
        d1 = c.getTime();
        d2 = t.getTime();
        
        strStartHour = dateFormat.format(d1);
        strEndHour = dateFormat.format(d2);
        
        Representation rep = new Representation(date, strStartHour, strEndHour, show);
        if(rep.create()) {
        	 return 1;
        }
        
        else
        	return 0;
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
        btnBack.setBounds(280, 300, 120, 40);
        contentPane.add(btnBack);                    
	}
}
