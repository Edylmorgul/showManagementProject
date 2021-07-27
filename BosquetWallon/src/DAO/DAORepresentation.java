package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Representation;
import POJO.Show;

public class DAORepresentation extends DAO<Representation> {

	public DAORepresentation(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Representation obj) {
		 try
	        {
	            PreparedStatement state = connect.prepareStatement("INSERT INTO T_representation(fkRepreSpec, dateRepresentation, heureDebut, heureFin) VALUES (?,?,?,?)");
	            state.setLong(1, obj.getShow().getId());
	            state.setString(2, obj.getDate());
	            state.setString(3, obj.getBeginHour());
	            state.setString(4, obj.getEndHour());
	            state.execute();
	            return true;
	        }

	        catch(SQLException e)
	        {
	        	System.out.println("Catch Representation " + e.getMessage());
	            e.printStackTrace();
	        }

	        return false;
	}

	@Override
	public boolean delete(Representation obj) {

		try
		{
			PreparedStatement state = connect.prepareStatement("DELETE FROM T_representation WHERE id = " + obj.getId());		
			state.execute();
			return true;
		}
		
		catch(SQLException e)
		{
			System.out.println("Catch Representation " + e.getMessage());
			e.printStackTrace();
		}
    	
    	return false;
	}

	@Override
	public boolean update(Representation obj) {
		try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_representation SET fkRepreSpec =?, dateRepresentation =?, heureDebut =?, heureFin =? WHERE id = " + obj.getId());
            state.setLong(1, obj.getShow().getId());
            state.setString(2, obj.getDate());
            state.setString(3, obj.getBeginHour());
            state.setString(4, obj.getEndHour());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
        	System.out.println("Catch Reservation " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
	}

	@Override
	public Representation find(long id) {
		Representation representation = null;
		Show show = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_representation " 
    		+ "INNER JOIN T_spectacle ON T_representation.fkRepreSpec = T_spectacle.id "
    		+ "WHERE T_representation.id = " + id);
            if(result.first()) {
            	show = new Show(result.getLong("fkRepreSpec"), result.getString("titre"), result.getString("description"), result.getInt("nbrPlaceParClient"));   
            	representation = new Representation(result.getLong("id"), result.getString("dateRepresentation"), result.getString("heureDebut"), result.getString("heureFin"), show);
            }           	         
        }
        catch(SQLException e){
        	System.out.println("Catch Representation " + e.getMessage());
       	 	e.printStackTrace();
        }
        return representation;
	}

	@Override
    public List<Representation> getAll() {
        List<Representation> list = new LinkedList<Representation>();
         try {
             String sql ="SELECT * FROM T_representation "
             		+ "INNER JOIN T_spectacle ON T_representation.fkRepreSpec = T_spectacle.id";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
            	 Show show = new Show(result.getLong("fkRepreSpec"), result.getString("titre"), result.getString("description"), result.getInt("nbrPlaceParClient"));   
                 Representation representation = new Representation(result.getLong("id"), result.getString("dateRepresentation"), result.getString("heureDebut"), result.getString("heureFin"), show);
                 list.add(representation);
            }
         } catch (SQLException e) {
        	 System.out.println("Catch Representation " + e.getMessage());
        	 e.printStackTrace();
        }
         return list;
    }
}
