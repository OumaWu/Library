package Model;

public class Novel extends Book {

	public Novel(String id, String title, String author) {
		super(id, title, author);
	}

	@Override
	public Book clone() {
		return new Novel(getId(), getTitle(), getAuthor());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}
