package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Gestionnaire;

public class DAOGestionnaire extends DAO<Gestionnaire> {

	public DAOGestionnaire(Connection conn){
        super(conn);
    }

	@Override
    public boolean create(Gestionnaire obj) {
        
        return false;
    }
    
	@Override
    public boolean delete(Gestionnaire obj){
		 	
    	return false;
    }

	@Override
    public boolean update(Gestionnaire obj){
			
	    return false;
    }

	@Override
    public Gestionnaire find(long id){		
    	Gestionnaire ges = null;
        try{
        	String sql ="SELECT * FROM T_personne "
        			+ "INNER JOIN T_gestionnaire ON T_gestionnaire.idGestionnaire = T_personne.id "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	ges = new Gestionnaire(id, result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
        }
        catch(SQLException e){
        	System.out.println("Catch Gestionnaire " + e.getMessage());
            e.printStackTrace();
        }
        return ges;
    }

	@Override
	public List<Gestionnaire> getAll() {	
		List<Gestionnaire> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne "
			 		+ "INNER JOIN T_gestionnaire ON T_gestionnaire.idGestionnaire = T_personne.id";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Gestionnaire manager = new Gestionnaire(result.getLong("idGestionnaire"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
				 list.add(manager);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Gestionnaire " + e.getMessage());
			 e.printStackTrace();
		}	
		 
		 return list;
	}
}
