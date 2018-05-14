package Model;

public class Manuel extends Book {

	public Manuel(String id, String title, String author, boolean availability) {
		super(id, title, author, availability);
	}

	@Override
	public Book clone() {
		return new Manuel(getId(), getTitle(), getAuthor(), isAvailability());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}