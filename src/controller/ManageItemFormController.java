package controller;

import business.BOFactory;
import business.BOType;
import business.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ItemTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageItemFormController implements Initializable {

    public AnchorPane root;
    public TableView<ItemTM> tblItems;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;

    private ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtUnitPrice.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);

        loadAllItems();

        tblItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {
                ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    txtCode.clear();
                    txtDescription.clear();
                    txtQtyOnHand.clear();
                    txtUnitPrice.clear();
                    return;
                }

                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtDescription.setDisable(false);
                txtQtyOnHand.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtCode.setText(selectedItem.getCode());
                txtDescription.setText(selectedItem.getDescription());
                txtQtyOnHand.setText(selectedItem.getQtyOnHand() + "");
                txtUnitPrice.setText(selectedItem.getUnitPrice() + "");
            }
        });
    }


    public void navigateToHome(MouseEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/DashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }


    private void loadAllItems() {

        ObservableList<ItemTM> items = tblItems.getItems();
        items.clear();
        try {
            items = FXCollections.observableArrayList(itemBO.getAllItems());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblItems.setItems(items);
    }


    public void btnAddNew_OnAction(ActionEvent actionEvent) {

        txtCode.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        tblItems.getSelectionModel().clearSelection();
        txtDescription.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtDescription.requestFocus();
        btnSave.setDisable(false);

        // Generate a new id
        try {
            txtCode.setText(itemBO.getNewItemCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSave_OnAction(ActionEvent event) {

        if (txtCode.getText().trim().isEmpty() || txtDescription.getText().trim().isEmpty() || txtQtyOnHand.getText().trim().isEmpty() ||
                txtUnitPrice.getText().trim().isEmpty()){

            new Alert(Alert.AlertType.ERROR,"Please Make Sure To Fill All The Fields").show();
            return;
        }
        double unitPrice = Double.parseDouble(txtUnitPrice.getText().trim());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText().trim());

        if (btnSave.getText().equals("Save")) {

            try {
                boolean saveItem = itemBO.saveItem(txtCode.getText(), txtDescription.getText(), qtyOnHand, unitPrice);

                if (!saveItem) {
                    new Alert(Alert.AlertType.ERROR, "Record cannot be added!", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Record added successfully", ButtonType.OK).show();
                    //tblItems.getItems().clear();
                    tblItems.refresh();
                    btnAddNew_OnAction(event);

                    loadAllItems();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            try {
                boolean updateItem = itemBO.updateItem(txtDescription.getText(), qtyOnHand, unitPrice, txtCode.getText());

                if (!updateItem){
                    new Alert(Alert.AlertType.ERROR,"Record cannot be updated!", ButtonType.OK).show();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Record updated successfully", ButtonType.OK).show();
                    tblItems.getItems().clear();

                    loadAllItems();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        reset();
    }


    public void reset(){

        btnDelete.setDisable(true);

        btnSave.setText("Save");
        txtCode.setText("");
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");

        txtCode.setPromptText("Medicine Code");
        txtDescription.setPromptText("Medicine Description");
        txtQtyOnHand.setPromptText("Qty. on Hand");
        txtUnitPrice.setPromptText("Unit Price");

}


    public void btnDelete_OnAction(ActionEvent event) {

        Optional<ButtonType> alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this medicine item?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (alert.get().equals(ButtonType.YES)) {
            try {
                itemBO.deleteItem(txtCode.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            clearAll();
            btnSave.setText("Save");
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
            btnAddNew.requestFocus();
            loadAllItems();
        }
    }


    private void clearAll() {

        txtCode.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }
}
