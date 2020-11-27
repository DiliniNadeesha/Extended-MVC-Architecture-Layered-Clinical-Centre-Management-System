package controller;

import business.BOFactory;
import business.BOType;
import business.custom.LabTestBO;
import business.custom.PregMotherBO;
import com.jfoenix.controls.*;
import entity.LabTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageLabTestFormController implements Initializable {
    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXListView<LabTest> ListTest;
    public TextField txtSearch;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPatientName;
    public JFXTextField txtPatientContact;
    public JFXDatePicker dtpDate;
    public JFXTextField txtCost;
    public JFXTextField txtDoctor;
    public JFXTextField txtTester;
    public JFXTextField txtLabContact;
    public JFXComboBox cmbMidWife;

    private LabTestBO labTestBO = BOFactory.getInstance().getBO(BOType.LAB_TEST);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void txtSearchOnKeyReleased(KeyEvent keyEvent) {
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
    }


    public void btnSave_OnAction(ActionEvent actionEvent) {
    }


    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }
}
