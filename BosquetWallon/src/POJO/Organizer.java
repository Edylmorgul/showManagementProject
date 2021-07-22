package POJO;

import java.util.LinkedList;
import java.util.List;

public class Organizer extends Person {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private List<Booking> bookingList = new LinkedList<>();
	private String phoneNumber = "";
	private String gender = "";
	
	// Constructeurs
	public Organizer() {
		super();
	}
	
	public Organizer(long id, String name, String firstName, String address, String email, String password, String phoneNumber, String gender) {
		super(id, name, firstName, address, email, password);
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	public Organizer(String name, String firstName, String address, String email, String password, String phoneNumber, String gender) {
		super(name, firstName, address, email, password);
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	// GET/SET
	public String getPhoneNumber() {
		return phoneNumber;
	}
		
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public List<Booking> getBookingList() {
		return bookingList;
	}
	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	// Méthodes
	@Override
	public boolean create() {
		super.create();
		return Global.getFactory().getOrganizerDAO().create(this);
	}

	@Override
	public boolean delete() {
		return super.delete();
	}

	@Override
	public boolean update() {
		if(super.update())
			return Global.getFactory().getOrganizerDAO().update(this);
		
		return false;
	}

	@Override
	public Organizer find() {
		return Global.getFactory().getOrganizerDAO().find(this.id);
	}
	
	@Override
	public Organizer find(long id) {
		return Global.getFactory().getOrganizerDAO().find(id);
	}
	
	public static List<Organizer> getAll() {		
		return Global.getFactory().getOrganizerDAO().getAll();
	}
	
	@Override
    public String toString() { 
        return String.format(super.toString() + " telephone : " + phoneNumber + " sexe : " + gender); 
    }
	
	// Obtenir la liste des reservations d'un organisateur
	public void getListBookingByOrganizer() {
		List<Booking> list = Booking.getAll();
		this.bookingList.clear(); // Solution temporaire pour régler petit bug d'affichage au niveau des listes
			
		for(Booking res : list) {
			if(res.getOrganizer().getId() == this.id)
				this.bookingList.add(res);			
		}		
	}
}
