package fr.diginamic.jdbc;

import fr.diginamic.database.Connector;

public class TestInsertion {

	public static void main(String[] args) {
		Connector connect = new Connector("db");
		String insert = "INSERT INTO FOURNISSEUR (NOM ) VALUES('"+    " La Maison de la Peinture "    +"')"; ;
		connect.requeteUpdate(insert);
	}
	

}
