/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import Dao.CateEmpDao;
import Dao.EmpDao;
import Dao.productDao;
import Dao.vendorDao;
import Dao.CateEmpDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.product;
import util.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.emp;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.catemp;

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<emp, String> tblname;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtpassword;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;
    @FXML
    private JFXButton btnup;
    @FXML
    private TextField txtid;
    @FXML
    private JFXButton btnclear;
    @FXML
    private TextField txtphone;
    @FXML
    private TableView<emp> tableproductview;
    @FXML
    private TableColumn<emp, String> tblaccount;
    @FXML
    private TableColumn<emp, String> tblsalary;
    @FXML
    private TableColumn<emp, String> tblrank;    @FXML
    private TextField txtsalary;
    @FXML
    private JFXComboBox<String> cbb;
    @FXML
    private TableColumn<emp, String> tblphone;
    PreparedStatement preparedStatement = null;
    String query = null;
    emp emps = null;
    Connection connection = null;
    private boolean Save;
    private boolean isUpdate;
    CateEmpDao camDao = new CateEmpDao();
    @FXML
    private TextField txtaccount;
    @FXML
    private TableColumn<emp, String> tbldelete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEmp();
        fillCBB();
    }    
    public ObservableList<emp> findAll() {
        ObservableList<emp> listpro = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT emp.ID, emp.NameEmp, emp.PhoneEmp, emp.Account, emp.Password, emp.Salary,emp.IDCateEmp, catemp.EmpCate FROM emp INNER JOIN catemp ON emp.IDCateEmp = catemp.ID ORDER BY ID DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                emp emp = new emp();
                emp.setID(rs.getString("ID"));
                emp.setNameEmp(rs.getString("NameEmp"));
                emp.setPhoneEmp(rs.getString("PhoneEmp"));
                emp.setAccount(rs.getString("Account"));
                emp.setPassword(rs.getString("Password"));
                emp.setSalary(rs.getDouble("Salary"));
                emp.setIDCateEmp(rs.getString("IDCateEmp"));
                emp.setEmpCate(rs.getString("EmpCate"));
                listpro.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpro;
    }
    public void showEmp() {
        ObservableList<emp> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameEmp"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneEmp"));
        tblaccount.setCellValueFactory(new PropertyValueFactory<>("Account"));
        tblsalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        tblrank.setCellValueFactory(new PropertyValueFactory<>("EmpCate"));
        Callback<TableColumn<emp, String>, TableCell<emp, String>> cellFoctory = (TableColumn<emp, String> param) -> {
            final TableCell<emp, String> cell = new TableCell<emp, String>() {

                @Override
                public void updateItem(String emp, boolean empty) {
                    super.updateItem(emp, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:15px;"
                                + "-fx-fill:#ff1744;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                emps = tableproductview.getSelectionModel().getSelectedItem();
                                query = "delete from emp where ID='" + emps.getID() + "'";
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                showEmp();
                            } catch (SQLException ex) {
                                Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
        tbldelete.setCellFactory(cellFoctory);
        tableproductview.setItems(list);
    }
        private void ClearText() {
        txtid.setText(null);
        txtname.setText(null);
        cbb.getItems();
        txtpassword.setText(null);
        txtaccount.setText(null);
        txtphone.setText(null);
        txtsalary.setText(null);
    }
        public void fillCBB() {
        ResultSet rs;
        try {
            ObservableList<String> listven = FXCollections.observableArrayList();
            String sql = "SELECT EmpCate FROM catemp";
            Connection con = DBConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listven.add(rs.getString("EmpCate"));
            }
            cbb.setItems(listven);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
            

    @FXML
    private void clickTable(MouseEvent event) {
        emp e = tableproductview.getSelectionModel().getSelectedItem();
        txtid.setText(e.getID());
        txtname.setText(e.getNameEmp());
        cbb.setValue(e.getEmpCate());
        txtaccount.setText(e.getAccount());
        txtpassword.setText(e.getPassword());
        txtphone.setText(e.getPhoneEmp());
        txtsalary.setText(String.valueOf(e.getSalary()));
    }

    @FXML
    private void SearchAction(ActionEvent event) {
        try {
            EmpDao empDao = new EmpDao();
            if (txtname.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else{ 
                emp empl = empDao.searchEmp(txtname.getText());
                if (empl != null) {
                txtid.setText(empl.getID());
                txtname.setText(empl.getNameEmp());
                cbb.setValue(empl.getCate().getEmpCate());
                txtaccount.setText(empl.getAccount());
                txtpassword.setText(empl.getPassword());
                txtsalary.setText(String.valueOf(empl.getSalary()));
                txtphone.setText(empl.getPhoneEmp());
                String tilte = "Employee Searched ";
                String message = "Employee Is " + "" + txtname.getText() + "";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            } else {
                String tilte = "Searched Employee Not Found";
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
    private void insertAction(ActionEvent event) throws Exception {
        try {
            emp emp = new emp();
            emp.setID(txtid.getText());
            emp.setNameEmp(txtname.getText());
            emp.setPhoneEmp(txtphone.getText());
            emp.setAccount(txtaccount.getText());
            emp.setPassword(txtpassword.getText());
            emp.setSalary(Double.parseDouble(txtsalary.getText()));
            catemp cate = camDao.findByName(cbb.getValue());
            emp.setCate(cate);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            EmpDao empDao = new EmpDao();
            if (empDao.insert(emp)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Employee Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Employee Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showEmp();

            } else {
                (new Alert(Alert.AlertType.ERROR, "Emp Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Employee Is Not Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Account Employee Is Already On The Sever";
            String message = "Employee Is Not Added";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showEmp();
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws Exception {
        try {
           emp emp = new emp();
            emp.setID(txtid.getText());
            emp.setNameEmp(txtname.getText());
            emp.setPhoneEmp(txtphone.getText());
            emp.setAccount(txtaccount.getText());
            emp.setPassword(txtpassword.getText());
            emp.setSalary(Double.parseDouble(txtsalary.getText()));
            catemp cate = camDao.findByName(cbb.getValue());
            emp.setCate(cate);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            EmpDao empDao = new EmpDao();
            if (empDao.update(emp)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Employee Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Employee Is Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showEmp();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Employee Not Updated ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Un Successful";
                message = "Employee Is Not Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Employe Is Already On The Sever";
            String message = "Employee Is Not Updated";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }

    @FXML
    private void ClearAction(ActionEvent event) {
         if (event.getSource() == btnclear) {
            ClearText();
        }
    }

//    @FXML
//    private void vendorAction(ActionEvent event) {
//    }

    @FXML
    private void vendorAction(ActionEvent event) {
    }


    
}
