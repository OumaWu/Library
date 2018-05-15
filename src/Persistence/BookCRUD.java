package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Book;
import Model.Magazine;
import Model.Manuel;
import Model.Novel;

public class BookCRUD extends CRUDoperations {

	private static List<Book> books;

	public BookCRUD() {
		books = new ArrayList<Book>();
	}

	public boolean deleteBook(String id) throws SQLException {
		boolean result = false;

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.delete("books", "WHERE id = " + id);
		DBTool.closeConnection();
		return result;
	}

	public boolean insertBook(Book book) throws SQLException {
		boolean result = false;
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("id", book.getId());
		values.put("title", book.getTitle());
		values.put("author", book.getAuthor());
		values.put("category", book.getCategory());
		values.put("availability", String.valueOf(book.getAvailability() ? 1 : 0));

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.insert("books", values);
		DBTool.closeConnection();
		return result;
	}

	// Retrive book list from database
	public List<Book> retrieveBooks() throws SQLException {

		DBTool.getConnection(dbType, server, db, usr, pwd);
		ResultSet rs = DBTool.selectAll("books");
		books.clear();

		while (rs.next()) {
			Book book = null;
			switch (rs.getString("category").toUpperCase()) {
			case "NOVEL":
				book = new Novel(rs.getString("id"), rs.getString("title"), rs.getString("author"),
						rs.getBoolean("availability"));
				break;
			case "MANUEL":
				book = new Manuel(rs.getString("id"), rs.getString("title"), rs.getString("author"),
						rs.getBoolean("availability"));
				break;
			case "MAGAZINE":
				book = new Magazine(rs.getString("id"), rs.getString("title"), rs.getString("author"),
						rs.getBoolean("availability"));
				break;
			}
			if (book != null)
				books.add(book);
		}
		DBTool.closeConnection();
		return books;
	}
}
