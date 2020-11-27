package util;

import java.time.LocalDate;

public class OrderTM {

    private String orderId;
    private LocalDate orderDate;
    private String momId;
    private String momName;
    private double orderTotal;

    public OrderTM() {
    }

    public OrderTM(String orderId, LocalDate orderDate, String momId, String momName, double orderTotal) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.momId = momId;
        this.momName = momName;
        this.orderTotal = orderTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getMomId() {
        return momId;
    }

    public void setMomId(String momId) {
        this.momId = momId;
    }

    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", momId='" + momId + '\'' +
                ", momName='" + momName + '\'' +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
