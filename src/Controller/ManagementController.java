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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManagementController implements Initializable {

	// Library instance
	private static Library library;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		library = new Library();
		DatabaseManager.configCRUDInstance(DBTool.MYSQL, "localhost", "library", "root", "111");

		try {
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

	public void openWindow() {
		if (customersTab.isSelected())
			openCustomerEditWindow();

		else if (booksTab.isSelected())
			;

		else if (reservationTab.isSelected())
			;
	}

	public void openCustomerEditWindow() {
		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/View/CustomerEdit.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Add a customer");
			stage.setScene(new Scene(root));
			CustomerEditController.setManagementController(this);
			stage.showAndWait();
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
		library.setCustomers(DatabaseManager.customerCRUD.retrieveCustomers());
		customersTable.getItems().clear();
		customersTable.getItems().addAll(library.getCustomers());
	}

	/**
	 * refresh the Books table
	 * 
	 * @throws SQLException
	 */
	public void loadBooksTable() throws SQLException {
		library.setBooks(DatabaseManager.bookCRUD.retrieveBooks());
		booksTable.getItems().clear();
		booksTable.getItems().addAll(library.getBooks());
	}

	/**
	 * refresh the Reservations table
	 * 
	 * @throws SQLException
	 */
	public void loadReservationsTable() throws SQLException {
		library.setReservation(DatabaseManager.reservationCRUD.retrieveReservations());
		reservationTable.getItems().clear();
		reservationTable.getItems().addAll(library.getReservation());
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

}
