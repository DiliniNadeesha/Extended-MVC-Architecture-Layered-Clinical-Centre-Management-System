package dao.custom;

import dao.CrudDAO;
import entity.MidWife;

import java.sql.ResultSet;

public interface MidWifeDAO extends CrudDAO<MidWife, String> {

    String getLastMidWifeId() throws Exception;

    ResultSet searchMidWifeByName(String mname) throws Exception;
}
