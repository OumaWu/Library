package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Customer;
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
	private static String nextId;
	private boolean result = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// setCustomerCRUD(DatabaseManager.bookCRUD);
		rbNovel.setUserData(rbNovel.getText());
		rbManuel.setUserData(rbManuel.getText());
		rbMagazine.setUserData(rbMagazine.getText());
	}

	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel;
	@FXML
	private TextField tfTItle, tfAuthor;

	private ComboBox<Customer> cbCustomer;

	@FXML
	private ToggleGroup status;
	@FXML
	private RadioButton rbNovel, rbManuel, rbMagazine;

	// public void insertBook() throws SQLException {
	// // ((RadioButton) bookType.getSelectedToggle()).getText()
	//
	// boolean result = false;
	// String title = tfTItle.getText();
	// String author = tfAuthor.getText();
	// // RadioButton selected = (RadioButton) bookType.getSelectedToggle();
	// String bookType = selected.getText().toUpperCase();
	//
	// if (title.isEmpty() || author.isEmpty()) {
	// WindowManager.getInstance().promptAlert("Please fill all the fields in
	// the form !");
	// } else {
	// Book book = null;
	// switch (bookType) {
	// case "NOVEL":
	// book = new Novel(nextId, title, author);
	// break;
	// case "MANUEL":
	// book = new Manuel(nextId, title, author);
	// break;
	// case "MAGAZINE":
	// book = new Magazine(nextId, title, author);
	// break;
	// }
	//
	// result = bookCRUD.insertBook(book);
	// WindowManager.getInstance().promptAlert("Create book " + (result ? "with
	// success !" : "failed"));
	// if (result)
	// this.close();
	// }
	// setResult(result);
	// }

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
}
