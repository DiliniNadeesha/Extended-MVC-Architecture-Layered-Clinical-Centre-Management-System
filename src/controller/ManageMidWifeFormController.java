package controller;

import business.BOFactory;
import business.BOType;
import business.custom.MidWifeBO;
import business.custom.PregMotherBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.MidWife;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageMidWifeFormController implements Initializable {
    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public ListView<MidWife> lstMidWife;
    public TextField txtSearch;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextArea txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtAmount;
    public JFXComboBox cmbDivision;
    public JFXTextField txtHospital;

    private MidWifeBO midWifeBO = BOFactory.getInstance().getBO(BOType.MID_WIFE);

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
