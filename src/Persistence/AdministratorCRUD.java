package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Administrator;
import Model.Customer;

public class AdministratorCRUD extends CRUDoperations {

	private static String administrator;

	public AdministratorCRUD() {
		administrator = null;
	}
	
	/**
	 * Check if login informations are correct
	 * @param login
	 * @param pwdSQL
	 * @return
	 * @throws SQLException
	 */
	public boolean loginAdministrator(String login, String pwdSQL) throws SQLException {
		boolean resultBool = false;
		ResultSet result = null;
		
		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.select("administrators", "*", "WHERE login = '" + login + "' and password = '" + pwdSQL + "'");
		
		if(result.next()) {
			resultBool = true;
			administrator = result.getString("login");
		}
		
		DBTool.closeConnection();
		return resultBool;
	}
	
}
