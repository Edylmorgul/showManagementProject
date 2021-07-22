package POJO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
    // Données
    protected long id = 0;
    protected String name = "";
    protected String firstName = "";
    protected String address = "";
    protected String email = "";
    protected String password = "";
    //protected String role = ""; ==> Vraiment neccessaire de rajouter un rôle car utilisation d'une methode pour déterminer type utilisateur ? 
    
    // Constructeurs
    public Person() {
    	
    }
    
    public Person(long id, String name, String firstName, String address, String email, String password) {
    	this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.password = password;
    }
    
    public Person(String name, String firstName, String address, String email, String password) {
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    // GET/SET
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String prenom) {
        this.firstName = prenom;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Methodes  
    public boolean create() {
		this.email = this.email.toLowerCase();
		return Global.getFactory().getPersonDAO().create(this);
    }
    
    public boolean delete() {
    	return Global.getFactory().getPersonDAO().delete(this);
	}
    
    public boolean update() {
    	return Global.getFactory().getPersonDAO().update(this);
    }
    
    public Person find() {
    	return Global.getFactory().getPersonDAO().find(this.id);
    }
    
    public Person find(long id) {
    	return Global.getFactory().getPersonDAO().find(id);
    }
    
    // Liste en static pour éviter de créer un objet pour rien juste pour récup des objets en DB
    public static List<? extends Person> getAll(){ //Solution pour éviter problème de compatibilité entre liste pour héritage    	
    	return Global.getFactory().getPersonDAO().getAll();
    }
    
    @Override
    public String toString() { 
    	return String.format("Nom : " + name + " - " + "Prénom : " + firstName + " - " + "Adresse : " + address + " - " + "Email : " + email + " - " + "Mot de passe : " +  password);  
    } 
    
    // Vérifier si email déjà présent ==> Pas de double compte sur même adresse mail donc adresse unique
    @SuppressWarnings("unchecked")
	public static boolean checkEmail(String email) {
		List<Person> list = new LinkedList<>();
		
		list = (List<Person>) getAll();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).email.equals(email)) {
				return true;
			}
		}
				
		return false;
	}
    
    // Connexion à un compte utilisateur
    @SuppressWarnings("unchecked")
	public Person login(String email, String password) {
		List<Person> list = new LinkedList<>();
		Person pers = new Person();
		email = email.toLowerCase();
		
		list = (List<Person>) getAll();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).email.equals(email) && list.get(i).getPassword().equals(password)) {
				pers = list.get(i);
				break;
			}
		}
		
		return pers;
	}
    
    // Déterminer le type de l'utilisateur
    public Object checkTypeUser() {
    	
    	Spectator spec = new Spectator();
    	Organizer org = new Organizer();
    	Manager gest = new Manager();
    	
    	spec = spec.find(this.getId());
    	if(spec != null)
    		return spec;
    	
    	org = org.find(this.getId());
    	if(org != null)
    		return org;
    	
    	gest = gest.find(this.getId());     	
    	return gest;
    }
}
