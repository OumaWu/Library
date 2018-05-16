package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Model.Book;
import Model.DatabaseManager;
import Persistence.BookCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SelectBookController implements Initializable {

	private static BookCRUD bookCRUD;
	private List<Book> books;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setBookCRUD(DatabaseManager.bookCRUD);
	}

	@FXML
	private ListView<Object> bookList;
	@FXML
	private Button btOk;
	private Book book;

	public void setSelectedBook() {

	}

	public Book getSelectedBook() {
		return book;
	}

	/**
	 * @param bookCRUD
	 *            the bookCRUD to set
	 */
	public void setBookCRUD(BookCRUD bookCRUD) {
		SelectBookController.bookCRUD = bookCRUD;
	}

	public void loadBooksByType(String bookType) {
		try {
			books = bookCRUD.retrieveBooksByType(bookType);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
