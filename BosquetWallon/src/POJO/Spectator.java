package POJO;

import java.util.LinkedList;
import java.util.List;

public class Spectator extends Person {	
	
	private static final long serialVersionUID = 1L;
	
	// Donn�es
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
Les m�thodes statiques en Java sont h�rit�es, mais ne peuvent pas �tre remplac�es. 
Si vous d�clarez la m�me m�thode dans une sous-classe, vous devez masquer la m�thode de la superclasse au lieu de la remplacer. 
Les m�thodes statiques ne sont pas polymorphes. Au moment de la compilation, la m�thode statique sera li�e statiquement.
*/
