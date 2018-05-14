package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Book;
import Model.Customer;
import Model.Library;
import Model.Reservation;
import Persistence.BookCRUD;
import Persistence.CustomerCRUD;
import Persistence.DBTool;
import Persistence.ReservationCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagementController implements Initializable {

	// Database configuration parameters
	private static int dbType;
	private static String server, db, usr, pwd;
	// Library instance
	private static Library library;
	// Database operation classes
	private static CustomerCRUD customerCRUD;
	private static BookCRUD bookCRUD;
	private static ReservationCRUD reservationCRUD;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dbType = DBTool.MYSQL;
		server = "localhost";
		db = "library";
		usr = "root";
		pwd = "111";

		bookCRUD = new BookCRUD(dbType, server, db, usr, pwd);
		customerCRUD = new CustomerCRUD(dbType, server, db, usr, pwd);
		reservationCRUD = new ReservationCRUD(dbType, server, db, usr, pwd);

		try {
			library = new Library(bookCRUD.retrieveBooks());
			library.setCustomers(customerCRUD.retrieveCustomers());
			library.setReservation(reservationCRUD.retrieveReservations());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialiseBooksTable();
		System.out.println("Management page is now loaded!");
	}

	@FXML
	private Tab customersTab, booksTab, reservationTab;

	@FXML
	private TableView<Customer> customersTable;
	@FXML
	private TableView<Book> booksTable;
	@FXML
	private TableView<Reservation> reservationTable;
	@FXML
	private TableColumn<Customer, String> cidColumn, fnColumn, lnColumn;
	@FXML
	private TableColumn<Book, String> bidColumn, titleColumn, authorColumn, categoryColumn, availabilityColumn;
	@FXML
	private TableColumn<Reservation, String> ridColumn, rbidColumn, rcidnColumn, bDate, rDate, stateColumn;

	public void initialiseCustomersTable() {

		if (customersTable.getItems().isEmpty()) {
			cidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			customersTable.getItems().addAll(library.getCustomers());
		}
	}

	public void initialiseBooksTable() {

		if (booksTable.getItems().isEmpty()) {
			bidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
			availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
			booksTable.getItems().addAll(library.getBooks());
		}
	}

	public void initialiseReservationTable() {
		if (reservationTable.getItems().isEmpty()) {
			ridColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			rbidColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
			rcidnColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
			bDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
			rDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
			stateColumn.setCellValueFactory(new PropertyValueFactory<>("returned"));
			reservationTable.getItems().addAll(library.getReservation());
		}
	}

	// Open the management window
	private void openManagementWindow() {

	}
}
