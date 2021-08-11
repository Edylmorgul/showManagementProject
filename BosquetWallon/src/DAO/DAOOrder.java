package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Spectator;
import POJO.Order;

public class DAOOrder extends DAO<Order> {

	public DAOOrder(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Order obj) {
		int id;
		try
        {
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_commande(fkComCli, modePayement, modeLivraison, cout) VALUES (?,?,?,?)");
            state.setLong(1, obj.getSpectator().getId());
            state.setString(2, obj.getPaymentMethod());
            state.setString(3, obj.getDeliveryMethod());
            state.setDouble(4, obj.getTotal());
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
        	System.out.println("Catch Commande " + e.getMessage());
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delete(Order obj) {
		try
		{
			PreparedStatement state = connect.prepareStatement("DELETE FROM T_commande WHERE id = " + obj.getId());		
			state.execute();
			return true;
		}
		
		catch(SQLException e)
		{
			System.out.println("Catch Commande " + e.getMessage());
			e.printStackTrace();
		}
    	
    	return false;
	}

	@Override
    public boolean update(Order obj) {
        try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_commande SET fkComCli =?, modePayement =?, modeLivraison =?, cout =? WHERE id = " + obj.getId());
            state.setLong(1, obj.getSpectator().getId());
            state.setString(2, obj.getPaymentMethod());
            state.setString(3, obj.getDeliveryMethod());
            state.setDouble(4, obj.getTotal());
            state.executeUpdate();
            return true;
        }
        catch (SQLException e) {
        	System.out.println("Catch Commande " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
    }

	@Override
	public Order find(long id) {
		Order commande = null;
		Spectator client = null;
        try{
        	String sql ="SELECT * FROM T_commande "
        			+ "INNER JOIN T_client ON T_client.idClient = T_commande.fkComCli "
        			+ "INNER JOIN T_personne ON T_client.idClient = T_personne.id "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first()) {
            	client = new Spectator(result.getLong("fkComCli"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("telephone"), result.getString("sexe"));
            	commande = new Order(id, result.getString("modePayement"), result.getNString("modeLivraison"), result.getDouble("cout"), client);
            }           	
        }
        catch(SQLException e){
        	System.out.println("Catch Commande " + e.getMessage());
            e.printStackTrace();
        }
        
        return commande;
	}

	@Override
	public List<Order> getAll() {
		List<Order> list = new LinkedList<>();
        try{
        	String sql ="SELECT * FROM T_commande "
        			+ "INNER JOIN T_client ON T_client.idClient = T_commande.fkComCli "
        			+ "INNER JOIN T_personne ON T_client.idClient = T_personne.id";
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            while(result.next()) {
            	Spectator client = new Spectator(result.getLong("fkComCli"), result.getString("nom"), result.getString("prenom"), result.getString("telephone"), result.getString("email"), result.getString("password"), result.getString("telephone"), result.getString("sexe"));
            	Order commande = new Order(result.getLong("id"), result.getString("modePayement"), result.getNString("modeLivraison"), result.getDouble("cout"), client);
            	list.add(commande);
            }           	
        }
        catch(SQLException e){
        	System.out.println("Catch Commande " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;
	}
}
