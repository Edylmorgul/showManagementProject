package POJO;

import java.io.Serializable;
import java.util.List;

public class Configuration implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
	private String type = "";
	private String description = "";
	
	//CONSTRUCTEURS
	public Configuration(){
			 
	}
		 
	public Configuration(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public Configuration(long id, String type, String description) {
		this.id = id;
		this.type = type;
		this.description = description;
	}
	
	// Accesseurs
	public long  getId() {
		return id;
	}
		
	public void setId(long id) {
		this.id = id;
	}

	public String  getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String  getDescription() {
		 return description;
	}
		
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Methodes
	public Configuration find() {
		return Global.getFactory().getConfigurationDAO().find(this.id);
	}
		
	public static List<Configuration> getAll(){
		return Global.getFactory().getConfigurationDAO().getAll();
	}
		
		@Override
	public String toString() { 
	    return String.format(type + " - " + description); 
	}
}
