package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entities.Fournisseur;

public interface FournisseurDAO {
	
		List<Fournisseur> extraire();
		void insert(Fournisseur fournisseur);
		int update(String ancienNom, String nouveauNom);
		boolean delete(Fournisseur fournisseur);
		
}
