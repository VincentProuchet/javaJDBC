package fr.diginamic.jdbc;

import fr.diginamic.jdbc.Connector;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestUpdate {

	public static void main(String[] args) {
		FournisseurDaoJdbc fDaoJdbc = new FournisseurDaoJdbc();
		fDaoJdbc.update("La Maison de la Peinture", "La Maison des Peintures");
	}

}
