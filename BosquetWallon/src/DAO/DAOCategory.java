package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Category;
import POJO.Show;

public class DAOCategory extends DAO<Category> {

	public DAOCategory(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Category obj) {
		
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_categorie(fkCatSpec, type, prix, nbrPlaceDispo, nbrPlaceMax) VALUES (?,?,?,?,?)"); 
            state.setLong(1, obj.getShow().getId());
            state.setString(2, obj.getType());
            state.setDouble(3, obj.getPrix());
            state.setInt(4, obj.getNbrPlaceDispo());
            state.setInt(5, obj.getNbrPlaceMax());
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Categorie " + e.getMessage());
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean delete(Category obj) {
		try
		{
			PreparedStatement state = connect.prepareStatement("DELETE FROM T_categorie WHERE id = " + obj.getId());		
			state.execute();
			return true;
		}
		
		catch(SQLException e)
		{
			System.out.println("Catch Categorie " + e.getMessage());
			e.printStackTrace();
		}
    	
    	return false;
	}

	@Override
	public boolean update(Category obj) {
		
        try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_categorie SET fkCatSpec =? ,type =?, prix =?, nbrPlaceMax =?, nbrPlaceDispo =? WHERE id = " + obj.getId());
            state.setLong(1, obj.getShow().getId());
            state.setString(2, obj.getType());
            state.setDouble(3, obj.getPrix());
            state.setInt(4,  obj.getNbrPlaceMax());
            state.setInt(5,  obj.getNbrPlaceDispo());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
        	System.out.println("Catch Categorie " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
	}

	@Override
	public Category find(long id) {
		Show show = null;
		Category cat = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_categorie "
    		+ "INNER JOIN T_spectacle ON T_spectacle.id = T_categorie.fkCatSpec "
    		+ "WHERE id = " + id);
            if(result.first()) {
            	show = new Show(result.getLong("fkCatSpec"), result.getString("titre"), result.getString("description"), result.getInt("nbrPlaceParClient"));  
            	cat = new Category(id, result.getString("type"), result.getDouble("prix"), result.getInt("nbrPlaceDispo"), result.getInt("nbrPlaceMax"),show); 
            }           	         
        }
        catch(SQLException e){
        	System.out.println("Catch Categorie " + e.getMessage());
            e.printStackTrace();
        }
        return cat;
	}

	@Override
    public List<Category> getAll() {
        List<Category> list = new LinkedList<Category>();
         try {
             String sql ="SELECT * FROM T_categorie "
             		+ "INNER JOIN T_spectacle ON T_spectacle.id = T_categorie.fkCatSpec";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Show show = new Show(result.getLong("fkCatSpec"), result.getString("titre"), result.getString("description"), result.getInt("nbrPlaceParClient"));  
             	 Category cat = new Category(result.getLong("id"), result.getString("type"), result.getDouble("prix"), result.getInt("nbrPlaceDispo"), result.getInt("nbrPlaceMax"),show); 
                 list.add(cat);
            }
         } catch (SQLException e) {
        	 System.out.println("Catch Categorie " + e.getMessage());
        	 e.printStackTrace();
        }
         return list;
    }
}
