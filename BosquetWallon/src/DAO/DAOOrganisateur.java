package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Global;
import POJO.Organisateur;

public class DAOOrganisateur extends DAO<Organisateur>{

	public DAOOrganisateur(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Organisateur obj) {		
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
	public boolean delete(Organisateur obj) {
		
    	return Global.getFactory().getPersonneDAO().delete(obj);
	}

	@Override
	public boolean update(Organisateur obj) {
				 	    			
    	return Global.getFactory().getPersonneDAO().update(obj);
	}

	@Override
	public Organisateur find(long id) {
		Organisateur org = null;
        try{
        	String sql ="SELECT * FROM T_personne "
        			+ "INNER JOIN T_organisateur ON T_organisateur.idOrganisateur = T_personne.id "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	org = new Organisateur(id, result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
        }
        catch(SQLException e){
        	System.out.println("Catch Organisateur " + e.getMessage());
            e.printStackTrace();
        }
        return org;
	}

	@Override
	public List<Organisateur> getAll() {
		// 0. Obtenir tous les organisateurs
		List<Organisateur> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne "
			 		+ "INNER JOIN T_organisateur ON T_organisateur.idOrganisateur = T_personne.id";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Organisateur org = new Organisateur(result.getLong("idOrganisateur"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
				 list.add(org);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Organisateur " + e.getMessage());
			 e.printStackTrace();
		}	
		 
		 return list;
	}
}
