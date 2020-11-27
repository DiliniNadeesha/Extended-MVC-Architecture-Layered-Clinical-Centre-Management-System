package controller;

import business.BOFactory;
import business.BOType;
import business.custom.DoctorBO;
import business.custom.PregMotherBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import entity.Doctor;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageDoctorFormController implements Initializable {
    public AnchorPane root;
    public TextField txtSearch;
    public JFXListView<Doctor> lstDoctors;
    public JFXComboBox<String> cmbArea;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtHospital;

    private DoctorBO doctorBO = BOFactory.getInstance().getBO(BOType.DOCTOR);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadAllDoctors();

        String doctorsText = " Endocrinologists\n" +
                " VOG Specialist\n" +
                " General Surgeon\n" +
                " Emergency Medicine Specialist\n" +
                " Radiologists\n" +
                " Surgical Intensivists \n" +
                " Anesthesiologists\n" +
                " Specialists in Allergy and Immunology \n" +
                " Anesthesiology\n" +
                " Dermatologists \n" +
                " Medical geneticist\n" +
                " Neurologists\n" +
                " Obstetrician/Gynecologists\n" +
                " Ophthalmologists\n" +
                " Psychiatry\n" +
                " Radiation Oncology \n" +
                " Urology\n" +
                " Critical Care Medicine Specialists\n" +
                " Gastroenterologists";

        String[] doctors = doctorsText.split("\n");
        ObservableList<String> olDoctors = FXCollections.observableArrayList(Arrays.asList(doctors));
        cmbArea.setItems(olDoctors);

        lstDoctors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Doctor>() {
            @Override
            public void changed(ObservableValue<? extends Doctor> observable, Doctor oldValue, Doctor newValue) {
                if(lstDoctors.getSelectionModel().getSelectedItem() == null){
                    return;
                }

                Doctor selectedItem = lstDoctors.getSelectionModel().getSelectedItem();

                String id = selectedItem.getId();

                try {
                    Doctor doctors = doctorBO.findDoctors(id);
                    txtId.setText(doctors.getId());
                    txtName.setText(doctors.getName());
                    txtNic.setText(doctors.getNic());
                    txtContact.setText(doctors.getContactNo());
                    txtEmail.setText(doctors.getEmail());
                    String area = selectedItem.getArea();
                    cmbArea.getSelectionModel().select(area);
                    txtHospital.setText(doctors.getHospital());

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

    private void loadAllDoctors() {

        ObservableList<Doctor> items = lstDoctors.getItems();
        items.clear();
        List<Doctor> allDoctors = null;
        try {
            allDoctors = doctorBO.getAllDoctors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Doctor> doctors = FXCollections.observableArrayList(allDoctors);
        for (Doctor doctor : allDoctors) {
            items.add(doctor);
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

        String dname = txtSearch.getText();

        ObservableList<Doctor> items = lstDoctors.getItems();
        items.clear();

        try {
            ResultSet resultSet = doctorBO.searchDoctorByName(dname);

            while (resultSet.next()){
                String rst = resultSet.getString(1);
                items.add(new Doctor(rst));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void btnAddNew_OnAction(ActionEvent event) {

        txtId.clear();
        txtName.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        cmbArea.getSelectionModel().clearSelection();
        txtHospital.clear();

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtNic.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        cmbArea.setDisable(false);
        txtHospital.setDisable(false);

        txtName.requestFocus();

        btnSave.setDisable(false);

        // Generate a new doctor id
        try {
            txtId.setText(doctorBO.getNewDoctorId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSave_OnAction(ActionEvent event) {

        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtNic.getText().isEmpty() || txtContact.getText().isEmpty()
                || txtEmail.getText().isEmpty() || txtHospital.getText().isEmpty()) {

            new Alert(Alert.AlertType.ERROR, "Please Make Sure To Fill All The Fields").show();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("Save")) {

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
                    boolean saveDoctor = doctorBO.saveDoctor(txtId.getText(), txtName.getText(), txtNic.getText(),
                            txtContact.getText(), txtEmail.getText(), cmbArea.getSelectionModel().getSelectedItem(), txtHospital.getText());

                    if (!saveDoctor) {
                        new Alert(Alert.AlertType.ERROR, "Record cannot be added!", ButtonType.OK).show();

                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Record added successfully", ButtonType.OK).show();
                        lstDoctors.getItems().clear();

                        loadAllDoctors();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else {
                try {
                    System.out.println("Working");
                    boolean updateDoctor = doctorBO.updateDoctor(txtName.getText(), txtNic.getText(), txtContact.getText(),
                            txtEmail.getText(), cmbArea.getValue(), txtHospital.getText(), txtId.getText());

                    System.out.println(updateDoctor);

                    if (!updateDoctor){
                        new Alert(Alert.AlertType.ERROR,"Record cannot be updated!", ButtonType.OK).show();
                    }
                    else {
                        new Alert(Alert.AlertType.INFORMATION,"Record updated successfully", ButtonType.OK).show();
                        lstDoctors.getSelectionModel().clearSelection();

                        loadAllDoctors();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            }

            reset();
        }


    public void reset(){

        btnDelete.setDisable(true);

        btnSave.setText("Save");
        txtId.setText("");
        txtName.setText("");
        txtNic.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        cmbArea.setValue(null);
        txtHospital.setText("");

        txtId.setPromptText("Doctor's ID");
        txtName.setPromptText("Doctor's Name");
        txtNic.setPromptText("Doctor's NIC");
        txtContact.setPromptText("Doctor's Contact No");
        txtEmail.setPromptText("Doctor's Email");
        txtHospital.setPromptText("Doctor's Hospital");

    }

    public void btnDelete_OnAction(ActionEvent event) throws SQLException {

        Optional<ButtonType> alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this doctor?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (alert.get().equals(ButtonType.YES)) {
            try {
                doctorBO.deleteDoctor(txtId.getText());
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
            loadAllDoctors();
        }
    }


    private void clearAll() {

        txtId.clear();
        txtName.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        cmbArea.getSelectionModel().clearSelection();
        txtHospital.clear();
        txtSearch.clear();
    }
}
