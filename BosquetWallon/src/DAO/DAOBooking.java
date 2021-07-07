package DAO;

import java.sql.Connection;
import java.util.List;

import POJO.Booking;

public class DAOBooking extends DAO<Booking>{
	
	public DAOBooking(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
