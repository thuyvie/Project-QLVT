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
public class Page1Controller implements Initializable {

    @FXML
    private JFXButton btnlogin;
    @FXML
    private JFXButton btnlogin1;
    @FXML
    private VBox pane;
    @FXML
    private Pane context;
    @FXML
    private JFXButton btnlogin2;
    @FXML
    private JFXButton btnlogin3;
    @FXML
    private JFXButton btnlogin4;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void issue(MouseEvent event) throws IOException {
        setUi("test1");
        new FadeIn(context).play();
    }

    @FXML
    private void receipt(MouseEvent event) throws IOException {
        setUi("test2");
        new FadeIn(context).play();
    }

    @FXML
    private void order(MouseEvent event) throws IOException {
        setUi("order_1");
        new FadeIn(context).play();
    }

    @FXML
    private void debt(MouseEvent event) throws IOException {
        setUi("test1");
        new FadeIn(context).play();
    }

    @FXML
    private void listdebt(MouseEvent event) throws IOException {
        setUi("test2");
        new FadeIn(context).play();
    }
     private void setUi(String location) throws IOException{
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location + ".fxml")));
    }
}
