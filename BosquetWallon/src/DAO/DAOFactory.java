package DAO;

import java.sql.Connection;

import POJO.*;

public class DAOFactory {
	
	protected static final Connection conn = BosquetConnection.getInstance();
	
	// Construction des instances d'objets d'accès aux données
	// Personne
	public DAO<Person> getPersonneDAO(){
		return new DAOPerson(conn);
	}
	
	// Client
	public DAO<Spectator> getClientDAO(){
		return new DAOSpectator(conn);
	}
	
	// Organisateur
	public DAO<Organizer> getOrganisateurDAO(){
		return new DAOOrganizer(conn);
	}
	
	// Gestionnaire
	public DAO<Manager> getGestionnaireDAO(){
		return new DAOManager(conn);
	}
	
	// Artiste
	public DAO<Artist> getArtisteDAO(){
		return new DAOArtist(conn);
	}
	
	// Catégorie 
	
	// Commande
	
	// Spectacle
	public DAO<Show> getSpectacleDAO(){
		return new DAOShow(conn);
	}
	
	// Réservation
	public DAO<Booking> getReservationDAO(){
		return new DAOBooking(conn);
	}
	
	// Planning salle
	public DAO<Planning> getPlanningDAO(){
		return new DAOPlanning(conn);
	}
	
	// Configuration
	
	// Place

}
