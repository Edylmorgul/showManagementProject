package DAO;

import java.sql.Connection;
import java.util.List;

import POJO.Spectacle;

public class DAOSpectacle extends DAO<Spectacle> {

	public DAOSpectacle(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Spectacle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Spectacle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Spectacle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Spectacle find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spectacle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
