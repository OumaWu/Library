package Model;

import java.time.LocalDate;

public class Reservation {
	private String id;
	private String bookId;
	private String customerId;
	private LocalDate bookDate;
	private LocalDate returnDate;
	private boolean returned;

	public Reservation() {
	}

	public Reservation(String id, String bookId, String customerId, LocalDate bookDate, LocalDate returnDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.customerId = customerId;
		this.bookDate = bookDate;
		this.returnDate = returnDate;
		this.setReturned(false);
	}

	public Reservation(String id, String bookId, String customerId, LocalDate bookDate, LocalDate returnDate,
			boolean returned) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.customerId = customerId;
		this.bookDate = bookDate;
		this.returnDate = returnDate;
		this.setReturned(returned);
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
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param book
	 *            the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the bookDate
	 */
	public LocalDate getBookDate() {
		return bookDate;
	}

	/**
	 * @param bookDate
	 *            the bookDate to set
	 */
	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}

	/**
	 * @return the returnDate
	 */
	public LocalDate getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate
	 *            the returnDate to set
	 */
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public int getIntId() {
		return Integer.parseInt(this.id);
	}

}
