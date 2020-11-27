package entity;

public class ClinicCard implements SuperEntity {

    private String id;
    private String name;
    private String age;
    private String address;
    private String nic;
    private String contactNo;
    private String email;
    private String husband;
    private String husbandContact;
    private String blood;
    private String weight;
    private String height;
    private String situation;
    private String method;
    private String physician;
    private String hospital;

    public ClinicCard() {
    }

    public ClinicCard(String name) { this.name = name; }

    public ClinicCard(String id, String name, String age, String address, String nic, String contactNo, String email, String husband, String husbandContact, String blood, String weight, String height, String situation, String method, String physician, String hospital) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
        this.husband = husband;
        this.husbandContact = husbandContact;
        this.blood = blood;
        this.weight = weight;
        this.height = height;
        this.situation = situation;
        this.method = method;
        this.physician = physician;
        this.hospital = hospital;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getNic() { return nic; }

    public void setNic(String nic) { this.nic = nic; }

    public String getContactNo() { return contactNo; }

    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getHusband() { return husband; }

    public void setHusband(String husband) { this.husband = husband; }

    public String getHusbandContact() { return husbandContact; }

    public void setHusbandContact(String husbandContact) { this.husbandContact = husbandContact; }

    public String getBlood() { return blood; }

    public void setBlood(String blood) { this.blood = blood; }

    public String getWeight() { return weight; }

    public void setWeight(String weight) { this.weight = weight; }

    public String getHeight() { return height; }

    public void setHeight(String height) { this.height = height; }

    public String getSituation() { return situation; }

    public void setSituation(String situation) { this.situation = situation; }

    public String getMethod() { return method; }

    public void setMethod(String method) { this.method = method; }

    public String getPhysician() { return physician; }

    public void setPhysician(String physician) { this.physician = physician; }

    public String getHospital() { return hospital; }

    public void setHospital(String hospital) { this.hospital = hospital; }

    @Override
    public String toString() { return name; }
}
