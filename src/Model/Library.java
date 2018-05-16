package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Library implements Cloneable, Iterable<Book> {

	private List<Book> books;
	private List<Customer> customers;
	private List<Reservation> reservations;

	public Library() {
		this.books = new ArrayList<Book>();
		this.setCustomers(new ArrayList<Customer>());
		this.setReservations(new ArrayList<Reservation>());
	}

	public Library(List<Book> books) {
		this.books = books;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book getBook(String id) {
		for (Book book : this) {
			if (book.getId().equals(id)) {
				return book;
			}
		}
		return null;
	}

	public boolean add(Book book) {
		if (book != null && !this.books.contains(book)) {
			return this.books.add(book);
		}
		return false;
	}

	/**
	 *
	 */
	public boolean remove(String id) {
		return this.books.remove(getBook(id));
	}

	/**
	 *
	 */
	public boolean remove(Book book) {
		return this.books.remove(book);
	}

	public boolean remove(Customer customer) {
		return this.customers.remove(customer);
	}

	public boolean remove(Reservation reservation) {
		return this.reservations.remove(reservation);
	}

	/**
	 * supprimer une liste de documents
	 */
	public boolean removeAll(List<Book> books) {
		return this.books.removeAll(books);
	}

	public boolean isReserved(String id) {
		boolean result = false;
		for (Reservation re : this.reservations) {
			if (id.equals(re.getBookId()))
				result = true;
		}
		return result;
	}

	public boolean hasReservation(String id) {
		boolean result = false;
		for (Reservation re : this.reservations) {
			if (id.equals(re.getCustomerId()))
				result = true;
		}
		return result;
	}

	public List<String> getBookTypes() {
		List<String> bookTypes = new ArrayList<String>();
		for (Book book : this.books) {
			// Avoid the duplicated category
			if (!bookTypes.contains(book.getCategory()))
				bookTypes.add(book.getCategory());
		}
		return bookTypes;
	}

	public List<Book> getBooksByType(String type) {
		List<Book> books = new ArrayList<Book>();
		for (Book book : this.books) {
			if (book.getCategory().equalsIgnoreCase(type))
				books.add(book);
		}
		return books;
	}

	/**
	 * find the next id of books
	 * 
	 * @return lastId + 1
	 */
	public String findNextBookId() {
		int lastId = 0;
		for (Book book : books) {
			if (lastId < book.getIntId())
				lastId = book.getIntId();
		}
		return convertId(lastId + 1);
	}

	/**
	 * find the next id of customers
	 * 
	 * @return lastId + 1
	 */
	public String findNextCustomerId() {
		int lastId = 0;
		for (Customer customer : customers) {
			if (lastId < customer.getIntId())
				lastId = customer.getIntId();
		}
		return convertId(lastId + 1);
	}

	/**
	 * Convert an int id to database format
	 * 
	 * @param nextId
	 * @return StringId
	 */
	private static String convertId(int nextId) {
		String id = String.valueOf(nextId);
		int zeros = 5 - String.valueOf(nextId).length();
		for (int i = 0; i < zeros; i++) {
			id = ("0" + id);
		}
		return id;
	}

	/**
	 * recherche de documents par titre
	 * 
	 * @param title
	 */
	public List<Book> findByTitle(String title) {
		List<Book> res = new ArrayList<Book>();
		for (Book book : this) {
			if (book.getTitle().equals(title)) {
				res.add(book);
			}
		}
		return res;
	}

	/**
	 * recherche de documents par titre
	 * 
	 * @param author
	 */
	public List<Book> findByAuthor(String author) {
		List<Book> res = new ArrayList<Book>();
		for (Book book : this) {
			if (book.getAuthor().equals(author)) {
				res.add(book);
			}
		}
		return res;
	}

	/**
	 * Opérer un tri lexicographique des documents de la bibliothèque
	 */
	public void sort() {
		Collections.sort(this.books);
	}

	/**
	 * Réaliser un clonage profond de votre bibliothèque
	 */
	@Override
	public Library clone() {
		Library clone = new Library();

		for (Book book : books) {
			clone.books.add(book.clone());
		}
		return clone;
	}

	@Override
	public Iterator<Book> iterator() {
		return this.books.iterator();
	}
}
