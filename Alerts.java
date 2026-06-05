import javafx.scene.control.Alert;

public class Alerts {

    public Alerts(){

    }

    public   static void alert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
