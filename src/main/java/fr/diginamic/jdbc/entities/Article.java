package fr.diginamic.jdbc.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vincent
 *
 */
public class Article {

	/** id */
	private int id;
	/** referenceString */
	private String referenceString;
	/** designationString */
	private String designationString;
	/** prix */
	private float prix;
	/** idFournisseur */
	private int idFournisseur;
	/// Database colonnes
	/** ID */
	private static String ID = "ID";
	/** REF */
	private static String REF = "REF";
	/** DESIGNATION */
	private static String DESIGNATION = "DESIGNATION";
	/** PRIX */
	private static String PRIX = "PRIX";
	/** ID_FOU */
	private static String ID_FOU = "ID_FOU";

	//Request//
	///////////
	/** SELECT * FROM article */
	public static final String FIND_ALL_ARTICLES = "SELECT * FROM article";
	/** SELECT * FROM article WHERE article.DESIGNATION LIKE */
	public static final String FIND_THIS_ARTICLE = "SELECT * FROM article WHERE LCASE(article.DESIGNATION) LIKE LCASE(";

	/** UPDATE article SET article.DESIGNATION = UPPER(article.DESIGNATION) */
	public static final String UPDATE_TO_UPPER_CASE = "UPDATE article SET article.DESIGNATION = UPPER(article.DESIGNATION)";
	/** UPDATE article SET article.DESIGNATION = LOWER(article.DESIGNATION) */
	public static final String UPDATE_TO_LOWER_CASE = "UPDATE article SET article.DESIGNATION = LOWER(article.DESIGNATION)";
	/** UPDATE article SET article.DESIGNATION = ' */
	public static final String UPDATE_DESIGNATION = "UPDATE article SET article.DESIGNATION = '";

	/** INSERT INTO ARTICLE (ID,REF,DESIGNATION,PRIX,ID_FOU) VALUES( */
	public static final String INSERT_THIS_ARTICLE = "INSERT INTO ARTICLE (ID,REF,DESIGNATION,PRIX,ID_FOU ) VALUES( ";

	/** DELETE ARTICLE FROM ARTICLE WHERE ARTICLE.ID = */
	public static final String DELETE_THIS_ARTICLE = "DELETE ARTICLE FROM ARTICLE WHERE ARTICLE.ID = ";
	////////////
	/**
	 * Constructeur
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */
	public Article(ResultSet data) throws SQLException {

		this.id = data.getInt(ID);
		this.referenceString = data.getString(REF);
		this.designationString = data.getString(DESIGNATION);
		this.prix = data.getFloat(PRIX);
		this.idFournisseur = data.getInt(ID_FOU);
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter
	 * 
	 * @return the referenceString
	 */
	public String getReferenceString() {
		return referenceString;
	}

	/**
	 * Getter
	 * 
	 * @return the designationString
	 */
	public String getDesignationString() {
		return designationString;
	}

	/**
	 * Getter
	 * 
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * Getter
	 * 
	 * @return the idFournisseur
	 */
	public int getIdFournisseur() {
		return idFournisseur;
	}

	/**
	 * Setter
	 * 
	 * @param referenceString the referenceString to set
	 */
	public void setReferenceString(String referenceString) {
		this.referenceString = referenceString;
	}

	/**
	 * Setter
	 * 
	 * @param designationString the designationString to set
	 */
	public void setDesignationString(String designationString) {
		this.designationString = designationString;
	}

	/**
	 * Setter
	 * 
	 * @param prix the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
	 * Setter
	 * 
	 * @param idFournisseur the idFournisseur to set
	 */
	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}
	
	@Override
	public String toString() {
		
		return  new StringBuilder(this.id)
				.append("  ").append(this.referenceString)
					.append("  ").append(this.designationString)
					.append(" ").append(this.prix)
					.append("  ").append(this.idFournisseur)
					.toString()
					;
	}
	
	/**forme une requete d'inssertion pour un Article 
	 * dans une base de données 
	 * 
	 * @return INSERT INTO ARTICLE (ID,REF,DESIGNATION,PRIX,ID_FOU) 
	 * 								VALUES(
	 */
	public String getInsertStatement() {
		return new StringBuilder(INSERT_THIS_ARTICLE)
				.append(this.id)
				.append(",'").append(this.referenceString).append("'")
				.append(",'").append(this.designationString).append("'")
				.append(",").append(this.prix)
				.append(",").append(this.idFournisseur)	
				.append(")s")
				.toString();
		
	}
	/**
	 * @param nouvelleDesignation
	 * @return UPDATE article SET article.DESIGNATION = '
	 */
	public String updateDeignation(String nouvelleDesignation) {
		return new StringBuilder(UPDATE_DESIGNATION)
				.append(this.designationString)
				.append("'")
				.toString();
		
		
	}

}
