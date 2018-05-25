package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Book;
import Model.Customer;
import Model.DatabaseManager;
import Model.Library;
import Model.Reservation;
import Persistence.DBTool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagementController implements Initializable {

	// Library instance
	private static Library library;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		library = new Library();
		DatabaseManager.configCRUDInstance(DBTool.MYSQL, "localhost", "library", "root", "111");

		try {
			retriveCustomers();
			retriveBooks();
			retriveReservations();
			initialiseCustomersTable();
			initialiseBooksTable();
			initialiseCustomersTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	@FXML
	private Button btAdd, btDelete, btSearch;

	/**
	 * Add button event handling
	 * 
	 * @throws SQLException
	 */
	public void openWindow() throws SQLException {
		if (customersTab.isSelected())
			openCustomerEditWindow();

		else if (booksTab.isSelected())
			openBookEditWindow();

		else if (reservationTab.isSelected())
			openReservationEditWindow();
	}

	/**
	 * Delete button event handling
	 * 
	 * @throws SQLException
	 */
	public void deleteOption() throws SQLException {
		if (customersTab.isSelected())
			deleteCustomer();

		else if (booksTab.isSelected())
			deleteBook();

		else if (reservationTab.isSelected())
			deleteReservation();
	}

	/**
	 * Delete a row selected from the book table
	 * 
	 * @throws SQLException
	 */
	public void deleteBook() throws SQLException {

		boolean result = false;
		String id = booksTable.getSelectionModel().getSelectedItem().getId();

		// Check if the customer's id is in the reservation list
		if (library.isReserved(id)) {
			WindowManager.getInstance().promptAlert("Error! The Book has reservation records, can't be deleted !");
		} else {
			result = DatabaseManager.bookCRUD.deleteBook(id);
		}
		if (result) {
			library.remove(booksTable.getSelectionModel().getSelectedItem());
			loadBooksTable();
		}
		WindowManager.getInstance().promptAlert("Delete book " + (result ? "with success !" : "failed !"));
	}

	/**
	 * Delete a row selected from the reservation table
	 * 
	 * @throws SQLException
	 */
	public void deleteReservation() throws SQLException {
		boolean result = false;
		String id = reservationTable.getSelectionModel().getSelectedItem().getId();
		result = DatabaseManager.reservationCRUD.deleteReservation(id);
		if (result) {
			library.remove(reservationTable.getSelectionModel().getSelectedItem());
			loadReservationsTable();
			retriveBooks();
			loadBooksTable();
		}
		WindowManager.getInstance().promptAlert("Delete reservation " + (result ? "with success !" : "failed !"));
	}

	/**
	 * Delete a row selected from the customer table
	 * 
	 * @throws SQLException
	 */
	public void deleteCustomer() throws SQLException {

		boolean result = false;
		String id = customersTable.getSelectionModel().getSelectedItem().getId();

		// Check if the customer's id is in the reservation list
		if (library.hasReservation(id)) {
			WindowManager.getInstance().promptAlert("Error! The customer has reservation records, can't be deleted !");
		} else {
			result = DatabaseManager.customerCRUD.deleteCustomer(id);
		}
		if (result) {
			library.remove(customersTable.getSelectionModel().getSelectedItem());
			loadCustomersTable();
		}
		WindowManager.getInstance().promptAlert("Delete customer " + (result ? "with success !" : "failed !"));
	}

	public void openBookEditWindow() throws SQLException {
		try {

			WindowManager manager = WindowManager.getInstance();
			boolean result = manager.openBookEditWindow(library.findNextBookId());

			if (result) {
				this.retriveBooks();
				this.loadBooksTable();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ! Open failed !!");
			e.printStackTrace();
		}
	}

	public void openReservationEditWindow() throws SQLException {
		WindowManager manager = WindowManager.getInstance();
		try {
			boolean result = manager.openReservationEditWindow(library);

			if (result) {
				this.retriveReservations();
				this.loadReservationsTable();
				this.retriveBooks();
				this.loadBooksTable();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openCustomerEditWindow() throws SQLException {
		try {
			WindowManager manager = WindowManager.getInstance();
			boolean result = manager.openCustomerEditWindow(library.findNextCustomerId());

			if (result) {
				this.retriveCustomers();
				this.loadCustomersTable();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ! Open failed !!");
			e.printStackTrace();
		}
	}

	/**
	 * refresh the customers table
	 * 
	 * @throws SQLException
	 */
	public void loadCustomersTable() throws SQLException {
		customersTable.getItems().clear();
		customersTable.getItems().addAll(library.getCustomers());
	}

	/**
	 * refresh the Books table
	 * 
	 * @throws SQLException
	 */
	public void loadBooksTable() throws SQLException {
		booksTable.getItems().clear();
		booksTable.getItems().addAll(library.getBooks());
	}

	/**
	 * refresh the Reservations table
	 * 
	 * @throws SQLException
	 */
	public void loadReservationsTable() throws SQLException {
		reservationTable.getItems().clear();
		reservationTable.getItems().addAll(library.getReservations());
	}

	/**
	 * Initialise the Customers table
	 * 
	 * @throws SQLException
	 */
	public void initialiseCustomersTable() throws SQLException {
		cidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		loadCustomersTable();
	}

	/**
	 * Initialise the Books table
	 * 
	 * @throws SQLException
	 */
	public void initialiseBooksTable() throws SQLException {

		bidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
		loadBooksTable();
	}

	/**
	 * Initialise the Reservations table
	 * 
	 * @throws SQLException
	 */
	public void initialiseReservationTable() throws SQLException {

		ridColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		rbidColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		rcidnColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		bDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		rDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<>("returned"));
		loadReservationsTable();
	}

	public void retriveCustomers() throws SQLException {
		library.setCustomers(DatabaseManager.customerCRUD.retrieveCustomers());
	}

	public void retriveBooks() throws SQLException {
		try {
			library.setBooks(DatabaseManager.bookCRUD.retrieveBooks());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void retriveReservations() throws SQLException {
		library.setReservations(DatabaseManager.reservationCRUD.retrieveReservations());
	}
}
