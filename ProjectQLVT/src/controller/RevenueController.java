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
public class RevenueController implements Initializable {

    @FXML
    private VBox pane;
    @FXML
    private Pane context;
    @FXML
    private JFXButton btn;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void debtstatis(MouseEvent event) throws IOException {
        setUi("Dthu");
        new FadeIn(context).play();
    }

    @FXML
    private void btn1(MouseEvent event) {
    }

    @FXML
    private void btn2(MouseEvent event) {
    }

     private void setUi(String location) throws IOException{
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location + ".fxml")));
    }
}
