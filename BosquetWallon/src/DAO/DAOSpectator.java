package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Spectator;
import POJO.Global;

public class DAOSpectator extends DAO<Spectator>{

    public DAOSpectator(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Spectator obj) {
    	
        try
        {        	
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_client(idClient, telephone, sexe) VALUES (?,?,?)");
            state.setLong(1, obj.getId());
            state.setString(2, obj.getPhoneNumber());
            state.setString(3, obj.getGender());
            state.execute();
            return true; 	               	
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Spectateur " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public boolean delete(Spectator obj){
    	
    	return Global.getFactory().getPersonDAO().delete(obj);
    }

    @Override
    public boolean update(Spectator obj){
		
    	// Modifier personne
    	if(Global.getFactory().getPersonDAO().update(obj)) {
    		// Modifier spectateur
    		try {
                PreparedStatement state = connect.prepareStatement("UPDATE T_client SET telephone =?, sexe =? WHERE idClient = " + obj.getId());
                state.setString(1, obj.getPhoneNumber());
                state.setString(2, obj.getGender());
                state.executeUpdate();
                return true;
             }
             catch (SQLException e) {
            	 System.out.println("Catch Spectateur " + e.getMessage());
            	 e.printStackTrace();
            }  
    		
    		return false;
    	}
    	
    	else
    		return false;
    }

    @Override
    public Spectator find(long id){    	
    	Spectator client = null;
        try{
        	String sql ="SELECT * FROM T_personne "
        			+ "INNER JOIN T_client ON T_client.idClient = T_personne.id "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	client = new Spectator(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("telephone"), result.getString("sexe"));
        }
        catch(SQLException e){
        	System.out.println("Catch Spectateur " + e.getMessage());
            e.printStackTrace();
        }
        
        return client;
    }

	@Override
	public List<Spectator> getAll() {		
		List<Spectator> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne "
			 		+ "INNER JOIN T_client ON T_client.idClient = T_personne.id";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Spectator client = new Spectator(result.getLong("idClient"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("telephone"), result.getString("sexe"));
				 list.add(client);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Spectateur " + e.getMessage());
			 e.printStackTrace();
		}	
		 
		 return list;
	}
}

