package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Reservation;

public class ReservationCRUD extends CRUDoperations {

	private static List<Reservation> reservations;

	public ReservationCRUD() {
		reservations = new ArrayList<Reservation>();
	}

	public boolean updateReservation(Reservation oldR, Reservation newR) throws SQLException {
		boolean result = false;
		HashMap<String, String> values = new HashMap<String, String>();
		HashMap<String, String> where = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		values.put("id", newR.getId());
		values.put("book_id", newR.getBookId());
		values.put("customer_id", newR.getCustomerId());
		values.put("book_date", formatter.format(newR.getBookDate()));
		values.put("return_date", formatter.format(newR.getReturnDate()));
		values.put("returned", String.valueOf(newR.isReturned() ? 1 : 0));

		where.put("id", oldR.getId());

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.update("book_reservations", values, where);
		DBTool.closeConnection();
		return result;
	}

	public boolean deleteReservation(String id) throws SQLException {
		boolean result = false;

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.delete("book_reservations", "WHERE id = " + id);
		DBTool.closeConnection();
		return result;
	}

	public boolean insertReservation(Reservation reservation) throws SQLException {
		boolean result = false;
		HashMap<String, String> values = new HashMap<String, String>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		values.put("id", reservation.getId());
		values.put("book_id", reservation.getBookId());
		values.put("customer_id", reservation.getCustomerId());
		values.put("book_date", reservation.getBookDate().toString());
		values.put("return_date", reservation.getReturnDate().toString());
		values.put("returned", String.valueOf(reservation.isReturned() ? 1 : 0));

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.insert("book_reservations", values);
		DBTool.closeConnection();
		return result;
	}

	// Retrive customers' information from database and return a list
	public List<Reservation> retrieveReservations() throws SQLException {

		DBTool.getConnection(dbType, server, db, usr, pwd);
		ResultSet rs = DBTool.selectAll("book_reservations");
		reservations.clear();

		while (rs.next()) {
			Reservation reservation = new Reservation();
			reservation.setId(rs.getString("id"));
			reservation.setBookId(rs.getString("book_id"));
			reservation.setCustomerId(rs.getString("customer_id"));
			reservation.setBookDate(rs.getDate("book_date").toLocalDate());
			reservation.setReturnDate(rs.getDate("return_date").toLocalDate());
			reservation.setReturned(rs.getBoolean("returned"));
			reservations.add(reservation);
		}
		DBTool.closeConnection();
		return reservations;
	}
}
