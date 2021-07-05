package POJO;

import java.util.LinkedList;
import java.util.List;

public class Organisateur extends Personne {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private List<Reservation> listeReservation = new LinkedList<>();
	
	// Constructeurs
	public Organisateur() {
		super();
	}
	
	public Organisateur(long id, String name, String firstName, String phoneNumber, String email, String password) {
		super(id, name, firstName, phoneNumber, email, password);
	}
	
	public Organisateur(String name, String firstName, String phoneNumber, String email, String password) {
		super(name, firstName, phoneNumber, email, password);
	}
	
	// GET/SET
	public List<Reservation> getListReservation() {
		return listeReservation;
	}
	public void setListReservation(List<Reservation> listeReservation) {
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
	public Organisateur find() {
		return Global.getFactory().getOrganisateurDAO().find(this.id);
	}
	
	@Override
	public Organisateur find(long id) {
		return Global.getFactory().getOrganisateurDAO().find(id);
	}
	
	public static List<Organisateur> getAll() {		
		return Global.getFactory().getOrganisateurDAO().getAll();
	}
}
