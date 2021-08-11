package FRAME;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Order;

public class SpectatorDisplayOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Order> listOrder;
	private DefaultListModel<Order> listModel = new DefaultListModel<>();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public SpectatorDisplayOrder(Spectator cli) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Vos commandes");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 585, 50);
		contentPane.add(titleLabel);
		
		// Affichage des commandes du client
		displayOrderFrame(cli);
		
		// Bouton retour client action
		btnBackClientFrame(cli);	
	}
	
	private void displayOrderFrame(Spectator client) {		
		listOrder = new JList<>();
		client.getListOrderBySpectator();
		if(!client.getOrderList().isEmpty()) {
			for (Order com : client.getOrderList())
				listModel.addElement(com);            
	        
	        listOrder.setModel(listModel);
	        listOrder.setBounds(45, 90, 690, 230);
	        
	        scrollPane = new JScrollPane(listOrder, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(45, 90, 500, 230);
	        contentPane.add(scrollPane);	        	    
		}
		
		else {
			JLabel lblNewLabel_5 = new JLabel("Vous n'avez encore aucune commande !");
	        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_5.setBounds(50, 200, 490, 45);
			contentPane.add(lblNewLabel_5);
		}    
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
