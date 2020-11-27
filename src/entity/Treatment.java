package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Treatment implements SuperEntity {

    private String code;
    private String description;
    private String docotorName;
    private String patientName;
    private String patientContact;
    private BigDecimal cost;
    private Date startDate;
    private Date endDate;
    private String docId;

    public Treatment() {
    }

    public Treatment(String code, String description, String docotorName, String patientName, String patientContact, BigDecimal cost, Date startDate, Date endDate, String docId) {
        this.code = code;
        this.description = description;
        this.docotorName = docotorName;
        this.patientName = patientName;
        this.patientContact = patientContact;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.docId = docId;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getDocotorName() { return docotorName; }

    public void setDocotorName(String docotorName) { this.docotorName = docotorName; }

    public String getPatientName() { return patientName; }

    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientContact() { return patientContact; }

    public void setPatientContact(String patientContact) { this.patientContact = patientContact; }

    public BigDecimal getCost() { return cost; }

    public void setCost(BigDecimal cost) { this.cost = cost; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getDocId() { return docId; }

    public void setDocId(String docId) { this.docId = docId; }

    @Override
    public String toString() { return description; }
}
