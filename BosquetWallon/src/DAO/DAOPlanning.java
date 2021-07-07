package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Gestionnaire;
import POJO.Planning;
import POJO.Reservation;
import POJO.Spectacle;

public class DAOPlanning extends DAO<Planning>{
	
	public DAOPlanning(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Planning obj) {		
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_planning (fkPlanGest, fkPlanReserv, fkPlanSpec, dateDebut, dateFin, reserver) VALUES (?,?,?,?,?,?)");
            state.setLong(1, obj.getManager().getId());
            state.setNull(2, java.sql.Types.INTEGER);	
            state.setNull(3, java.sql.Types.INTEGER);
            state.setString(4, obj.getStartDate());
            state.setString(5, obj.getEndDate());
            state.setBoolean(6, obj.getAvailable());               
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Planning " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
	}

	@Override
    public boolean delete(Planning obj){
        try
        {
            PreparedStatement state = connect.prepareStatement("DELETE FROM T_planning WHERE id = ?");
            state.setLong(1, obj.getId());
            state.execute();
            return true;
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Planning " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

	@Override
	public boolean update(Planning obj) {		
		try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_planning SET fkPlanGest =?, fkPlanReserv =?, fkPlanSpec =?, dateDebut =?, dateFin =?, reserver =? WHERE id = " + obj.getId());
            state.setLong(1, obj.getManager().getId());
            state.setLong(2, obj.getReservation().getId());
            state.setLong(3, obj.getShow().getId());
            state.setString(4, obj.getStartDate());
            state.setString(5, obj.getEndDate());
            state.setBoolean(6, obj.getAvailable());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
        	System.out.println("Catch Planning " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
	}

	@Override
	public Planning find(long id) {
		Gestionnaire manager = null;
    	Planning plan = null;
    	Reservation reservation = null;
    	Spectacle show = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_planning "
    		+ "INNER JOIN T_gestionnaire ON T_planning.fkPlanGest = T_gestionnaire.idGestionnaire "
    		+ "INNER JOIN T_personne ON T_personne.id = T_gestionnaire.idGestionnaire "
    		+ "FULL OUTER JOIN T_reservation ON T_reservation.id = T_planning.fkPlanReserv "
    		+ "FULL OUTER JOIN T_spectacle ON T_spectacle.id = T_planning.fkPlanSpec "
    		+ "WHERE T_planning.id = " + id);
            if(result.first()) {
            	manager = new Gestionnaire(result.getLong("fkPlanGest"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
            	reservation = new Reservation(result.getLong("fkPlanReserv"), result.getDouble("acompte"), result.getDouble("solde"), result.getInt("statut"), result.getDouble("prix"));
            	show = new Spectacle(result.getLong("fkPlanSpec"), result.getString("titre"), result.getInt("nbrPlaceParClient"));   
            	plan = new Planning(id, result.getString("dateDebut"), result.getString("dateFin"), result.getBoolean("reserver"), manager, reservation, show); 
            }           	         
        }
        catch(SQLException e){
        	System.out.println("Catch Planning " + e.getMessage());
            e.printStackTrace();
        }
        return plan;
	}

	@Override
	public List<Planning> getAll() {		
		List<Planning> list = new LinkedList<Planning>();
		 try {
			 String sql ="SELECT * FROM T_planning "
			    		+ "INNER JOIN T_gestionnaire ON T_planning.fkPlanGest = T_gestionnaire.idGestionnaire "
			    		+ "INNER JOIN T_personne ON T_personne.id = T_gestionnaire.idGestionnaire "
			    		+ "FULL OUTER JOIN T_reservation ON T_reservation.id = T_planning.fkPlanReserv "
			    		+ "FULL OUTER JOIN T_spectacle ON T_spectacle.id = T_planning.fkPlanSpec";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Gestionnaire manager = new Gestionnaire(result.getLong("fkPlanGest"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"));
				 Reservation reservation = new Reservation(result.getLong("fkPlanReserv"), result.getDouble("acompte"), result.getDouble("solde"), result.getInt("statut"), result.getDouble("prix"));
				 Spectacle show = new Spectacle(result.getLong("fkPlanSpec"), result.getString("titre"), result.getInt("nbrPlaceParClient"));   
				 Planning plan = new Planning(result.getLong("id"), result.getString("dateDebut"), result.getString("dateFin"), result.getBoolean("reserver"), manager, reservation, show); 	 
				 list.add(plan);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Planning " + e.getMessage());
			 e.printStackTrace();
		}			 
		 return list;
	}
}
