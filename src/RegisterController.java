import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegisterController implements Initializable {
    
    @FXML    
    private TextField firstNameField;   
    
    @FXML    
    private TextField lastNameField;  
    
    @FXML    
    private TextField emailAddressField;  
    
    @FXML    
    private TextField phoneNumberField;  
    
    @FXML
    private TextField passwordField;  
    
    @FXML    
    private TextField addressLineField;  
 
    @FXML 
    private Button finishButton;
      
    private CustomerDAO customerDAO;
    
    private CustomerAuthenDAO customerAuthenDAO;
            
     
    public RegisterController() {
        // Initialize customerDAO object and authentication object.
        try{
            this.customerDAO = new MySQLCustomerDAO() {};
            this.customerAuthenDAO = new CustomerAuthenDAO(MySQLConn.getConnection());
        } catch (SQLException e) {
            // If an exception occurs during initialization (e.g., database connectivity failure)
            // Display an error message to the user
            displayErrorMessage("Database Connection Error", "Failed to connect to the database. Please check your database configuration.");            
            e.printStackTrace();
        }  
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disable registerButton until all text fields are filled
        finishButton.setDisable(true);
        
        // Add listener to text fields to enable/disable registerButton
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        emailAddressField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        addressLineField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());

        
    }             
    
    
    private void checkFieldsFilled() {
        // Enable registerButton only if all text fields are filled
        boolean allFieldsFilled = !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && 
                !emailAddressField.getText().isEmpty() && !phoneNumberField.getText().isEmpty() &&
                !passwordField.getText().isEmpty() && !addressLineField.getText().isEmpty();
        
        finishButton.setDisable(!allFieldsFilled);
    }
    
    private boolean areAllFieldsFilled() {
        // Check if all text fields are filled
        return !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && 
                !emailAddressField.getText().isEmpty() && !phoneNumberField.getText().isEmpty() &&
                !passwordField.getText().isEmpty() && !addressLineField.getText().isEmpty();       
    }
        
    // Method to handle registration button click
    @FXML
    private void finishButtonClicked() throws SQLException {
        // Check if all text fields are filled
        if (!areAllFieldsFilled()) {
            // Show alert if any field is empty
            displayErrorMessage("Error", "All fields are required. Please fill in all fields.");
            return;
        }
        
        // Retrieve input values
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String emailAddress = emailAddressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String password = passwordField.getText();
        String addressLine = addressLineField.getText();
    
                                         
        
        // create new Customer object
        Customer customer = new Customer(firstName, lastName, emailAddress, phoneNumber, addressLine);
        
         
        int customerID = customerDAO.insertCustomer(customer);
        
        // insert customer data into database
        if (customerDAO != null) {            
            customerAuthenDAO.insertCustomerAuthen(customerID, emailAddress, password);                          
        } else {
            System.out.println("Unable to save!");
            }          
        
        try {
            // Display success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have been successfully registered!");
            alert.showAndWait();

            // Close the current window
            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.close();

            // Open the login window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
            Parent root = loader.load();            
            Stage loginStage = new Stage(); // Get the current stage
            Scene scene = new Scene(root);
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            // Display error alert if registration fails
            displayErrorMessage("Registration Error", "An error occurred during registration. Please try again.");
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
    
    // Method to clear all entries when "Clear All" button is clicked
    @FXML
    private void resetAllButtonClicked() {
        firstNameField.clear(); // Clear first name field
        lastNameField.clear(); // Clear last name field
        emailAddressField.clear(); // Clear email address field
        phoneNumberField.clear(); // Clear phone number field
        passwordField.clear(); // Clear password field
        addressLineField.clear(); // Clear town field
                
    }
    
    

    
    // Method to display an error message
    private void displayErrorMessage(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
