package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.dao.IFournisseurDAO;
import fr.diginamic.jdbc.enums.StoreMode;

/**
 * @author Vincent
 *
 */
public class Connector {

	/** connection */
	private Connection jdbcConnection =null;

	/** DB_URL */
	private static String DB_URL;
	/** DB_USER */
	private static String DB_USER;
	/** DB_PWD */
	private static String DB_PWD;
	private static StoreMode STORE_MODE;
	

	////////////////////////////////////////////////////////
	/** SEPARATE_LOGS avec ______________________________________________________ */
	public static final String SEPARATE_LOGS = "______________________________________________________";
	// ce bloc est exécuté dés le chargement de la classe permet de ne charger  les données qu'une seule fois
	static {
				
				// on recupére un .properties dans le répertoire resources
				ResourceBundle dbProperties = ResourceBundle.getBundle("db");
				
				STORE_MODE = StoreMode.valueOf(dbProperties.getString("store.mode"));
				
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
				
				STORE_MODE = StoreMode.valueOf(dbProperties.getString("store.mode"));
				
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
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException {
		if (this.jdbcConnection== null) {
			this.jdbcConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		}
		return this.jdbcConnection;
	}
	/** ferme la connexion si elle existe
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if(this.jdbcConnection!=null && !this.jdbcConnection.isClosed()) {
			this.jdbcConnection.close();
		}

	}
	
	public IFournisseurDAO getFournisseurDAO() {
		return STORE_MODE.getFournisseurStore();
		
	}
	
	
	

	/**
	 * Pour test de connexion Permet de tester tous nouveaux paramètre ou la réponse
	 * d'un base de données attention la connection n'est pas fermée
	 * 
	 * @return true si une connexion valide et établie
	 */
	public boolean testConnection() {
		try {
			this.getConnection();
			return this.jdbcConnection.isValid(0);

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
	public ResultSet Read(String requete) {
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
	public int Update(String requete) {
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
	public String CleanUserEntry( String userEntry) {
		userEntry = userEntry.toLowerCase().replaceAll("['`\"]", "#");;
				
		userEntry = userEntry.replaceAll("[^aàâäãbcdeéèêëfghiîïjklmnñoôöõpqrstuûüvwxyz][^0-9]", "#");
		while(userEntry.contains("##")  ){
			userEntry = userEntry.replace("##", "#");
		}
		userEntry =userEntry.replace("#"," ").strip().replace(" ", "%");
		System.out.println(userEntry);
		return new StringBuilder()	.append("'%")
									.append(userEntry)
									.append("%')")
									.toString();
		
		
		
	}

}
