import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainScene extends Application {

    @Override
    public void start(Stage stage) {


        Label title = new Label("Library Management System ");
        title.setFont(Font.font("Segoe UI Semibold", 36));

        Label descrption = new Label(
                "where books are founded"
        );
        descrption.setFont(Font.font("Segoe UI", 16));
        descrption.setTextFill(Color.GRAY);


        Button startBtn = new Button("Enter library");
        startBtn.setStyle("""
            -fx-background-color: #F5AFAF;
            -fx-text-fill: white;
            -fx-font-size: 18;
            -fx-background-radius: 30;
            -fx-padding: 12 40;
        """);

        startBtn.setOnMouseEntered(e ->
                startBtn.setStyle("""
                -fx-background-color: #F5AFAF;
                -fx-text-fill: white;
                -fx-font-size: 18;
                -fx-background-radius: 30;
                -fx-padding: 12 40;
        """));

        startBtn.setOnMouseExited(e ->
                startBtn.setStyle("""
                -fx-background-color: #F5AFAF;
                -fx-text-fill: white;
                -fx-font-size: 18;
                -fx-background-radius: 30;
                -fx-padding: 12 40;
        """));

        VBox vBox = new VBox(20, title, descrption, startBtn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("""
            -fx-background-color: #FCF8F8;
            -fx-padding: 40;
            -fx-background-radius: 20;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 25, 0, 0, 8);
        """);

        StackPane root = new StackPane(vBox);
        root.setStyle("""
            -fx-background-color: linear-gradient(to bottom right, #f5f7fa, #c3cfe2);
        """);

        FadeTransition ft = new FadeTransition(Duration.seconds(1), vBox);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        startBtn.setOnAction(e->{
            Table s = new Table();
            s.show(stage);
        });


        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("library  Management System");
        stage.setFullScreen(true);
        stage.getIcons().add(new Image("file:C:/Users/User/Downloads/book-management.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
