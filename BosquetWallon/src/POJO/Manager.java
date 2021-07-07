package POJO;

import java.util.List;

public class Manager extends Person {
	
	private static final long serialVersionUID = 1L;
	
	// Données
	private String phoneNumber = "";
	
	// Constructeurs
	public Manager() {
		super();
	}
	
	public Manager(long id, String name, String firstName, String address, String email, String password, String phoneNumber) {
		super(id, name, firstName, address, email, password);
		this.phoneNumber = phoneNumber;
	}
	
	public Manager(String name, String firstName, String address, String email, String password, String phoneNumber) {
		super(name, firstName,address, email, password);
		this.phoneNumber = phoneNumber;
	}
	
	// GET/SET
	public String getPhoneNumber() {
		return phoneNumber;
	}
		
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// Methodes
	@Override
	public boolean create() {
		super.create();
		return Global.getFactory().getManagerDAO().create(this);
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
	public Manager find() {
		return Global.getFactory().getManagerDAO().find(this.id);
	}
	
	@Override
	public Manager find(long id) {
		return Global.getFactory().getManagerDAO().find(id);
	}
	
	public static List<Manager> getAll() {
		return Global.getFactory().getManagerDAO().getAll();
	}
}
