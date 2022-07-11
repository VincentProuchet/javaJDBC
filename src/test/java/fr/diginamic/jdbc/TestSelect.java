package fr.diginamic.jdbc;

import static org.hamcrest.CoreMatchers.containsString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import fr.diginamic.database.Connector;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestSelect {
	public static ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
	
	public static void main(String[] args) throws SQLException {
		Connector connect = new Connector("db");
		
		String requete = "SELECT * FROM fournisseur ";
		
		ResultSet set = connect.requeteRead(requete);
		while (set.next()) {
			fournisseurs.add(new Fournisseur(set.getInt("ID"),set.getString("NOM") ));
			
			
		}
		
		
		for (Fournisseur f: fournisseurs) {
			System.out.println(f);
					
		}
		

	}

}
