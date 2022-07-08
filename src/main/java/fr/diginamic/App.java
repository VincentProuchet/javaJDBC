package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.rowset.JdbcRowSet;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Connection connection ;
	private static final String DB_URL; 
	private static final String DB_USER ;
	private static final String DB_PWD ;
	
	static {
		ResourceBundle dbProperties = ResourceBundle.getBundle("db"); 
		//"jdbc:mariadb://localhost:3307/compta"
		DB_URL = "jdbc:"+ dbProperties.getString("jdbc.db.type")
						+"://"+dbProperties.getString("jdbc.db.adress")
						+ ":"+ dbProperties.getString("jdbc.db.port")
						+ "/"+ dbProperties.getString("jdbc.db.name")
						;
		DB_USER = dbProperties.getString("jdbc.db.user");
		DB_PWD = dbProperties.getString("jdbc.db.password");
		
	}
	
	
    public static void main( String[] args ) throws SQLException
    {
    	createConnection();
    	
        System.out.println( connection );
        
        connection.close();
        
    }
    
    /** Getter
	 * @return the connection
	 */
	public static Connection getConnection() {
		return connection;
	}

	public static boolean createConnection() throws SQLException {
    	App.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    	return connection.isValid(0);
    	
    }
   
}
