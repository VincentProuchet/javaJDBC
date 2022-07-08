package fr.diginamic;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws SQLException 
     */
    @Test
    public void shouldAnswerWithTrue() throws SQLException
    {
        assertTrue(App.createConnection() );
        App.getConnection().close();
    }
}
