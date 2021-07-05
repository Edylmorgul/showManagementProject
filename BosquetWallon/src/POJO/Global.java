package POJO;

import DAO.DAOFactory;

//Variables globales et utilitaires du projet
public final class Global {
	
	private static DAOFactory factory = new DAOFactory();
	private static String letterPattern = "[a-zA-Z]+$"; // N'autorise que les lettres minuscules/majuscules
	private static String numberPattern = "^[0-9]*$"; // N'autorise que les nombres;
	private static String emailPattern = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"; // Vérifie la validité d'une adresse mail
	private static String phonePattern = "^[0-9]{4}[- .]?[0-9]{2}[- .]?[0-9]{2}[- .]?[0-9]{2}$"; // 10 nombres + permet les espaces, traits et points
	private static String passwordPattern = "^(?=.*[0-9])" // Doit contenir un chiffre
            				+ "(?=.*[a-z])(?=.*[A-Z])" // Doit contenir majuscule et minuscule
            				+ "(?=\\S+$).{4,20}$"; // Pas d'espace permis + longueur mdp 4 - 20

	public static DAOFactory getFactory() {
		return factory;
	}	
	public static String getLetterPattern() {
		return letterPattern;
	}
	public static String getNumberPattern() {
		return numberPattern;
	}
	public static String getEmailPattern() {
		return emailPattern;
	}
	public static String getPhonePattern() {
		return phonePattern;
	}
	public static String getPasswordPattern() {
		return passwordPattern;
	}
}
