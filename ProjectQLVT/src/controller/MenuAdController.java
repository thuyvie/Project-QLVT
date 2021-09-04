/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class MenuAdController implements Initializable {

    @FXML
    private BorderPane boderPane;
    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane contentArea;
    @FXML
    private Pane context;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void page4(MouseEvent event) throws IOException {
        loadUI("Employee");
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
