package Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import Model.Customer;

public class TestDBTool {

	static String server = "localhost";
	static String db = "library";
	static String usr = "root";
	static String pwd = "111";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test connection method : success
		// getConnectionTest();

		// Test selectAll method : success
		// try {
		// selectAllTest();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Test insert method : success
		// insertTest();

	}

	public static void insertTest() {

		Customer cus = new Customer("00006", "Cristiano", "Ronaldo");
		HashMap<String, String> values = new HashMap<String, String>();

		values.put("id", cus.getId());
		values.put("firstname", cus.getFirstName());
		values.put("lastname", cus.getLastName());

		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = DBTool.insert("customers", values);

			if (res)
				System.out.println("Insert with success !");
			else
				System.out.println("Insert failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void selectAllTest() throws SQLException {

		DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);

		ResultSet rs = DBTool.selectAll("customers");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		for (int i = 1; i <= columnsNumber; i++) {
			if (i > 1)
				System.out.print("  ");
			System.out.print(rsmd.getColumnName(i));
		}

		System.out.println("");

		while (rs.next()) {

			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print("\t");
				System.out.print(rs.getString(i));
			}
			System.out.println("");
		}
		DBTool.closeConnection();
	}

	public static void getConnectionTest() {

		Connection conn = DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
		if (conn != null) {
			System.out.println("Connection Test Success !");
		}
		DBTool.closeConnection();
	}

}
