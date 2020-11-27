package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LabTestDAO;
import entity.LabTest;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LabTestDAOImpl implements LabTestDAO {

    @Override
    public String getLastTestCode() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM labtest ORDER BY code DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchTestByDescription(String description) throws Exception {
        return CrudUtil.execute("SELECT description FROM labtest WHERE description LIKE '%" + description + "%'");
    }

    @Override
    public List<LabTest> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM labtest");
        List<LabTest> labTests = new ArrayList<>();
        while (rst.next()) {
            labTests.add(new LabTest(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getBigDecimal(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)));
        }
        return labTests;
    }

    @Override
    public LabTest find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM labtest WHERE code=?", key);
        if (rst.next()) {
            return new LabTest(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getBigDecimal(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10));
        }
        return null;
    }

    @Override
    public boolean save(LabTest labTest) throws Exception {
        return CrudUtil.execute("INSERT INTO labtest VALUES (?,?,?,?,?,?,?,?,?,?)", labTest.getCode(), labTest.getDescription(), labTest.getPatientName(),
                labTest.getPatientContact(), labTest.getDate(), labTest.getCost(), labTest.getDocotorName(), labTest.getTester(), labTest.getLabContact(), labTest.getMidId());
    }

    @Override
    public boolean update(LabTest labTest) throws Exception {
        return CrudUtil.execute("UPDATE labtest SET description=?, patName=?, patContactNo=?, testDate=?, testCost=?, docName=?, labortaryTester=?, labContactNo=?, midId=? WHERE code=?",
                labTest.getDescription(), labTest.getPatientName(), labTest.getPatientContact(), labTest.getDate(), labTest.getCost(), labTest.getDocotorName(), labTest.getTester(),
                labTest.getLabContact(), labTest.getMidId(), labTest.getCode());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM labtest WHERE code=?", key);
    }
}
