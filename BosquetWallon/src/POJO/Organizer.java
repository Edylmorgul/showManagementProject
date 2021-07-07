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
		return Global.getFactory().getOrganisateurDAO().create(this);
	}

	@Override
	public boolean delete() {
		return super.delete();
	}

	@Override
	public boolean update() {
		if(super.update())
			return Global.getFactory().getOrganisateurDAO().update(this);
		
		return false;
	}

	@Override
	public Organizer find() {
		return Global.getFactory().getOrganisateurDAO().find(this.id);
	}
	
	@Override
	public Organizer find(long id) {
		return Global.getFactory().getOrganisateurDAO().find(id);
	}
	
	public static List<Organizer> getAll() {		
		return Global.getFactory().getOrganisateurDAO().getAll();
	}
}
