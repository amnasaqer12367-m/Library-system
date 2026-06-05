import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Table extends Application {

    static TableView<Book> table;
    private HBox sortHBox;
      static ArrayList<Book> books = new ArrayList<>();
    static ObservableList<Book> data = FXCollections.observableArrayList();
    private TextField search;
    private ComboBox<String> searchType;
    private RadioButton sortByTitleRadioButton, sortByAuthorRadioButton, sortByYearRadioButton;
    private ToggleGroup sortToggleGroup;


    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("""
                    -fx-background-color: linear-gradient(#F5AFAF);
                """);
        Label title = new Label("Library Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setStyle(  "-fx-background-color: #F5AFAF;-fx-text-fill: white;");



        ImageView iv = new ImageView(new Image("file:C:/Users/User/Downloads/book-management.png")
        );
        iv.setFitWidth(350);
        iv.setFitHeight(350);
        iv.setOpacity(0.9);
        iv.setPreserveRatio(true);

        table = new TableView<>();
        table.setItems(data);
        table.setMaxSize(900, 600);

        TableColumn<Book, Integer> id = new TableColumn<>("Book Id");
        id.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titles= new TableColumn<>("Title ");
        titles.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> auther = new TableColumn<>("Author");
        auther.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> category = new TableColumn<>("Category");
        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Integer> publishedYear = new TableColumn<>("Published Year");
        publishedYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Book, Integer> isbn = new TableColumn<>("ISBN ");
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        table.getColumns().addAll(id, titles, auther, category, publishedYear,isbn);

        table.setItems(data);

        table.setStyle("""
                    -fx-background-color: #F9DFDF;
                    -fx-border-color black;
                    -fx-border-width: 2;
                    -fx-border-radius: 10;
                    -fx-background-radius: 10;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 4);
                """);

        table.setFixedCellSize(40);
        root.setCenter(table);


        String style = """
                    -fx-background-color: #F5AFAF;
                    -fx-text-fill: white;
                    -fx-font-size: 14px;
                    -fx-font-weight: bold;
                    -fx-background-radius: 20;
                    -fx-border-radius: 20;
                    -fx-padding: 10 22;
                """;
        String btn = """
                    -fx-background-color: #213448;
                    -fx-text-fill: white;
                    -fx-font-size: 14px;
                    -fx-font-weight: bold;
                    -fx-background-radius: 20;
                    -fx-border-radius: 20;
                    -fx-padding: 10 22;
                """;


        Button add = new Button("Add Book");
        Button load = new Button("Load Book");
        Button update = new Button("Edit Book");
        Button delete = new Button("Delete Book");
        Button showAll = new Button("Show All");
        Button save = new Button("Save");
        Button stat = new Button("Statistics");
        Button back = new Button("Back");

        add.setStyle(style);
        update.setStyle(style);
        delete.setStyle(style);
        back.setStyle(style);
        save.setStyle(style);
        showAll.setStyle(style);
        load.setStyle(style);
        stat.setStyle(style);

        HBox bottom = new HBox(25, load,add, update, delete, save,stat, back);
        bottom.setAlignment(Pos.CENTER);
        bottom.setStyle("-fx-padding: 20;");
        root.setBottom(bottom);


        searchType = new ComboBox<>();
        searchType.getItems().addAll("BookId", "Title","Keyword");
        searchType.setValue("id");
        searchType.setPrefWidth(100);

        search = new TextField();
        Label ll = new Label("Search");
        ll.setStyle("""
                    -fx-text-fill: white;
                     -fx-font-weight: bold;
                """);
        search.setPromptText("search");
        search.setStyle("""
                    -fx-background-color: #F9DFDF;
                    -fx-border-color: #94B4C1;
                     -fx-font-weight: bold;
                    -fx-border-radius: 15;
                    -fx-background-radius: 15;
                    -fx-padding: 8;
                """);
        searchType.setStyle("""
                    -fx-background-color: #F9DFDF;
                    -fx-border-color: #94B4C1;
                    -fx-border-radius: 8;
                    -fx-background-radius: 8;
                    -fx-padding: 6;
                """);
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-background-color: #F5AFAF;");
        v.setSpacing(10);


        sortHBox = new HBox();
        sortHBox.setPadding(new Insets(15, 15, 15, 15));
        sortHBox.setSpacing(20);
        sortHBox.setAlignment(Pos.CENTER);

        //sort
        sortByTitleRadioButton = new RadioButton("Sort by Title");
        sortByAuthorRadioButton = new RadioButton("Sort by Author");
        sortByYearRadioButton = new RadioButton("Sort by Year");

        sortToggleGroup = new ToggleGroup();
        sortByAuthorRadioButton.setToggleGroup(sortToggleGroup);
        sortByYearRadioButton.setToggleGroup(sortToggleGroup);
        sortByTitleRadioButton.setToggleGroup(sortToggleGroup);

        sortHBox.getChildren().addAll(sortByTitleRadioButton, sortByAuthorRadioButton, sortByYearRadioButton);

        HBox top = new HBox(20, ll, searchType, search, showAll,sortHBox);
        top.setAlignment(Pos.CENTER);
        top.setStyle("""
                    -fx-background-color: #F5AFAF;
                    -fx-padding: 15;
                """);

        v.getChildren().addAll(title, top);
        root.setTop(v);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Libray System");
        stage.getIcons().add(new Image("file:C:/Users/User/Downloads/book-management.png"));
        stage.setFullScreen(true);
        stage.show();

        add.setOnAction(e -> {
            Add a = new Add();
            a.show(stage);
        });
        update.setOnAction(e -> {
            Book book = (Book) table.getSelectionModel().getSelectedItem();
            if (book == null) {
                Alerts.alert( Alert.AlertType.ERROR,"please select a book to edit");
                return;
            }
            Update u = new Update();
            u.show(new Stage());
            u.fillFields(book);
        });
        delete.setOnAction(e -> {
            Book book = table.getSelectionModel().getSelectedItem();
            if (book == null) {
                Alerts.alert(Alert.AlertType.ERROR, "please choose book");
                return;
            }

            Alerts.alert(Alert.AlertType.INFORMATION, "Are you sure you want to delete this book:  " +book.getBookId() + "?");
           books.remove(book);
            data.remove(book);
            table.refresh();
            Alerts.alert(Alert.AlertType.INFORMATION, "deleted success");

        });
        search.setOnAction(e -> {
            String searchText = search.getText();
            ObservableList<Book> data = FXCollections.observableArrayList();
            if (searchType.getItems() != null) {
                String select = searchType.getValue();

                if (select.equalsIgnoreCase("Bookid")) {
                    try {
                        for (Book book : Table.data) {
                            if (book.getBookId().equalsIgnoreCase(searchText)) {
                               data.add(book);
                              Alerts.alert(Alert.AlertType.INFORMATION, "book: " + book.getBookId() + "with title: " + book.getTitle()+ "with auther: " + book.getAuthor() + "category: " +
                                      book.getCategory() + "published year: " + book.getYear()+"isbn: "+ book.getIsbn());
                                Table.table.setItems(data);
                            }
                        }
                        if (data.isEmpty()) {
                            Alerts.alert(Alert.AlertType.INFORMATION, " book not found");
                            Table.table.setItems(data);
                        }
                    } catch (NumberFormatException ex) {
                      Alerts. alert(Alert.AlertType.ERROR, "book not found");
                    }

                } else if (select.equalsIgnoreCase("Title")) {
                    try {
                        for (Book book : Table.data) {
                            if (book.getTitle().equalsIgnoreCase(searchText)) {
                                data.add(book);
                                Alerts.alert(Alert.AlertType.INFORMATION, "book: " + book.getBookId() + "with title: " + book.getTitle() + "with auther: " + book.getAuthor() + "category: " +
                                        book.getCategory() + "published year: " + book.getYear() + "isbn: " + book.getIsbn());
                                Table.table.setItems(data);
                            }
                        }
                        if (data.isEmpty()) {
                            Alerts.alert(Alert.AlertType.INFORMATION, "title not found");
                            Table.table.setItems(data);
                        }
                    } catch (NumberFormatException ex) {
                        Alerts.alert(Alert.AlertType.ERROR, "not found");
                    }
                }
                  else if (select.equalsIgnoreCase("Keyword")){
                    try {
                        for (Book book : Table.data) {
                            if (book.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                                    book.getAuthor().toLowerCase().contains(searchText.toLowerCase())) {
                                data.add(book);
                                Alerts.alert(Alert.AlertType.INFORMATION, "book: " + book.getBookId() + "with title: " + book.getTitle() + "with auther: " + book.getAuthor() + "category: " +
                                        book.getCategory() + "published year: " + book.getYear() + "isbn: " + book.getIsbn());
                                Table.table.setItems(data);
                            }
                        }
                        if (data.isEmpty()) {
                            Alerts.alert(Alert.AlertType.INFORMATION, "title not found");
                            Table.table.setItems(data);
                        }
                    } catch (NumberFormatException ex) {
                        Alerts.alert(Alert.AlertType.ERROR, "not found");
                    }
                } else {
                    Alerts.alert(Alert.AlertType.INFORMATION, "noun");
                }
            }

        });
        showAll.setOnAction(e -> {
            table.setItems(Table.data);
        });
        stat.setOnAction(e->{
            Statistics s = new Statistics();
            s.show(stage);
        });
load.setOnAction(e->{
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Load books File");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    File file = fileChooser.showOpenDialog(stage);

    FileManager.load(String.valueOf(file));
    if (file != null) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File Loaded");
        alert.setContentText("Loaded: " + file.getName());
        alert.showAndWait();
    }
});
        sortByAuthorRadioButton.setOnAction(e -> sortAction());
        sortByTitleRadioButton.setOnAction(e -> sortAction());
        sortByYearRadioButton.setOnAction(e -> sortAction());

        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("save books");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                FileManager.save(String.valueOf(file),books);


            }
        });
        back.setOnAction(e -> {
            MainScene s = new MainScene();
            s.start(stage);
        });


    }
    private void sortAction() {
        if (sortToggleGroup.getSelectedToggle() != null) {
            RadioButton selectedToggle = (RadioButton) sortToggleGroup.getSelectedToggle();
            if (selectedToggle == sortByAuthorRadioButton) {
                sortByAuthor();
            }
            if (selectedToggle == sortByTitleRadioButton) {
                sortByTitle();
            }
            if (selectedToggle == sortByYearRadioButton) {
                sortByYear();
            }
        }
    }

    //sort methods
    private void sortByTitle() {
        Collections.sort(data, new TitleComparator());
        table.refresh();
    }

    private void sortByAuthor() {
        Collections.sort(data, new AuthorComparator());
        table.refresh();
    }

    private void sortByYear() {
        Collections.sort(data, new YearComparator());
        table.refresh();
    }

    public static void main(String[] args) {
        launch();
    }
}
