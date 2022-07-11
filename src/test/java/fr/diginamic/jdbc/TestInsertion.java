package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestInsertion {

	public static void main(String[] args) {
		FournisseurDaoJdbc  fournisseurJdbc= new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur(0, "La Maison de la Peinture");
		fournisseurJdbc.insert(fournisseur);
		fournisseurJdbc.insert(new Fournisseur(0, " L’Espace Création"));
		
	}
	

}
