package POJO;

import java.io.Serializable;
import java.util.List;

public class Spectacle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Données
	private long id = 0;
	private String title = "";
	private int numberPlaceByCli = 0;
	Configuration config;
	
	// Constructeurs
	public Spectacle() {
	
	}
	
	public Spectacle(long id, String title, int numberPlaceByCli, Configuration config) {
		this.id = id;
		this.title = title;
		this.numberPlaceByCli = numberPlaceByCli;
		this.config = config;
	}
	
	public Spectacle(long id, String title, int numberPlaceByCli) {
		this.id = id;
		this.title = title;
		this.numberPlaceByCli = numberPlaceByCli;
	}
	
	public Spectacle(String title, int numberPlaceByCli, Configuration config) {	
		this.title = title;
		this.numberPlaceByCli = numberPlaceByCli;
		this.config = config;
	}
	
	// GET/SET
	public long getId() {
	    return id;
	}

	public void setId(long id) {
	    this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String titre) {
		this.title = titre;
	}
	
	public int getNumberPlaceByCli() {
		return numberPlaceByCli;
	}
	
	public void setNumberPlaceByCli(int numberPlaceByCli) {
		this.numberPlaceByCli = numberPlaceByCli;
	}
	
	public Configuration getConfig(){
		return config;
	}
	
	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	// Methodes
	public boolean create() {
		this.title = this.title.toLowerCase();
		return Global.getFactory().getSpectacleDAO().create(this);
	}
	
	public boolean delete() {
    	return Global.getFactory().getSpectacleDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getSpectacleDAO().update(this);
	}
	
	public Spectacle find() {
		return Global.getFactory().getSpectacleDAO().find(this.id);
	}
	
	public static List<Spectacle> getAll(){
		return Global.getFactory().getSpectacleDAO().getAll();
	}	
		
	@Override
    public String toString() { 
        return String.format("Spectacle : " + title); 
    }	
}

/*
 Singleton possible
 */
