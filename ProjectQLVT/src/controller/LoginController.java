/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.stage.Stage;
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
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void Close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String username = tfusername.getText();
        String pass = tfpassword.getText();
        
        if (username.equals("") && pass.equals("")) {
            
            JOptionPane.showMessageDialog(null, "UserName or Password Blank");
        }
        else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlvt?useSSL=false", "root","");
                
                pst = conn.prepareStatement("select * from emp where Account = ? and Password = ?");
                pst.setString(1, username);
                pst.setString(2, pass);
                
                rs = pst.executeQuery();
                
                if (rs.next() ) {

                    
                    btnlogin.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/MenuUI.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    
                }
                else{
                   JOptionPane.showMessageDialog(null, "Login Failed"); 
                   
                   tfusername.setText("");
                   tfpassword.setText("");
                   tfusername.requestFocus();
                }
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex){
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

   


}