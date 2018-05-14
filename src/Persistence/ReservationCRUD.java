package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Reservation;

public class ReservationCRUD {

	private int dbType;
	private String server, db, usr, pwd;
	private static List<Reservation> reservations = new ArrayList<Reservation>();

	public ReservationCRUD(int dbType, String server, String db, String usr, String pwd) {
		this.dbType = dbType;
		this.server = server;
		this.db = db;
		this.usr = usr;
		this.pwd = pwd;
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
