package entity;

import java.sql.Date;

public class Order implements SuperEntity {

    private String id;
    // Here is use java.sql.Date
    private Date date;
    private String motherId;

    public Order() {
    }

    public Order(String id, Date date, String motherId) {
        this.id = id;
        this.date = date;
        this.motherId = motherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", motherId='" + motherId + '\'' +
                '}';
    }
}
