package fr.diginamic.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.Connector;
import fr.diginamic.jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDAO {
	private ArrayList<Fournisseur> fournisseurs ;

	@Override
	public List<Fournisseur> extraire() {
		fournisseurs = new ArrayList<>();
		Connector connect = new Connector("db");

		String requete = "SELECT * FROM fournisseur ";

		ResultSet set = connect.requeteRead(requete);
		try {
		while (set.next()) {
			fournisseurs.add(new Fournisseur(set.getInt("ID"), set.getString("NOM")));

		}
		set.close();// TODO Auto-generated method stub
		}catch (SQLException e) {
			e.printStackTrace();
			
			System.err.println(e.getMessage());
		}
		
		
		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {

		StringBuilder insert = new StringBuilder("INSERT INTO FOURNISSEUR (NOM ) VALUES('").append(fournisseur.getNom())
				.append("')");
		Connector connect = new Connector("db");
		connect.requeteUpdate(insert.toString());

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		String update = new StringBuilder("UPDATE fournisseur SET fournisseur.NOM = '")
				.append(nouveauNom).append("'")
				.append("where fournisseur.NOM like '%")
				.append(ancienNom).append("%'").toString();

		Connector connect = new Connector("db");

		return connect.requeteUpdate(update);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		
		String delete = new StringBuilder("DELETE fournisseur FROM fournisseur WHERE")
				.append(" ID = ")
				.append(fournisseur.getId())
				.append(" OR ")
				.append(" NOM = '")
				.append(fournisseur.getNom())
				.append("'")
				.toString();
		Connector connect = new Connector("db");
		if (connect.requeteUpdate(delete) > 0) {
			return true;
		}
		return false;
	}

}
