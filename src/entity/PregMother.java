package entity;

public class PregMother implements SuperEntity {

    private String id;
    private String name;
    private String address;
    private String nic;
    private String contactNo;
    private String email;

    public PregMother() {
    }

    public PregMother(String name) { this.name = name; }

    public PregMother(String id, String name, String address, String nic, String contactNo, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

//    @Override
//    public String toString() {
//        return "PregMother{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
    @Override
    public String toString() { return name; }
}
