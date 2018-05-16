package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Customer;
import Model.Library;
import Persistence.ReservationCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReservationEditController implements Initializable {

	// Database operation classes
	private static ReservationCRUD reservationCRUD;
	private static Library library;
	private static String nextId;
	private boolean result = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// setCustomerCRUD(DatabaseManager.bookCRUD);
	}

	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel, btSelectBook;
	@FXML
	private TextField tfTItle, tfAuthor;

	private ComboBox<Customer> cbCustomer;

	@FXML
	private ToggleGroup status;
	@FXML
	private RadioButton rbNovel, rbManuel, rbMagazine;

	public void openSelectBookWindow() {
		try {
			WindowManager.getInstance().openBookSelectWindow(library.getBooksByType("Novel"));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		((Stage) this.layout.getScene().getWindow()).close();
	}

	public void setCustomerCRUD(ReservationCRUD reservationCRUD) {
		ReservationEditController.reservationCRUD = reservationCRUD;
	}

	public void setNextId(String nextId) {
		ReservationEditController.nextId = nextId;
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

	public void setLibrary(Library library) {
		ReservationEditController.library = library;
	}
}
