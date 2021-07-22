package POJO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Planning implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // Données
    private long id = 0;
    private String startDate = null;
    private String endDate = null;
    private boolean available = false;
    private Manager manager = null;
    private Booking reservation = null;
    private Show show = null; //==> Un spectacle avec une ou plusieurs representations par jour ou plusieurs spectacles avec une ou plusieurs representation par jour ? 
    
    // Constructeur
    public Planning() {

    }
    
    public Planning(long id, String startDate, String endDate, boolean available, Manager manager, Booking reservation, Show show) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.available = available;
        this.manager = manager;
        this.reservation = reservation;
        this.show = show;
    }
    
    public Planning(long id, String startDate, String endDate, boolean available) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.available = available;
    }
    
    public Planning(String startDate, String endDate, boolean available, Manager manager, Booking reservation, Show show) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.available = available;
        this.manager = manager;
        this.reservation = reservation;
        this.show = show;
    }

	// GET/SET
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String dateDebutR) {
        this.startDate = dateDebutR;
    }
    
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String dateFinR) {
        this.endDate = dateFinR;
    }
    
    public boolean getAvailable() {
    	return available;
    }
    
    public void setAvailable(boolean disponible) {
    	this.available = disponible;
    }
    
    public Manager getManager() {
    	return manager;
    }
    
    public void setManger(Manager manager) {
    	this.manager = manager;
    }
    
    public Booking getReservation() {
    	return reservation;
    }
    
    public void setReservation(Booking reservation) {
    	this.reservation = reservation;
    }
    
    public Show getShow() {
    	return show;
    }
    
    public void setShow(Show show) {
    	this.show = show;
    }
    
    // Methodes
    public boolean create() {     
        return Global.getFactory().getPlanningDAO().create(this);
    }
    
    public boolean delete() {
    	return Global.getFactory().getPlanningDAO().delete(this);
    } 
    
    public boolean update() {
        return Global.getFactory().getPlanningDAO().update(this);
    }  
    
    public Planning find() {
    	return Global.getFactory().getPlanningDAO().find(this.id);
    }
    
    public Planning find(long id) {
    	return Global.getFactory().getPlanningDAO().find(id);
    }
    
    public static List<Planning> getAll() {		
		return Global.getFactory().getPlanningDAO().getAll();
	}
    
    // Vérifier si date déja présente en DB
    public boolean checkPlanning(Planning obj) {
        List<Planning> list = new LinkedList<Planning>();
        list = getAll();
        for(Planning salle : list) {
            if(salle.equals(obj)){
                return true;
            }
        }
        return false;
    }
    
    // Vérifier si date choisie à déjà été selectionnée
    public boolean checkPlanning(List<Planning> list, Planning plan) {    	
        for(Planning salle : list) {
            if(salle.equals(plan)){
                return true;
            }
        }
        return false;
    }
    
    // Récupérer date actuelle
    public static Date dateToday() {
        Long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        return date;
	}
    
    // Obtenir liste des dates possibles ==> Balaye les dates passées non reservées et les dates reservées
    public static List<Planning> getDateAvailable() {
    	Date dateToday = dateToday();
        List<Planning> list = new LinkedList<Planning>();
        List<Planning> listDateAvailable = new LinkedList<Planning>();      
        list = getAll();
        for(Planning plan : list) {
        	Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(plan.startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            if(date.after(dateToday) && plan.getAvailable() == false){            	
                listDateAvailable.add(plan);
            }
        }                  
        return listDateAvailable;
    }
    
    // Déterminer prix location salle selon jour
    @SuppressWarnings("deprecation")
	public double rentalPrice() {
    	Date date = null;
    	try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        if(date.getDay() == 5 || date.getDay() == 6) {
            return 4500;
        }
        return 3000;
    }
    
    @Override
    public boolean equals(Object obj) {
      Planning plan;
      if (obj ==null || obj.getClass()!=this.getClass()){
          return false;
      }

      else {
          plan =(Planning)obj;
          if(
                  plan.getStartDate().equals(getStartDate())
                  & plan.getEndDate().equals(getEndDate())) { 
              {
                  return true;
              }
          }

          else {
                  return false;
          }
      }
  }
   
  @Override
  public int hashCode() {
      return Objects.hash(startDate, endDate);
  }
    
    @Override
    public String toString() { 
        return String.format("Date : " + startDate + " au " + endDate + " - " + " au prix de : " + rentalPrice()); 
    }
}