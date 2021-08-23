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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class DsOweController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<?> tblds;
    @FXML
    private TableColumn<?, ?> tblname;
    @FXML
    private TableColumn<?, ?> tblphone;
    @FXML
    private TableColumn<?, ?> tblemail;
    @FXML
    private TableColumn<?, ?> tbladdress;
    @FXML
    private TableColumn<?, ?> tblform;
    @FXML
    private TableColumn<?, ?> tblstt;
    @FXML
    private TableColumn<?, ?> tblchitiet;
    @FXML
    private TextField txttimkiem;
    @FXML
    private JFXButton btnsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchAction(ActionEvent event) {
    }
    
}
