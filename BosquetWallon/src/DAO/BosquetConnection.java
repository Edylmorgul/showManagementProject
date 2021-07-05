package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BosquetConnection {
	private static Connection instance = null;
	
	private BosquetConnection(){
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://./DB_bosquetWallon.accdb";
			instance = DriverManager.getConnection(url);
		}
		catch(ClassNotFoundException ex){
	JOptionPane.showMessageDialog(null, "Classe de driver introuvable" + ex.getMessage());
			System.exit(0);
		}
		catch (SQLException ex) {
	JOptionPane.showMessageDialog(null, "Erreur JDBC : " + ex.getMessage());
		}
		if (instance == null) {
            JOptionPane.showMessageDialog(null, "La base de données est inaccessible, fermeture du programme.");
            System.exit(0);
        }
	}
	
	public static Connection getInstance() {
		if(instance == null){
			new BosquetConnection();
		}
		return instance;
	}
}
