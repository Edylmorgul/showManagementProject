package POJO;

import java.util.LinkedList;
import java.util.List;

public class Spectator extends Person {	
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private String phoneNumber = "";
	private String gender = "";
	private List<Order> orderList = new LinkedList<>();
			
	// Constructeurs
	public Spectator() {
		super();
	}
	
	public Spectator(long id, String name, String firstName, String address, String email, String password, String phoneNumber, String gender) {
		super(id, name, firstName, address, email, password);
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	public Spectator(String name, String firstName, String address, String email, String password, String phoneNumber, String gender) {
		super(name, firstName, address, email, password);
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	// GET/SET
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	// Methodes
	@Override
	public boolean create() {
		super.create();
		return Global.getFactory().getSpectatorDAO().create(this);
	}

	@Override
	public boolean delete() {
		return super.delete();
	}

	@Override
	public boolean update() {
		if(super.update())
			return Global.getFactory().getSpectatorDAO().update(this);
		
		return false;
	}
	
	@Override
	public Spectator find() {
		return Global.getFactory().getSpectatorDAO().find(this.id);	
	}
	
	@Override
	public Spectator find(long id) {
		return Global.getFactory().getSpectatorDAO().find(id);	
	}

	public static List<Spectator> getAll() {
		return Global.getFactory().getSpectatorDAO().getAll();
	}
}

/*
Les méthodes statiques en Java sont héritées, mais ne peuvent pas être remplacées. 
Si vous déclarez la même méthode dans une sous-classe, vous devez masquer la méthode de la superclasse au lieu de la remplacer. 
Les méthodes statiques ne sont pas polymorphes. Au moment de la compilation, la méthode statique sera liée statiquement.
*/
