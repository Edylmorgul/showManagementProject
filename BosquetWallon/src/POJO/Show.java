package POJO;

import java.io.Serializable;
import java.util.List;

public class Show implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Données
	private long id = 0;
	private String title = "";
	private String description = "";
	private int tiketPerPerson = 0;
	Configuration config;
	
	// Constructeurs
	public Show() {
	
	}
	
	public Show(long id, String title, String description, int tiketPerPerson, Configuration config) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.tiketPerPerson = tiketPerPerson;
		this.config = config;
	}
	
	public Show(long id, String title, String description, int tiketPerPerson) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.tiketPerPerson = tiketPerPerson;
	}
	
	public Show(String title, String description, int tiketPerPerson, Configuration config) {	
		this.title = title;
		this.description = description;
		this.tiketPerPerson = tiketPerPerson;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getTiketPerPerson() {
		return tiketPerPerson;
	}
	
	public void setTiketPerPerson(int numberPlaceByCli) {
		this.tiketPerPerson = numberPlaceByCli;
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
		return Global.getFactory().getShowDAO().create(this);
	}
	
	public boolean delete() {
    	return Global.getFactory().getShowDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getShowDAO().update(this);
	}
	
	public Show find() {
		return Global.getFactory().getShowDAO().find(this.id);
	}
	
	public static List<Show> getAll(){
		return Global.getFactory().getShowDAO().getAll();
	}	
		
	@Override
    public String toString() { 
        return String.format("Spectacle : " + title); 
    }	
}

/*
 Singleton possible
 */
