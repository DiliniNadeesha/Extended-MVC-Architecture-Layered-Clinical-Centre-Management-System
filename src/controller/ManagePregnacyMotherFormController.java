package controller;

import business.BOFactory;
import business.BOType;
import business.custom.PregMotherBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import entity.PregMother;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagePregnacyMotherFormController implements Initializable {
    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TextField txtSearch;
    public JFXListView<PregMother> lstMothers;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextArea txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;

    private PregMotherBO pregMotherBO = BOFactory.getInstance().getBO(BOType.PREG_MOTHER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadAllMothers();

        lstMothers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PregMother>() {
            @Override
            public void changed(ObservableValue<? extends PregMother> observable, PregMother oldValue, PregMother newValue) {

                if(lstMothers.getSelectionModel().getSelectedItem() == null){
                    return;
                }

                PregMother selectedItem = lstMothers.getSelectionModel().getSelectedItem();

                String id = selectedItem.getId();

                try {
                    PregMother mothers = pregMotherBO.findMothers(id);
                    txtId.setText(mothers.getId());
                    txtName.setText(mothers.getName());
                    txtAddress.setText(mothers.getAddress());
                    txtNic.setText(mothers.getNic());
                    txtContact.setText(mothers.getContactNo());
                    txtEmail.setText(mothers.getEmail());

                    btnSave.setText("Update");
                    btnSave.setDisable(false);
                    btnDelete.setDisable(false);
                    txtName.requestFocus();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
   }

    private void loadAllMothers() {

        ObservableList<PregMother> items = lstMothers.getItems();
        items.clear();
        List<PregMother> allMothers = null;
        try {
            allMothers = pregMotherBO.getAllMothers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<PregMother> mothers = FXCollections.observableArrayList(allMothers);
        for (PregMother allMother : allMothers) {
            items.add(allMother);
        }
    }


    public void navigateToHome(MouseEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/DashBoard.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }


    public void txtSearchOnKeyReleased(KeyEvent keyEvent) {

        String mname = txtSearch.getText();

        ObservableList<PregMother> items = lstMothers.getItems();
        items.clear();

        try {
            ResultSet resultSet = pregMotherBO.searchMotherByName(mname);

            while (resultSet.next()){
                String rst = resultSet.getString(1);
                items.add(new PregMother(rst));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnAddNew_OnAction(ActionEvent event) {

        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtNic.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        txtName.requestFocus();
        btnSave.setDisable(false);

        // Generate a new Mother ID
        try {
            txtId.setText(pregMotherBO.getNewMotherId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSave_OnAction(ActionEvent event) {

        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtAddress.getText().isEmpty() ||
                txtNic.getText().isEmpty() || txtContact.getText().isEmpty() || txtEmail.getText().isEmpty()){

            new Alert(Alert.AlertType.ERROR,"Please Make Sure To Fill All The Fields").show();
            return;
        }

            String regex = "^\\d{9}[v]$";
            Pattern pattern = Pattern.compile(regex);
            String dCon = txtNic.getText();
            Matcher matcher = pattern.matcher(dCon);

            if (!matcher.matches()) {
                new Alert(Alert.AlertType.ERROR, "You are entered NIC Number is wrong, Please re enter!").show();
                return;
            }

            if (!Pattern.matches("\\d{3}[-]\\d{7}", txtContact.getText())) {
                new Alert(Alert.AlertType.ERROR, "You are entered contact number is wrong, Please re enter!").show();
                return;
            }

            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", txtEmail.getText())) {
                new Alert(Alert.AlertType.ERROR, "You are entered email address is wrong, Please re enter!").show();
                return;
            }

            if (btnSave.getText().equals("Save")) {

                try {
                    boolean saveMother = pregMotherBO.saveMother(txtId.getText(), txtName.getText(), txtAddress.getText(),
                            txtNic.getText(), txtContact.getText(), txtEmail.getText());

                    if (!saveMother) {
                        new Alert(Alert.AlertType.ERROR, "Record cannot be added!", ButtonType.OK).show();
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Record added successfully", ButtonType.OK).show();
                        lstMothers.getItems().clear();

                        loadAllMothers();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        else {
            try {
                boolean updateMother = pregMotherBO.updateMother(txtName.getText(), txtAddress.getText(), txtNic.getText(),
                        txtContact.getText(), txtEmail.getText(), txtId.getText());

                if (!updateMother){
                    new Alert(Alert.AlertType.ERROR,"Record cannot be updated!", ButtonType.OK).show();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Record updated successfully", ButtonType.OK).show();
                    lstMothers.getItems().clear();

                    loadAllMothers();
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
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtContact.setText("");
        txtEmail.setText("");

        txtId.setPromptText("Mother's ID");
        txtName.setPromptText("Mother's Name");
        txtAddress.setPromptText("Mother's Address");
        txtNic.setPromptText("Mother's NIC");
        txtContact.setPromptText("Mother's Contact No");
        txtEmail.setPromptText("Mother's Email");

    }


    public void btnDelete_OnAction(ActionEvent event) throws SQLException {

        Optional<ButtonType> alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this mother?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (alert.get().equals(ButtonType.YES)) {
            try {
                pregMotherBO.deleteMother(txtId.getText());
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
            loadAllMothers();
        }
    }


    private void clearAll() {

        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        txtSearch.clear();
    }
}
