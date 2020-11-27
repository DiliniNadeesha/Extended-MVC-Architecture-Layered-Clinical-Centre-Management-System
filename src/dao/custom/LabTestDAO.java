package dao.custom;

import dao.CrudDAO;
import entity.LabTest;

import java.sql.ResultSet;

public interface LabTestDAO extends CrudDAO<LabTest, String> {

    String getLastTestCode() throws Exception;

    ResultSet searchTestByDescription(String description) throws Exception;
}
