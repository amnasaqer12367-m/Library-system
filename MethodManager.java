import java.util.*;

public class MethodManager {

    private ArrayList<Book> books = new ArrayList<>();

    public void load(String file) {
       // books = FileManager.load(file);
    }

    public void addBook(Book b) {
        books.add(b);

    }

    public void deleteBook(String id) {
        boolean removed = books.removeIf(b -> b.getBookId().equals(id));
        if (removed) {
            System.out.println("Book deleted and saved.");
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }
    public boolean editBook(String id, String newTitle, String newAuthor, String newCategory ,int newYear ,String isbn) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) {
                if (newTitle != null) b.setTitle(newTitle);
                if (newAuthor != null) b.setAuthor(newAuthor);
                if (newCategory != null) b.setCategory(newCategory);
                if (newYear != 0) b.setYear(newYear);
                if (isbn != null) b.setIsbn(isbn);


                return true;
            }
        }

        return false;
    }
    public void displayAll() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public Book searchByID(String id) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    public void searchByKeyword(String key) {
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(key.toLowerCase()) ||
                    b.getAuthor().toLowerCase().contains(key.toLowerCase())) {
                System.out.println(b);
            }
        }
    }
    public void searchByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(b);
            }
        }
    }


    public void sortByTitle() {
        books.sort(new TitleComparator());
    }

    public void sortByAuthor() {
        books.sort(new AuthorComparator());
    }

    public void sortByYear() {
        books.sort(new YearComparator());
    }


    public void statistics() {
        System.out.println("\nBooks per Year:");
        for (int i = 0; i < books.size(); i++) {

            int year = books.get(i).getYear();
            int count = 0;

            for (int j = 0; j < books.size(); j++) {
                if (books.get(j).getYear() == year) {
                    count++;
                }
            }

            boolean printed = false;
            for (int k = 0; k < i; k++) {
                if (books.get(k).getYear() == year) {
                    printed = true;
                    break;
                }
            }

            if (!printed) {
                System.out.println(year + " : " + count);
            }
        }

        int maxYear = books.get(0).getYear();
        int minYear = books.get(0).getYear();
        int maxCount = 0;
        int minCount = books.size();

        for (int i = 0; i < books.size(); i++) {

            int year = books.get(i).getYear();
            int count = 0;

            for (Book b : books) {
                if (b.getYear() == year)
                    count++;
            }

            if (count > maxCount) {
                maxCount = count;
                maxYear = year;
            }

            if (count < minCount) {
                minCount = count;
                minYear = year;
            }
        }

        System.out.println("\nMax Year: " + maxYear + " (" + maxCount + ")");
        System.out.println("Min Year: " + minYear + " (" + minCount + ")");

        System.out.println("\nBooks per Author:");

        for (int i = 0; i < books.size(); i++) {

            String author = books.get(i).getAuthor();
            int count = 0;

            for (Book b : books) {
                if (b.getAuthor().equals(author))
                    count++;
            }

            boolean doneBefore = false;
            for (int k = 0; k < i; k++) {
                if (books.get(k).getAuthor().equals(author)) {
                    doneBefore = true;
                    break;
                }
            }

            if (!doneBefore) {
                System.out.println(author + " : " + count);
            }
        }
    }

    public void save(String file) {
        FileManager.save(file, books);
    }
}