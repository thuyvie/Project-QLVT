/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


/**
 *
 * @author ASUS
 */
public class FXMain extends Application {
    double x,y;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/MenuUI.fxml")); 
       Parent root = FXMLLoader.load(getClass().getResource("/view/DsOwe.fxml")); 
//          Parent root = FXMLLoader.load(getClass().getResource("/view/DetailOwe.fxml"));
//       Parent root = FXMLLoader.load(getClass().getResource("/view/order_1.fxml"));
//       Parent root = FXMLLoader.load(getClass().getResource("/view/showPayment.fxml"));
//         Parent root = FXMLLoader.load(getClass().getResource("/view/DetailOwe.fxml"));
        Scene scene = new Scene(root);

        
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged(event -> {
        primaryStage.setX(event.getScreenX() - x);
        primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
