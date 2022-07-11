package fr.diginamic;

import java.sql.SQLException;

import fr.diginamic.jdbc.Connector;



/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App {


	public static void main(String[] args) throws SQLException {

		Connector connection = new Connector("db");
		System.out.println(connection.getConnection());
		connection.getConnection().close();

	}


}
