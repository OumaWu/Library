package Model;

import java.sql.Date;

public class Reservation {
	private String id;
	private Book book;
	private Customer customer;
	private Date bookDate;
	private Date returnDate;

	public Reservation(String id, Book book, Customer customer, Date bookDate, Date returnDate) {
		super();
		this.id = id;
		this.book = book;
		this.customer = customer;
		this.bookDate = bookDate;
		this.returnDate = returnDate;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book
	 *            the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the bookDate
	 */
	public Date getBookDate() {
		return bookDate;
	}

	/**
	 * @param bookDate
	 *            the bookDate to set
	 */
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate
	 *            the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
