package dao.custom;

import dao.CrudDAO;
import entity.Treatment;

import java.sql.ResultSet;

public interface TreatmentDAO extends CrudDAO<Treatment, String> {

    String getLastTreatmentCode() throws Exception;

    ResultSet searchTreatmentByDescription(String description) throws Exception;
}
