package POJO;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Donn�es
	private long id = 0;
	private double acompte = 0;
	private double solde = 0;
	private int statut = 0;
	private double prix = 0;
	private Organizer org;
	private List<Planning> listPlan = new LinkedList<>();
	
	// Constructeurs
	public Booking() {

    }
	
	public Booking(long id, double acompte, double solde, int statut, double prix, Organizer org) {
		this.id = id;
		this.acompte = acompte;
		this.solde = solde;
		this.statut = statut;
		this.prix = prix;	
		this.org = org;
	}
	
	public Booking(long id, double acompte, double solde, int statut, double prix) {
		this.id = id;
		this.acompte = acompte;
		this.solde = solde;
		this.statut = statut;
		this.prix = prix;	
	}
	
	public Booking(double acompte, int statut, Organizer org) {
		this.acompte = acompte;
		this.statut = statut;
		this.org = org;
	}
	
	// GET/SET
	 public long getId() {
	    return id;
	}

	public void setId(long id) {
	    this.id = id;
	}
	
	public double getAcompte() {
		return acompte;
	}

	public void setAcompte(double acompte) {
		this.acompte = acompte;
	}
	
	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public int getStatut() {
		return statut;
	}
	
	public void setStatut(int statut) {
		this.statut = statut;
	}
	
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Organizer getOrganisateur() {
		return org;
	}
	
	public void setOrganisateur(Organizer org) {
		this.org = org;
	}
	
	public List<Planning> getListPlanning(){
		return listPlan;
	}
	
	public void setListPlan(List<Planning> listPlan) {
		this.listPlan = listPlan;
	}
	
	// Methodes
	public boolean create() {     
        return Global.getFactory().getBookingDAO().create(this);
    }
	
	public boolean delete() {
		return Global.getFactory().getBookingDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getBookingDAO().update(this);
	}
	
	public Booking find() {
		return Global.getFactory().getBookingDAO().find(this.id);
	}
	
	public Booking find(long id) {
		return Global.getFactory().getBookingDAO().find(id);
	}
	
	public static List<Booking> getAll(){
		return Global.getFactory().getBookingDAO().getAll();
	}
	
	@Override
    public String toString() { 
        return String.format("Prix location salle : " + prix + " - " + "Acompte : " + acompte + " - " + "Solde � payer : " + solde); 
    } 
	
	// Calcul du solde selon prix + acompte - montant d�j� pay� par utilisateur = restant d� � payer 
	public double calculateSolde(double montant, String op) {
		if(op.equals("-")) {
			this.solde -= montant;
		}
		
		else {
			this.solde += montant;
		}
		
		return this.solde;
	}
}
