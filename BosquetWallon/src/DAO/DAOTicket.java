package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Ticket;

public class DAOTicket extends DAO<Ticket> {

	public DAOTicket(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Ticket obj) {
		
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_place (fkPlaceCom, fkPlaceRep, prix) VALUES (?,?,?)");
            state.setDouble(1, obj.getOrder().getId());
            state.setDouble(2, obj.getRepresentation().getId()); 
            state.setDouble(3, obj.getPrice());
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
        	System.out.println("Catch place " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
	}

	@Override
	public boolean delete(Ticket obj) {
		
		try
		{
			PreparedStatement state = connect.prepareStatement("DELETE FROM T_place WHERE id = " + obj.getId());		
			state.execute();
			return true;
		}
		
		catch(SQLException e)
		{
			System.out.println("Catch place " + e.getMessage());
			e.printStackTrace();
		}
    	
    	return false;
	}

	@Override
	public boolean update(Ticket obj) {
		
		try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_place SET fkPlaceCom =?, fkPlaceRep =?, prix =? WHERE id = " + obj.getId());
            state.setDouble(1, obj.getOrder().getId());
            state.setDouble(2, obj.getRepresentation().getId()); 
            state.setDouble(3, obj.getPrice());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
        	System.out.println("Catch place " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
	}

	@Override
	public Ticket find(long id) {
		
		return null;
	}

	@Override
    public List<Ticket> getAll() {
        List<Ticket> list = new LinkedList<Ticket>();
         try {
             String sql ="SELECT * FROM Place";
             ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
             while(result.next()) {
                 Ticket place = new Ticket();
                 place.setId(result.getInt("numPlace"));
                 place.setPrice(result.getDouble("prix"));
                 list.add(place);
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
         return list;
    }

}
