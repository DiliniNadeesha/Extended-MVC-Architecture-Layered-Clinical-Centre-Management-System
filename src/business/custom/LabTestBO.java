package business.custom;

import business.SuperBO;
import entity.LabTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

public interface LabTestBO extends SuperBO {

    public List<LabTest> getAllTests() throws Exception;

    public LabTest findTests(String key) throws Exception;

    public ResultSet searchTestsByDescription(String description) throws Exception;

    public boolean saveTest(String code, String description, String patientName, String patientContact, Date date, BigDecimal cost, String docotorName, String tester, String labContact, String midId)throws Exception;

    public boolean deleteTest(String testcode)throws Exception;

    public boolean updateTest(String description, String patientName, String patientContact, Date date, BigDecimal cost, String docotorName, String tester, String labContact, String midId, String code)throws Exception;

    public String getNewTestCode()throws Exception;

}
