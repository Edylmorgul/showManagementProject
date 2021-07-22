package POJO;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
	private double deposit = 0;
	private double balance = 0;
	private int status = 0;
	private double price = 0;
	private Organizer org = null;
	private List<Planning> planningList = new LinkedList<>();
	
	// Constructeurs
	public Booking() {

    }
	
	public Booking(long id, double deposit, double balance, int status, double price, Organizer org) {
		this.id = id;
		this.deposit = deposit;
		this.balance = balance;
		this.status = status;
		this.price = price;	
		this.org = org;
	}
	
	public Booking(long id, double deposit, double balance, int status, double price) {
		this.id = id;
		this.deposit = deposit;
		this.balance = balance;
		this.status = status;
		this.price = price;	
	}
	
	public Booking(double deposit, int statut, Organizer org) {
		this.deposit = deposit;
		this.status = statut;
		this.org = org;
	}
	
	// GET/SET
	 public long getId() {
	    return id;
	}

	public void setId(long id) {
	    this.id = id;
	}
	
	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Organizer getOrganizer() {
		return org;
	}
	
	public void setOrganizer(Organizer org) {
		this.org = org;
	}
	
	public List<Planning> getPlanningList(){
		return planningList;
	}
	
	public void setPlanningList(List<Planning> planningList) {
		this.planningList = planningList;
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
        return String.format("Prix location salle : " + price + " - " + "Acompte : " + deposit + " - " + "Solde à payer : " + balance); 
    } 
	
	// Calcul du solde selon prix + acompte - montant déjà payé par utilisateur = restant dû à payer 
	public double calculateBalance(double amount, String op) {
		if(op.equals("-")) {
			this.balance -= amount;
		}
		
		else {
			this.balance += amount;
		}
		
		return this.balance;
	}
	
	// Calcul du solde de la reservation ==> Acompte + prix location des salles + tarif si plusieurs jours
	public double calculateTotalPrice() {
		int numberDay = 0;
			
		for(Planning plan : this.planningList) {
			this.price += plan.rentalPrice();
			numberDay ++;
		}
			
		this.price = calculateReduction(numberDay, price);
		this.balance = this.deposit + this.price;
		return this.balance;
	}
		
	// Calculer reduction par nombre de jours
	private double calculateReduction(int numberDay, double price) {
		double reduc;
			
		if(numberDay == 2) {
			reduc = price * 0.05;
			price -= reduc;
		}
			
		if(numberDay >= 3 && numberDay <7) {
			reduc = price * 0.10;
			price -= reduc;
		}
			
		if(numberDay >=7 && numberDay < 15) {
			reduc = price * 0.20;
			price -= reduc;
		}
			
		if(numberDay > 15) {
			reduc = price * 0.30;
			price -= reduc;
		}
		return price;
	}
	
	// Obtenir liste des planning par reservation
	public void getListPlanningByReservation() {
		List<Planning> list = Planning.getAll();
			
		this.planningList.clear();
		for(Planning plan : list) {
			if(plan.getReservation().getId() == this.id)
				this.planningList.add(plan);			
		}
	}
}
