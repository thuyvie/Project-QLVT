/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Page1Controller implements Initializable {

    @FXML
    private VBox pane;
    @FXML
    private JFXButton btnlogin;
    @FXML
    private JFXButton btnlogin1;
    @FXML
    private JFXButton btnlogin2;
    @FXML
    private JFXButton btnlogin3;
    @FXML
    private JFXButton btnlogin4;
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
    private void issue(MouseEvent event) {
    }

    @FXML
    private void receipt(MouseEvent event) {
    }

    @FXML
    private void order(MouseEvent event) {
    }

    @FXML
    private void debt(MouseEvent event) {
    }

    @FXML
    private void listdebt(MouseEvent event) {
    }
    
}
