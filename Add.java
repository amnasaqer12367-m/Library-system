import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Add {
    private ObservableList<Book> list = Table.data;

    public void show(Stage stage) {

        // grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("""
                    -fx-background-color: white;
                """);

        // title
        Label title = new Label("Add New Book\n id will be auto");
        title.setFont(new Font("Arial", 28));
        title.setStyle("""
                    -fx-font-weight: bold;
                    -fx-text-fill: %s;
                """);
        GridPane.setColumnSpan(title, 2);
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER);

        // label
        String labelStyle = """
                    -fx-font-size: 14px;
                    -fx-font-weight: bold;
                    -fx-text-fill: %s;
                """;

        Label titles = new Label("title");
        Label auther = new Label("auther :");
        Label category = new Label("category:");
        Label publishedYear = new Label("publishedYear:");
        Label isbn = new Label("isbn:");

        titles.setStyle(labelStyle);
        auther.setStyle(labelStyle);
        category.setStyle(labelStyle);
        publishedYear.setStyle(labelStyle);
        isbn.setStyle(labelStyle);

        // text field
        TextField titlesField = new TextField();
        TextField autherField = new TextField();
        TextField categoryField = new TextField();
        TextField publishedYearField = new TextField();
        TextField isbnField = new TextField();


        String fieldStyle = """
                    -fx-font-size: 14px;
                    -fx-background-color: white;
                    -fx-background-radius: 6;
                    -fx-border-radius: 6;
                    -fx-border-color: #213448;
                    -fx-border-width: 1;
                    -fx-padding: 8;
                """;

        titlesField.setStyle(fieldStyle);
        autherField.setStyle(fieldStyle);
        categoryField.setStyle(fieldStyle);
        publishedYearField.setStyle(fieldStyle);
        isbnField.setStyle(fieldStyle);

        // button
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


        Button add = new Button("Add Book");
        Button cancel = new Button("Cancel");


        add.setStyle(style);
        cancel.setStyle(style);


        add.setOnMouseExited(e -> add.setStyle(style));

        cancel.setOnMouseExited(e -> cancel.setStyle(style));


        HBox btnBox = new HBox(20, add, cancel);
        btnBox.setAlignment(Pos.CENTER);


        grid.add(title, 0, 0);
        grid.add(titles, 0, 1);
        grid.add(titlesField, 1, 1);
        grid.add(auther, 0, 2);
        grid.add(autherField, 1, 2);
        grid.add(category, 0, 3);
        grid.add(categoryField, 1, 3);
        grid.add(publishedYear, 0, 4);
        grid.add(publishedYearField, 1, 4);
        grid.add(isbn, 0, 5);
        grid.add(isbnField, 1, 5);
        grid.add(btnBox, 1, 6);


        add.setOnAction(e -> {
            String titless = titlesField.getText();
            String authors = autherField.getText();
            String categorys = categoryField.getText();
            String publishedYears = publishedYearField.getText();
            String isbns = isbnField.getText();
            int publishedYearss = Integer.parseInt(publishedYears);


            if (titless == null || titless.isEmpty()) {
                Alerts.alert(Alert.AlertType.ERROR, "please enter book title");
                return;
            }
            if (authors == null || authors.isEmpty()) {
                Alerts.alert(Alert.AlertType.ERROR, "please enter author name");
                return;
            }
            if (categorys == null || categorys.isEmpty()) {
                Alerts.alert(Alert.AlertType.ERROR, "please enter book category");
                return;
            }

            try {
                if (publishedYears == null || publishedYears.isEmpty()) {
                    Alerts.alert(Alert.AlertType.ERROR, "please enter published year");
                    return;
                }


                if (publishedYearss < 0) {
                    Alerts.alert(Alert.AlertType.ERROR, "publish year cannot be negative " + publishedYear);
                    return;
                }

                if (publishedYearss > LocalDate.now().getYear()) {
                    Alerts.alert(Alert.AlertType.ERROR, "Publish year cannot be greater than current year " + publishedYear);
                    return;
                }
            } catch (NumberFormatException ex) {
                Alerts.alert(Alert.AlertType.ERROR, "Invalid publish year");
                return;
            }

            if (isbns == null || isbns.isEmpty()) {
                Alerts.alert(Alert.AlertType.ERROR, "please enter isbn");
                return;
            }

            boolean isbnExists = false;

            for (Book book : Table.books) {
                if (book.getIsbn().equals(isbns)) {
                    isbnExists = true;
                }
            }
            if (isbnExists) {
                Alerts.alert(Alert.AlertType.ERROR, "isbn already exists");
                return;
            }
            String ids = "" + (Table.data.size() + 1);

            Book book = new Book(ids, titless, authors, categorys, publishedYearss, isbns);
            Table.books.add(book);
            Table.data.add(book);
            Alerts.alert(Alert.AlertType.INFORMATION, "book added successfully");


            titlesField.clear();
            autherField.clear();
            categoryField.clear();
            publishedYearField.clear();
            isbnField.clear();
            Table t = new Table();
            t.show(stage);
        });

        cancel.setOnAction(e -> {
          Table t = new Table();
          t.show(stage);
        });


        Scene scene = new Scene(grid, 900, 650);
        stage.setScene(scene);
        stage.setTitle("Add new book");
        stage.show();
    }

}