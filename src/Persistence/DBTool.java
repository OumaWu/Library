package Persistence;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBTool {
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	private static DBTool dbInstance;
	private static int dbType;
	private static String driver;
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet res;

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
		insertStmt += "VALUES ('" + String.join("','", values.values()) + "')";

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
			if (pstmt != null) {
				pstmt.close();
			}
			conn.setAutoCommit(true);
		}

		return result;
	}

	/**
	 * Select all rows from a table given
	 * 
	 * @param table
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet selectAll(String table) throws SQLException {

		String selectStmt = "SELECT * FROM " + table;

		try {
			pstmt = conn.prepareStatement(selectStmt);
			res = pstmt.executeQuery(selectStmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// finally {
		// if (pstmt != null) {
		// pstmt.close();
		// }
		// }
		return res;
	}

	/**
	 * Close the connection when done CRUD operations
	 */
	public static void closeConnection() {
		try {
			if (res != null)
				res.close();
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

		String url = "";
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
