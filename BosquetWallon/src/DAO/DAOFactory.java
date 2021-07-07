package DAO;

import java.sql.Connection;

import POJO.*;

public class DAOFactory {
	
	protected static final Connection conn = BosquetConnection.getInstance();
	
	// Construction des instances d'objets d'acc�s aux donn�es
	// Personne
	public DAO<Personne> getPersonneDAO(){
		return new DAOPersonne(conn);
	}
	
	// Client
	public DAO<Client> getClientDAO(){
		return new DAOClient(conn);
	}
	
	// Organisateur
	public DAO<Organisateur> getOrganisateurDAO(){
		return new DAOOrganisateur(conn);
	}
	
	// Gestionnaire
	public DAO<Gestionnaire> getGestionnaireDAO(){
		return new DAOGestionnaire(conn);
	}
	
	// Artiste
	public DAO<Artiste> getArtisteDAO(){
		return new DAOArtiste(conn);
	}
	
	// Cat�gorie 
	
	// Commande
	
	// Spectacle
	public DAO<Spectacle> getSpectacleDAO(){
		return new DAOSpectacle(conn);
	}
	
	// R�servation
	public DAO<Reservation> getReservationDAO(){
		return new DAOReservation(conn);
	}
	
	// Planning salle
	
	// Configuration
	
	// Place

}
