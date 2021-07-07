package DAO;

import java.sql.Connection;
import java.util.List;

import POJO.Show;

public class DAOShow extends DAO<Show> {

	public DAOShow(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Show obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Show obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Show obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Show find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Show> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
