package fr.diginamic.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import fr.diginamic.database.Connector;

/**
 * @author Vincent
 *
 */
public class TestArticle {
	
	
	/**
	 * @throws SQLException
	 */
	@Test
	public void testFetchOne() throws SQLException {
		
		Connector connector = new Connector("db");
		String requeString = connector.formTheLikeRequest(Article.FIND_THIS_ARTICLE, "fraise");
		System.err.println(requeString);
		ResultSet results = connector.requeteRead(requeString);
		assertNotNull(results);
		if(results.next()) {
			Article article = new Article(results);
			System.out.println(article);
			assertTrue(10 == article.getId());
			assertEquals("F04", article.getReferenceString());
			assertEquals("fraises d’encastrement", article.getDesignationString());
			assertEquals(8.14, article.getPrix(), 0.001);
			assertTrue(2 == article.getIdFournisseur());
		}
	}
}
