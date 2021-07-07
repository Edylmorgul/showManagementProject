package POJO;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

public class Reservation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
	private double acompte = 0;
	private double solde = 0;
	private int statut = 0;
	private double prix = 0;
	private Organisateur org;
	private List<Planning> listPlan = new LinkedList<>();
	
	// Constructeurs
	public Reservation() {

    }
	
	public Reservation(long id, double acompte, double solde, int statut, double prix, Organisateur org) {
		this.id = id;
		this.acompte = acompte;
		this.solde = solde;
		this.statut = statut;
		this.prix = prix;	
		this.org = org;
	}
	
	public Reservation(long id, double acompte, double solde, int statut, double prix) {
		this.id = id;
		this.acompte = acompte;
		this.solde = solde;
		this.statut = statut;
		this.prix = prix;	
	}
	
	public Reservation(double acompte, int statut, Organisateur org) {
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
	
	public Organisateur getOrganisateur() {
		return org;
	}
	
	public void setOrganisateur(Organisateur org) {
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
        return Global.getFactory().getReservationDAO().create(this);
    }
	
	public boolean delete() {
		return Global.getFactory().getReservationDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getReservationDAO().update(this);
	}
	
	public Reservation find() {
		return Global.getFactory().getReservationDAO().find(this.id);
	}
	
	public Reservation find(long id) {
		return Global.getFactory().getReservationDAO().find(id);
	}
	
	public static List<Reservation> getAll(){
		return Global.getFactory().getReservationDAO().getAll();
	}
	
	@Override
    public String toString() { 
        return String.format("Prix location salle : " + prix + " - " + "Acompte : " + acompte + " - " + "Solde à payer : " + solde); 
    } 
	
	// Calcul du solde selon prix + acompte - montant déjà payé par utilisateur = restant dû à payer 
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
