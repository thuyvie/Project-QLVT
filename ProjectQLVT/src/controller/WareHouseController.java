/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class WareHouseController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<warehouse> tableproductview;
    @FXML
    private TableColumn<?, ?> tblidpro;
    @FXML
    private TableColumn<?, ?> tblinventory;
    @FXML
    private TableColumn<?, ?> tblamount;
    @FXML
    private TableColumn<?, ?> tblprice;
    @FXML
    private TableColumn<?, ?> tbldelete;
    @FXML
    private TextField txtidpro;
    @FXML
    private TextField txtinventory;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtamount;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;
    @FXML
    private JFXButton btnup;
    @FXML
    private TextField txxtid;
    @FXML
    private JFXButton btnclear;
    @FXML
    private Label txtdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickTable(MouseEvent event) {
    }

    @FXML
    private void SearchAction(ActionEvent event) {
    }

    @FXML
    private void insertAction(ActionEvent event) {
    }

    @FXML
    private void UpdateAction(ActionEvent event) {
    }

    @FXML
    private void ClearAction(ActionEvent event) {
    }
    
}
