package business.custom;

import business.SuperBO;
import entity.MidWife;

import java.sql.ResultSet;
import java.util.List;

public interface MidWifeBO extends SuperBO {

    public List<MidWife> getAllMidWifes() throws Exception;

    public MidWife findMidWifes(String key) throws Exception;

    public ResultSet searchMidWifeByName(String mname) throws Exception;

    public boolean saveMidWife(String id, String name, String address, String nic, String contactNo, String email, int amount, String division, String hospital)throws Exception;

    public boolean deleteMidWife(String midwifeId)throws Exception;

    public boolean updateMidWife(String name, String address, String nic, String contactNo, String email, int amount, String division, String hospital, String id)throws Exception;

    public String getNewMidWifeId()throws Exception;

}
