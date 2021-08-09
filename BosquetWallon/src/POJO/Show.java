package POJO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Show implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Données
	private long id = 0;
	private String title = "";
	private String description = "";
	private int ticketPerPerson = 0;
	Configuration config;
	List<Artist> artistList = new LinkedList<Artist>();
	List<Representation> representationList = new LinkedList<>();
	List<Category> categoryList = new LinkedList<>();
	
	// Constructeurs
	public Show() {
	
	}
	
	public Show(long id, String title, String description, int ticketPerPerson, Configuration config) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.ticketPerPerson = ticketPerPerson;
		this.config = config;
	}
	
	public Show(long id, String title, String description, int ticketPerPerson) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.ticketPerPerson = ticketPerPerson;
	}
	
	public Show(String title, String description, int ticketPerPerson, Configuration config) {	
		this.title = title;
		this.description = description;
		this.ticketPerPerson = ticketPerPerson;
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
	
	public int getTicketPerPerson() {
		return ticketPerPerson;
	}
	
	public void setTiketPerPerson(int ticketPerPerson) {
		this.ticketPerPerson = ticketPerPerson;
	}
	
	public List<Artist> getArtistList() {
		return artistList;
	}
	
	public void setArtistList(List<Artist> artistList) {
		this.artistList = artistList;
	}
	
	public List<Representation> getRepresentationList(){
		return representationList;
	}
	
	public void setRepresentationList(List<Representation> representationList) {
		this.representationList = representationList;
	}
	
	public List<Category> getCategoryList(){
		return categoryList;
	}
	
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
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
	
	// Vérifier si nom de spectacle déjà présent en DB
		public static boolean checkNameShow(String titre) {
			List<Show> liste = new LinkedList<Show>();
			int find = 0;
			
			liste = getAll();
			
			for(int i = 0; i < liste.size(); i++) {
				if(liste.get(i).getTitle().equals(titre)) {
					find = 1;
				}
			}
			
			if(find == 1) {
				return true;
			}
			
			return false;
		}
		
		// Obtenir liste des artistes par spectacle
		public void getListArtistByShow() {
			List<Artist> list = Artist.getAll();
			this.artistList.clear();
			
			for(Artist art : list) {
				if(art.getShow().getId() == this.id)
					this.artistList.add(art);
			}			
		}
		
		// Obtenir liste des représentations par spectacle
		public void getListRepresentationByShow(){
	        List<Representation> list = Representation.getAll();
	        this.representationList.clear();
	        
	        for(Representation res : list) {
	        	if(res.getShow().getId() == this.id)
	        		this.representationList.add(res);
	        }
		}
		
		// Obtenir liste des catégories par spectacle
		public void getListCategorieByShow(){
			List<Category> list = Category.getAll();
			this.categoryList.clear();
			
			for(Category cat : list) {
				if(cat.getShow().getId() == this.id)
					this.categoryList.add(cat);
			}
		}
		
	@Override
    public String toString() { 
        return String.format("Spectacle : " + title); 
    }	
}

/*
 Singleton possible
 */
