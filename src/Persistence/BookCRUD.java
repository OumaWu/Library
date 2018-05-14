package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Book;
import Model.Magazine;
import Model.Manuel;
import Model.Novel;

public class BookCRUD {

	private int dbType;
	private String server, db, usr, pwd;
	private static List<Book> books = new ArrayList<Book>();

	public BookCRUD(int dbType, String server, String db, String usr, String pwd) {
		this.dbType = dbType;
		this.server = server;
		this.db = db;
		this.usr = usr;
		this.pwd = pwd;
	}

	// Retrive books' list from database
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
