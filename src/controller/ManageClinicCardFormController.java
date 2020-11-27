package controller;

import business.BOFactory;
import business.BOType;
import business.custom.ClinicCardBO;
import business.custom.PregMotherBO;
import com.jfoenix.controls.*;
import entity.ClinicCard;
import entity.Doctor;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageClinicCardFormController implements Initializable {
    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TextField txtSearch;
    public JFXListView<ClinicCard> lstCard;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAge;
    public JFXTextArea txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtHusband;
    public JFXTextField txtHContact;
    public JFXComboBox cmbBlood;
    public JFXTextField txtWeight;
    public JFXTextField txtHeight;
    public JFXComboBox cmbSituation;
    public JFXComboBox cmbMethod;
    public JFXComboBox cmbPhisician;
    public JFXComboBox cmbHospital;

    private ClinicCardBO clinicCardBO = BOFactory.getInstance().getBO(BOType.CLINIC_CARD);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadAllCards();

        lstCard.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ClinicCard>() {
            @Override
            public void changed(ObservableValue<? extends ClinicCard> observable, ClinicCard oldValue, ClinicCard newValue) {
                if(lstCard.getSelectionModel().getSelectedItem() == null){
                    return;
                }

                ClinicCard selectedItem = lstCard.getSelectionModel().getSelectedItem();

                String id = selectedItem.getId();

                try {
                    ClinicCard cards = clinicCardBO.findCards(id);
                    txtId.setText(cards.getId());
                    txtName.setText(cards.getName());
                    txtAge.setText(cards.getAge());
                    txtAddress.setText(cards.getAddress());
                    txtNic.setText(cards.getNic());
                    txtContact.setText(cards.getContactNo());
                    txtEmail.setText(cards.getEmail());
                    txtHusband.setText(cards.getHusband());
                    txtHContact.setText(cards.getHusbandContact());
                    cmbBlood.getSelectionModel().select(selectedItem.getBlood());
                    txtWeight.setText(cards.getWeight());
                    txtHeight.setText(cards.getHeight());
                    cmbSituation.getSelectionModel().select(selectedItem.getSituation());
                    cmbMethod.getSelectionModel().select(selectedItem.getMethod());
                    cmbPhisician.getSelectionModel().select(selectedItem.getPhysician());
                    cmbHospital.getSelectionModel().select(selectedItem.getHospital());

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

    private void loadAllCards() {

        ObservableList<ClinicCard> items = lstCard.getItems();
        items.clear();
        List<ClinicCard> allCards = null;
        try {
            allCards = clinicCardBO.getAllCards();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<ClinicCard> cards = FXCollections.observableArrayList(allCards);
        for (ClinicCard card : allCards) {
            items.add(card);
        }
    }


    public void txtSearchOnKeyReleased(KeyEvent keyEvent) {

        String pname = txtSearch.getText();

        ObservableList<ClinicCard> items = lstCard.getItems();
        items.clear();

        try {
            ResultSet resultSet = clinicCardBO.searchCardByPatientName(pname);

            while (resultSet.next()){
                String rst = resultSet.getString(1);
                items.add(new ClinicCard(rst));
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    public void btnAddNew_OnAction(ActionEvent actionEvent) {

        txtId.clear();
        txtName.clear();
        txtAge.clear();
        txtAddress.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        txtHusband.clear();
        txtHContact.clear();
        cmbBlood.getSelectionModel().clearSelection();
        txtWeight.clear();
        txtHeight.clear();
        cmbSituation.getSelectionModel().clearSelection();
        cmbMethod.getSelectionModel().clearSelection();
        cmbPhisician.getSelectionModel().clearSelection();
        cmbHospital.getSelectionModel().clearSelection();

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAge.setDisable(false);
        txtAddress.setDisable(false);
        txtNic.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        txtHusband.setDisable(false);
        txtHContact.setDisable(false);
        cmbBlood.setDisable(false);
        txtWeight.setDisable(false);
        txtHeight.setDisable(false);
        cmbSituation.setDisable(false);
        cmbMethod.setDisable(false);
        cmbPhisician.setDisable(false);
        cmbHospital.setDisable(false);

        txtName.requestFocus();

        btnSave.setDisable(false);

        // Generate a new doctor id
        try {
            txtId.setText(clinicCardBO.getNewCardId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtAge.getText().isEmpty() || txtAddress.getText().isEmpty() || txtNic.getText().isEmpty() || txtContact.getText().isEmpty()
                || txtEmail.getText().isEmpty() || txtHusband.getText().isEmpty() || txtHContact.getText().isEmpty() || txtWeight.getText().isEmpty() || txtHeight.getText().isEmpty()){

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

        if (!Pattern.matches("\\d{3}[-]\\d{7}", txtHContact.getText())) {
            new Alert(Alert.AlertType.ERROR, "You are entered contact number is wrong, Please re enter!").show();
            return;
        }

        if (btnSave.getText().equals("Save")) {

            try {
                boolean saveCard = clinicCardBO.saveCard(txtId.getText(), txtName.getText(), txtAge.getText(), txtAddress.getText(), txtNic.getText(), txtContact.getText(), txtEmail.getText(), txtHusband.getText(),
                        txtHContact.getText(), cmbBlood.getSelectionModel().getSelectedItem().toString(), txtWeight.getText(), txtHeight.getText(), cmbSituation.getSelectionModel().getSelectedItem().toString(),
                        cmbMethod.getSelectionModel().getSelectedItem().toString(), cmbPhisician.getSelectionModel().getSelectedItem().toString(), cmbHospital.getSelectionModel().getSelectedItem().toString());

                if (!saveCard) {
                    new Alert(Alert.AlertType.ERROR, "Record cannot be added!", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Record added successfully", ButtonType.OK).show();
                    lstCard.getItems().clear();

                    loadAllCards();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            try {
                boolean updateCard = clinicCardBO.updateCard(txtName.getText(), txtAge.getText(), txtAddress.getText(), txtNic.getText(), txtContact.getText(), txtEmail.getText(), txtHusband.getText(),
                        txtHContact.getText(), cmbBlood.getSelectionModel().getSelectedItem().toString(), txtWeight.getText(), txtHeight.getText(), cmbSituation.getSelectionModel().getSelectedItem().toString(),
                        cmbMethod.getSelectionModel().getSelectedItem().toString(), cmbPhisician.getSelectionModel().getSelectedItem().toString(), cmbHospital.getSelectionModel().getSelectedItem().toString(), txtId.getText());

                if (!updateCard){
                    new Alert(Alert.AlertType.ERROR,"Record cannot be updated!", ButtonType.OK).show();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Record updated successfully", ButtonType.OK).show();
                    lstCard.getItems().clear();

                    loadAllCards();
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
        txtAge.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtHusband.setText("");
        txtHContact.setText("");
        cmbBlood.setValue(null);
        txtWeight.setText("");
        txtHeight.setText("");
        cmbSituation.setValue(null);
        cmbMethod.setValue(null);
        cmbPhisician.setValue(null);
        cmbHospital.setValue(null);

        txtId.setPromptText("Clini Card ID");
        txtName.setPromptText("Patient Name");
        txtAge.setPromptText("Patient Age");
        txtAddress.setPromptText("Patient Address");
        txtNic.setPromptText("Patient NIC No (xxxxxxxxxv)");
        txtContact.setPromptText("Patient Contact No (xxx-xxxxxxx)");
        txtEmail.setPromptText("Patient Email (xxx@gmail.com)");
        txtHusband.setPromptText("Patient's Husband Name");
        txtHContact.setPromptText("Patient's Husband Contact No (xxx-xxxxxxx)");
        cmbBlood.setValue("Patient's Blood Group");
        txtWeight.setPromptText("Patient Weight (Kg)");
        txtHeight.setPromptText("Patient Height (cm)");
        cmbSituation.setValue("Patient's Situation");
        cmbMethod.setValue("Recomended Child Delivery Method");
        cmbPhisician.setValue("Personal Physician's Name");
        cmbHospital.setValue("Nearest Hospital");

    }



    public void btnDelete_OnAction(ActionEvent actionEvent) {

        Optional<ButtonType> alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this clinic card?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (alert.get().equals(ButtonType.YES)) {
            try {
                clinicCardBO.deleteCard(txtId.getText());
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
            loadAllCards();
        }
    }


    private void clearAll() {

        txtId.clear();
        txtName.clear();
        txtAge.clear();
        txtAddress.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        txtHusband.clear();
        txtHContact.clear();
        cmbBlood.getSelectionModel().clearSelection();
        txtWeight.clear();
        txtHeight.clear();
        cmbSituation.getSelectionModel().clearSelection();
        cmbMethod.getSelectionModel().clearSelection();
        cmbPhisician.getSelectionModel().clearSelection();
        cmbHospital.getSelectionModel().clearSelection();
    }

}
