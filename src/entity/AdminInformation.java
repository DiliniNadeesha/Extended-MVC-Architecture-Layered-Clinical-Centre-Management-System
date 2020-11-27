package entity;

public class AdminInformation implements SuperEntity {

    private String code;
    private String name;
    private String nic;
    private String physicion;
    private String emergencyContact;
    private String insuarance;
    private String insuaranceContact;
    private String hospital;

    public AdminInformation() {
    }

    public AdminInformation(String name) { this.name = name; }

    public AdminInformation(String code, String name, String nic, String physicion, String emergencyContact, String insuarance, String insuaranceContact, String hospital) {
        this.code = code;
        this.name = name;
        this.nic = nic;
        this.physicion = physicion;
        this.emergencyContact = emergencyContact;
        this.insuarance = insuarance;
        this.insuaranceContact = insuaranceContact;
        this.hospital = hospital;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNic() { return nic; }

    public void setNic(String nic) { this.nic = nic; }

    public String getPhysicion() { return physicion; }

    public void setPhysicion(String physicion) { this.physicion = physicion; }

    public String getEmergencyContact() { return emergencyContact; }

    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public String getInsuarance() { return insuarance; }

    public void setInsuarance(String insuarance) { this.insuarance = insuarance; }

    public String getInsuaranceContact() { return insuaranceContact; }

    public void setInsuaranceContact(String insuaranceContact) { this.insuaranceContact = insuaranceContact; }

    public String getHospital() { return hospital; }

    public void setHospital(String hospital) { this.hospital = hospital; }

    @Override
    public String toString() { return name; }
}
