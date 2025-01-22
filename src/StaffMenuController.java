import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StaffMenuController implements Initializable {
        
@FXML
private void switchToMainScene(ActionEvent event) {
    Node node = (Node) event.getSource(); // Get the Node from the event
    Stage stage = (Stage) node.getScene().getWindow();

    try {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Parent root = loader.load();

        // Create a new scene
        Scene scene = new Scene(root);

        // Set the new scene on the stage
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'initialize'");
}
}
