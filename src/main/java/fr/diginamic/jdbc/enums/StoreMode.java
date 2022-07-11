package fr.diginamic.jdbc.enums;

import fr.diginamic.jdbc.dao.IFournisseurDAO;

import java.util.ResourceBundle;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

/**
 * Utilisé pour découpler le connecteur du type de stockage en back-end
 * 
 * @author Vincent
 *
 */
public enum StoreMode {
	/**
	 * Pour un stockage en base de donnée
	 * 
	 *
	 */
	JDBC {
		/** DB_URL */
		private String DB_URL;
		/** DB_USER */
		private String DB_USER;
		/** DB_PWD */
		private String DB_PWD;
		{

			// on recupére un .properties dans le répertoire resources
			ResourceBundle dbProperties = ResourceBundle.getBundle("db");

			DB_URL = "jdbc:" + dbProperties.getString("jdbc.db.type") + "://" + dbProperties.getString("jdbc.db.adress")
					+ ":" + dbProperties.getString("jdbc.db.port") + "/" + dbProperties.getString("jdbc.db.name");
			DB_USER = dbProperties.getString("jdbc.db.user");
			DB_PWD = dbProperties.getString("jdbc.db.password");

		}

		public IFournisseurDAO getFournisseurStore() {
			return new FournisseurDaoJdbc();
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub

		}

		@Override
		public void close() {
			// TODO Auto-generated method stub

		}

	},
	/**
	 * Pour un stockage en XML
	 */
	XML {
		public IFournisseurDAO getFournisseurStore() {
			return null;
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub

		}

		@Override
		public void close() {
			// TODO Auto-generated method stub

		}

	};

	public abstract void init();

	public abstract void close();

	public abstract IFournisseurDAO getFournisseurStore();

}
