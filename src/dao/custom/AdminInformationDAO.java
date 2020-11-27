package dao.custom;

import dao.CrudDAO;
import entity.AdminInformation;

import java.sql.ResultSet;

public interface AdminInformationDAO extends CrudDAO<AdminInformation, String> {

    String getLastInfoCode() throws Exception;

    ResultSet searchPatientByName(String pname) throws Exception;
}
