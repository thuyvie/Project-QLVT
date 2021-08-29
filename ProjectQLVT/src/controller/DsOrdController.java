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
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.detailOwe;
import model.ord;
import model.owe;
import util.DBConnect;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class DsOrdController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<ord> tblds;
    @FXML
    private TableColumn<ord, String> tblname;
    @FXML
    private TableColumn<ord, String> tblphone;
    @FXML
    private TableColumn<ord, String> tblemail;
    @FXML
    private TableColumn<ord, String> tbladdress;
    @FXML
    private TableColumn<ord, String> tblform;
    @FXML
    private TableColumn<ord, String> tblstt;
    @FXML
    private TableColumn<ord, String> tblcode;
    @FXML
    private TableColumn<ord, String> tblnamepro;
    @FXML
    private TableColumn<ord, Double> tblqty;
    @FXML
    private TableColumn<ord, String> tbldelete;
    @FXML
    private TextField txttimkiem;
    @FXML
    private JFXButton btnsearch;
    private TextField txtcodepro;
    @FXML
    private TableColumn<ord, Double> tblprice;
    @FXML
    private TableColumn<ord, Double> tblto;
    PreparedStatement preparedStatement = null;
    String query = null;
    ord de = null;
    Connection connection = null;
    ResultSet resultSet = null;
    ObservableList<ord> data;
    int index = -1;
    owe o = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        search();
        show();
    }    
     
    public ObservableList<ord> findAll() {
        ObservableList<ord> listowe = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = ("SELECT order_detail.OrderID, orders.NameCus, orders.PhoneCus, orders.EmailCus,"
                        + "orders.AddressCus, orders.dateOrd, orders.timeOrd, order_detail.IDProduct, product.itemCode, product.namepro,"
                        + "order_detail.qty, order_detail.Price, order_detail.Total FROM order_detail INNER JOIN orders ON order_detail.OrderID = orders.OrdID INNER JOIN product ON order_detail.IDProduct = product.itemCode");
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ord or = new ord();
                or.setNameCus(rs.getString("NameCus"));
                or.setPhoneCus(rs.getString("PhoneCus"));
                or.setEmailCus(rs.getString("EmailCus"));
                or.setAddressCus(rs.getString("AddressCus"));
                or.setDateOrd(rs.getString("dateOrd"));
                or.setTimeOrd(rs.getString("timeOrd"));
                or.setItemCode(rs.getString("itemCode"));
                or.setNamepro(rs.getString("namepro"));
                or.setQty(rs.getInt("qty"));
                or.setPrice(rs.getDouble("Price"));
                or.setTotal(rs.getDouble("Total"));
                listowe.add(or);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listowe;
    }
    public void show(){
        ObservableList<ord> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblcode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblnamepro.setCellValueFactory(new PropertyValueFactory<>("namepro"));
        tblqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("EmailCus"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("AddressCus"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("dateOrd"));
        tblstt.setCellValueFactory(new PropertyValueFactory<>("timeOrd"));
        tblprice.setCellValueFactory(new PropertyValueFactory<> ("Price"));
        tblto.setCellValueFactory(new PropertyValueFactory<> ("Total"));
        
         Callback<TableColumn<ord, String>, TableCell<ord, String>> cellFoctory = (TableColumn<ord, String> param) -> {
            // make cell containing buttons
            final TableCell<ord, String> cell = new TableCell<ord, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                de = tblds.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `student` WHERE id  ="+de.getOrdID();
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
//                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(DsOrdController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            de = tblds.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/view/order_1.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(DsOrdController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            OrdController addOrdController = loader.getController();
                            addOrdController.setOWE(true);
                            addOrdController.setTextField(de.getNameCus(), de.getPhoneCus(), de.getEmailCus(), de.getAddressCus(), de.getDateOrd(), de.getTimeOrd(), de.getItemCode(), de.getNamepro(), de.getQty(), de.getPrice(), de.getTotal());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         tbldelete.setCellFactory(cellFoctory);
         tblds.setItems(list);
         
         
    }
    public void search() {
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblcode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblnamepro.setCellValueFactory(new PropertyValueFactory<>("namepro"));
        tblqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("EmailCus"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("AddressCus"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("dateOrd"));
        tblstt.setCellValueFactory(new PropertyValueFactory<>("timeOrd"));
        tblprice.setCellValueFactory(new PropertyValueFactory<> ("Price"));
        tblto.setCellValueFactory(new PropertyValueFactory<> ("Total"));
        data = DBConnect.getOrd();
        tblds.setItems(data);
        FilteredList<ord> filteredData = new FilteredList<>(data, e -> true);
        txttimkiem.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super ord>) o -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (o.getNameCus().contains(newValue)) {
                    return true;
                } else if (o.getPhoneCus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ord> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblds.comparatorProperty());
        tblds.setItems(sortedData);

    }
//    @FXML
//    private void ClickAction(MouseEvent event) {
//        ord Ord = tblds.getSelectionModel().getSelectedItem();
//        txtcodepro.setText(Ord.getItemCode());
//    }

    @FXML
    private void SearchAction(ActionEvent event) {
    }

    @FXML
    private void ClickAction(MouseEvent event) {
    }
    
}
