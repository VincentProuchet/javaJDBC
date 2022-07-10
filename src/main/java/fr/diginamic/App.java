package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;

/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App {
	private static Connection connection;
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PWD;
	public static final String FIND_ALL_ARTICLES = "SELECT * FROM article";
	public static final String SEPARATE_LOGS ="______________________________________________________";
	// ce bloc est exécuté dés le chargement de la classe
	static {
		// on recupére un .properties dans le répertoire resources
		ResourceBundle dbProperties = ResourceBundle.getBundle("db");
		// "jdbc:mariadb://localhost:3307/compta"
		DB_URL = "jdbc:" + dbProperties.getString("jdbc.db.type") + "://" + dbProperties.getString("jdbc.db.adress")
				+ ":" + dbProperties.getString("jdbc.db.port") + "/" + dbProperties.getString("jdbc.db.name");
		DB_USER = dbProperties.getString("jdbc.db.user");
		DB_PWD = dbProperties.getString("jdbc.db.password");

	}

	public static void main(String[] args) throws SQLException {

		System.out.println(connection);

	}

	/**
	 * Getter
	 * 
	 * @return the connection
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * Pour test de connexion
	 * Permet de tester tous nouveaux paramètre
	 *  ou la réponse d'un base de données
	 * @return true si une connexion valide et établie
	 */
	public static boolean testConnection() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);) {
			App.connection = connection;
			return connection.isValid(0);

		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

	}

	/**
	 * retourne un resultSet pour la requête passé en paramètres
	 * null si la connexion échoue
	 * @param requete
	 * @return
	 */
	public static ResultSet requeteLit(String requete) {
		ResultSet results = null;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement thatStatement = connection.createStatement();
				ResultSet r = thatStatement.executeQuery(requete)) {
			results = r;
			connection.close();
			thatStatement.close();
			

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(SEPARATE_LOGS);
			System.err.println(e.getMessage());

		}
		
		
		return results;
		

	}

}
