package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Customer;
import Model.DatabaseManager;
import Persistence.CustomerCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomerEditController implements Initializable {

	// Database operation classes
	private static CustomerCRUD customerCRUD;
	private static String nextId;
	private boolean result = false;
	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel;
	@FXML
	private TextField tfFirstName, tfLastName;

	public void insertCustomer() throws SQLException {
		boolean result = false;
		String firstName = tfFirstName.getText();
		String lastName = tfLastName.getText();
		if (firstName.isEmpty() || lastName.isEmpty()) {
			WindowManager.getInstance().promptAlert("Please fill all the fields in the form !");
		} else {
			Customer customer = new Customer(nextId, firstName, lastName);
			result = customerCRUD.insertCustomer(customer);
			WindowManager.getInstance().promptAlert("Create customer " + (result ? "with success !" : "failed"));
			if (result)
				this.close();
		}
		setResult(result);
	}

	public void close() {
		((Stage) this.layout.getScene().getWindow()).close();
	}

	public void initialize(URL Location, ResourceBundle resources) {
		setCustomerCRUD(DatabaseManager.customerCRUD);
	}

	public void setCustomerCRUD(CustomerCRUD customerCRUD) {
		CustomerEditController.customerCRUD = customerCRUD;
	}

	public void setNextId(String nextId) {
		CustomerEditController.nextId = nextId;
	}

	/**
	 * @return the result
	 */
	public boolean getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

}
