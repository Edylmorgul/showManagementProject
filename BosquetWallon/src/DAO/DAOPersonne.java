package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Personne;

public class DAOPersonne extends DAO<Personne> {

    public DAOPersonne(Connection conn){
        super(conn);
    }

	@Override
    public boolean create(Personne obj) {		
		int id;
		
        try
        {        
        	PreparedStatement state = connect.prepareStatement("INSERT INTO T_personne(nom, prenom, telephone, email, password) VALUES (?,?,?,?,?)");
            state.setString(1, obj.getName());
            state.setString(2, obj.getFirstName());
            state.setString(3, obj.getPhoneNumber());
            state.setString(4, obj.getEmail());
            state.setString(5, obj.getPassword());
            state.execute();
            
            ResultSet rs = state.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getInt(1);
                obj.setId(id);
            }   
            return true;
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Personne " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public boolean delete(Personne obj){
    	try
		{
			PreparedStatement state = connect.prepareStatement("DELETE FROM T_personne WHERE id = ?"); // Plusieurs façon de réaliser une requête ==> Diff entre delete et update
			state.setLong(1, obj.getId());
			state.execute();
			return true;
		}
		
		catch(SQLException e)
		{
			System.out.println("Catch Personne " + e.getMessage());
			e.printStackTrace();
		}
    	
    	return false;
    }

    @Override
    public boolean update(Personne obj){
    	try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_personne SET nom =?, prenom =?, telephone =?, email =?, password =? WHERE id = " + obj.getId());
            state.setString(1, obj.getName());
            state.setString(2, obj.getFirstName());
            state.setString(3, obj.getPhoneNumber());
            state.setString(4, obj.getEmail());
            state.setString(5, obj.getPassword());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
        	System.out.println("Catch Personne " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
    }

    @Override
    public Personne find(long id){   	
    	Personne pers = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_personne "
    		+ "WHERE id = " + id);
            if(result.first())
            	pers = new Personne(id, result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));          
        }
        catch(SQLException e){
        	System.out.println("Catch Personne " + e.getMessage());
            e.printStackTrace();
        }
        return pers;
    }

	@Override
	public List<Personne> getAll() {		
		List<Personne> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Personne pers = new Personne(result.getLong("id"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password")); 
				 list.add(pers);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Personne " + e.getMessage());
			 e.printStackTrace();
		}			 
		 return list;
	}
}
