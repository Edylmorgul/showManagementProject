package DAO;

import java.sql.Connection;
import java.util.List;

import POJO.Reservation;

public class DAOReservation extends DAO<Reservation>{
	
	public DAOReservation(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reservation find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
