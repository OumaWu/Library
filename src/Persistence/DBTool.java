package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBTool {
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	private static DBTool dbInstance;
	private static int dbType;
	private static String driver;
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;

	private DBTool(int dbType) throws Exception {

		// Verify database type validity
		if (dbType != MYSQL && dbType != ORACLE) {
			throw new Exception("Error ! Please select a valid database type value ! (MYSQL : 1, ORACLE : 2)");
		}

		DBTool.dbType = dbType;
		// Assign different driver type string to driver;
		driver = dbType == MYSQL ? "com.mysql.jdbc.Driver" : "oracle.jdbc.OracleDriver";
	}

	/**
	 * Delete Operation
	 * 
	 * @param table
	 * @param where
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean delete(String table, String where) throws SQLException {
		boolean result = false;

		String deleteStmt = "DELETE FROM " + table;
		deleteStmt += " " + where;

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(deleteStmt);
			result = (pstmt.executeUpdate() > 0 ? true : false);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				System.err.print("Transaction is being rolled back");
				conn.rollback();
			}
		} finally {
			// conn.setAutoCommit(true);
			closeConnection();
		}

		return result;
	}

	/**
	 * Update operation
	 * 
	 * @param table
	 * @param values
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean update(String table, HashMap<String, String> values, HashMap<String, String> where)
			throws SQLException {
		boolean result = false;

		// Add the table name to the statement
		String updateStmt = "UPDATE " + table + " SET ";

		// Add the columns to the statement
		updateStmt += String.join(" = ?, ", values.keySet()) + " = ? ";

		// Where statement
		updateStmt += "WHERE " + String.join(" = ? AND ", where.keySet()) + " = ?";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(updateStmt);

			// Bind values
			int i = 1;
			for (String value : values.values()) {
				pstmt.setString(i++, value);
			}
			for (String value : where.values()) {
				pstmt.setString(i++, value);
			}

			result = (pstmt.executeUpdate() > 0 ? true : false);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				System.err.print("Transaction is being rolled back");
				conn.rollback();
			}
		} finally {
			// conn.setAutoCommit(true);
			closeConnection();
		}

		return result;
	}

	/**
	 * Insert operation
	 * 
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean insert(String table, HashMap<String, String> values) throws SQLException {

		boolean result = false;

		// Add the table name to the statement
		String insertStmt = "INSERT INTO " + table;

		// Add the columns to the statement
		insertStmt += " (" + String.join(",", values.keySet()) + ")";

		// Add the values to the statement
		insertStmt += " VALUES ('" + String.join("','", values.values()) + "')";

		System.out.println(insertStmt);

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insertStmt);

			result = (pstmt.executeUpdate() > 0 ? true : false);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				System.err.print("Transaction is being rolled back");
				conn.rollback();
			}
		} finally {
			// conn.setAutoCommit(true);
			closeConnection();
		}

		return result;
	}

	/**
	 * Select some columns from a table
	 * 
	 * @param table
	 * @param column
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet select(String table, String[] columns, String where) throws SQLException {

		ResultSet res = null;
		String selectStmt = "SELECT ";
		selectStmt += String.join(", ", columns);
		selectStmt += " FROM " + table;
		selectStmt += " " + where;

		System.out.println(selectStmt);

		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(selectStmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Select a column from a table
	 * 
	 * @param table
	 * @param column
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet select(String table, String column, String where) throws SQLException {

		ResultSet res = null;
		String selectStmt = "SELECT ";
		selectStmt += column;
		selectStmt += " FROM " + table;
		selectStmt += " " + where;

		System.out.println(selectStmt);

		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(selectStmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Select all rows from a table given
	 * 
	 * @param table
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet selectAll(String table) throws SQLException {

		ResultSet res = null;
		String selectStmt = "SELECT * FROM " + table;

		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(selectStmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Close the connection when done CRUD operations
	 */
	public static void closeConnection() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n********************Connection closed successfully !********************\n");
	}

	/**
	 * Get connection from database authentication parameters
	 * 
	 * @param dbType
	 * @param server
	 * @param db
	 * @param usr
	 * @param pwd
	 * @return Connection
	 */
	public static Connection getConnection(int dbType, String server, String db, String usr, String pwd) {

		try {
			// Create an dbInstance for operation
			if (dbInstance == null) {
				dbInstance = new DBTool(dbType);
			}
			// Load the database Driver
			Class.forName(driver).newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Driver O.K.");

		String url = "jdbc:mysql://" + server + "/" + db + "?useSSL=true";
		String user = usr;
		String passwd = pwd;

		if (dbType == MYSQL) {
			url = "jdbc:mysql://" + server + "/" + db + "?useSSL=true";
		} else {
			url = "jdbc:oracle:thin:@" + server + "/" + db + ":1521:orcl";
		}

		try {
			conn = (Connection) DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n********************Connexion successful !********************\n");

		return conn;
	}

	public static int getDbType() {
		return dbType;
	}

	public static void setDbType(int dbType) {
		DBTool.dbType = dbType;
	}

	/**
	 * @return the driver
	 */
	public static String getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 *            the driver to set
	 */
	public static void setDriver() {
		driver = dbType == MYSQL ? "com.mysql.jdbc.Driver" : "oracle.jdbc.OracleDriver";
	}

}
