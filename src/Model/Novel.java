package Model;

public class Novel extends Book {

	public Novel(String id, String title, String author, boolean availability) {
		super(id, title, author, availability);
	}

	@Override
	public Book clone() {
		return new Novel(getId(), getTitle(), getAuthor(), isAvailability());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}
