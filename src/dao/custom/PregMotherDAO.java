package dao.custom;

import dao.CrudDAO;
import entity.PregMother;

import java.sql.ResultSet;

public interface PregMotherDAO extends CrudDAO<PregMother,String> {

    String getLastMotherId() throws Exception;

    ResultSet searchMotherByName(String mname) throws Exception;
}
