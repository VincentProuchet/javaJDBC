package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestDelete {

	public static void main(String[] args) {
	Fournisseur fournisseur = new Fournisseur(0,"La Maison des Peintures");
	FournisseurDaoJdbc  fournisseurJdbc= new FournisseurDaoJdbc();
	fournisseurJdbc.delete(fournisseur);
	fournisseurJdbc.delete(new Fournisseur(0, " L’Espace Création"));
	}

}
