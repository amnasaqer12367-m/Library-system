import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Update {

    private ObservableList<Book> list = Table.data;

    private TextField idField, titleField, authorField,
            categoryField, yearField, isbnField;

    private Book selectedBook;

    public void show(Stage stage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: white;");

        // title
        Label title = new Label("Edit Book");
        title.setFont(new Font("Arial", 28));
        title.setStyle("-fx-font-weight: bold; -fx-text-fill:black; "  + ";");
        GridPane.setColumnSpan(title, 2);
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER);

        // label style
        String labelStyle = """
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: ;
                """;

        // labels
        Label id = new Label("Book ID:");
        Label titleLbl = new Label("Title:");
        Label author = new Label("Author:");
        Label category = new Label("Category:");
        Label year = new Label("Published Year:");
        Label isbn = new Label("ISBN:");

        id.setStyle(labelStyle);
        titleLbl.setStyle(labelStyle);
        author.setStyle(labelStyle);
        category.setStyle(labelStyle);
        year.setStyle(labelStyle);
        isbn.setStyle(labelStyle);

        // fields
        idField = new TextField();
        titleField = new TextField();
        authorField = new TextField();
        categoryField = new TextField();
        yearField = new TextField();
        isbnField = new TextField();

        String fieldStyle = """
                    -fx-font-size: 14px;
                    -fx-background-color: white;
                    -fx-background-radius: 6;
                    -fx-border-radius: 6;
                    -fx-border-color: #213448;
                    -fx-border-width: 1;
                    -fx-padding: 8;
                """;


        idField.setStyle(fieldStyle);
        titleField.setStyle(fieldStyle);
        authorField.setStyle(fieldStyle);
        categoryField.setStyle(fieldStyle);
        yearField.setStyle(fieldStyle);
        isbnField.setStyle(fieldStyle);

        // buttons
        Button update = new Button("Update");
        Button cancel = new Button("Cancel");
        // buttons
        String style = """
                    -fx-background-color: #F5AFAF;
                    -fx-text-fill: white;
                    -fx-font-size: 14px;
                    -fx-font-weight: bold;
                    -fx-background-radius: 20;
                    -fx-padding: 10 30;
                    -fx-cursor: hand;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4, 0, 0, 2);
                """;

        update.setStyle(style);
        cancel.setStyle(style);

        HBox btnBox = new HBox(20, update, cancel);
        btnBox.setAlignment(Pos.CENTER);

        // layout
        grid.add(title, 0, 0);

        grid.add(id, 0, 1);
        grid.add(idField, 1, 1);

        grid.add(titleLbl, 0, 2);
        grid.add(titleField, 1, 2);

        grid.add(author, 0, 3);
        grid.add(authorField, 1, 3);

        grid.add(category, 0, 4);
        grid.add(categoryField, 1, 4);

        grid.add(year, 0, 5);
        grid.add(yearField, 1, 5);

        grid.add(isbn, 0, 6);
        grid.add(isbnField, 1, 6);

        grid.add(btnBox, 1, 7);

        // update action
        update.setOnAction(e -> {

            if (selectedBook == null) return;

            selectedBook.setBookId(idField.getText());
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setCategory(categoryField.getText());
            selectedBook.setYear(Integer.parseInt(yearField.getText()));
            selectedBook.setIsbn(isbnField.getText());

            Table.table.refresh();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Book updated successfully");
            alert.show();
        });

        cancel.setOnAction(e -> stage.close());

        Scene scene = new Scene(grid, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Book Management System");
        stage.setFullScreen(true);
        stage.show();
    }

    public void fillFields(Book book) {

        selectedBook = book;

        idField.setText(book.getBookId());
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategory());
        yearField.setText(String.valueOf(book.getYear()));
        isbnField.setText(book.getIsbn());
    }
}