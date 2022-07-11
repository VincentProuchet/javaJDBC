package fr.diginamic.jdbc;

import org.junit.Test;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestFournisseurDaoJdbc {
	public static FournisseurDaoJdbc  fournisseurJdbc= new FournisseurDaoJdbc();
	
	@Test
	public void TestInsertion() {
		Fournisseur fournisseur = new Fournisseur(10, "L'espace Création");
		fournisseurJdbc.insert(fournisseur);
	}

}
