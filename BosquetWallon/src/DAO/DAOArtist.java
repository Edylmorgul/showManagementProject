package DAO;

import java.sql.Connection;
import java.util.List;

import POJO.Artist;

public class DAOArtist extends DAO<Artist>{

    public DAOArtist(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Artist obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Artist obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Artist obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Artist find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artist> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
