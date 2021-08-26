/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import javax.swing.JOptionPane;
//import java.sql.PreparedStatement;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.*;
//import javafx.util.Duration;
//import tray.animations.AnimationType;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private ImageView close;
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private JFXButton btnlogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    /**
     * Initializes the controller class.
     */
//    Connection conn;
//    PreparedStatement pst;
//    ResultSet rs;
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//
//    @FXML
//    private void Close(MouseEvent event) {
//        Stage stage = (Stage)panel.getScene().getWindow();
//        stage.close();
//    }
//    @FXML
//    private void handleButtonAction(ActionEvent event) throws IOException {
//        String username = tfusername.getText();
//        String pass = tfpassword.getText();
//
//        if (username.equals("") && pass.equals("")) {
//            JOptionPane.showMessageDialog(null, "User or Password Blank");
//        } else {
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                String url = "jdbc:mysql://localhost:3306/crm?useSSL=false";
//                String user = "root";
//                String password = "";
//                Connection conn = (Connection) DriverManager.getConnection(url, user, password);
//
//                pst = conn.prepareStatement("select * from employee where AccountEmployee =? and PassEmployee =?");
//                pst.setString(1, username);
//                pst.setString(2, pass);
//
//                rs = pst.executeQuery();
//                if (rs.next()) {
//                    Stage window = (Stage) this.panel.getScene().getWindow();
//                    window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/FXML/Dashboard.fxml"))));
//                    window.centerOnScreen();
//                    String tilte = "Sign In";
//                    String message = "WELCOME TO CRM MANEGMET SYSTEM ";
//                    tray.notification.TrayNotification tray = new TrayNotification();
//                    AnimationType type = AnimationType.POPUP;
//                    tray.setAnimationType(type);
//                    tray.setTitle(tilte);
//                    tray.setMessage(message);
//                    tray.setNotificationType(NotificationType.SUCCESS);
//                    tray.showAndDismiss(Duration.millis(3000));
//
//                } else {
//                    String tilte = "Sign In";
//                    String message = "UserName or Password failed";
//                    tray.notification.TrayNotification tray = new TrayNotification();
//                    AnimationType type = AnimationType.POPUP;
//
//                    tray.setAnimationType(type);
//                    tray.setTitle(tilte);
//                    tray.setMessage(message);
//                    tray.setNotificationType(NotificationType.INFORMATION);
//                    tray.showAndDismiss(Duration.millis(3000));
//                    tfusername.setText("");
//                    tfpassword.setText("");
//                    tfusername.requestFocus();
//                }
//
//            } catch (ClassNotFoundException ex) {
//                java.util.logging.Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                java.util.logging.Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//    }
}
