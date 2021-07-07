package POJO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
    // Donn�es
    protected long id = 0;
    protected String name = "";
    protected String firstName = "";
    protected String phoneNumber = "";
    protected String email = "";
    protected String password = "";
    
    // Constructeurs
    public Person() {
    	
    }
    
    public Person(long id, String nom, String prenom, String telephone, String email, String password) {
    	this.id = id;
        this.name = nom;
        this.firstName = prenom;
        this.phoneNumber = telephone;
        this.email = email;
        this.password = password;
    }
    
    public Person(String nom, String prenom, String telephone, String email, String password) {
        this.name = nom;
        this.firstName = prenom;
        this.phoneNumber = telephone;
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
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String telephone) {
        this.phoneNumber = telephone;
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
		return Global.getFactory().getPersonneDAO().create(this);
    }
    
    public boolean delete() {
    	return Global.getFactory().getPersonneDAO().delete(this);
	}
    
    public boolean update() {
    	return Global.getFactory().getPersonneDAO().update(this);
    }
    
    public Person find() {
    	return Global.getFactory().getPersonneDAO().find(this.id);
    }
    
    public Person find(long id) {
    	return Global.getFactory().getPersonneDAO().find(id);
    }
    
    // Liste en static pour �viter de cr�er un objet pour rien juste pour r�cup des objets en DB
    public static List<? extends Person> getAll(){ //Solution pour �viter probl�me de compatibilit� entre liste pour h�ritage    	
    	return Global.getFactory().getPersonneDAO().getAll();
    }
    
    @Override
    public String toString() { 
        return String.format("Nom : " + name + " - " + "Pr�nom : " + firstName + " - " + "Num�ro de t�l�phone : " + phoneNumber + " - " + "Email : " + email + " - " + "Mot de passe : " +  password); 
    } 
    
    // V�rifier si email d�j� pr�sent ==> Pas de double compte sur m�me adresse mail donc adresse unique
    @SuppressWarnings("unchecked")
	public static boolean checkEmail(String email) {
		List<Person> list = new LinkedList<>();
		int find = 0;
		
		list = (List<Person>) getAll();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).email.equals(email)) {
				find = 1;
			}
		}
		
		if(find == 1) {
			return true;
		}
		
		return false;
	}
    
    // Connexion � un compte utilisateur
    @SuppressWarnings("unchecked")
	public Person login(String email, String password) {
		List<Person> list = new LinkedList<>();
		Person pers = new Person();
		int find = 0;
		email = email.toLowerCase();
		
		list = (List<Person>) getAll();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).email.equals(email) && list.get(i).getPassword().equals(password)) {
				pers = list.get(i);
				find = 1;
			}
		}
		
		if(find == 0) {
			return null;
		}
		
		return pers;
	}
    
    // D�terminer le type de l'utilisateur
    public Object checkTypeUser(Person pers) {
    	
    	Spectator cli = new Spectator();
    	Organizer org = new Organizer();
    	Manager gest = new Manager();
    	
    	cli = cli.find(pers.getId());
    	org = org.find(pers.getId());
    	gest = gest.find(pers.getId());
    	
    	if(cli != null)
    		return cli;
    	else if(org != null)
    		return org;
    	else
    		return gest;
    }
}