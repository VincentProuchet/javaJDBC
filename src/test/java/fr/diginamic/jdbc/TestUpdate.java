package fr.diginamic.jdbc;

import fr.diginamic.database.Connector;

public class TestUpdate {

	public static void main(String[] args) {
		String update = "UPDATE fournisseur SET fournisseur.NOM = '"+ "La Maison des Peintures"      +"'"
										+"where fournisseur.ID = 4";
		Connector connect = new Connector("db");
		connect.requeteUpdate(update);
	}

}
