package Model;

public class Magazine extends Book {

	public Magazine(String id, String title, String author, boolean availability) {
		super(id, title, author, availability);
	}

	@Override
	public Book clone() {
		return new Magazine(getId(), getTitle(), getAuthor(), isAvailability());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}