package controller;

import business.BOFactory;
import business.BOType;
import business.custom.AdminInformationBO;
import business.custom.PregMotherBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.AdminInformation;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageAdministrativeInformationFormController implements Initializable {
    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TextField txtSearch;
    public JFXListView<AdminInformation> lstInfo;
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtPhysician;
    public JFXTextField txtEmergency;
    public JFXTextField txtInCentre;
    public JFXTextField txtInCentreContact;
    public JFXTextField txtHospital;

    private AdminInformationBO adminInformationBO = BOFactory.getInstance().getBO(BOType.ADMIN_INFORMATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadAllInformations();

        lstInfo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AdminInformation>() {
            @Override
            public void changed(ObservableValue<? extends AdminInformation> observable, AdminInformation oldValue, AdminInformation newValue) {
                if(lstInfo.getSelectionModel().getSelectedItem() == null){
                    return;
                }

                AdminInformation selectedItem = lstInfo.getSelectionModel().getSelectedItem();

                String code = selectedItem.getCode();

                try {
                    AdminInformation informations = adminInformationBO.findInfo(code);
                    txtCode.setText(informations.getCode());
                    txtName.setText(informations.getName());
                    txtNic.setText(informations.getNic());
                    txtPhysician.setText(informations.getPhysicion());
                    txtEmergency.setText(informations.getEmergencyContact());
                    txtInCentre.setText(informations.getInsuarance());
                    txtInCentreContact.setText(informations.getInsuaranceContact());
                    txtHospital.setText(informations.getHospital());

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

    private void loadAllInformations() {

        ObservableList<AdminInformation> items = lstInfo.getItems();
        items.clear();
        List<AdminInformation> allInfos = null;
        try {
            allInfos = adminInformationBO.getAllInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<AdminInformation> informations = FXCollections.observableArrayList(allInfos);
        for (AdminInformation allinfo : allInfos) {
            items.add(allinfo);
        }
    }


    public void txtSearchOnKeyReleased(KeyEvent keyEvent) {

        String pname = txtSearch.getText();

        ObservableList<AdminInformation> items = lstInfo.getItems();
        items.clear();

        try {
            ResultSet resultSet = adminInformationBO.searchInfoByName(pname);

            while (resultSet.next()){
                String rst = resultSet.getString(1);
                items.add(new AdminInformation(rst));
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

        txtCode.clear();
        txtName.clear();
        txtNic.clear();
        txtPhysician.clear();
        txtEmergency.clear();
        txtInCentre.clear();
        txtInCentreContact.clear();
        txtHospital.clear();

        txtCode.setDisable(false);
        txtName.setDisable(false);
        txtNic.setDisable(false);
        txtPhysician.setDisable(false);
        txtEmergency.setDisable(false);
        txtInCentre.setDisable(false);
        txtInCentreContact.setDisable(false);
        txtHospital.setDisable(false);

        // Generate a new Mother ID
        try {
            txtCode.setText(adminInformationBO.getNewInfoCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (txtCode.getText().isEmpty() || txtName.getText().isEmpty() || txtNic.getText().isEmpty() || txtPhysician.getText().isEmpty() ||
                txtEmergency.getText().isEmpty() || txtInCentre.getText().isEmpty() || txtInCentreContact.getText().isEmpty() || txtHospital.getText().isEmpty()){

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

        if (!Pattern.matches("\\d{3}[-]\\d{7}", txtEmergency.getText())) {
            new Alert(Alert.AlertType.ERROR, "You are entered contact number is wrong, Please re enter!").show();
            return;
        }

        if (!Pattern.matches("\\d{3}[-]\\d{7}", txtInCentreContact.getText())) {
            new Alert(Alert.AlertType.ERROR, "You are entered contact number is wrong, Please re enter!").show();
            return;
        }

        if (btnSave.getText().equals("Save")) {

            try {
                boolean saveInfo = adminInformationBO.saveInfo(txtCode.getText(), txtName.getText(), txtNic.getText(), txtPhysician.getText(),
                        txtEmergency.getText(), txtInCentre.getText(), txtInCentreContact.getText(), txtHospital.getText());

                if (!saveInfo) {
                    new Alert(Alert.AlertType.ERROR, "Record cannot be added!", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Record added successfully", ButtonType.OK).show();
                    lstInfo.getItems().clear();

                    loadAllInformations();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            try {
                boolean updateInfo = adminInformationBO.updateInfo(txtName.getText(), txtNic.getText(), txtPhysician.getText(), txtEmergency.getText(),
                        txtInCentre.getText(), txtInCentreContact.getText(), txtHospital.getText(), txtCode.getText());

                if (!updateInfo){
                    new Alert(Alert.AlertType.ERROR,"Record cannot be updated!", ButtonType.OK).show();
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Record updated successfully", ButtonType.OK).show();
                    lstInfo.getItems().clear();

                    loadAllInformations();
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
        txtName.setText("");
        txtNic.setText("");
        txtPhysician.setText("");
        txtEmergency.setText("");
        txtInCentre.setText("");
        txtInCentreContact.setText("");
        txtHospital.setText("");

        txtCode.setPromptText("Info Code");
        txtName.setPromptText("Patient Name");
        txtNic.setPromptText("Patient NIC (xxxxxxxxxv)");
        txtPhysician.setPromptText("Personal Physician Name");
        txtEmergency.setPromptText("Emergency Contact No (xxx-xxxxxxx)");
        txtInCentre.setPromptText("Insuarance Centre Name");
        txtInCentreContact.setPromptText("Insuarance Centre Contact No (xxx-xxxxxxx)");
        txtHospital.setPromptText("Nearest Hospital");

    }



    public void btnDelete_OnAction(ActionEvent actionEvent) {

        Optional<ButtonType> alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this admin information?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (alert.get().equals(ButtonType.YES)) {
            try {
               adminInformationBO.deleteInfo(txtCode.getText());
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
            loadAllInformations();
        }
    }


    private void clearAll() {

        txtCode.clear();
        txtName.clear();
        txtNic.clear();
        txtPhysician.clear();
        txtEmergency.clear();
        txtInCentre.clear();
        txtInCentreContact.clear();
        txtHospital.clear();
    }
}
