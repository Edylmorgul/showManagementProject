package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Person;

public class DAOPerson extends DAO<Person> {

    public DAOPerson(Connection conn){
        super(conn);
    }

	@Override
    public boolean create(Person obj) {		
		int id;
		
        try
        {        
        	PreparedStatement state = connect.prepareStatement("INSERT INTO T_personne(nom, prenom, adresse, email, password) VALUES (?,?,?,?,?)");
            state.setString(1, obj.getName());
            state.setString(2, obj.getFirstName());
            state.setString(3, obj.getAddress());
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
    public boolean delete(Person obj){
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
    public boolean update(Person obj){
    	try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_personne SET nom =?, prenom =?, adresse =?, email =?, password =? WHERE id = " + obj.getId());
            state.setString(1, obj.getName());
            state.setString(2, obj.getFirstName());
            state.setString(3, obj.getAddress());
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
    public Person find(long id){   	
    	Person pers = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_personne "
    		+ "WHERE id = " + id);
            if(result.first())
            	pers = new Person(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"));          
        }
        catch(SQLException e){
        	System.out.println("Catch Personne " + e.getMessage());
            e.printStackTrace();
        }
        return pers;
    }

	@Override
	public List<Person> getAll() {		
		List<Person> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Person pers = new Person(result.getLong("id"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password")); 
				 list.add(pers);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Personne " + e.getMessage());
			 e.printStackTrace();
		}			 
		 return list;
	}
}
