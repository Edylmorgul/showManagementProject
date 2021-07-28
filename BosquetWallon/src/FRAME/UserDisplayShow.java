package FRAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import POJO.Spectator;
import POJO.Manager;
import POJO.Organizer;
import POJO.Person;
import POJO.Planning;
import POJO.Booking;
import POJO.Show;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class UserDisplayShow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Show> list;
	private DefaultListModel<Show> listModel = new DefaultListModel<>();
	private JScrollPane scrollPane;
	private JList<Show> listShow;
	private Planning planning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UserDisplayShow(Person param) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titre
		JLabel lblNewLabel = new JLabel("Liste des spectacles");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 600, 50);
		contentPane.add(lblNewLabel);
				
		// Liste des spectacles
		displayShowFrame(param);			
		
		// Bouton informations complémentaires
		btnDetailsShowFrame();
	}
	
	private void displayShowFrame(Person param) {
		if(param instanceof Spectator) {
			displayShowOtherUserFrame();
			btnChooseSpecClientFrame((Spectator) param);
			btnBackClientFrame((Spectator) param);
		}
		
		else if(param instanceof Organizer) {
			displayShowOrganizerFrame((Organizer) param);
			btnChooseSpecOrganizerFrame(param);
			btnBackOrganizerFrame((Organizer) param);
		}
		
		else if(param instanceof Manager) {
			displayShowOtherUserFrame();
			btnBackManagerFrame((Manager) param);
		}
		
		else {
			displayShowOtherUserFrame();
			btnBackMenuFrame();
		}
	}	
	
	private void displayShowOtherUserFrame() {
		listShow = new JList<Show>();
		
		list = new LinkedList<Show>();
        list = Show.getAll(); 
        
        if(!list.isEmpty()) {
        	for (Show obj : list) {
            	obj.getListArtistByShow();
            	obj.getListRepresentationByShow();
            	if(!obj.getArtistList().isEmpty() && !obj.getRepresentationList().isEmpty())
            		listModel.addElement(obj);
            }
            listShow.setVisibleRowCount(3);
            listShow.setModel(listModel);
            listShow.setBounds(150, 70, 285, 140);
            
            scrollPane = new JScrollPane(listShow, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBounds(150, 70, 285, 140);
            contentPane.add(scrollPane);
        }
        
        else {
        	JLabel lblNewLabel_1 = new JLabel("Aucun spectacle de disponible !");
            lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
    		lblNewLabel_1.setBounds(90, 150, 420, 45);
    		contentPane.add(lblNewLabel_1);
        }        
	}
		
	private void displayShowOrganizerFrame(Organizer param) {
		listShow = new JList<Show>();
		list = new LinkedList<Show>();
		boolean isFind = false;
		param.getListBookingByOrganizer();
		Show show = new Show();
		
		for(Booking res : param.getBookingList()) {
			res.getListPlanningByReservation();
			for(Planning plan : res.getPlanningList()) {
				if(plan.getShow().getId() != 0) {
					show = plan.getShow().find();
					list.add(show);
					planning = plan; // Date du spectacle 
					isFind = true;
				}					
			}
		}	
		
		if(isFind) {
			for (Show obj : list) {
	            listModel.addElement(obj);
	        }
	        listShow.setVisibleRowCount(3);
	        listShow.setModel(listModel);
	        listShow.setBounds(98, 66, 285, 139);
	        
	        scrollPane = new JScrollPane(listShow, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(150, 60, 285, 139);
	        contentPane.add(scrollPane);
		}
		
		else {
			JLabel lblNewLabel_1 = new JLabel("Vous n'avez pas de spectacle !");
            lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
    		lblNewLabel_1.setBounds(90, 150, 420, 45);
    		contentPane.add(lblNewLabel_1);
		}       
	}
	
	private void btnChooseSpecOrganizerFrame(Person param) {
		JButton btnChooseShow = new JButton("Representations");
		btnChooseShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show show = listShow.getSelectedValue();				
				if(show == null)
					JOptionPane.showMessageDialog(null, "Veuillez choisir un spectacle !");	
				else { 
						show.getListRepresentationByShow();
						if(!show.getRepresentationList().isEmpty())
							JOptionPane.showMessageDialog(null, "Vous avez déjà ajouté vos représentations pour ce spectacle !");						
						else {
							OrganizerOptionShow frame = new OrganizerOptionShow((Organizer) param, show, planning);
	                        frame.setLocationRelativeTo(null);
	                        frame.setVisible(true);
	                        dispose();
						}                       
					}
				}
		});
		btnChooseShow.setBounds(230, 220, 120, 40);
		contentPane.add(btnChooseShow);
	}
	
	private void btnChooseSpecClientFrame(Person param) {
		JButton btnChooseShow = new JButton("Reserver");
		btnChooseShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(listShow.getSelectedValue() == null)
					JOptionPane.showMessageDialog(null, "Veuillez choisir un spectacle !");					
				else { 
						
					}
				}
		});
		btnChooseShow.setBounds(230, 220, 120, 40);
		contentPane.add(btnChooseShow);
	}
	
	private void btnDetailsShowFrame() {
		JButton btnPopUpInfos = new JButton("D\u00E9tails");
		btnPopUpInfos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listShow.getSelectedValue() == null)
					JOptionPane.showMessageDialog(null, "Veuillez choisir un spectacle !");
				else {
					Show show = listShow.getSelectedValue();
                    UserDetailsInfosShow frame = new UserDetailsInfosShow(show);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
				}
			}
		});
		btnPopUpInfos.setBounds(230, 270, 120, 40);
		contentPane.add(btnPopUpInfos);
	}
	
	private void btnBackMenuFrame() {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMainMenu frame = new UserMainMenu();
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnBack.setBounds(230, 320, 120, 40);
		contentPane.add(btnBack);
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
		btnBack.setBounds(230, 320, 120, 40);
		contentPane.add(btnBack);
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
		btnBack.setBounds(230, 320, 120, 40);
		contentPane.add(btnBack);
	}
	
	private void btnBackManagerFrame(Manager manager) {
		JButton btnBack = new JButton("Retour");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerAction frame = new ManagerAction(manager);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);  
			    dispose();
			}
		});
		btnBack.setBounds(230, 320, 120, 40);
		contentPane.add(btnBack);
	}
}
