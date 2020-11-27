package dao.custom;

import dao.CrudDAO;
import entity.Doctor;

import java.sql.ResultSet;

public interface DoctorDAO extends CrudDAO<Doctor, String> {

    String getLastDoctorId() throws Exception;

    ResultSet searchDoctorByName(String dname) throws Exception;
}
