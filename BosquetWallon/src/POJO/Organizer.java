package POJO;

import java.util.LinkedList;
import java.util.List;

public class Organizer extends Person {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private List<Booking> listeReservation = new LinkedList<>();
	
	// Constructeurs
	public Organizer() {
		super();
	}
	
	public Organizer(long id, String name, String firstName, String phoneNumber, String email, String password) {
		super(id, name, firstName, phoneNumber, email, password);
	}
	
	public Organizer(String name, String firstName, String phoneNumber, String email, String password) {
		super(name, firstName, phoneNumber, email, password);
	}
	
	// GET/SET
	public List<Booking> getListReservation() {
		return listeReservation;
	}
	public void setListReservation(List<Booking> listeReservation) {
		this.listeReservation = listeReservation;
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
		super.update();
		return Global.getFactory().getOrganisateurDAO().update(this);
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
