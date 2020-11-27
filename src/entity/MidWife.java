package entity;

public class MidWife implements SuperEntity {

    private String id;
    private String name;
    private String address;
    private String nic;
    private String contactNo;
    private String email;
    private int amount;
    private String division;
    private String hospital;

    public MidWife() {
    }

    public MidWife(String id, String name, String address, String nic, String contactNo, String email, int amount, String division, String hospital) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
        this.amount = amount;
        this.division = division;
        this.hospital = hospital;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getNic() { return nic; }

    public void setNic(String nic) { this.nic = nic; }

    public String getContactNo() { return contactNo; }

    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public String getHospital() { return hospital; }

    public void setHospital(String hospital) { this.hospital = hospital; }

    @Override
    public String toString() { return name; }
}
