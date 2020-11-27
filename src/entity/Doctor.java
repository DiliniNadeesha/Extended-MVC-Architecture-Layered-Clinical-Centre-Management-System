package entity;

public class Doctor implements SuperEntity {

    private String id;
    private String name;
    private String nic;
    private String contactNo;
    private String email;
    private String area;
    private String hospital;

    public Doctor() {
    }

    public Doctor(String name) { this.name = name; }

    public Doctor(String id, String name, String nic, String contactNo, String email, String area, String hospital) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
        this.area = area;
        this.hospital = hospital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() { return name; }
}
