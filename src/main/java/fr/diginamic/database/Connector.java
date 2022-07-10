package fr.diginamic.database;

import java.awt.geom.AffineTransform;
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
	private final String DB_URL;
	/** DB_USER */
	private final String DB_USER;
	/** DB_PWD */
	private final String DB_PWD;
	/** requéte  SELECT * FROM article */
	public static final String FIND_ALL_ARTICLES = "SELECT * FROM article";
	/** UPDATE article SET article.DESIGNATION = UPPER(article.DESIGNATION) */
	public static final String UPDATE_TO_UPPER_CASE = "UPDATE article SET article.DESIGNATION = UPPER(article.DESIGNATION)";
	/** UPDATE article SET article.DESIGNATION = LOWER(article.DESIGNATION) */
	public static final String UPDATE_TO_LOWER_CASE = "UPDATE article SET article.DESIGNATION = LOWER(article.DESIGNATION)";
	
	
	
	/** SEPARATE_LOGS  avec ______________________________________________________*/
	public static final String SEPARATE_LOGS = "______________________________________________________";
	// ce bloc est exécuté dés le chargement de la classe
	static {

	}

	/** Constructeur
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
	 * d'un base de données
	 * attention la connection n'est pas fermée
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
	 * @return
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
	 * execute la requête passé en paramètre
	 * connexion échoue
	 * 
	 * @param requete
	 * @return nombre d'enregistrement affectés -1 si la requéte echoue
	 */
	public int requeteUpdate(String requete) {
		int results = -1; 
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement thatStatement = connection.createStatement();
				  ) {
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


}