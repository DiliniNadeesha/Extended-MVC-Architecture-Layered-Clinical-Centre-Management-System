package dao.custom;

import dao.CrudDAO;
import entity.ClinicCard;

import java.sql.ResultSet;

public interface ClinicCardDAO extends CrudDAO<ClinicCard, String> {

    String getLastCardId() throws Exception;

    ResultSet searchPatientByName(String pname) throws Exception;
}
