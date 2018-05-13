package Model;

public class Manuel extends Book {

	public Manuel(String id, String title, String author) {
		super(id, title, author);
	}

	@Override
	public Book clone() {
		return new Manuel(getId(), getTitle(), getAuthor());
	}

	@Override
	public int compareTo(Book o) {
		return getId().compareTo(o.getId());
	}
}