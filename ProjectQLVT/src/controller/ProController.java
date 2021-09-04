/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProController implements Initializable {

    @FXML
    private VBox pane;
    @FXML
    private JFXButton btnlogin;
    @FXML
    private JFXButton btnlogin1;
    @FXML
    private Pane context;
    @FXML
    private JFXButton btnlogin2;
    @FXML
    private JFXButton btnlogin3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void setUi(String location) throws IOException{
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location + ".fxml")));
    }

    @FXML
    private void product(MouseEvent event) throws IOException {
        setUi("product_1");
        new FadeIn(context).play();
    }

    @FXML
    private void catepro(MouseEvent event) throws IOException {
        setUi("catepro");
        new FadeIn(context).play();
    }

    @FXML
    private void vendor(MouseEvent event) throws IOException {
        setUi("vendor");
        new FadeIn(context).play();
    }

    @FXML
    private void warehouse(MouseEvent event) throws IOException {
        setUi("Warehouse");
        new FadeIn(context).play();
    }
    
}
