package Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import Model.Customer;
import Model.Reservation;

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

		// Test update method : success
		// updateTest();

		// Test update method2 : success
		// updateTest2();

		// Test select method : success
		// try {
		// selectTest();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Test delete method :
		// deleteTest();

		// success
		// insertReservationTest();

		// success
		// deleteReservationTest();

		// success
		// updateReservationTest();
	}

	public static void updateReservationTest() {
		ReservationCRUD crud = new ReservationCRUD();

		Date bookD = new Date();
		Date returnD = new Date();
		bookD.setTime(Calendar.getInstance().getTimeInMillis());
		returnD.setTime(Calendar.getInstance().getTimeInMillis() + 186400000);

		Reservation r = new Reservation();
		Reservation r2 = new Reservation();
		r.setId("11111");
		r.setCustomerId("11111");
		r.setBookId("11111");
		r.setBookDate(bookD);
		r.setReturnDate(returnD);
		r.setReturned(false);
		r2.setId("11111");
		r2.setCustomerId("11111");
		r2.setBookId("11111");
		r2.setBookDate(bookD);
		r2.setReturnDate(returnD);
		r2.setReturned(true);

		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = crud.updateReservation(r, r2);
			DBTool.closeConnection();

			if (res)
				System.out.println("Update Reservation with success !");
			else
				System.out.println("Update Reservation failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteReservationTest() {
		ReservationCRUD crud = new ReservationCRUD();
		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = crud.deleteReservation("11111");
			DBTool.closeConnection();

			if (res)
				System.out.println("Delete Reservation with success !");
			else
				System.out.println("Delete Reservation failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertReservationTest() {
		ReservationCRUD crud = new ReservationCRUD();

		Date bookD = new Date();
		Date returnD = new Date();
		bookD.setTime(Calendar.getInstance().getTimeInMillis());
		returnD.setTime(Calendar.getInstance().getTimeInMillis() + 186400000);

		Reservation r = new Reservation();
		r.setId("11111");
		r.setCustomerId("11111");
		r.setBookId("11111");
		r.setBookDate(bookD);
		r.setReturnDate(returnD);
		r.setReturned(false);

		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = crud.insertReservation(r);
			DBTool.closeConnection();

			if (res)
				System.out.println("Insert Reservation with success !");
			else
				System.out.println("Insert Reservation failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteTest() {
		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = DBTool.delete("customers", "WHERE lastname = 'Kim'");

			if (res)
				System.out.println("Delete with success !");
			else
				System.out.println("Delete failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateTest2() {

		HashMap<String, String> values = new HashMap<String, String>();
		HashMap<String, String> where = new HashMap<String, String>();

		values.put("firstname", "SanPang");
		values.put("lastname", "JIN");
		where.put("lastname", "Kim");
		where.put("firstname", "Jong-un");

		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = DBTool.update("customers", values, where);
			DBTool.closeConnection();

			if (res)
				System.out.println("Update with success !");
			else
				System.out.println("Update failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateTest() {

		HashMap<String, String> values = new HashMap<String, String>();
		HashMap<String, String> where = new HashMap<String, String>();

		values.put("firstname", "Jong-un");
		values.put("lastname", "Kim");
		where.put("id", "00005");

		try {
			DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
			boolean res = DBTool.update("customers", values, where);
			DBTool.closeConnection();

			if (res)
				System.out.println("Update with success !");
			else
				System.out.println("Update failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			DBTool.closeConnection();

			if (res)
				System.out.println("Insert with success !");
			else
				System.out.println("Insert failed !");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void selectTest() throws SQLException {

		DBTool.getConnection(DBTool.MYSQL, server, db, usr, pwd);
		String[] columns = { "lastname", "firstname" };

		ResultSet rs = DBTool.select("customers", columns, "WHERE lastname like 'Kim'");
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
