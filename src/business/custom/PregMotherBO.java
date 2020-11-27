package business.custom;

import business.SuperBO;
import entity.PregMother;

import java.sql.ResultSet;
import java.util.List;

public interface PregMotherBO extends SuperBO {

    public List<PregMother> getAllMothers() throws Exception;

    public PregMother findMothers(String key) throws Exception;

    public ResultSet searchMotherByName(String mname) throws Exception;

    public boolean saveMother(String id, String name, String address, String nic, String contactNo, String email)throws Exception;

    public boolean deleteMother(String motherId)throws Exception;

    public boolean updateMother(String name, String address, String nic, String contactNo, String email, String motherId)throws Exception;

    public String getNewMotherId()throws Exception;

}
