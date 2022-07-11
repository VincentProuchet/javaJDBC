package fr.diginamic.jdbc;

import static org.hamcrest.CoreMatchers.describedAs;

import fr.diginamic.database.Connector;

public class TestDelete {

	public static void main(String[] args) {
	 String delete = "DELETE fournisseur FROM fournisseur WHERE LOWER(fournisseur.NOM) like LOWER('%"+"La Maison des Peintures" +"%')";
	 Connector connect = new Connector("db");
	 connect.requeteUpdate(delete);
	}

}
