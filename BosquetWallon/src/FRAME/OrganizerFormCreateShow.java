package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POJO.Category;
import POJO.Configuration;
import POJO.Global;
import POJO.Organizer;
import POJO.Planning;
import POJO.Booking;
import POJO.Show;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;

public class OrganizerFormCreateShow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titleField;
	private JTextField noCatField;
	private String[] typeConfigurationTab = {"Debout", "Concert", "Cirque"};
	private JComboBox<String> typeConfigurationCombo;
	private JLabel diamondLabel;
    private JLabel goldLabel;
    private JLabel silverLabel;
    private JLabel bronzeLabel;
    private JTextField diamondField;
    private JTextField goldField;
    private JTextField silverField;
    private JTextField bronzeField;
    private JLabel lblNewLabel_6;
    private Show show;
    private Configuration config;
    private Category cat;
    private List<Category> listCat;
    private String configType = "Debout";   
    private JSpinner spinner;
    private JTextField descriptionField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public OrganizerFormCreateShow(Booking res, Organizer orga, Planning plan) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel_1 = new JLabel("Cr\u00E9ation de spectacle");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 0, 785, 50);
		contentPane.add(lblNewLabel_1);
		
		// Contenu global	    
	    JLabel lblNewLabel_2 = new JLabel("Titre du spectacle :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(310, 60, 180, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("S\u00E9lectionner configuration :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(30, 200, 270, 30);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Montant par catégories :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(420, 200, 270, 30);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("(Optionnel) Limiter place par client :");
		lblNewLabel.setBounds(30, 320, 200, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Description :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(30, 151, 177, 30);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_6 = new JLabel("Pas de catégorie pour cette configuration !");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(420, 250, 270, 30);
		contentPane.add(lblNewLabel_6);
		
		typeConfigurationCombo = new JComboBox(typeConfigurationTab);
		typeConfigurationCombo.setBounds(100, 270, 90, 20);
		contentPane.add(typeConfigurationCombo);
		
		titleField = new JTextField();
		titleField.setBounds(310, 100, 170, 30);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(217, 141, 373, 50);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		diamondLabel = new JLabel("Diamant :");
	    diamondLabel.setBounds(420, 290, 80, 25);
	    contentPane.add(diamondLabel);
	    
	    goldLabel = new JLabel("Or :");
	    goldLabel.setBounds(420, 320, 80, 25);
	    contentPane.add(goldLabel);
	    
	    silverLabel = new JLabel("Argent");
	    silverLabel.setBounds(420, 350, 80, 25);
	    contentPane.add(silverLabel);
	    
	    bronzeLabel = new JLabel("Bronze");
	    bronzeLabel.setBounds(420, 380, 80, 25);
	    contentPane.add(bronzeLabel);
	    
	    diamondField = new JTextField();
	    diamondField.setText("0");
		diamondField.setBounds(500, 290, 90, 20);
		contentPane.add(diamondField);
		diamondField.setColumns(10);
		
		goldField = new JTextField();
		goldField.setText("0");
		goldField.setBounds(500, 320, 90, 20);
		contentPane.add(goldField);
		goldField.setColumns(10);
		
		silverField = new JTextField();
		silverField.setText("0");
		silverField.setBounds(500, 350, 90, 20);
		contentPane.add(silverField);
		silverField.setColumns(10);
		
		bronzeField = new JTextField();
		bronzeField.setText("0");
		bronzeField.setBounds(500, 380, 90, 20);
		contentPane.add(bronzeField);
		bronzeField.setColumns(10);
		
		noCatField = new JTextField();
		noCatField.setText("0");
		noCatField.setBounds(500, 290, 90, 20);
		contentPane.add(noCatField);
		noCatField.setColumns(10);
		
		spinner = new JSpinner();
		spinner.setBounds(240, 320, 45, 20);
		contentPane.add(spinner);
		
		// Par défaut non visible ==> Une autre façon de changer les éléments dynamiquement 		
		diamondField.setVisible(false);
		goldField.setVisible(false);
		silverField.setVisible(false);
		bronzeField.setVisible(false);
		
		diamondLabel.setVisible(false);
		goldLabel.setVisible(false);
		silverLabel.setVisible(false);
		bronzeLabel.setVisible(false);
	    
	    // Affichage catégorie selon configuration
	    selectConfigurationFrame();
						
		// Bouton enregistrer spectacle 
		validateShowFrame(orga, res, plan);
		
		// Bouton annuler création ==> Retour au menu
		btnCancelFrame(orga);	
	}
	
	private void validateShowFrame(Organizer org, Booking res, Planning plan) {
		JButton btnValidateShow = new JButton("VALIDER");
		btnValidateShow.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnValidateShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(titleField.getText().isEmpty() || descriptionField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Veuillez indiquer un titre et une description !");
				else if(Show.checkNameShow(titleField.getText()))
					JOptionPane.showMessageDialog(null, "Titre de spectacle déjà présent !");				
				else if(Integer.parseInt(spinner.getValue().toString()) < 0)
					JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre de place valide !");	
				// Debout
				else if(configType.equals("Debout")) {	
					if(noCatField.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez indiquer les montants !");
					else if(!noCatField.getText().matches(Global.getNumberPattern()))
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre !");
					else if(Double.parseDouble(noCatField.getText()) <= 0)
						JOptionPane.showMessageDialog(null, "Veuillez entrer un montant valide !");
					else {
						config = new Configuration();
						config.setId(1);						
						show = new Show(titleField.getText(), descriptionField.getText(), Integer.parseInt(spinner.getValue().toString()), config);						
						listCat = new LinkedList<>();
						cat = new Category(configType, Double.parseDouble(noCatField.getText()),8000,8000, show);
						cat.setShow(show);
						listCat.add(cat);
						show.setCategoryList(listCat);		
						
						createShowElementFrame(org, show, plan);
					}
				}
				// Concert
				else if(configType.equals("Concert")) {
					if(goldField.getText().isEmpty() || silverField.getText().isEmpty() || bronzeField.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez indiquer les montants !");
					else if(!goldField.getText().matches(Global.getNumberPattern()) || !silverField.getText().matches(Global.getNumberPattern()) || !bronzeField.getText().matches(Global.getNumberPattern()))
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre !");
					else if(Double.parseDouble(goldField.getText()) <= 0 || Double.parseDouble(silverField.getText()) <= 0 || Double.parseDouble(bronzeField.getText()) <=0)
						JOptionPane.showMessageDialog(null, "Veuillez entrer un montant valide !");
					else {				
						config = new Configuration();
						config.setId(2);
						show = new Show(titleField.getText(), descriptionField.getText(), Integer.parseInt(spinner.getValue().toString()), config);
						
						listCat = new LinkedList<>();
						cat = new Category("Or", Double.parseDouble(goldField.getText()),500,500, show);
						cat.setShow(show);
						listCat.add(cat);
						cat = new Category("Argent", Double.parseDouble(silverField.getText()),1500,1500, show);
						cat.setShow(show);
						listCat.add(cat);
						cat = new Category("Bronze", Double.parseDouble(bronzeField.getText()),3000,3000, show);
						cat.setShow(show);
						listCat.add(cat);
						show.setCategoryList(listCat);
						
						createShowElementFrame(org, show, plan);
					}
				}
				// Cirque
				else {
					if(diamondField.getText().isEmpty() || goldField.getText().isEmpty() || silverField.getText().isEmpty() || bronzeField.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez indiquer les montants !");
					else if(!diamondField.getText().matches(Global.getNumberPattern()) || !goldField.getText().matches(Global.getNumberPattern()) || !silverField.getText().matches(Global.getNumberPattern()) || !bronzeField.getText().matches(Global.getNumberPattern()))
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre !");
					else if(Double.parseDouble(diamondField.getText()) <=0 || Double.parseDouble(goldField.getText()) <= 0 || Double.parseDouble(silverField.getText()) <= 0 || Double.parseDouble(bronzeField.getText()) <=0)
						JOptionPane.showMessageDialog(null, "Veuillez entrer un montant valide !");
					else {						
						config = new Configuration();
						config.setId(3);
						show = new Show(titleField.getText(), descriptionField.getText(), Integer.parseInt(spinner.getValue().toString()), config);
						
						listCat = new LinkedList<>();
						cat = new Category("Diamant", Double.parseDouble(diamondField.getText()),1000,1000, show);
						cat.setShow(show);
						listCat.add(cat);
						cat = new Category("Or", Double.parseDouble(goldField.getText()),2000,2000, show);
						cat.setShow(show);
						listCat.add(cat);
						cat = new Category("Argent", Double.parseDouble(silverField.getText()),1500,1500, show);
						cat.setShow(show);
						listCat.add(cat);
						cat = new Category("Bronze", Double.parseDouble(bronzeField.getText()),1500,1500, show);
						cat.setShow(show);
						listCat.add(cat);
						show.setCategoryList(listCat);
						
						createShowElementFrame(org, show, plan);
					}
				}
			}							
		});
		btnValidateShow.setBounds(350, 450, 120, 40);
		contentPane.add(btnValidateShow);
	}
	
	private void createShowElementFrame(Organizer param, Show show, Planning plan) {
		if(show.create()) {
			for(Category cat : show.getCategoryList())
				cat.create();
				
			plan.setShow(show);
			plan.update();	
			JOptionPane.showMessageDialog(null, "Le spectacle a été ajouté !");	
			
			OrganizerAction frame = new OrganizerAction(param);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);  
			dispose();
		}
		else
			JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la creation du spectacle !");		
	}
	
	private void selectConfigurationFrame() {			
		typeConfigurationCombo.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent e){
	        	if(e.getItem().equals("Debout")) {  	        		
	        		diamondField.setVisible(false);
	        		goldField.setVisible(false);
	        		silverField.setVisible(false);
	        		bronzeField.setVisible(false);
	        		
	        		lblNewLabel_6.setVisible(true);
	        		noCatField.setVisible(true);
	        		
	        		diamondLabel.setVisible(false);
	        		goldLabel.setVisible(false);
	        		silverLabel.setVisible(false);
	        		bronzeLabel.setVisible(false);
	        		
	        		configType = "Debout";
	        	}	
	        	else if(e.getItem().equals("Concert")){
	        		diamondField.setVisible(false);
	        		goldField.setVisible(true);
	        		silverField.setVisible(true);
	        		bronzeField.setVisible(true);
	        		
	        		lblNewLabel_6.setVisible(false);
	        		noCatField.setVisible(false);
	        		
	        		diamondLabel.setVisible(false);
	        		goldLabel.setVisible(true);
	        		silverLabel.setVisible(true);
	        		bronzeLabel.setVisible(true);
	        		
	        		configType = "Concert";
	        	}
	        	else {
	        		diamondField.setVisible(true);
	        		goldField.setVisible(true);
	        		silverField.setVisible(true);
	        		bronzeField.setVisible(true);
	        		
	        		lblNewLabel_6.setVisible(false);
	        		noCatField.setVisible(false);
	        		
	        		diamondLabel.setVisible(true);
	        		goldLabel.setVisible(true);
	        		silverLabel.setVisible(true);
	        		bronzeLabel.setVisible(true);
	        		
	        		configType = "Cirque";
	        	}	        			
	        }        	
	    });
	}
	
	private void btnCancelFrame(Organizer orga) {
		JButton btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrganizerAction frame = new OrganizerAction(orga);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnCancel.setBounds(350, 500, 120, 40);
		contentPane.add(btnCancel);			
	}
}
