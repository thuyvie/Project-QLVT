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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private BorderPane boderPane;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    
//    public void DashBoard(javafx.event.ActionEvent actionEvent) throws IOException {
//        Parent fxml = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));  
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(fxml);
//    }


    @FXML
    private void DashBoard(MouseEvent event) throws IOException {
        loadUI("/view/DashBoard");
    }

    @FXML
    private void page1(MouseEvent event) throws IOException {
        loadUI("/view/Order");
    }

    @FXML
    private void page2(MouseEvent event) throws IOException {
        loadUI("/view/Product");

    }

    @FXML
    private void page3(MouseEvent event) throws IOException {
        loadUI("/view/page3");

    }
    
    private void loadUI(String page){
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
                    } catch (IOException ex) {
            Logger.getLogger(MenuUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boderPane.setCenter(root);
    }

   
    
}
