package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Booking;
import POJO.Organizer;

public class DAOBooking extends DAO<Booking>{
	
	public DAOBooking(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking obj) {
		int id = 0;
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_reservation(fkResOrga, acompte, solde, statut, prix) VALUES (?,?,?,?,?)");   
            state.setLong(1, obj.getOrganizer().getId());
            state.setDouble(2, obj.getDeposit());
            state.setDouble(3, obj.getBalance());
            state.setInt(4, obj.getStatus());
            state.setDouble(5, obj.getPrice());
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
        	System.out.println("Catch Reservation " + e.getMessage());
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean delete(Booking obj) {
		try
		{
			PreparedStatement state = connect.prepareStatement("DELETE FROM T_reservation WHERE id = " + obj.getId());		
			state.execute();
			return true;
		}
		
		catch(SQLException e)
		{
			System.out.println("Catch Reservation " + e.getMessage());
			e.printStackTrace();
		}
    	
    	return false;
	}

	@Override
	public boolean update(Booking obj) {
		try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_reservation SET fkResOrga =?, acompte =?, solde =?, statut =?, prix =? WHERE id = " + obj.getId());
            state.setLong(1, obj.getOrganizer().getId());
            state.setDouble(2,obj.getDeposit());
            state.setDouble(3, obj.getBalance());
            state.setInt(4, obj.getStatus());
            state.setDouble(5, obj.getPrice());
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
	public Booking find(long id) {
		Booking res = null;
		Organizer org = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_reservation "
    		+ "INNER JOIN T_organisateur ON T_reservation.fkResOrga = T_organisateur.idOrganisateur "
    		+ "INNER JOIN T_personne ON T_organisateur.idOrganisateur = T_personne.id " 
    		+ "WHERE T_reservation.id = " + id);
            if(result.first()) {
            	org = new Organizer(result.getLong("fkResOrga"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("telephone"), result.getString("sexe"));
            	res = new Booking(id, result.getDouble("acompte"), result.getDouble("solde"), result.getInt("statut"), result.getDouble("prix"), org); 
            }           	         
        }
        catch(SQLException e){
        	System.out.println("Catch Reservation " + e.getMessage());
            e.printStackTrace();
        }
        return res;
	}

	@Override
	public List<Booking> getAll() {
		List<Booking> list = new LinkedList<Booking>();
		 try {
			 String sql ="SELECT * FROM T_reservation "
			    		+ "INNER JOIN T_organisateur ON T_reservation.fkResOrga = T_organisateur.idOrganisateur "
			    		+ "INNER JOIN T_personne ON T_organisateur.idOrganisateur = T_personne.id";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Organizer org = new Organizer(result.getLong("fkResOrga"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("telephone"), result.getString("sexe"));
				 Booking res = new Booking(result.getLong("id"), result.getDouble("acompte"), result.getDouble("solde"), result.getInt("statut"), result.getDouble("prix"), org); 	
				 list.add(res);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Reservation " + e.getMessage());
			 e.printStackTrace();
		}			 
		 return list;
	}

}
