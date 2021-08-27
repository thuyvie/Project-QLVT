/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.payowe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.detailOwe;
import model.owe;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class OweNController implements Initializable {

    @FXML
    private Pane order;
    @FXML
    private Label txtoweid;
    @FXML
    private JFXButton btnsave;
    @FXML
    private TextField txtpaid;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtphone;
    @FXML
    private Pane discount;
    @FXML
    private TextField txttotal;
    @FXML
    private Label txtrs;
    @FXML
    private JFXCheckBox txtfull2;
    @FXML
    private JFXCheckBox txtun2;
    @FXML
    private JFXButton btnprint;
    @FXML
    private ImageView request;
    @FXML
    private TextField txtpaid2;
    @FXML
    private Label txttotaldebt;
    @FXML
    private Label txtpai;
    @FXML
    private Label txtowe;
    @FXML
    private TableColumn<owe, String> tblow;
    @FXML
    private TableColumn<owe, String> tblname;
    @FXML
    private TableColumn<owe, String> tblphone;
    @FXML
    private TableColumn<owe, String> tblmoney;
    @FXML
    private TableColumn<owe, String> tbltt;
    @FXML
    private TableView<owe> tblN;
    double count;
    int total;
    private int index;
    private boolean OWE;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    @FXML
    private TableColumn<owe, String> tblstt;
    @FXML
    private TextField txtsearch;
    ObservableList<owe> data;
    void setOWE(boolean o) {
        this.OWE = o;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showN();
        search();
    }

    public ObservableList<owe> findAll() {
        ObservableList<owe> listow = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT detailowe.NameCus, detailowe.PhoneCus, detailowe.Paid, detailowe.TotalDebt, detailowe.IdOwe, owe.status FROM detailowe INNER JOIN owe ON detailowe.IdOwe = owe.IdOwe ORDER BY IdOwe DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                owe ow = new owe();
                ow.setNameCus(rs.getString("NameCus"));
                ow.setPhoneCus(rs.getString("PhoneCus"));
                ow.setPaid(rs.getDouble("Paid"));
                ow.setTotalDebt(rs.getDouble("TotalDebt"));
                ow.setIdOwe(rs.getString("IdOwe"));
                ow.setStatus(rs.getString("status"));
                listow.add(ow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listow;
    }
     public void search() {
        tblow.setCellValueFactory(new PropertyValueFactory<>("IdOwe"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblmoney.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        tbltt.setCellValueFactory(new PropertyValueFactory<>("TotalDebt"));
        tblstt.setCellValueFactory(new PropertyValueFactory<> ("status"));
        data = DBConnect.getOwe();
        tblN.setItems(data);
        FilteredList<owe> filteredData = new FilteredList<>(data, e -> true);
        txtsearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super owe>) owe2 -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (owe2.getNameCus().contains(newValue)) {
                    return true;
                } else if (owe2.getPhoneCus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<owe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblN.comparatorProperty());
        tblN.setItems(sortedData);

    }
    void setTextField(String NameCus, String PhoneCus, Double Paid, Double TotalDebt, String IDdOwe) {
        txtname.setText(NameCus);
        txtphone.setText(PhoneCus);
        txtpaid.setText(String.valueOf(Paid));
        txttotal.setText(String.valueOf(TotalDebt));
        txttotaldebt.setText(String.valueOf(TotalDebt));
        txtoweid.setText(IDdOwe);
    }

    public void showN() {
        ObservableList<owe> listow = findAll();
        tblow.setCellValueFactory(new PropertyValueFactory<>("IdOwe"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblmoney.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        tbltt.setCellValueFactory(new PropertyValueFactory<>("TotalDebt"));
        tblstt.setCellValueFactory(new PropertyValueFactory<> ("status"));
        tblN.setItems(listow);
    }

    private void finalTotaladd() {
        txtrs.setText(txtowe.getText());

    }

    @FXML
    private void SaveAction(ActionEvent event) {
        count = Double.parseDouble(txtpaid2.getText());
        txtpai.setText(count + "");
        double total = 0;

        total = Double.parseDouble(txttotal.getText()) - count;

        txtowe.setText(String.valueOf(total));
        finalTotaladd();
    }
    public void Print(String child){
        
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("child", child);
        
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Bill/bill.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, DBConnect.getConnect());
            JasperViewer.viewReport(jp); 
            
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"challanreport.pdf")));
        } catch (JRException ex) {
            Logger.getLogger(ShowPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void printAndSaveAction(ActionEvent event) {
        payowe P = new payowe();
        String IdOwe = txtoweid.getText();
        String NameCus = txtname.getText();
        String PhoneCus = txtphone.getText();
        String status = "";
        if (txtfull2.isSelected()) {
            status += txtfull2.getText();
        } else if (txtun2.isSelected()) {
            status += txtun2.getText();
        }
        double Paid = Double.parseDouble(txtpai.getText());
        double Owe = Double.parseDouble(txtowe.getText());
        double TotalDebt = Double.parseDouble(txtrs.getText());
        
        try {
            boolean printAndSave = P.placeOrder2(new owe(IdOwe, NameCus, PhoneCus, status, Paid, Owe, TotalDebt));
            Print(IdOwe);
            if (printAndSave) {
                (new Alert(Alert.AlertType.CONFIRMATION, "OweReceipt Successfully", new ButtonType[]{ButtonType.OK})).show();
                String tilte = "OweReceipt SUCCESS";
                String message = "OweReceipt SUCCESS";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                showN();
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));

            } else {
                (new Alert(Alert.AlertType.ERROR, "OweReceipt Unsuccessfully", new ButtonType[]{ButtonType.OK})).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reloadAction(MouseEvent event) {
        txtname.setText("");
        txtphone.setText("");
        txtowe.setText("");
        txtoweid.setText("");
        txtpai.setText("");
        txtpaid.setText("");
        txtpaid2.setText("");
        txtrs.setText("");
        txttotal.setText("");
        txttotaldebt.setText("");
    }

    @FXML
    private void clickTB(MouseEvent event) {
        owe de = tblN.getSelectionModel().getSelectedItem();
        txtoweid.setText(de.getIdOwe());
        txtname.setText(de.getNameCus());
        txtphone.setText(de.getPhoneCus());
        txtpaid.setText(String.valueOf(de.getPaid()));
        txttotal.setText(String.valueOf(de.getTotalDebt()));
        txttotaldebt.setText(String.valueOf(de.getTotalDebt()));
    }

}
