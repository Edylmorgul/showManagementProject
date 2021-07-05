package POJO;

import java.util.List;

public class Gestionnaire extends Personne {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	
	// Constructeurs
	public Gestionnaire() {
		super();
	}
	
	public Gestionnaire(long id, String name, String firstName, String phoneNumber, String email, String password) {
		super(id, name, firstName, phoneNumber, email, password);
	}
	
	public Gestionnaire(String name, String firstName, String phoneNumber, String email, String password) {
		super(name, firstName,phoneNumber, email, password);
	}

	// Methodes
	@Override
	public boolean create() {
		super.create();
		return Global.getFactory().getGestionnaireDAO().create(this);
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
	public Gestionnaire find() {
		return Global.getFactory().getGestionnaireDAO().find(this.id);
	}
	
	@Override
	public Gestionnaire find(long id) {
		return Global.getFactory().getGestionnaireDAO().find(id);
	}
	
	public static List<Gestionnaire> getAll() {
		return Global.getFactory().getGestionnaireDAO().getAll();
	}
}
