import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RequestServiceController {

    @FXML
    private void switchToMainMenu(ActionEvent event) {
        Node node = (Node) event.getSource();
        // Get the Stage object from the current scene
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Set the new scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    @FXML
private void switchToViewAppointment(ActionEvent event) {
    Node node = (Node) event.getSource(); // Get the Node from the event
    Stage stage = (Stage) node.getScene().getWindow();

    try {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppointment.fxml"));
        Parent root = loader.load();

        // Create a new scene
        Scene scene = new Scene(root);

        // Set the new scene on the stage
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


        @FXML
        private void switchToBrowseServices(ActionEvent event) {
            Node node = (Node) event.getSource(); // Get the Node from the event
            Stage stage = (Stage) node.getScene().getWindow();
        
            try {
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("BrowseServices.fxml"));
                Parent root = loader.load();
        
                // Create a new scene
                Scene scene = new Scene(root);
        
                // Set the new scene on the stage
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}


