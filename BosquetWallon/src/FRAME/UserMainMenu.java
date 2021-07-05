package FRAME;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class UserMainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static UserMainMenu frame = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new UserMainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel titleLabel = new JLabel("Bosquet Wallon");
		titleLabel.setForeground(Color.RED);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setBounds(0, 0, 580, 50);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel);
		
		// Sous titre
		JLabel subTitleLabel = new JLabel("Que voulez-vous faire ?");
		subTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTitleLabel.setBounds(205, 70, 170, 30);
		contentPane.add(subTitleLabel);
		
		// Contenu global
		
		// Menu
		menuFrame();		
	}
	
	// Menu du programme
	private void menuFrame() {
		// 0. Création barre menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setEnabled(false);
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(100, 125, 400, 150);
		contentPane.add(menuBar);
		
		// 1. Création menu
		JMenu register = createJmenu("S'enregistrer");
		JMenu login = createJmenu("Se connecter");
		JMenu displayShow = createJmenu("Voir spectacle");
		JMenu exit = createJmenu("Quitter");
		
		// 2. Modification des menus
		register.setFont(new Font("Segoe UI", Font.BOLD, 12));
		login.setFont(new Font("Segoe UI", Font.BOLD, 12));
		displayShow.setFont(new Font("Segoe UI", Font.BOLD, 12));
		exit.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		// 3. Listener
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserFormRecord frame = new UserFormRecord();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserLogin frame = new UserLogin();				
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		
		displayShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		// 4. Ajout des menus à la barre
		menuBar.add(new JMenu("|"));
		menuBar.add(register);
		menuBar.add(new JMenu("|"));
		menuBar.add(login);
		menuBar.add(new JMenu("|"));
		menuBar.add(displayShow);
		menuBar.add(new JMenu("|"));
		menuBar.add(exit);
		menuBar.add(new JMenu("|"));
	}
	
	private JMenu createJmenu(String name) {
		return new JMenu(name);
	}
}
