package Model;

public class Novel extends Book {

	public Novel() {
	}

	public Novel(String id, String title, String author, boolean availability) {
		super(id, title, author, availability);
	}

	public Novel(String id, String title, String author) {
		super(id, title, author);
	}

	@Override
	public Book clone() {
		return new Novel(getId(), getTitle(), getAuthor(), getAvailability());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}
