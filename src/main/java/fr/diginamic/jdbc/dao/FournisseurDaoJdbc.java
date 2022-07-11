package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.Connector;
import fr.diginamic.jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements IFournisseurDAO {
	private ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

	public static final String ID = "ID";
	public static final String NOM = "NOM";

	public static final String FIND_ALL_QUERY = "SELECT * FROM fournisseur ";
	/** SELECT * FROM article WHERE article.DESIGNATION LIKE */
	public static final String FIND_THIS_QUERY = "SELECT * FROM fournisseur WHERE LCASE(nom) LIKE LCASE(?)";

	/** UPDATE article SET article.DESIGNATION = ' */
	public static final String UPDATE_DESIGNATION_QUERY = "UPDATE fournisseur SET fournisseur.NOM =? WHERE fournisseur.NOM =?";

	/** INSERT INTO ARTICLE (ID,REF,DESIGNATION,PRIX,ID_FOU) VALUES( */
	public static final String INSERT_THIS_QUERY = "INSERT INTO FOURNISSEUR (NOM ) VALUES(?)";

	/** DELETE ARTICLE FROM ARTICLE WHERE ARTICLE.ID = */
	public static final String DELETE_THIS_QUERY = "DELETE fournisseur FROM fournisseur WHERE ID=? OR NOM=?";

	@Override
	public List<Fournisseur> extraire() {
		fournisseurs = new ArrayList<>();
		

		ResultSet set = null;
		try {
			Connector connect = new Connector();
				PreparedStatement read = connect.getConnection().prepareStatement(FIND_ALL_QUERY);
			set = read.executeQuery() ;
			while (set.next()) {
				fournisseurs.add(new Fournisseur(set.getInt(ID), set.getString(NOM)));

			}
			set.close();// TODO Auto-generated method stub


		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {

		int results = -1;

		try {
			Connector connect = new Connector();
			PreparedStatement insert = connect.getConnection().prepareStatement(INSERT_THIS_QUERY);
			insert.setString(1, fournisseur.getNom());
			results = insert.executeUpdate();
			insert.close();
			connect.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());

		}

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		int results = -1;
		try {
			Connector connect = new Connector();
			PreparedStatement insert = connect.getConnection().prepareStatement(UPDATE_DESIGNATION_QUERY);
			insert.setString(1, nouveauNom);
			insert.setString(2, ancienNom);
			results = insert.executeUpdate();
			insert.close();
			connect.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());

		}
		return results;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {

		String delete = String.format(DELETE_THIS_QUERY, fournisseur.getId(), fournisseur.getNom());
		boolean results = false;

		try {
			Connector connect = new Connector();
			PreparedStatement insert = connect.getConnection().prepareStatement(DELETE_THIS_QUERY);
			insert.setInt(1, fournisseur.getId());
			insert.setString(2, fournisseur.getNom());
			if (insert.executeUpdate() > 0) {
				results = true;
			}

			insert.close();
			connect.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());

		}
		return results;

	}

}
