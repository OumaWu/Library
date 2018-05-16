package Model;

public class Magazine extends Book {

	public Magazine() {
	}

	public Magazine(String id, String title, String author, boolean availability) {
		super(id, title, author, availability);
	}

	public Magazine(String id, String title, String author) {
		super(id, title, author);
	}

	@Override
	public Book clone() {
		return new Magazine(getId(), getTitle(), getAuthor(), getAvailability());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}