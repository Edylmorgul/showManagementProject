package POJO;

import java.io.Serializable;
import java.util.List;

public class Representation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private long id = 0;
    private String date = null;
    private String beginHour = null;
    private String endHour = null;
    private Show show = null;
	
	// Constructeurs
	public Representation() {
		
    }

    public Representation(String date, String beginHour, String endHour, Show show) {
        this.date = date;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.show = show;
    }
    
    public Representation(long id, String date, String beginHour, String endHour, Show show) {
    	this.id = id;
        this.date = date;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.show = show;
    }
	
	// GET/SET
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String  getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String  getBeginHour() {
        return beginHour;
    }
    
    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }

    public String  getEndHour() {
        return endHour;
    }
    
    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
    
    public Show getShow() {
    	return show;
    }
    
    public void setShow(Show show) {
    	this.show = show;
    }
		
	// Methodes
	public boolean create() {     
        return Global.getFactory().getRepresentationDAO().create(this);
    }
	
	public boolean delete() {
		return Global.getFactory().getRepresentationDAO().delete(this);
	}
	
	public boolean update() {
		return Global.getFactory().getRepresentationDAO().update(this);
	}
	
	public Representation find() {
		return Global.getFactory().getRepresentationDAO().find(this.id);
	}
	
	public static List<Representation> getAll(){
		return Global.getFactory().getRepresentationDAO().getAll();
	}
	
	@Override
    public String toString() { 		
        return String.format("Date : " + date + " - " + beginHour + " - " + endHour); 
    } 
}
