package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Global;
import POJO.Organizer;

public class DAOOrganizer extends DAO<Organizer>{

	public DAOOrganizer(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Organizer obj) {		
		try
        {       	
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_organisateur(idOrganisateur) VALUES (?)");
            state.setLong(1, obj.getId());
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Organisateur " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
	}

	@Override
	public boolean delete(Organizer obj) {
		
    	return Global.getFactory().getPersonneDAO().delete(obj);
	}

	@Override
	public boolean update(Organizer obj) {
				 	    			
    	return Global.getFactory().getPersonneDAO().update(obj);
	}

	@Override
	public Organizer find(long id) {
		Organizer org = null;
        try{
        	String sql ="SELECT * FROM T_personne "
        			+ "INNER JOIN T_organisateur ON T_organisateur.idOrganisateur = T_personne.id "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	org = new Organizer(id, result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
        }
        catch(SQLException e){
        	System.out.println("Catch Organisateur " + e.getMessage());
            e.printStackTrace();
        }
        return org;
	}

	@Override
	public List<Organizer> getAll() {
		// 0. Obtenir tous les organisateurs
		List<Organizer> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne "
			 		+ "INNER JOIN T_organisateur ON T_organisateur.idOrganisateur = T_personne.id";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Organizer org = new Organizer(result.getLong("idOrganisateur"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
				 list.add(org);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Organisateur " + e.getMessage());
			 e.printStackTrace();
		}	
		 
		 return list;
	}
}
