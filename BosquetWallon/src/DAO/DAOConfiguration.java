package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Configuration;

//Configuration table utilitaire qui ne changera pas avec le temps ==> Plupart des methodes obsolètes
//On aurait pu, en DB faire un lien entre config et categorie vu que les caétogies dépendent de la configuration mais alors configuration devient une table comme les autres
//Afin d'éviter cela, lien direct entre catégorie et spectacle ==> Une autre façon de faire
//Je ne sais pas ce qui est mieux entre les deux façon de faire... :)
public class DAOConfiguration extends DAO<Configuration> {

	public DAOConfiguration(Connection conn) {
		super(conn);
		
	}

	@Override
	public boolean create(Configuration obj) {
		
		return false;
	}

	@Override
	public boolean delete(Configuration obj) {
		
		return false;
	}

	@Override
	public boolean update(Configuration obj) {
		
		return false;
	}

	@Override
	public Configuration find(long id) {
		Configuration config = null;
        try{
        	String sql ="SELECT * FROM TR_configuration "
        			+ "WHERE id = " + id;
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            if(result.first())
            	config = new Configuration(id, result.getString("type"), result.getString("description"));
        }
        catch(SQLException e){
        	System.out.println("Catch Configuration " + e.getMessage());
            e.printStackTrace();
        }
        
        return config;
	}

	@Override
	public List<Configuration> getAll() {
		List<Configuration> list = new LinkedList<>();
        try {
            String sql ="SELECT * FROM TR_configuration";
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
            while(result.next()) {
                Configuration config = new Configuration(result.getLong("id"), result.getString("type"), result.getString("description"));
                list.add(config);
           }
        } catch (SQLException e) {
       	 System.out.println("Catch Configuration " + e.getMessage());
       	 e.printStackTrace();
       }
        return list;
	}

}
