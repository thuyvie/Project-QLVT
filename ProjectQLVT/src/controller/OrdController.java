/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.orderDao;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.customer;
import model.dtmTM;
import model.ord;
import model.product;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.vendorlot;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class OrdController implements Initializable {

    @FXML
    private Pane order;
    @FXML
    private Label txtdate;
    @FXML
    private Label txttime;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtphone;
    @FXML
    private TextField txtitemcode;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txtitemname;
    @FXML
    private TextField txtprice;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private Pane discount;
    @FXML
    private Label txtqantity;
    @FXML
    private Label txttotal;
    @FXML
    private Label txtrs;
    @FXML
    private TableView<dtmTM> dtm;
    @FXML
    private JFXButton btnorder;
    @FXML
    private JFXButton btnremove;
    @FXML
    private Label labelemp;
    @FXML
    private TextField txtsearchphone;
    @FXML
    private TextField txtemail;
    @FXML
    private JFXButton btnsearchphone;
    @FXML
    private TableColumn<dtmTM, String> tblname;
    @FXML
    private TableColumn<dtmTM, String> tblprice;
    @FXML
    private TableColumn<dtmTM, String> tblqty;
    @FXML
    private TableColumn<dtmTM, String> tblrs;
    private TableColumn<dtmTM, Double> txtttttt;
    ArrayList<dtmTM> items = new ArrayList<>();
    int count;
    int total;
    int qty;
    int stockQuantity;
    double rs = 0;
    private int index;
    orderDao ordDao = new orderDao();
    product pro = new product();
    dtmTM dtmTM;
    @FXML
    private ImageView request;
    private TextField txtorder;
    @FXML
    private TextField txtnote;
    private Label txtorderid;
    @FXML
    private Label txtorderid2;
    @FXML
    private JFXButton btnok;
    @FXML
    private TableColumn<dtmTM, String> tblmoney;
    @FXML
    private Label txttrangthai;
    private TextField txtqtyy;
    private boolean update;
    @FXML
    private ImageView box;
    @FXML
    private ImageView ds;

    /**
     * Initializes the controller class.
     */
    void setOWE(boolean o) {
        this.update = o;
    }

    void setTextField(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, String itemCode, String namepro, int qty, Double Price, Double Total) {
        txtorderid2.setText(OrdID);
        txtname.setText(NameCus);
        txtphone.setText(PhoneCus);
        txtaddress.setText(AddressCus);
        txtemail.setText(EmailCus);
        txtitemcode.setText(itemCode);
        txtitemname.setText(namepro);
        txtprice.setText(String.valueOf(Price));
        txtqty.setText(String.valueOf(qty));
        txttotal.setText(String.valueOf(Total));
        txtdate.setText(dateOrd);
        txttime.setText(timeOrd);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateDateTime();
        showOrder();
        settxtoweid();
    }

    public void showOrder() {
        tblname.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblprice.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblqty.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblrs.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        tblmoney.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    public void generateDateTime() {
        this.txtdate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, (e) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            this.txttime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1.0D)));
        timeline.setCycleCount(-1);
        timeline.play();

    }

    private void finalTotaladd() {
//         double TotalPrice = 0.0;
//         TotalPrice = dtm.getItems().stream().map(
//            (item) -> item.getTotal()).reduce(TotalPrice, (accumulator, _item) -> accumulator + _item);
//
//          txtttttt.setText(String.valueOf(TotalPrice));

        txtrs.setText(txttotal.getText());
    }

    private void reloadTotalQty() {
        int qty = 0;
        double total = 0;
        double rs = 0;
        for (dtmTM dtmTM : items) {
            qty = Integer.parseInt(dtmTM.getQTY());
            double p = Double.parseDouble(dtmTM.getPrice());
            total = p * Integer.parseInt(dtmTM.getQTY());
            rs += total;
        }
        txtqantity.setText(qty + "");
        txttotal.setText(total + "");
        txtrs.setText(rs + "");
    }

    public void OrderDetailFieldRest() {
        txtname.setText(null);
        txtaddress.setText(null);
        txtphone.setText(null);
        txtemail.setText(null);
        txttotal.setText(null);
        txtqantity.setText(null);
        txtrs.setText(null);
        txtitemcode.setText(null);
        txtsearchphone.setText(null);
        txtitemname.setText(null);
        txtprice.setText(null);
        txtqty.setText(null);
        txtorderid2.setText(null);
        tblname.setText(null);
        tblprice.setText(null);
        tblqty.setText(null);
        tblrs.setText(null);
        txtnote.setText(null);
        txttrangthai.setText(null);
    }

    @FXML
    private void clickTable(MouseEvent event) {
        dtmTM c = dtm.getSelectionModel().getSelectedItem();
        txtitemname.setText(c.getName());
        txtprice.setText(c.getPrice());
        txtitemcode.setText(c.getCode());
    }

    public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(OrdID) FROM orders";
        ResultSet resultSet = CrudUtil.executeQuery(SQL);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    public void settxtoweid() {
        try {
            int id = getRowCount();
            if (id < 9) {
                this.txtorderid2.setText("K00" + (id + 1));
            } else if (id < 99) {
                this.txtorderid2.setText("K0" + (id + 1));
            } else {
                this.txtorderid2.setText("K" + (id + 1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Print(String ord) {

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("ord", ord);

        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Bill/ord.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, DBConnect.getConnect());
            JasperViewer.viewReport(jp);

//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"challanreport.pdf")));
        } catch (JRException ex) {
            Logger.getLogger(ShowPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void orderAction(ActionEvent event) {

        String OrdID = txtorderid2.getText();
        String NameCus = txtname.getText();
        String PhoneCus = txtphone.getText();
        String EmailCus = txtemail.getText();
        String AddressCus = txtaddress.getText();
        String dateOrd = txtdate.getText();
        String timeOrd = txttime.getText();
//        int IDEmp = Integer.parseInt(labelemp.getText());
        double amount = Double.parseDouble(txtrs.getText());
        String note = txtnote.getText();
        if (update == false) {
            try {
                boolean placeorder = ordDao.placeOrder(new ord(OrdID, NameCus, PhoneCus, EmailCus, AddressCus, dateOrd, timeOrd, items, amount, note));
                Print(OrdID);

                if (placeorder) {
                    OrderDetailFieldRest();
                    (new Alert(Alert.AlertType.CONFIRMATION, "Order Successfully", new ButtonType[]{ButtonType.OK})).show();
                    String tilte = "ORDER SUCCESS";
                    String message = "THANK YOU FOR CUSTOMER";
                    tray.notification.TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    settxtoweid();
                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));

                } else {
                    (new Alert(Alert.AlertType.ERROR, "Order  Unsuccessfully", new ButtonType[]{ButtonType.OK})).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean placeorder = ordDao.placeOrder3(new ord(OrdID, NameCus, PhoneCus, EmailCus, AddressCus, dateOrd, timeOrd, items, amount, note));
                Print(OrdID);

                if (placeorder) {
                    OrderDetailFieldRest();
                    (new Alert(Alert.AlertType.CONFIRMATION, "Order Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                    String tilte = "ORDER Update SUCCESS";
                    String message = "THANK YOU FOR CUSTOMER";
                    tray.notification.TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    settxtoweid();
                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));

                } else {
                    (new Alert(Alert.AlertType.ERROR, "Order  Unsuccessfully", new ButtonType[]{ButtonType.OK})).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
//        items.remove(index);
//        dtm.setItems(FXCollections.observableArrayList(items));
//        reloadTotalQty();
        double tot = 0;
        tot = Integer.parseInt(txtqty.getText()) * Double.parseDouble(txtprice.getText());
        int selectedIndex = dtm.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            dtm.getItems().remove(selectedIndex);
            items.remove(selectedIndex);
                rs = rs - tot;
            
        }

        int qty = 0;
        double total = 0;
        double rs = 0;
        for (dtmTM dtmTM : items) {
            qty = Integer.parseInt(dtmTM.getQTY());
            double p = Double.parseDouble(dtmTM.getPrice());
            total = p * Integer.parseInt(dtmTM.getQTY());
            rs += total;
        }
        txtqantity.setText(qty + "");
        txttotal.setText(total + "");
        txtrs.setText(rs + "");
    }

    @FXML
    private void adddiscountKey(KeyEvent event) {
    }

    @FXML
    private void searchAction2(ActionEvent event) {
        try {
            orderDao ord = new orderDao();
            if (txtsearchphone.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else {
                customer cus = ord.searchCus(txtsearchphone.getText());
                if (cus != null) {
                    txtname.setText(cus.getNameCus());
                    txtaddress.setText(cus.getAddressCus());
                    txtphone.setText(cus.getPhoneCus());
                    txtemail.setText(cus.getEmailCus());
                } else {
                    String tilte = "Searched Customer Not Found";
                    String message = "Try Again";
                    tray.notification.TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.ERROR);
                    tray.showAndDismiss(Duration.millis(3000));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchAction1(ActionEvent event) {
        try {
            orderDao ord = new orderDao();
            if (txtitemcode.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else {
                product pro = ord.searchPro(txtitemcode.getText());
                if (pro != null) {
                    txtitemcode.setText(pro.getItemCode());
                    txtitemname.setText(pro.getNamepro());
                    txtprice.setText(String.valueOf(pro.getPrice()));
                    txttrangthai.setText(String.valueOf(pro.getQty()));
                } else {
                    String tilte = "Searched Product Not Found";
                    String message = "Try Again";
                    tray.notification.TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.ERROR);
                    tray.showAndDismiss(Duration.millis(3000));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reloadAction(MouseEvent event) {
        txtname.setText("");
        txtaddress.setText("");
        txtphone.setText("");
        txtemail.setText("");
        txttotal.setText("");
        txtqantity.setText("");
        txtrs.setText("");
        txtitemcode.setText("");
        txtsearchphone.setText("");
        txtitemname.setText("");
        txtprice.setText("");
        txtqty.setText("");
        txttrangthai.setText("");
    }

    public void setEmpID(String IDEmp) {
        labelemp.setText(IDEmp);
        System.out.println(IDEmp + "IDEmp");
    }

    @FXML
    private void OKAction(ActionEvent event) {
        int i = 1;
        count = Integer.parseInt(txtqty.getText());
        txtqantity.setText(count + "");
        double total = 0;

        total = Integer.parseInt(txtqty.getText()) * Double.parseDouble(txtprice.getText());
        rs = rs + total;
        txttotal.setText(String.valueOf(total));
        txtrs.setText(rs + "");
        i++;
        String code = txtitemcode.getText();
        String name = txtitemname.getText();
        String price = txtprice.getText();
        String QTY = txtqty.getText();
        Double Total = Double.parseDouble(txttotal.getText());
        dtmTM rowData = new dtmTM(code, name, price, QTY, Total);
        items.add(rowData);
        dtm.setItems(FXCollections.observableArrayList(items));
    }

    @FXML
    private void BoxAction(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Dspro.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DsAction(MouseEvent event) {
    }
}
