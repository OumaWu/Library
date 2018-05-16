package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Model.Book;
import Model.Library;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowManager {

	private static final WindowManager windowManager = new WindowManager();

	public static WindowManager getInstance() {
		return windowManager;
	}

	/**
	 * Open the management window
	 */
	public void openManagementWindow() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Management.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Books Management");
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ! Open failed !!");
			e.printStackTrace();
		}
	}

	/**
	 * Promt alert message when there's a invalid CURD operation
	 * 
	 * @param msg
	 */
	public void promptAlert(String msg) {
		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Alert.fxml"));
			root = loader.load();
			loader.<AlertController> getController().setMessage(msg);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Alert");
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error ! Open failed !!");
			e.printStackTrace();
		}
	}

	public boolean openCustomerEditWindow(String nextId) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerEdit.fxml"));
		Parent root = loader.load();
		CustomerEditController controller = loader.<CustomerEditController> getController();
		controller.setNextId(nextId);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Add a customer");
		stage.setScene(new Scene(root));
		stage.showAndWait();

		return controller.getResult();
	}

	public boolean openBookEditWindow(String nextId) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BookEdit.fxml"));
		Parent root = loader.load();
		BookEditController controller = loader.<BookEditController> getController();
		controller.setNextId(nextId);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Add a book");
		stage.setScene(new Scene(root));
		stage.showAndWait();

		return controller.getResult();
	}

	public boolean openReservationEditWindow(Library library) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Reservation.fxml"));
		Parent root = loader.load();
		ReservationEditController controller = loader.<ReservationEditController> getController();
		controller.setLibrary(library);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Create a book reservation");
		stage.setScene(new Scene(root));
		stage.showAndWait();

		return controller.getResult();
	}

	public String openBookSelectWindow(List<Book> books)
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SelectBook.fxml"));
		Parent root = loader.load();
		SelectBookController controller = loader.<SelectBookController> getController();
		controller.setBooks(books);
		controller.setBookListAndIds();
		controller.setBinding();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Select a book");
		stage.setScene(new Scene(root));
		stage.showAndWait();

		return controller.getSelectedBookId();
	}
}
