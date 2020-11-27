package business.custom;

import business.SuperBO;
import entity.AdminInformation;

import java.sql.ResultSet;
import java.util.List;

public interface AdminInformationBO extends SuperBO {

    public List<AdminInformation> getAllInfo() throws Exception;

    public AdminInformation findInfo(String key) throws Exception;

    public ResultSet searchInfoByName(String pname) throws Exception;

    public boolean saveInfo(String code, String name, String nic, String physicion, String emergencyContact, String insuarance, String insuaranceContact, String hospital)throws Exception;

    public boolean deleteInfo(String infocode)throws Exception;

    public boolean updateInfo(String name, String nic, String physicion, String emergencyContact, String insuarance, String insuaranceContact, String hospital, String code)throws Exception;

    public String getNewInfoCode()throws Exception;

}
