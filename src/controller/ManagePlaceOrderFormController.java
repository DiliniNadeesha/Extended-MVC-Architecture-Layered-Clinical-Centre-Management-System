package controller;

import business.BOFactory;
import business.BOType;
import business.custom.ItemBO;
import business.custom.OrderBO;
import business.custom.PregMotherBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.PregMother;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.*;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManagePlaceOrderFormController {

    static ArrayList<Order> ordersDB = new ArrayList<>();

    public AnchorPane root;
    public Label lblId;
    public Label lblDate;
    public Label lblTotal;
    public JFXComboBox<PregMother> cmbMotherId;
    public JFXComboBox<ItemTM> cmbItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnAdd;
    public JFXButton btnPlaceOrder;
    public TableView<OrderDetailTM> tblOrderDetails;
    private boolean readOnly;

    private OrderBO orderBO = BOFactory.getInstance().getBO(BOType.ORDER);
    private ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);
    private PregMotherBO pregMotherBO = BOFactory.getInstance().getBO(BOType.PREG_MOTHER);


    public void initialize() {

        readOnly = false;

        // Basic initializations
        txtQtyOnHand.setEditable(false);
        txtUnitPrice.setEditable(false);
        txtDescription.setEditable(false);

        // Let's set the date
        LocalDate today = LocalDate.now();
        lblDate.setText(today.toString());

        // Let's load all the mother ids
        loadAllMothers();

        // When user selects a mother id
        cmbMotherId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PregMother>() {
            @Override
            public void changed(ObservableValue<? extends PregMother> observable, PregMother oldValue, PregMother newValue) {
                if (newValue == null) {
                    //txtMotherName.clear();
                    return;
                }
                //txtMotherName.setText(newValue.getName());
            }
        });

        // Let's load all the item codes
        loadAllItems();

        // When user selects a item code
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {
                if (newValue == null) {
                    txtUnitPrice.clear();
                    txtDescription.clear();
                    txtQtyOnHand.clear();
                    btnAdd.setDisable(true);
                    return;
                }

                btnAdd.setDisable(false);
                txtDescription.setText(newValue.getDescription());
                calculateQtyOnHand(newValue);
                txtUnitPrice.setText(newValue.getUnitPrice() + "");
            }
        });

        // Let's map columns
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblOrderDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("button"));

        btnAdd.setDisable(true);

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetailTM>() {
            @Override
            public void changed(ObservableValue<? extends OrderDetailTM> observable, OrderDetailTM oldValue, OrderDetailTM selectedOrderDetail) {
                if (null == selectedOrderDetail) {
                    return;
                }

                String selectedItemCode = selectedOrderDetail.getCode();
                ObservableList<ItemTM> items = cmbItemCode.getItems();
                for (ItemTM item : items) {
                    if (item.getCode().equals(selectedItemCode)) {
                        cmbItemCode.getSelectionModel().select(item);
                        txtQtyOnHand.setText(item.getQtyOnHand() + "");
                        txtQty.setText(selectedOrderDetail.getQty() + "");
                        if (!readOnly) {
                            btnAdd.setText("Update");
                        }
                        if (readOnly) {
                            txtQty.setDisable(true);
                            btnAdd.setDisable(true);
                        }
                        cmbItemCode.setDisable(true);
                        Platform.runLater(() -> {
                            txtQty.requestFocus();
                        });
                        break;
                    }
                }
            }
        });

        generateOrderId();
    }

    private void generateOrderId() {

        // Generate a new id
        try {
            lblId.setText(orderBO.getNewOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadAllMothers() {

        cmbMotherId.getItems().clear();
        try {
            cmbMotherId.setItems(FXCollections.observableArrayList(pregMotherBO.getAllMothers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadAllItems() {

        cmbItemCode.getItems().clear();
        try {
            cmbItemCode.setItems(FXCollections.observableArrayList(itemBO.getAllItems()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void calculateQtyOnHand(ItemTM item) {

        txtQtyOnHand.setText(item.getQtyOnHand() + "");
        ObservableList<OrderDetailTM> orderDetails = tblOrderDetails.getItems();
        for (OrderDetailTM orderDetail : orderDetails) {
            if (orderDetail.getCode().equals(item.getCode())) {
                int displayQty = item.getQtyOnHand() - orderDetail.getQty();
                txtQtyOnHand.setText(displayQty + "");
                break;
            }
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


    public void btnAdd_OnAction(ActionEvent actionEvent) {

        // Let's do some validation
        if (txtQty.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Qty can't be empty", ButtonType.OK).show();
            return;
        }
        int qty = Integer.parseInt(txtQty.getText());
        if (qty < 1 || qty > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid Qty.", ButtonType.OK).show();
            return;
        }

        ItemTM selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();
        ObservableList<OrderDetailTM> orderDetails = tblOrderDetails.getItems();

        if (btnAdd.getText().equals("Add")) {
            boolean exist = false;
            for (OrderDetailTM orderDetail : orderDetails) {
                if (orderDetail.getCode().equals(selectedItem.getCode())) {
                    exist = true;
                    orderDetail.setQty(orderDetail.getQty() + qty);
                    tblOrderDetails.refresh();
                    break;
                }
            }

            if (!exist) {
                Button btnDelete = new Button("Delete");
                OrderDetailTM orderDetail = new OrderDetailTM(selectedItem.getCode(),
                        selectedItem.getDescription(),
                        qty,
                        selectedItem.getUnitPrice(), qty * selectedItem.getUnitPrice(), btnDelete);
                btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tblOrderDetails.getSelectionModel().clearSelection();
                        cmbItemCode.getSelectionModel().clearSelection();
                        cmbItemCode.setDisable(false);
                        txtQty.clear();
                        orderDetails.remove(orderDetail);
                        cmbItemCode.requestFocus();
                    }
                });
                orderDetails.add(orderDetail);
            }

            calculateTotal();
            cmbItemCode.getSelectionModel().clearSelection();
            txtQty.clear();
            cmbItemCode.requestFocus();
        } else {
            // Update
            OrderDetailTM selectedOrderDetail = tblOrderDetails.getSelectionModel().getSelectedItem();
            selectedOrderDetail.setQty(qty);
            selectedOrderDetail.setTotal(qty * selectedOrderDetail.getUnitPrice());
            tblOrderDetails.refresh();

            tblOrderDetails.getSelectionModel().clearSelection();
            btnAdd.setText("Add");
            cmbItemCode.setDisable(false);
            cmbItemCode.getSelectionModel().clearSelection();
            txtQty.clear();
            calculateTotal();
            cmbItemCode.requestFocus();
        }
    }

    private void calculateTotal() {
        ObservableList<OrderDetailTM> orderDetails = tblOrderDetails.getItems();
        double netTotal = 0;
        for (OrderDetailTM orderDetail : orderDetails) {
            netTotal += orderDetail.getTotal();
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(2);
        numberInstance.setMinimumFractionDigits(2);
        numberInstance.setGroupingUsed(false);
        String formattedText = numberInstance.format(netTotal);
        lblTotal.setText("Total: " + formattedText);
    }


    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {

        // Validation
        if (cmbMotherId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "You need to select a mother", ButtonType.OK).show();
            cmbMotherId.requestFocus();
            return;
        }

        if (tblOrderDetails.getItems().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "Ubata pissuda yako, nikan order dannea", ButtonType.OK).show();
            cmbItemCode.requestFocus();
            return;
        }

        boolean result = false;
        try {
            result = orderBO.placeOrder(new OrderTM(lblId.getText(), LocalDate.now(), cmbMotherId.getValue().getId(), cmbMotherId.getValue().getName(),0),tblOrderDetails.getItems());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!result){
            new Alert(Alert.AlertType.ERROR, "Mudalali wade awul wage", ButtonType.OK).show();
            return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Mudalali wade goda", ButtonType.OK).showAndWait();

        tblOrderDetails.getItems().clear();
        txtQty.clear();
        cmbMotherId.getSelectionModel().clearSelection();
        cmbItemCode.getSelectionModel().clearSelection();
        calculateTotal();
        generateOrderId();
    }


    void initializeWithSearchOrderForm(String orderId) {

        lblId.setText(orderId);
        readOnly = true;
        for (Order order : ordersDB) {
            if (order.getId().equals(orderId)) {
                lblDate.setText(order.getDate() + "");

                // To select the customer
                String customerId = order.getMomId();
                for (PregMother pregMother : cmbMotherId.getItems()) {
                    if (pregMother.getId().equals(customerId)) {
                        cmbMotherId.getSelectionModel().select(pregMother);
                        break;
                    }
                }

                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    String description = null;
                    for (ItemTM item : cmbItemCode.getItems()) {
                        if (item.getCode().equals(orderDetail.getCode())) {
                            description = item.getDescription();
                            break;
                        }
                    }
                    OrderDetailTM orderDetailTM = new OrderDetailTM(
                            orderDetail.getCode(),
                            description,
                            orderDetail.getQty(),
                            orderDetail.getUnitPrice(),
                            orderDetail.getQty() * orderDetail.getUnitPrice(),
                            null
                    );
                    tblOrderDetails.getItems().add(orderDetailTM);
                    calculateTotal();
                }

                cmbMotherId.setDisable(true);
                cmbItemCode.setDisable(true);
                btnAdd.setDisable(true);
                btnPlaceOrder.setVisible(false);
                break;
            }
        }
    }
}
