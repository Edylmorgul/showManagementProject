package POJO;

import java.util.LinkedList;
import java.util.List;

public class Spectator extends Person {	
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private List<Order> listeCommande = new LinkedList<>();
			
	// Constructeurs
	public Spectator() {
		super();
	}
	
	public Spectator(long id, String name, String firstName, String phoneNumber, String email, String password) {
		super(id, name, firstName, phoneNumber, email, password);
	}
	
	public Spectator(String name, String firstName, String phoneNumber, String email, String password) {
		super(name, firstName, phoneNumber, email, password);
	}
	
	// GET/SET
	public List<Order> getListCommande() {
		return listeCommande;
	}
	public void setCommande(List<Order> listeCommande) {
		this.listeCommande = listeCommande;
	}

	// Methodes
	@Override
	public boolean create() {
		super.create();
		return Global.getFactory().getClientDAO().create(this);
	}

	@Override
	public boolean delete() {
		return super.delete();
	}

	@Override
	public boolean update() {
		return super.update();
	}
	
	@Override
	public Spectator find() {
		return Global.getFactory().getClientDAO().find(this.id);	
	}
	
	@Override
	public Spectator find(long id) {
		return Global.getFactory().getClientDAO().find(id);	
	}

	public static List<Spectator> getAll() {
		return Global.getFactory().getClientDAO().getAll();
	}
}

/*
Les méthodes statiques en Java sont héritées, mais ne peuvent pas être remplacées. 
Si vous déclarez la même méthode dans une sous-classe, vous devez masquer la méthode de la superclasse au lieu de la remplacer. 
Les méthodes statiques ne sont pas polymorphes. Au moment de la compilation, la méthode statique sera liée statiquement.
*/
