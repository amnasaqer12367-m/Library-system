import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Statistics {

    private GridPane grid;
    private TextField categoryTextField, authorNameTextField, yearTextField;
    private Label categoryText, authorNameText, yearText;


    public void show(Stage stage) {

        grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("  -fx-background-color: white;");

        // title
        Label title = new Label("Statistics");
        title.setFont(new Font("Arial", 28));
        title.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #213448;
                """);
        GridPane.setColumnSpan(title, 2);
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER);

        // styles
        String labelStyle = """
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        -fx-text-fill: #213448;
                """;

        String fieldStyle = """
                        -fx-font-size: 14px;
                        -fx-background-color: white;
                        -fx-background-radius: 6;
                        -fx-border-radius: 6;
                        -fx-border-color: #213448;
                        -fx-border-width: 1;
                        -fx-padding: 8;
                """;

        String style = """
                        -fx-background-color: #F5AFAF;
                        -fx-text-fill: white;
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 20;
                        -fx-padding: 10 20;
                        -fx-cursor: hand;
                """;

        // label
        categoryText = new Label("Category:");
        authorNameText = new Label("Author Name:");
        yearText = new Label("Year:");

        categoryText.setStyle(labelStyle);
        authorNameText.setStyle(labelStyle);
        yearText.setStyle(labelStyle);

        // fields
        categoryTextField = new TextField();
        authorNameTextField = new TextField();
        yearTextField = new TextField();

        categoryTextField.setStyle(fieldStyle);
        authorNameTextField.setStyle(fieldStyle);
        yearTextField.setStyle(fieldStyle);

        // buttons
        Button maxAuthor = new Button("Max books by author");
        Button minAuthor = new Button("Min books by author");
        Button maxYear = new Button("Max books by year");
        Button minYear = new Button("Min books by year");
        Button activeAuthor = new Button("Check author active");
        Button back = new Button("Back");


        maxAuthor.setStyle(style);
        minAuthor.setStyle(style);
        maxYear.setStyle(style);
        minYear.setStyle(style);
        activeAuthor.setStyle(style);
        back.setStyle(style);

        // layout
        grid.add(title, 0, 0);

        grid.add(categoryText, 0, 1);
        grid.add(categoryTextField, 1, 1);

        grid.add(authorNameText, 0, 2);
        grid.add(authorNameTextField, 1, 2);

        grid.add(yearText, 0, 3);
        grid.add(yearTextField, 1, 3);

        grid.add(maxYear, 0, 4);
        grid.add(minYear, 1, 4);
        grid.add(maxAuthor, 0, 5);
        grid.add(minAuthor, 1, 5);
        grid.add(activeAuthor, 0, 6);
        grid.add(back, 1, 6);

        authorNameTextField.setOnAction(e -> numberOfBooksByAuthor());
        categoryTextField.setOnAction(e -> numberOfBooksByCategory());
        yearTextField.setOnAction(e -> numberOfBooksByYear());

        maxYear.setOnAction(e -> getMaxBooksByYear());
        minYear.setOnAction(e -> getMinBooksByYear());
        maxAuthor.setOnAction(e -> getMaxBooksByAuthor());
        minAuthor.setOnAction(e -> getMinBooksByAuthor());
        activeAuthor.setOnAction(e -> checkAuthorActive());

        back.setOnAction(e -> {
            Table t = new Table();
            t.show(stage);
        });


        Scene scene = new Scene(grid, 900, 650);
        stage.setScene(scene);
        stage.setTitle("Statistics");
        stage.show();
    }


    private void numberOfBooksByCategory() {
        String category = categoryTextField.getText();
        int count = 0;

        for (Book book : Table.books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                count++;
            }
        }

        if (count == 0) {
            Alerts.alert(Alert.AlertType.INFORMATION, "no books found for the category: " + category);
            return;
        }

        Alerts.alert(Alert.AlertType.INFORMATION, "There are " + count + " books of this " + category + " category");
    }

    private void numberOfBooksByAuthor() {
        String authorName = authorNameTextField.getText();
        int count = 0;

        for (Book book : Table.books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                count++;
            }
        }
        if (count == 0) {
            Alerts.alert(Alert.AlertType.INFORMATION, "No books found for the author: " + authorName);
            return;
        }
        Alerts.alert(Alert.AlertType.INFORMATION, "There are " + count + " books written by " + authorName);
    }

    private void numberOfBooksByYear() {
        try {
            String yearString = yearTextField.getText();
            int year = Integer.parseInt(yearString);
            int count = 0;

            for (Book book : Table.books) {
                if (book.getYear() == year) {
                    count++;
                }
            }

            if (count == 0) {
                Alerts.alert(Alert.AlertType.INFORMATION,
                        "No books found for the year: " + yearString);
                return;
            }

            Alerts.alert(Alert.AlertType.INFORMATION,
                    count + " Books found for the year: " + year);

        } catch (NumberFormatException e) {
            Alerts.alert(Alert.AlertType.ERROR, "Please enter a valid year");
        }
    }

    private void getMaxBooksByYear() {
        if (Table.books.isEmpty()) {
            Alerts.alert(Alert.AlertType.INFORMATION, "no books found");
            return;
        }

        ArrayList<Integer> years = new ArrayList<>();

        int maxYear = 0;
        int maxBooks = 0;

        for (Book book : Table.books) {
            int year = book.getYear();

            if (years.contains(year)) {
                continue;
            }
            years.add(year);
            int count = 0;
            for (Book b : Table.books) {
                if (b.getYear() == year) {
                    count++;
                }
            }

            if (count > maxBooks) {
                maxBooks = count;
                maxYear = year;
            }
        }
        Alerts.alert(Alert.AlertType.INFORMATION, "year with the most books: " + maxYear + " number of books: " + maxBooks);
    }

    private void getMinBooksByYear() {
        if (Table.books.isEmpty()) {
            Alerts.alert(Alert.AlertType.INFORMATION, "No books found");
            return;
        }
        ArrayList<Integer> years = new ArrayList<>();
        int minYear = 0;
        int minBooks = Integer.MAX_VALUE;

        for (Book book : Table.books) {
            int year = book.getYear();

            if (years.contains(year)) {
                continue;
            }

            years.add(year);

            int count = 0;
            for (Book b : Table.books) {
                if (b.getYear() == year) {
                    count++;
                }
            }

            if (count < minBooks) {
                minBooks = count;
                minYear = year;
            }
        }

        Alerts.alert(Alert.AlertType.INFORMATION, "year with the lowest books: " + minYear + " number of books: " + minBooks);
    }

    private void getMaxBooksByAuthor() {
        if (Table.books.isEmpty()) {
            Alerts.alert(Alert.AlertType.INFORMATION, "No books found");
            return;
        }

        ArrayList<String> authors = new ArrayList<>();

        String bestAuthor = "";
        int maxBooks = 0;
        ArrayList<String> bestTitles = new ArrayList<>();

        for (Book book : Table.books) {
            String author = book.getAuthor();

            if (authors.contains(author)) {
                continue;
            }

            authors.add(author);

            int count = 0;
            ArrayList<String> titles = new ArrayList<>();

            for (Book b : Table.books) {
                if (b.getAuthor().equalsIgnoreCase(author)) {
                    count++;
                    titles.add(b.getTitle());
                }
            }

            if (count > maxBooks) {
                maxBooks = count;
                bestAuthor = author;
                bestTitles = titles;
            }
        }

        Alerts.alert(Alert.AlertType.INFORMATION, "Author with the most books: " + bestAuthor + " Number: " + maxBooks + " Titles: " + bestTitles);
    }
    private void getMinBooksByAuthor() {
        if (Table.books.isEmpty()) {
            Alerts.alert(Alert.AlertType.INFORMATION, "No books found");
            return;
        }

        ArrayList<String> authors = new ArrayList<>();

        String bestAuthor = "";
       int minBooks = 999;
        ArrayList<String> bestTitles = new ArrayList<>();

        for (Book book : Table.books) {
            String author = book.getAuthor();

            if (authors.contains(author)) {
                continue;
            }

            authors.add(author);

            int count = 0;
            ArrayList<String> titles = new ArrayList<>();

            for (Book b : Table.books) {
                if (b.getAuthor().equalsIgnoreCase(author)) {
                    count++;
                    titles.add(b.getTitle());
                }
            }

            if (count < minBooks) {
                minBooks = count;
                bestAuthor = author;
                bestTitles = titles;
            }
        }

        Alerts.alert(Alert.AlertType.INFORMATION,
                "Author with the most books: " + bestAuthor +
                        " Number: " + minBooks +
                        " Titles: " + bestTitles);
    }
    private void checkAuthorActive() {
        String authorName = authorNameTextField.getText();

        int currentYear = java.time.LocalDate.now().getYear();
        boolean isActive = false;

        for (Book book : Table.books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                if (currentYear - book.getYear() <= 5) {
                    isActive = true;
                    break;
                }
            }
        }

        if (isActive) {
            Alerts.alert(Alert.AlertType.INFORMATION,
                    "Author is active ");
        } else {
            Alerts.alert(Alert.AlertType.INFORMATION,
                    "Author is not active");
        }
    }
}