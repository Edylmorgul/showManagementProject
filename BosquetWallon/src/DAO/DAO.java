package DAO;

import java.sql.*;
import java.util.List;

public abstract class DAO<T> {
	protected Connection connect = null;

    public DAO(Connection conn){
        this.connect = conn;
    }

    /**
     * Permet de cr�er une entr�e dans la base de donn�es
     * @param obj
     */
    public abstract boolean create(T obj);

    /**
     * Permet la suppression d'une entr�e de la base
     * @param obj
     */  
    public abstract boolean delete(T obj);
    
    /**
     * Permet de mettre � jour les donn�es d'une entr�e dans la base 
     * @param obj
     */ 
    public abstract boolean update(T obj);
    
    /**
     * Permet de r�cup�rer un objet via son ID
     * @param id
     * @return
     */  
    public abstract T find(long id);
  
    /**
     * Permet d'obtenir tous les objets
     * @return
     */   
    public abstract List<T> getAll();
}
