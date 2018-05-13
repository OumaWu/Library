package Model;

public class Magazine extends Book {

	public Magazine(String id, String title, String author) {
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