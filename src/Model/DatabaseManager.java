package Model;

import Persistence.BookCRUD;
import Persistence.CustomerCRUD;
import Persistence.ReservationCRUD;

/**
 * Proxy Pattern class
 * 
 * @author SONY
 *
 */
public class DatabaseManager {

	private final static DatabaseManager instance = new DatabaseManager();

	// Database configuration parameters
	// private static int dbType;
	// private static String server, db, usr, pwd;

	// Database operation classes
	public static final CustomerCRUD customerCRUD = new CustomerCRUD();
	public static final BookCRUD bookCRUD = new BookCRUD();
	public static final ReservationCRUD reservationCRUD = new ReservationCRUD();

	public static DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseManager() {
	}

	public static void configCRUDInstance(int dbType, String server, String db, String usr, String pwd) {
		bookCRUD.configDB(dbType, server, db, usr, pwd);
		customerCRUD.configDB(dbType, server, db, usr, pwd);
		reservationCRUD.configDB(dbType, server, db, usr, pwd);
	}

	// public static CustomerCRUD getCustomerCRUD() {
	// return customerCRUD;
	// }
	//
	// public static ReservationCRUD getReservationCRUD() {
	// return reservationCRUD;
	// }
	//
	// public static BookCRUD getBookCRUD() {
	// return bookCRUD;
	// }

}
