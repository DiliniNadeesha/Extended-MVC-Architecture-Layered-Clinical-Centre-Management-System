package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController {
    public AnchorPane root;
    public ImageView imgPregMother;
    public ImageView imgAdminInfo;
    public ImageView imgMidWife;
    public ImageView imgDoctor;
    public ImageView imgPlaceOrder;
    public ImageView imgClinicCard;
    public ImageView imgTreatment;
    public ImageView imgLabTest;
    public ImageView imgMedItems;
    public Label lblMenu;
    public Label lblDescription;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void playMouseEnterAnimation(MouseEvent event) {

        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            switch(icon.getId()){
                case "imgPregMother":
                    lblMenu.setText("Manage Pregnant Mothers");
                    lblDescription.setText("Click to add, edit, delete, search or view pregnant mothers");
                    break;
                case "imgDoctor":
                    lblMenu.setText("Manage Doctors");
                    lblDescription.setText("Click to add, edit, delete, search or view doctors");
                    break;
                case "imgClinicCard":
                    lblMenu.setText("Manage Clinic Cards");
                    lblDescription.setText("Click to add, edit, delete, search or view clinic card");
                    break;
                case "imgAdminInfo":
                    lblMenu.setText("Administrative Information's");
                    lblDescription.setText("Click to add, edit, delete, search or view administrative information's");
                    break;
                case "imgMidWife":
                    lblMenu.setText("Manage MidWife's");
                    lblDescription.setText("Click to add, edit, delete, search or view midwife's");
                    break;
                case "imgLabTest":
                    lblMenu.setText("Manage Lab Tests");
                    lblDescription.setText("Click to add, edit, delete, search or view lab tests");
                    break;
                case "imgTreatment":
                    lblMenu.setText("Manage Treatments");
                    lblDescription.setText("Click to add, edit, delete, search or view treatments");
                    break;
                case "imgMedItems":
                    lblMenu.setText("Manage Medicine Items");
                    lblDescription.setText("Click to add, edit, delete, search or view medicine items");
                    break;
                case "imgPlaceOrder":
                    lblMenu.setText("Place Orders");
                    lblDescription.setText("Click here if you want to place a new order");
                    break;
            }

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {

        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigate(MouseEvent event) throws IOException {

        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch(icon.getId()){
                case "imgPregMother":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManagePregnacyMotherForm.fxml"));
                    break;
                case "imgDoctor":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageDoctorForm.fxml"));
                    break;
                case "imgClinicCard":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageClinicCardForm.fxml"));
                    break;
                case "imgAdminInfo":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageAdministrativeInformationForm.fxml"));
                    break;
                case "imgMidWife":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageMidWifeForm.fxml"));
                    break;
                case "imgLabTest":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageLabTestForm.fxml"));
                    break;
                case "imgTreatment":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageTreatmentForm.fxml"));
                    break;
                case "imgMedItems":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageItemForm.fxml"));
                    break;
                case "imgPlaceOrder":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManagePlaceOrderForm.fxml"));
                    break;
            }

            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }
}
