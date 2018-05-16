package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.DatabaseManager;
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
	private String bookId = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setReservationCRUD(DatabaseManager.reservationCRUD);
	}

	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel, btSelectBook;
	@FXML
	private TextField tfTItle, tfAuthor;
	@FXML
	private ComboBox<String> cbCustomer, cbBookType;

	@FXML
	private ToggleGroup status;
	@FXML
	private RadioButton rbNovel, rbManuel, rbMagazine;

	/**
	 * Open the book selection window with the type selected in the combobox
	 */
	public void openSelectBookWindow() {
		try {
			String bookType = cbBookType.getSelectionModel().getSelectedItem();
			bookId = WindowManager.getInstance().openBookSelectWindow(library.getBooksByType(bookType));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bookId);
	}

	public void setComboBoxValues() {
		cbBookType.getItems().addAll(library.getBookTypes());
		cbBookType.setPromptText(cbBookType.getItems().get(0));
	}

	public void close() {
		((Stage) this.layout.getScene().getWindow()).close();
	}

	public void setReservationCRUD(ReservationCRUD reservationCRUD) {
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
