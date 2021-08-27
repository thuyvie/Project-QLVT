/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Selector;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuUIController implements Initializable {

    @FXML
    private Pane contentArea;
    @FXML
    private BorderPane boderPane;
    @FXML
    private Pane ap;
    @FXML
    private LineChart<String, Number> linechart;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inilinechart();
        
    }    
    @FXML
    private void DashBoard(MouseEvent event) throws IOException {
       boderPane.setCenter(contentArea);
    }

    @FXML
    private void page1(MouseEvent event) throws IOException {  
        loadUI("Order");
//        setUi("Order");
//        new FadeIn(contentArea).play();
    }

    @FXML
    private void page2(MouseEvent event) throws IOException {
        loadUI("Product");
//        setUi("Product");
//        new FadeIn(contentArea).play();
    }

    @FXML
    private void page3(MouseEvent event) throws IOException {
        loadUI("page3");
//       setUi("page3");
//        new FadeIn(contentArea).play();
    }
    
    private void loadUI(String page){
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("/view/"+page+".fxml"));
                    } catch (IOException ex) {
            Logger.getLogger(MenuUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boderPane.setCenter(root);
    }
//private void setUi(String location) throws IOException{
//        contentArea.getChildren().clear();
//        contentArea.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location + ".fxml")));
//    }
    private void inilinechart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Dec",213));
        series.getData().add(new XYChart.Data<>("Jan",310));
        series.getData().add(new XYChart.Data<>("Feb",156));
        series.getData().add(new XYChart.Data<>("Mar",168));
        series.getData().add(new XYChart.Data<>("April",220));
        linechart.getData().addAll(series);
        linechart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke: #fff");
    }
}
