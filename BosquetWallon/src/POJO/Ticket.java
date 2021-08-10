package POJO;

import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
	private double price = 0;
	private Order order = null;
	private Representation representation = null;
	
	// Constructeurs
	public Ticket() {
		
	}
	
	public Ticket(double price, Order order, Representation representation) {
		this.price = price;
		this.order = order;
		this.representation = representation;
	}
	
	public Ticket(Order order, Representation representation) {
		this.order = order;
		this.representation = representation;
	}
	
	public Ticket(long id, double price, Order order, Representation representation) {
		this.id = id;
		this.price = price;
		this.order = order;
		this.representation = representation;
	}
	
	// GET/SET
	public long getId() {
	    return id;
	}

	public void setId(long id) {
	    this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Representation getRepresentation() {
		return representation;
	}
	
	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}
		
	// Methodes
	public boolean create() {
		return Global.getFactory().getTicketDAO().create(this);
	}
	
	public boolean delete() {
		return Global.getFactory().getTicketDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getTicketDAO().update(this);
	}
	
	public Ticket find() {
		return Global.getFactory().getTicketDAO().find(this.id);
	}
	
	public static List<Ticket> getAll(){
		return Global.getFactory().getTicketDAO().getAll();
	}
	
	// Calcul du prix d'une place selon la configuration et la categorie par rapport au prix de base données par l'organisateur pour chaque catégories
	public void calculateTicketPrice(Configuration config, Category cat) {
		if(config.getType().equals("Debout")) {
			this.price = cat.getPrix() * 1.05;
		}
		
		else if(config.getType().equals("Concert")) {
			switch(cat.getType()) {
				case "Bronze":    	//concert - bronze
					this.price = cat.getPrix() * 1.10;
					break;
				case "Argent":    	//concert - argent
					this.price = cat.getPrix() * 1.20;
					break;
				case "Or":    		//concert - or
					this.price  = cat.getPrix() * 1.30;
					break;
			}
		}
		
		else {
			switch(cat.getType()) {
				case "Bronze":    	//cirque - bronze
					this.price = cat.getPrix() * 1.15;
					break;
				case "Argent":    	//cirque - argent
					this.price = cat.getPrix() * 1.25;
					break;
				case "Or":    		//cirque - or
					this.price = cat.getPrix() * 1.35;
					break;
				case "Diamant":    	//cirque - diamant
					this.price = cat.getPrix() * 1.50;
					break;
			}
		}		
    }
	
	@Override
    public String toString() { 
        return String.format("Prix : " + price); 
    }
}
