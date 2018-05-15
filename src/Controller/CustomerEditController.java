package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Customer;
import Model.DatabaseManager;
import Model.Library;
import Persistence.CustomerCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomerEditController implements Initializable {

	// Database operation classes
	private static ManagementController management;
	private static CustomerCRUD customerCRUD;
	private static String nextId;
	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel;
	@FXML
	private TextField tfFirstName, tfLastName;

	public static void setManagementController(ManagementController management) {
		CustomerEditController.management = management;
	}

	public void insertCustomer() throws SQLException {
		nextId = Library.findNextCustomerId();
		Customer customer = new Customer(nextId, tfFirstName.getText(), tfLastName.getText());
		boolean result = customerCRUD.insertCustomer(customer);

		System.out.println("Create customer " + (result ? "with success !" : "failed"));
		this.close();
		// refresh the customers table after insertion
		if (result) {
			management.retriveCustomers();
			management.loadCustomersTable();
		}
	}

	public void close() {
		((Stage) this.layout.getScene().getWindow()).close();
	}

	public void initialize(URL Location, ResourceBundle resources) {
		setCustomerCRUD(DatabaseManager.customerCRUD);
	}

	public static void setCustomerCRUD(CustomerCRUD customerCRUD) {
		CustomerEditController.customerCRUD = customerCRUD;
	}

}
