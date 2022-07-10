package fr.diginamic.database;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import fr.diginamic.entities.Article;

/**
 * Test la classe de connection à un base de données
 * 
 * @author Vincent
 *
 */
public class testConnector {
	/**
	 * Teste les paramètres de connexion si la base de données est joignable si les
	 * dépendances sont correctement paramétrées
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testDatabase() throws SQLException {
		Connector connector = new Connector("db");
		assertTrue(connector.testConnection());
		connector.getConnection().close();
	}

	/**
	 * Teste la fonction de requête en lecture à la base de données
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testlecture() throws SQLException {
		Connector connector = new Connector("db");
		ResultSet results = connector.requeteRead(Article.FIND_ALL_ARTICLES);
		assertNotNull(results);
		StringBuilder st = null;
		while (results.next()) {
			st = new StringBuilder();
			st.append(results.getInt("ID")).append("  ").append(results.getString("DESIGNATION"));
			System.out.println(st.toString());
		}
	}

	/**
	 * Teste la fonction de requête en mise à jours
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testUpdate() throws SQLException {
		int results = 0;
		Connector connector = new Connector("db");
		results = connector.requeteUpdate(Article.UPDATE_TO_UPPER_CASE);
		assertTrue(results > 1);
		results = connector.requeteUpdate(Article.UPDATE_TO_LOWER_CASE);
		assertTrue(results > 1);

	}

}
