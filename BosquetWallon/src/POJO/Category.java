package POJO;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
    private String type = "";
    private double prix = 0;
    private int nbrPlaceDispo = 0;
    private int nbrPlaceMax = 0;
    private Show show;
    
    // Constructeur
    public Category() {

    }
    
    public Category(long id, String type, double prix, int nbrPlaceDispo, int nbrPlaceMax, Show show) {
		this.id = id;
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
		this.show = show;
	}
    
    public Category(long id, String type, double prix, int nbrPlaceDispo, int nbrPlaceMax) {
		this.id = id;
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
	}
    
    public Category(String type, double prix, int nbrPlaceDispo, int nbrPlaceMax, Show show) {
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
		this.show = show;
	}
    
    // GET/SET	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public int getNbrPlaceDispo() {
		return nbrPlaceDispo;
	}
	
	public void setNbrPlaceDispo(int nbrPlaceDispo) {
		this.nbrPlaceDispo = nbrPlaceDispo;
	}
	
	public int getNbrPlaceMax() {
		return nbrPlaceMax;
	}
	
	public void setNbrPlaceMax(int nbrPlaceMax) {
		this.nbrPlaceMax = nbrPlaceMax;
	}
	
	public Show getShow() {
		return show;
	}
	
	public void setShow(Show show) {
		this.show = show;
	}
	
	// Methodes
	public boolean create() { 
        return Global.getFactory().getCategoryDAO().create(this);
    }
	
	public boolean delete() {
		return Global.getFactory().getCategoryDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getCategoryDAO().update(this);
	}
	
	public Category find() {
		return Global.getFactory().getCategoryDAO().find(this.id);
	}
	
	public static List<Category> getAll(){
		return Global.getFactory().getCategoryDAO().getAll();
	}
	
	// Augmenter place dispo par catégorie
	public void increasePlace(int nbrPlace) {
		this.nbrPlaceDispo += nbrPlace;
	}
	
	// Dimuner place dispo par catégorie
	public void decreasePlace(int nbrPlace) {
		this.nbrPlaceDispo -= nbrPlace;
	}
	
	@Override
    public String toString() { 
        return String.format(type + " - " + prix + " - " + nbrPlaceDispo + " - " + nbrPlaceMax); 
    }
}

