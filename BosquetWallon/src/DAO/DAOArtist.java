package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import POJO.Artist;
import POJO.Show;

public class DAOArtist extends DAO<Artist>{

    public DAOArtist(Connection conn){
        super(conn);
    }

	@Override
	public boolean create(Artist obj) {
		try
        {          	
            PreparedStatement state = connect.prepareStatement("INSERT INTO T_artiste(idArtiste, fkArtisteSpec, pseudo) VALUES (?,?,?)");
            state.setLong(1, obj.getId());
            state.setLong(2, obj.getShow().getId());
            state.setString(3, obj.getPseudo());
            state.execute();
            return true;    	 
        }

        catch(SQLException e)
        {
        	System.out.println("Catch Artiste " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
	}

	@Override
	public boolean delete(Artist obj) {
		
		return false;
	}

	@Override
	public boolean update(Artist obj) {
		try {
            PreparedStatement state = connect.prepareStatement("UPDATE T_artiste SET pseudo =?, fkArtisteSpec =? WHERE idArtiste = " + obj.getId());
            state.setString(1, obj.getPseudo());
            state.setLong(2, obj.getShow().getId());
            state.executeUpdate();
            return true;
         }
         catch (SQLException e) {
        	 System.out.println("Catch Artiste " + e.getMessage());
        	 e.printStackTrace();
        }    	    			
    	return false;
	}

	@Override
	public Artist find(long id) {
		Artist artiste = null;
    	Show show = null;
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM T_artiste "
    		+ "INNER JOIN T_personne ON T_artiste.idArtiste = T_personne.id "
    		+ "INNER JOIN T_spectacle ON T_spectacle.id = T_artiste.fkArtisteSpec "
    		+ "WHERE id = " + id);
            if(result.first()) {
            	show = new Show(result.getLong("fkArtisteSpec"), result.getString("titre"), result.getString("description"), result.getInt("nbrPlaceParClient"));
            	artiste = new Artist(id, result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("pseudo"), show);  
            }           	        
        }
        catch(SQLException e){
        	System.out.println("Catch Artiste " + e.getMessage());
            e.printStackTrace();
        }
        return artiste;
	}

	@Override
	public List<Artist> getAll() {
		List<Artist> list = new LinkedList<>();
		 try {
			 String sql ="SELECT * FROM T_personne "
			 		+ "INNER JOIN T_artiste ON T_artiste.idArtiste = T_personne.id "
			 		+ "INNER JOIN T_spectacle ON T_spectacle.id = T_artiste.fkArtisteSpec";
			 ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			 while(result.next()) {
				 Show show = new Show(result.getLong("fkArtisteSpec"), result.getString("titre"), result.getString("description"), result.getInt("nbrPlaceParClient"));
				 Artist artiste = new Artist(result.getLong("idArtiste"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"), result.getString("email"), result.getString("password"), result.getString("pseudo"), show);
				 list.add(artiste);
			}
		 } catch (SQLException e) {
			 System.out.println("Catch Artiste " + e.getMessage());
			 e.printStackTrace();
		}		 
		 return list;
	}
}
