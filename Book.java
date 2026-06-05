public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int year;
    private String isbn;
    private static int counter = 1;



    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book(String bookId, String title, String author, String category, int year, String isbn) {
        this.bookId = String.format("%03d", counter++);
        this.title = title;
        this.author = author;
        this.category = category;
        this.year = year;
        this.isbn = isbn;
    }


    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getYear() { return year; }
    public String getIsbn() { return isbn; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return bookId+ ", " + title + ", " + author + ", " +
                category + ", " + year + ", " + isbn;
    }
}