package POJO;

import java.util.List;

public class Artist extends Person {
	
	private static final long serialVersionUID = 1L;

	// Données
	String pseudo;
	private Show show;
	
	// Construteur
	public Artist() {
		super();
	}
	
	public Artist(long id, String name, String firstName, String phoneNumber, String email, String password, String pseudo, Show show) {
		super(id, name, firstName, phoneNumber, email, password);
		this.pseudo = pseudo;
		this.show = show;
	}
	
	public Artist(String name, String firstName, String phoneNumber, String email, String password, String pseudo, Show show) {
		super(name, firstName, phoneNumber, email, password);
		this.pseudo = pseudo;
		this.show = show;
	}
	
	public Artist(String pseudo, Show show) {
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
	
	public Show getShow() {
		return show;
	}
	
	public void setShow(Show show) {
		this.show = show;
	}

	// Methodes
	@Override
	public boolean create() {
		super.create();
		this.pseudo = this.pseudo.toLowerCase();
		return Global.getFactory().getArtistDAO().create(this);
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
	public Artist find() {	
		return Global.getFactory().getArtistDAO().find(this.id);
	}
	
	@Override
	public Artist find(long id) {	
		return Global.getFactory().getArtistDAO().find(id);
	}

	public static List<Artist> getAll() {		
		return Global.getFactory().getArtistDAO().getAll();
	}
}
