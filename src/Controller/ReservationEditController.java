package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Book;
import Model.Customer;
import Model.DatabaseManager;
import Model.Library;
import Model.Reservation;
import Persistence.ReservationCRUD;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
	private String SelectedCustomerId = "";
	private String SelectedbookId = "";
	private List<String> customerIds;
	private ListProperty<String> customerProperty;
	private ListProperty<String> customerIdsProperty;
	private LocalDate bookDate;
	private LocalDate returnDate;
	private boolean isReturned;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setReservationCRUD(DatabaseManager.reservationCRUD);
		customerProperty = new SimpleListProperty<>();
		customerIdsProperty = new SimpleListProperty<>();
		customerIds = new ArrayList<String>();
	}

	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel, btSelectBook;
	@FXML
	private Label lbBook;
	@FXML
	private TextField tfTItle, tfAuthor;
	@FXML
	private ComboBox<String> cbBookType, cbCustomer;
	@FXML
	private DatePicker dpBookDate, dpRetunDate;
	@FXML
	private ToggleGroup status;
	@FXML
	private RadioButton rbBorrowing, rbReturn;

	/**
	 * Method to insert the reservation
	 */
	public void insertReservation() {
		WindowManager manager = WindowManager.getInstance();
		if (SelectedCustomerId.isEmpty()) {
			manager.promptAlert("Please select a customer!");
		} else if (SelectedbookId.isEmpty()) {
			manager.promptAlert("Please select a book!");
		} else if (bookDate == null) {
			manager.promptAlert("Please select a booking date!");
		} else if (returnDate == null) {
			manager.promptAlert("Please select a return date!");
		} else if (returnDate.isBefore(bookDate)) {
			manager.promptAlert("Error! The return date you've selected is earlier"
					+ " than the booking date.\nPlease select a valid return date!");
		} else {
			nextId = library.findNextReservationId();
			Reservation reservation = new Reservation(nextId, SelectedbookId, SelectedCustomerId, bookDate, returnDate,
					isReturned);
			try {
				result = reservationCRUD.insertReservation(reservation);
				if (result) {
					manager.promptAlert("Create reservation with success!");
					close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Open the book selection window with the type selected in the combobox
	 */
	public void openSelectBookWindow() {
		try {
			String bookType = cbBookType.getSelectionModel().getSelectedItem();
			List<Book> books = library.getAvailableBooksByType(bookType);
			if (!books.isEmpty()) {
				SelectedbookId = WindowManager.getInstance().openBookSelectWindow(books, bookType);
				if (!SelectedbookId.isEmpty())
					lbBook.setText(library.getBook(SelectedbookId).getTitle());
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setComboBoxValues() {
		cbBookType.getItems().addAll(library.getBookTypes());
		for (Customer customer : library.getCustomers()) {
			cbCustomer.getItems().add(customer.toString());
			customerIds.add(customer.getId());
		}
	}

	public void rbBorrowingAction() {
		isReturned = false;
	}

	public void rbReturnAction() {
		isReturned = true;
	}

	public void setBookDate() {
		bookDate = dpBookDate.getValue();
	}

	public void setReturnDate() {
		returnDate = dpRetunDate.getValue();
	}

	public void setSelectedCusomerId() {
		if (!cbCustomer.getSelectionModel().isEmpty()) {
			SelectedCustomerId = customerIdsProperty.get(cbCustomer.getSelectionModel().getSelectedIndex());
		}
	}

	public void close() {
		((Stage) this.layout.getScene().getWindow()).close();
	}

	public void setReservationCRUD(ReservationCRUD reservationCRUD) {
		ReservationEditController.reservationCRUD = reservationCRUD;
	}

	public void setBinding() {
		customerProperty.set(cbCustomer.getItems());
		customerIdsProperty.set(FXCollections.observableArrayList(customerIds));
		// Bind the customers' ids to the customer list
		customerProperty.bind(customerIdsProperty);
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
