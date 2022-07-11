package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author Vincent
 *
 */
public class Connector {

	/** connection */
	private Connection connection;

	/** DB_URL */
	private static String DB_URL;
	/** DB_USER */
	private static String DB_USER;
	/** DB_PWD */
	private static String DB_PWD;

	

	////////////////////////////////////////////////////////
	/** SEPARATE_LOGS avec ______________________________________________________ */
	public static final String SEPARATE_LOGS = "______________________________________________________";
	// ce bloc est exécuté dés le chargement de la classe permet de ne charger  les données qu'une seule fois
	static {
				// on recupére un .properties dans le répertoire resources
				ResourceBundle dbProperties = ResourceBundle.getBundle("db");
				// "jdbc:mariadb://localhost:3307/compta"
				DB_URL = "jdbc:" + dbProperties.getString("jdbc.db.type") + "://" + dbProperties.getString("jdbc.db.adress")
						+ ":" + dbProperties.getString("jdbc.db.port") + "/" + dbProperties.getString("jdbc.db.name");
				DB_USER = dbProperties.getString("jdbc.db.user");
				DB_PWD = dbProperties.getString("jdbc.db.password");

	}

	/**
	 * Constructeur
	 * 
	 * @param dbResourceFileName
	 */
	public Connector(String dbResourceFileName) {
		// on recupére un .properties dans le répertoire resources
				ResourceBundle dbProperties = ResourceBundle.getBundle(dbResourceFileName);
				// "jdbc:mariadb://localhost:3307/compta"
				DB_URL = "jdbc:" + dbProperties.getString("jdbc.db.type") + "://" + dbProperties.getString("jdbc.db.adress")
						+ ":" + dbProperties.getString("jdbc.db.port") + "/" + dbProperties.getString("jdbc.db.name");
				DB_USER = dbProperties.getString("jdbc.db.user");
				DB_PWD = dbProperties.getString("jdbc.db.password");

	}
	/** Constructeur
	 * créer une version rapide du connector
	 * permet de ne pas recharger les données chaque fois que l'on vas créer un nouvelle version de l'instance
	 */
	public Connector() {
		
	}

	/**
	 * Getter
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Pour test de connexion Permet de tester tous nouveaux paramètre ou la réponse
	 * d'un base de données attention la connection n'est pas fermée
	 * 
	 * @return true si une connexion valide et établie
	 */
	public boolean testConnection() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);) {
			this.connection = connection;
			return connection.isValid(0);

		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

	}

	/**
	 * retourne un resultSet pour la requête passé en paramètres null si la
	 * connexion échoue
	 * 
	 * @param requete
	 * @return ResultSet ou null en cas d'echec
	 */
	public ResultSet requeteRead(String requete) {
		ResultSet results = null;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement thatStatement = connection.createStatement();
				ResultSet r = thatStatement.executeQuery(requete)) {
			results = r;
			connection.close();
			thatStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(SEPARATE_LOGS);
			System.err.println(e.getMessage());

		}

		return results;

	}

	/**
	 * execute la requête passé en paramètre connexion échoue
	 * 
	 * @param requete
	 * @return nombre d'enregistrement affectés -1 si la requéte echoue
	 */
	public int requeteUpdate(String requete) {
		int results = -1;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement thatStatement = connection.createStatement();) {
			results = thatStatement.executeUpdate(requete);
			connection.close();
			thatStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(SEPARATE_LOGS);
			System.err.println(e.getMessage());

		}

		return results;

	}
	
	/**Forme une requête like avec la requête passé en paramètre 
	 * et ce qui pourrait être un entrée utilisateur
	 * retire les espaces inutiles retire tous caractères non alphanumériques
	 * 
	 * @param likerequest requête like 
	 * @param userEntry texte de recherche
	 * @return String une requête Like formé avec l'entrée utilisateur filtrée et les quotes placés autour.
	 */
	public String formTheLikeRequest(String likerequest, String userEntry) {
		userEntry = userEntry.toLowerCase().replaceAll("['`\"]", "#");;
				
		userEntry = userEntry.replaceAll("[^aàâäãbcdeéèêëfghiîïjklmnñoôöõpqrstuûüvwxyz][^0-9]", "#");
		while(userEntry.contains("##")  ){
			userEntry = userEntry.replace("##", "#");
		}
		userEntry =userEntry.replace("#"," ").strip().replace(" ", "%");
		
		return new StringBuilder().append(likerequest)
									.append("'%")
									.append(userEntry)
									.append("%')")
									.toString();
		
		
		
	}

}
