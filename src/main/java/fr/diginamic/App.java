package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String DB_URL = "jdbc:mariadb://localhost:3307/compta";
	private static final String DB_USER = "root";
	private static final String DB_PWD = "1111";
    public static void main( String[] args ) throws SQLException
    {
    	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    	
        System.out.println( connection );
        
        
        connection.close();
    }
}
