package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Book;
import Model.Customer;
import Model.Library;
import Model.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagementController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			library.retriveCustomer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Management page is now loaded!");
	}

	private static Library library = new Library();
	@FXML
	private Tab customersTab;

	@FXML
	private TableView<Customer> customersTable, booksTable, reservationTable;
	@FXML
	private TableColumn<Customer, String> cidColumn, fnColumn, lnColumn;
	@FXML
	private TableColumn<Book, String> bidColumn, titleColumn, authorColumn, categoryColumn, availabilityColumn;
	@FXML
	private TableColumn<Reservation, String> ridColumn, rbidColumn, rcidnColumn, bDate, rDate;

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
			fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			customersTable.getItems().addAll(library.getCustomers());
		}
	}

	public void initialiseReservationTable() {
		if (reservationTable.getItems().isEmpty()) {
			ridColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			customersTable.getItems().addAll(library.getCustomers());
		}
	}

	// Open the management window
	private void openManagementWindow() {

	}
}
