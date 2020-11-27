package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class LabTest implements SuperEntity {

    private String code;
    private String description;
    private String patientName;
    private String patientContact;
    private Date date;
    private BigDecimal cost;
    private String docotorName;
    private String tester;
    private String labContact;
    private String midId;

    public LabTest() {
    }

    public LabTest(String code, String description, String patientName, String patientContact, Date date, BigDecimal cost, String docotorName, String tester, String labContact, String midId) {
        this.code = code;
        this.description = description;
        this.patientName = patientName;
        this.patientContact = patientContact;
        this.date = date;
        this.cost = cost;
        this.docotorName = docotorName;
        this.tester = tester;
        this.labContact = labContact;
        this.midId = midId;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPatientName() { return patientName; }

    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientContact() { return patientContact; }

    public void setPatientContact(String patientContact) { this.patientContact = patientContact; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public BigDecimal getCost() { return cost; }

    public void setCost(BigDecimal cost) { this.cost = cost; }

    public String getDocotorName() { return docotorName; }

    public void setDocotorName(String docotorName) { this.docotorName = docotorName; }

    public String getTester() { return tester; }

    public void setTester(String tester) { this.tester = tester; }

    public String getLabContact() { return labContact; }

    public void setLabContact(String labContact) { this.labContact = labContact; }

    public String getMidId() { return midId; }

    public void setMidId(String midId) { this.midId = midId; }

    @Override
    public String toString() { return description; }
}
