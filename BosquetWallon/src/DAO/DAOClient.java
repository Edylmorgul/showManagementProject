package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Client;
import POJO.Global;

public class DAOClient extends DAO<Client>{

    public DAOClient(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(Client obj) {
    	
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
    public boolean delete(Client obj){
    	
    	return Global.getFactory().getPersonneDAO().delete(obj);
    }

    @Override
    public boolean update(Client obj){
		
    	return Global.getFactory().getPersonneDAO().update(obj);
    }

    @Override
    public Client find(long id){    	
    	Client client = null;
        try{
        	String sql ="SELECT * FROM T_personne "
        			+ "INNER JOIN T_client ON T_client.idClient = T_personne.id "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	client = new Client(id, result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
        }
        catch(SQLException e){
        	System.out.println("Catch Client " + e.getMessage());
            e.printStackTrace();
        }
        
        return client;
    }

	@Override
	public List<Client> getAll() {		
		List<Client> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne "
			 		+ "INNER JOIN T_client ON T_client.idClient = T_personne.id";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Client client = new Client(result.getLong("idClient"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
				 list.add(client);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Client " + e.getMessage());
			 e.printStackTrace();
		}	
		 
		 return list;
	}
}

