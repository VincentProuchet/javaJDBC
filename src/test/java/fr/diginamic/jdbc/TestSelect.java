package fr.diginamic.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestSelect {
	public static ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
	
	public static void main(String[] args) throws SQLException {
		FournisseurDaoJdbc fDaoJdbc = new FournisseurDaoJdbc();
		
		
		
		for (Fournisseur f: fDaoJdbc.extraire()) {
			System.out.println(f);
					
		}
		

	}

}
