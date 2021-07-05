package DAO;

import java.sql.Connection;
import java.util.List;

import POJO.Artiste;

public class DAOArtiste extends DAO<Artiste>{

    public DAOArtiste(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Artiste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Artiste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Artiste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Artiste find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artiste> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
