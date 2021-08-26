/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.detailOwe;
import model.owe;
import model.owe2;
import model.product;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class DsOweController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<owe> tblds;
    @FXML
    private TableColumn<owe, String> tblname;
    @FXML
    private TableColumn<owe, String> tblphone;
    @FXML
    private TableColumn<owe, String> tblemail;
    @FXML
    private TableColumn<owe, String> tbladdress;
    @FXML
    private TableColumn<owe, String> tblform;
    @FXML
    private TableColumn<owe, String> tblstt;
    @FXML
    private TableColumn<owe, String> tblchitiet;
    @FXML
    private TextField txttimkiem;
    @FXML
    private JFXButton btnsearch;
    PreparedStatement preparedStatement = null;
    String query = null;
    detailOwe de= null;
    Connection connection = null;
    ResultSet resultSet = null ;
    ObservableList<owe> data;
    int index = -1;
    owe o = null;
    /**
     * Initializes the controller class.
     */
    public ObservableList<owe> findAll() {
        ObservableList<owe> listowe = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT detailowe.Paid, detailowe.Owe, detailowe.TotalDebt, detailowe.IdOwe,owe.NameCus,owe.PhoneCus, owe.EmailCus, owe.AddressCus,owe.PaymentForm,owe.status FROM detailowe INNER JOIN owe ON detailowe.IdOwe = owe.IdOwe";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                owe ow= new owe();
                ow.setIdOwe(rs.getString("IdOwe"));
                ow.setNameCus(rs.getString("NameCus"));
                ow.setPhoneCus(rs.getString("PhoneCus"));
                ow.setEmailCus(rs.getString("EmailCus"));
                ow.setAddressCus(rs.getString("AddressCus"));
                ow.setPaymentForm(rs.getString("PaymentForm"));
                ow.setStatus(rs.getString("status"));
                ow.setPaid(rs.getDouble("Paid"));
                ow.setOwe(rs.getDouble("Owe"));
                ow.setTotalDebt(rs.getDouble("TotalDebt"));
                listowe.add(ow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listowe;
    }
   
    public void showOwe(){
        ObservableList<owe> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("EmailCus"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("AddressCus"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("PaymentForm"));
        tblstt.setCellValueFactory(new PropertyValueFactory<>("status"));
        Callback<TableColumn<owe, String>, TableCell<owe, String>> cellFoctory = (TableColumn<owe, String> param) -> {
            final TableCell<owe, String> cell = new TableCell<owe, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        JFXButton add = new JFXButton("Detail");
                        add.setPrefWidth(59);
                        add.setPrefHeight(26);
                        add.setStyle(
                            "-fx-background-color: #7FC8A9;"
                            + "-fx-background-radius: 15;"
                        );
                        add.setOnMouseClicked((MouseEvent event) -> {
                            
                                 o = tblds.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader ();
                                loader.setLocation(getClass().getResource("/view/DetailOwe.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(DsOweController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            DetailOweController dtcontroller = loader.getController();
                            dtcontroller.setfindAll(true);
//                            dtcontroller.setTextField(o.getNameCus(),o.getPhoneCus(),o.getPaid(), o.getOwe(), o.getTotalDebt());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });

                        HBox managebtn = new HBox(add);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(add, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
        tblchitiet.setCellFactory(cellFoctory);
        tblds.setItems(list);
    }
    public void search(){
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("EmailCus"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("AddressCus"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("PaymentForm"));
        tblstt.setCellValueFactory(new PropertyValueFactory<>("status"));
        data = DBConnect.getOwe();
        tblds.setItems(data);
        FilteredList<owe> filteredData = new FilteredList<>(data, e -> true);
            txttimkiem.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super owe>) owe2 ->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(owe2.getNameCus().contains(newValue)){
                        return true;
                    }else if(owe2.getPhoneCus().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<owe> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblds.comparatorProperty());
            tblds.setItems(sortedData);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showOwe();
        search();
    }    

    @FXML
    private void SearchAction(ActionEvent event) {
        if(event.getSource() == btnsearch){
            search();
        }
    }
    
}
