package POJO;

import java.util.List;

public class Artiste extends Personne {
	
	private static final long serialVersionUID = 1L;

	// Données
	String pseudo;
	private Spectacle show;
	
	// Construteur
	public Artiste() {
		super();
	}
	
	public Artiste(long id, String name, String firstName, String phoneNumber, String email, String password, String pseudo, Spectacle show) {
		super(id, name, firstName, phoneNumber, email, password);
		this.pseudo = pseudo;
		this.show = show;
	}
	
	public Artiste(String name, String firstName, String phoneNumber, String email, String password, String pseudo, Spectacle show) {
		super(name, firstName, phoneNumber, email, password);
		this.pseudo = pseudo;
		this.show = show;
	}
	
	public Artiste(String pseudo, Spectacle show) {
		this.pseudo = pseudo;
		this.show = show;
	}
	
	//GET/SET
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Spectacle getShow() {
		return show;
	}
	
	public void setShow(Spectacle show) {
		this.show = show;
	}

	// Methodes
	@Override
	public boolean create() {
		super.create();
		this.pseudo = this.pseudo.toLowerCase();
		return Global.getFactory().getArtisteDAO().create(this);
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
	public Artiste find() {	
		return Global.getFactory().getArtisteDAO().find(this.id);
	}
	
	@Override
	public Artiste find(long id) {	
		return Global.getFactory().getArtisteDAO().find(id);
	}

	public static List<Artiste> getAll() {		
		return Global.getFactory().getArtisteDAO().getAll();
	}
}
