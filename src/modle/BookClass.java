package modle;

import java.io.Serializable;

public class BookClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int book_class_id;
	private String book_class_name;
	
	public int getBook_class_id() {
		return book_class_id;
	}
	public void setBook_class_id(int book_class_id) {
		this.book_class_id = book_class_id;
	}
	public String getBook_class_name() {
		return book_class_name;
	}
	public void setBook_class_name(String book_class_name) {
		this.book_class_name = book_class_name;
	}
	
}
