/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.detailOwe;
import model.owe;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class DetailOweController implements Initializable {

    @FXML
    private AnchorPane pan2;
    @FXML
    private TableView<owe> tblchitiet;
    @FXML
    private TableColumn<owe, String> tblname1;
    @FXML
    private TableColumn<owe, String> tblphone1;
    @FXML
    private TableColumn<owe, Double> tblpaid;
    @FXML
    private TableColumn<owe, Double> tblowe;
    @FXML
    private TableColumn<owe, Double> tbltt;
    @FXML
    private TableColumn<owe, String> btnlap;
    private boolean findall;
    Connection connection = null;
    /**
     * Initializes the controller class.
     * @param NameCus
     * @param PhoneCus
     * @param Paid
     * @param Owe
     * @param TotalDebt
     */
    public void setTextField(String NameCus, String PhoneCus, Double Paid, Double Owe, Double TotalDebt){
        tblname1.setText(NameCus);
        tblphone1.setText(PhoneCus);
        tblpaid.setText(String.valueOf(Paid));
        tblowe.setText(String.valueOf(Owe));
        tbltt.setText(String.valueOf(TotalDebt));
    }
    public void setP(Double Paid, Double Owe, Double TotalDebt){
        tblpaid.setText(String.valueOf(Paid));
        tblowe.setText(String.valueOf(Owe));
        tbltt.setText(String.valueOf(TotalDebt));
    }
     public ObservableList<owe> findAll() {
        ObservableList<owe> listow = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT detailowe.Paid, detailowe.Owe, detailowe.TotalDebt, detailowe.IdOwe,owe.NameCus,owe.PhoneCus, owe.EmailCus, owe.AddressCus,owe.PaymentForm,owe.status FROM detailowe INNER JOIN owe ON detailowe.IdOwe = owe.IdOwe";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                owe ow= new owe();
                ow.setNameCus(rs.getString("NameCus"));
                ow.setPhoneCus(rs.getString("PhoneCus"));
                ow.setPaid(rs.getDouble("Paid"));
                ow.setOwe(rs.getDouble("Owe"));
                ow.setTotalDebt(rs.getDouble("TotalDebt"));
                listow.add(ow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listow;
    }
     public void Detail(){
         connection = DBConnect.getConnect();
         ObservableList<owe> listow = findAll();
        tblname1.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone1.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblpaid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        tblowe.setCellValueFactory(new PropertyValueFactory<>("Owe"));
        tbltt.setCellValueFactory(new PropertyValueFactory<>("TotalDebt"));
        Callback<TableColumn<owe, String>, TableCell<owe, String>> cellFoctory = (TableColumn<owe, String> param) -> {
            final TableCell<owe, String> cell = new TableCell<owe, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        JFXButton add = new JFXButton("NewOwe");
                        add.setPrefWidth(70);
                        add.setPrefHeight(26);
                        add.setStyle(
                            "-fx-background-color: #7FC8A9;"
                            + "-fx-background-radius: 15;"
                        );
                        add.setOnMouseClicked((MouseEvent event) -> {
                            
                                owe ow = tblchitiet.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader ();
                                loader.setLocation(getClass().getResource("/view/OweN.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(DsOweController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            OweNController owecontroller = loader.getController();
                            owecontroller.setOWE(true);
//                            owecontroller.setTextField(ow.getNameCus(), ow.getPhoneCus(), ow.getPaid(), ow.getTotalDebt(), ow.getIdOwe());
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
        btnlap.setCellFactory(cellFoctory);
        tblchitiet.setItems(listow);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Detail();
    }    
    public void setfindAll(boolean b){
        this.findall = b;
    }
}
