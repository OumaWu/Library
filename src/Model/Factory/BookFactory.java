package Model.Factory;

import Model.Book;

public class BookFactory {

	public static Book createBook(String type) {

		// Convert the type name to the complete
		// class name with correct format
		String bookClassName = "Model." + type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();

		// Java Reflection
		Book book = null;
		try {
			book = (Book) Class.forName(bookClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return book;
	}
}
