package fr.diginamic.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.Connector;
import fr.diginamic.jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDAO {
	private ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

	public static final String FIND_ALL_QUERY = "SELECT * FROM fournisseur ";
	/** SELECT * FROM article WHERE article.DESIGNATION LIKE */
	public static final String FIND_THIS_QUERY = "SELECT * FROM fournisseur WHERE LCASE(nom) LIKE LCASE('%";

	/** UPDATE article SET article.DESIGNATION = ' */
	public static final String UPDATE_DESIGNATION_QUERY = "UPDATE fournisseur SET fournisseur.NOM = '";
	public static final String UPDATE_DESIGNATION_QUERY_END = " WHERE fournisseur.NOM = '";

	/** INSERT INTO ARTICLE (ID,REF,DESIGNATION,PRIX,ID_FOU) VALUES( */
	public static final String INSERT_THIS_QUERY = "INSERT INTO FOURNISSEUR (NOM ) VALUES('";

	/** DELETE ARTICLE FROM ARTICLE WHERE ARTICLE.ID = */
	public static final String DELETE_THIS_QUERY = "DELETE fournisseur FROM fournisseur WHERE ";

	@Override
	public List<Fournisseur> extraire() {
		fournisseurs = new ArrayList<>();
		Connector connect = new Connector();

		ResultSet set = connect.requeteRead(FIND_ALL_QUERY);
		try {
			while (set.next()) {
				fournisseurs.add(new Fournisseur(set.getInt("ID"), set.getString("NOM")));

			}
			set.close();// TODO Auto-generated method stub
		} catch (SQLException e) {
			e.printStackTrace();

			System.err.println(e.getMessage());
		}

		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {

		StringBuilder insert = new StringBuilder(INSERT_THIS_QUERY).append(fournisseur.getNom()).append("')");
		Connector connect = new Connector();
		connect.requeteUpdate(insert.toString());

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		String update = new StringBuilder(UPDATE_DESIGNATION_QUERY).append(nouveauNom).append("'")
				.append(UPDATE_DESIGNATION_QUERY_END).append(ancienNom).append("'").toString();

		Connector connect = new Connector();

		return connect.requeteUpdate(update);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {

		String delete = new StringBuilder(DELETE_THIS_QUERY).append(" ID = ").append(fournisseur.getId()).append(" OR ")
				.append(" NOM = '").append(fournisseur.getNom()).append("'").toString();
		Connector connect = new Connector();
		if (connect.requeteUpdate(delete) > 0) {
			return true;
		}
		return false;
	}

}
