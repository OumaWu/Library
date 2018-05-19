package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import Controller.WindowManager;

public class AdministratorCRUD extends CRUDoperations {

	private static String administrator;

	public AdministratorCRUD() {
		administrator = null;
	}

	/**
	 * Check if login informations are correct
	 * 
	 * @param login
	 * @param pwdSQL
	 * @return
	 * @throws SQLException
	 */
	public boolean loginAdministrator(String login, String pwdSQL) throws SQLException {
		boolean resultBool = false;
		ResultSet result = null;

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.select("administrators", "*", "WHERE login = '" + login + "'");

		if (result.next()) {
			if (pwdSQL.equals(result.getString("password"))) {
				resultBool = true;
				administrator = result.getString("login");
				WindowManager.getInstance().promptAlert("Welcome, " + administrator + "!");
			}
		}

		DBTool.closeConnection();
		return resultBool;
	}

}
