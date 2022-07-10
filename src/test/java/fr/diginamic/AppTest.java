package fr.diginamic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /** Teste 
     * les paramètres de connexion 
     * 	si la base de données est joignable
     * 	si les dépendances sont correctement paramétrées
     * 
     * @throws SQLException 
     */
    @Test
    public void testDatabase() throws SQLException
    {
        assertTrue(App.testConnection() );
        App.getConnection().close();
    }
    /**Teste la fonction de requête en lecture à la base de données
     * 
     * @throws SQLException
     */
    @Test
    public void testlecture() throws SQLException
    {
    	ResultSet results = App.requeteLit(App.FIND_ALL_ARTICLES); 
        assertNotNull( results);
    	
    	while(results.next())
        System.out.println(results.getString("DESIGNATION") );
    }
}
