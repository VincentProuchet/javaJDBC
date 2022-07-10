package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;

import fr.diginamic.database.Connector;

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
