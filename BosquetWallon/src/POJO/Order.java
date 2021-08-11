package POJO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
	private String paymentMethod = "";
	private String deliveryMethod = "";
	private double total = 0;
	private Spectator spectator = null;
	private List<Ticket> ticketList = new LinkedList<>();
	
	// Constructeurs
	public Order() {
		
	}
	
	public Order(long id, String paymentMethod, String deliveryMethod, double total, Spectator spectator) {
		this.id = id;
		this.paymentMethod = paymentMethod;
		this.deliveryMethod = deliveryMethod;
		this.total = total;
		this.spectator = spectator;
	}
	
	public Order(String paymentMethod, String deliveryMethod, double total, Spectator spectator) {
		this.paymentMethod = paymentMethod;
		this.deliveryMethod = deliveryMethod;
		this.total = total;
		this.spectator = spectator;
	}
	
	public Order(String paymentMethod, String deliveryMethod, Spectator spectator) {
		this.paymentMethod = paymentMethod;
		this.deliveryMethod = deliveryMethod;
		this.spectator = spectator;
	}
	
	// GET/SET
	public long getId() {
	    return id;
	}

	public void setId(long id) {
	    this.id = id;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Spectator getSpectator() {
		return spectator;
	}
	
	public void setSpectator(Spectator spectator) {
		this.spectator = spectator;
	}
	
	public List<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	
	// Methodes
	public boolean create() {     
        return Global.getFactory().getOrderDAO().create(this);
    }
	
	public boolean delete() {
		return Global.getFactory().getOrderDAO().delete(this);
	}
	
	public boolean update(Order obj) {
		return Global.getFactory().getOrderDAO().update(this);
	}
	
	public Order find() {
		return Global.getFactory().getOrderDAO().find(this.id);
	}
	
	public static List<Order> getAll(){
		return Global.getFactory().getOrderDAO().getAll();
	}
	
	// Calcul total commande + frais 5 euros + mode paiement sepa 10 euros
	public double calculateTotalOrder(String deliveryMethod) {
		double total = 0;
		for(Ticket ticket : this.ticketList)
			total += ticket.getPrice();
		
		// 10 SEPA + 5 frais de dossier
		if(deliveryMethod.equals("Securise")) {
			return this.total =  total + 15;
		}
		
		this.total = total + 5;
		
		return this.total;
	}
	
	 @Override
	 public String toString() { 
	    return String.format("Mode de paiment : " + paymentMethod + " - " + " mode de livraison : " +  deliveryMethod + " - " + " Total commande : " + total); 
	 } 
}
