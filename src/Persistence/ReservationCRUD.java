package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Reservation;

public class ReservationCRUD extends CRUDoperations {

	private static List<Reservation> reservations;

	public ReservationCRUD() {
		reservations = new ArrayList<Reservation>();
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
			reservation.setBookDate(rs.getDate("book_date"));
			reservation.setReturnDate(rs.getDate("return_date"));
			reservation.setReturned(rs.getBoolean("returned"));
			reservations.add(reservation);
		}
		DBTool.closeConnection();
		return reservations;
	}
}
