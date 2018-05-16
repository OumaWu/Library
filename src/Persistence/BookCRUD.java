package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Book;

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
	public List<Book> retrieveBooks()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		DBTool.getConnection(dbType, server, db, usr, pwd);
		ResultSet rs = DBTool.selectAll("books");
		books.clear();

		while (rs.next()) {
			String bookType = rs.getString("category").toLowerCase();
			String className = "Model." + bookType.substring(0, 1).toUpperCase() + bookType.substring(1);

			System.out.println(className);

			// Java Reflection
			Book book = (Book) Class.forName(className).newInstance();
			book.setId(rs.getString("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setCategory(rs.getString("category"));
			book.setAvailability(rs.getBoolean("availability"));

			if (book != null)
				books.add(book);
		}
		DBTool.closeConnection();
		return books;
	}

	public List<Book> retrieveBooksByType(String type)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		DBTool.getConnection(dbType, server, db, usr, pwd);
		ResultSet rs = DBTool.select("books", "*", "WHERE type like " + type);
		books.clear();
		// Cast the passed type string to class name with package
		String className = "Model." + type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();

		while (rs.next()) {
			Book book = (Book) Class.forName(className).newInstance();
			book.setId(rs.getString("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setCategory(rs.getString("category"));
			book.setAvailability(rs.getBoolean("availability"));
			if (book != null)
				books.add(book);
		}
		DBTool.closeConnection();
		return books;
	}
}
