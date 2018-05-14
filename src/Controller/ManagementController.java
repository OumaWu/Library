package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Customer;
import Model.Library;
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
	private TableView<Customer> customersTable;
	@FXML
	private TableColumn<Customer, String> idColumn, fnColumn, lnColumn;

	public void initialiseCustomerTable() {
		if (customersTable.getItems().isEmpty()) {
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			fnColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			lnColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			customersTable.getItems().addAll(library.getCustomers());
		}
	}

	// Open the management window
	private void openManagementWindow() {

	}
}
