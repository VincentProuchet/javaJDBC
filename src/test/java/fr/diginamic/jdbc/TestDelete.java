package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestDelete {

	public static void main(String[] args) {
	Fournisseur fournisseur = new Fournisseur(10,"La Maison des Peintures");
	FournisseurDaoJdbc  fournisseurJdbc= new FournisseurDaoJdbc();
	fournisseurJdbc.delete(fournisseur);
	}

}
