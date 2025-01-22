import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


    public class MainSceneController implements Initializable {
    
        @FXML
        private TextField emailaddressTF;
    
        @FXML
        private TextField passwordTF;
        
        @FXML
        private Button btn_log_in;            
        
        @FXML
        private Node node; 
            
        private CustomerAuthenDAO customerAuthenDAO;
            
        private StaffDAO staffDAO;

        public MainSceneController() throws SQLException {
            
            // Initialize customerDAO object
            try{
                this.customerAuthenDAO = new CustomerAuthenDAO(MySQLConn.getConnection());
                
                // Initialize staffDAO object
                this.staffDAO = new StaffDAO();
            } catch (SQLException e) {
                // If an exception occurs during initialization (e.g., database connectivity failure)
                // Display an error message to the user
                displayErrorMessage("Database Connection Error", "Failed to connect to the database. Please check your database configuration.");            
                e.printStackTrace();
            }  
        }

        @FXML
        private void switchToScene1Fxml(ActionEvent event) throws IOException {
            Parent secondRoot = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene secondScene = new Scene(secondRoot);        
            stage.setScene(secondScene);
            stage.show();
        }
        
        @FXML
        private void switchToStaffMenu() {
            // Get the Stage object from the current scene
            Stage stage = (Stage) node.getScene().getWindow();
    
            try {
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffMenu.fxml"));
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
        private void switchToMainScene() {
            // Get the Stage object from the current scene
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
        private void switchToMainMenu() {
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
        private void MainScene() throws IOException, SQLException {
            
            String emailaddress = emailaddressTF.getText();
            String password = passwordTF.getText();
            
            
            if (emailaddress.contains("@mysms.com")) {
                // Staff login
                if (staffDAO.authenticateStaff(emailaddress, password)) {
                    // Close the current window
                    // Open the dashboard 
                    switchToStaffMenu();
                    System.out.println("Login successful");                     
                } else {
                    // Staff login failed
                    System.out.println("Staff login failed");                
                    displayErrorMessage("Error Logging In", "Try again or register.");
                    
                    // Reopen the login page
                    switchToScene1Fxml(null);
                }
            } else {
                // Retrieve customer Authentication details fron database
                
                if (customerAuthenDAO.authenticateCustomer(emailaddress, password)) {
                    // Login successful
                    switchToMainMenu(); 
                    
                    System.out.println("Login successful");
    
                } else {
                    // Login failed
                    // Display error message to the user
                    displayErrorMessage("Error Logging In", "Try again or register.");
                    // Reopen the login page
                    System.out.println("Login failed");
                }
            } 
            
        }
        
        /**
         * Initializes the controller class.
         * @param url
         * @param rb
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
        }    
    
       // Method to display an error message
        private void displayErrorMessage(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }    
    }
    



   