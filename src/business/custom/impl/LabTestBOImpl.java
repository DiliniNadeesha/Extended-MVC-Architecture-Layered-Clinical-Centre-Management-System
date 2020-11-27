package business.custom.impl;

import business.custom.LabTestBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.LabTestDAO;
import entity.LabTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LabTestBOImpl implements LabTestBO {

    private LabTestDAO labTestDAO = DAOFactory.getInstance().getDAO(DAOType.LAB_TEST);

    @Override
    public List<LabTest> getAllTests() throws Exception {
        List<LabTest> allTests = labTestDAO.findAll();
        List<LabTest> labTests = new ArrayList<>();
        for (LabTest test : allTests) {
            labTests.add(new LabTest(test.getCode(), test.getDescription(), test.getPatientName(), test.getPatientContact(), test.getDate(),
                    test.getCost(), test.getDocotorName(), test.getTester(), test.getLabContact(), test.getMidId()));
        }
        return labTests ;
    }

    @Override
    public LabTest findTests(String key) throws Exception {
        return labTestDAO.find(key);
    }

    @Override
    public ResultSet searchTestsByDescription(String description) throws Exception {
        return labTestDAO.searchTestByDescription(description);
    }

    @Override
    public boolean saveTest(String code, String description, String patientName, String patientContact, Date date, BigDecimal cost, String docotorName, String tester, String labContact, String midId) throws Exception {
        return labTestDAO.save(new LabTest(code, description, patientName, patientContact, date, cost, docotorName, tester, labContact, midId));
    }

    @Override
    public boolean deleteTest(String testcode) throws Exception {
        return labTestDAO.delete(testcode);
    }

    @Override
    public boolean updateTest(String description, String patientName, String patientContact, Date date, BigDecimal cost, String docotorName, String tester, String labContact, String midId, String code) throws Exception {
        return labTestDAO.update(new LabTest(code, description, patientName, patientContact, date, cost, docotorName, tester, labContact, midId));
    }

    @Override
    public String getNewTestCode() throws Exception {
        String lastTestCode = labTestDAO.getLastTestCode();

        if (lastTestCode == null) {
            return "T001";
        } else {
            int maxId = Integer.parseInt(lastTestCode.replace("T", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "T00" + maxId;
            } else if (maxId < 100) {
                id = "T0" + maxId;
            } else {
                id = "T" + maxId;
            }
            return id;
        }
    }
}
