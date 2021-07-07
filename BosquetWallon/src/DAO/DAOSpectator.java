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
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_client(idClient) VALUES (?)");
            state.setLong(1, obj.getId());
            state.execute();
            return true; 	               	
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Client " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public boolean delete(Spectator obj){
    	
    	return Global.getFactory().getPersonneDAO().delete(obj);
    }

    @Override
    public boolean update(Spectator obj){
		
    	return Global.getFactory().getPersonneDAO().update(obj);
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
            	client = new Spectator(id, result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
        }
        catch(SQLException e){
        	System.out.println("Catch Client " + e.getMessage());
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
				 Spectator client = new Spectator(result.getLong("idClient"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
				 list.add(client);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Client " + e.getMessage());
			 e.printStackTrace();
		}	
		 
		 return list;
	}
}

