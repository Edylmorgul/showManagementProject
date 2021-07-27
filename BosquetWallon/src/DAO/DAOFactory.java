package DAO;

import java.sql.Connection;

import POJO.*;

public class DAOFactory {
	
	protected static final Connection conn = BosquetConnection.getInstance();
	
	// Construction des instances d'objets d'accès aux données
	// Personne
	public DAO<Person> getPersonDAO(){
		return new DAOPerson(conn);
	}
	
	// Client
	public DAO<Spectator> getSpectatorDAO(){
		return new DAOSpectator(conn);
	}
	
	// Organisateur
	public DAO<Organizer> getOrganizerDAO(){
		return new DAOOrganizer(conn);
	}
	
	// Gestionnaire
	public DAO<Manager> getManagerDAO(){
		return new DAOManager(conn);
	}
	
	// Artiste
	public DAO<Artist> getArtistDAO(){
		return new DAOArtist(conn);
	}
	
	// Catégorie 
	public DAO<Category> getCategoryDAO(){
		return new DAOCategory(conn);
	}
	
	// Commande
	
	// Spectacle
	public DAO<Show> getShowDAO(){
		return new DAOShow(conn);
	}
	
	// Réservation
	public DAO<Booking> getBookingDAO(){
		return new DAOBooking(conn);
	}
	
	// Représentation
	public DAO<Representation> getRepresentationDAO(){
		return new DAORepresentation(conn);
	}
	
	// Planning salle
	public DAO<Planning> getPlanningDAO(){
		return new DAOPlanning(conn);
	}
	
	// Configuration
	public DAO<Configuration> getConfigurationDAO(){
		return new DAOConfiguration(conn);
	}
	
	// Place

}
