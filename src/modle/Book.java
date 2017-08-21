package modle;

public class Book {
	
	private  String  BookId;
	private  String  BookName;
	private  String  BookAuthor;
	private  String  BookClass;
	private  String  BookCf;
	public String getBookCfa() {
		if(BookCf!=null && BookCf.length() >20){
			return BookCf.substring(0,10)+"...";
		}
		return BookCfa;
	}
	public void setBookCfa(String bookCfa) {
		BookCfa = bookCfa;
	}
	private  String  BookCfa;
	private  String  BookPhoto;
	public String getBookId() {
		return BookId;
	}
	public void setBookId(String bookId) {
		BookId = bookId;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getBookAuthor() {
		return BookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}
	public String getBookClass() {
		return BookClass;
	}
	public void setBookClass(String bookClass) {
		BookClass = bookClass;
	}
	public String getBookCf() {
	
		return BookCf;
	}
	public void setBookCf(String bookCf) {
		BookCf = bookCf;
	}
	public String getBookPhoto() {
		return BookPhoto;
	}
	public void setBookPhoto(String bookPhoto) {
		BookPhoto = bookPhoto;
	}

}
