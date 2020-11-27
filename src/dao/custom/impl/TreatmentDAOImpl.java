package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TreatmentDAO;
import entity.Treatment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAOImpl implements TreatmentDAO {

    @Override
    public String getLastTreatmentCode() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM treatment ORDER BY code DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchTreatmentByDescription(String description) throws Exception {
        return CrudUtil.execute("SELECT description FROM treatment WHERE description LIKE '%" + description + "%'");
    }

    @Override
    public List<Treatment> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM treatment");
        List<Treatment> treatments = new ArrayList<>();
        while (rst.next()) {
            treatments.add(new Treatment(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getBigDecimal(6),
                    rst.getDate(7),
                    rst.getDate(8),
                    rst.getString(9)));
        }
        return treatments;
    }

    @Override
    public Treatment find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM treatment WHERE code=?", key);
        if (rst.next()) {
            return new Treatment(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getBigDecimal(6),
                    rst.getDate(7),
                    rst.getDate(8),
                    rst.getString(9));
        }
        return null;
    }

    @Override
    public boolean save(Treatment treatment) throws Exception {
        return CrudUtil.execute("INSERT INTO treatment VALUES (?,?,?,?,?,?,?,?,?)", treatment.getCode(), treatment.getDescription(), treatment.getDocotorName(), treatment.getPatientName(),
                treatment.getPatientContact(), treatment.getCost(), treatment.getStartDate(), treatment.getEndDate(), treatment.getDocId());
    }

    @Override
    public boolean update(Treatment treatment) throws Exception {
        return CrudUtil.execute("UPDATE treatment SET description=?, docName=?, patientName=?, patContactNo=?, cost=?, startDate=?, endDate=?, docId=? WHERE code=?", treatment.getDescription(),
                treatment.getDocotorName(), treatment.getPatientName(), treatment.getPatientContact(), treatment.getCost(), treatment.getStartDate(), treatment.getEndDate(),
                treatment.getDocId(), treatment.getCode());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM treatment WHERE code=?", key);
    }
}
