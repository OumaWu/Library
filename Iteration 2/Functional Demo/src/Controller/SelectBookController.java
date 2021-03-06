package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Book;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SelectBookController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		booksProperty = new SimpleListProperty<>();
		bookIdsProperty = new SimpleListProperty<>();
		bookIds = new ArrayList<String>();
	}

	@FXML
	private BorderPane layout;
	@FXML
	private Button btOk, btCancel;
	@FXML
	private Label lbTitle;
	@FXML
	private ListView<String> bookList;
	private List<Book> books;
	private List<String> bookIds;
	private ListProperty<String> booksProperty;
	private ListProperty<String> bookIdsProperty;
	private String SelectedBookId = "";

	// public void test() {
	// System.out.println(bookIdsProperty.get(bookList.getSelectionModel().getSelectedIndex())
	// + " : "
	// + bookList.getSelectionModel().getSelectedItem());
	// }

	public void setSelectedBookId() {
		if (!bookList.getSelectionModel().isEmpty()) {
			SelectedBookId = bookIdsProperty.get(bookList.getSelectionModel().getSelectedIndex());
			close();
		} else {
			WindowManager.getInstance().promptAlert("Please select a book!");
		}
	}

	public String getSelectedBookId() {
		// return the selected book's id;
		return SelectedBookId;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void setBookListAndIds() {
		for (Book book : this.books) {
			bookList.getItems().add(book.toString());
			bookIds.add(book.getId());
		}
	}

	public void setLabel(String type) {
		lbTitle.setText(type);
	}

	public void setBinding() {
		booksProperty.set(bookList.getItems());
		bookIdsProperty.set(FXCollections.observableArrayList(bookIds));
		// Bind the books' ids to the book list
		booksProperty.bind(bookIdsProperty);
	}

	public void close() {
		((Stage) this.layout.getScene().getWindow()).close();
	}
}
