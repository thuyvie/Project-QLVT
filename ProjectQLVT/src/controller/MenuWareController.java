/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuWareController implements Initializable {

    @FXML
    private BorderPane boderPane;
    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane contentArea;
    @FXML
    private Pane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void warehouse(MouseEvent event) {
        loadUI("Product");
    }

    @FXML
    private void btnLogout(MouseEvent event) throws IOException {
        btnLogout.getScene().getWindow().hide();
         
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml")); 
        Scene scene = new Scene(root);

        
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void loadUI(String page){
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("/view/"+page+".fxml"));
                    } catch (IOException ex) {
            Logger.getLogger(MenuUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boderPane.setCenter(root);
    }
}
