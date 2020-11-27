package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.AdminInformationDAO;
import entity.AdminInformation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminInformationDAOImpl implements AdminInformationDAO {

    @Override
    public String getLastInfoCode() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM admininformation ORDER BY code DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchPatientByName(String pname) throws Exception {
        return CrudUtil.execute("SELECT name FROM admininformation WHERE name LIKE '%" + pname + "%'");
    }

    @Override
    public List<AdminInformation> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM admininformation");
        List<AdminInformation> adminInformations = new ArrayList<>();
        while (rst.next()) {
            adminInformations.add(new AdminInformation(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)));
        }
        return adminInformations;
    }

    @Override
    public AdminInformation find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM admininformation WHERE code=?", key);
        if (rst.next()) {
            return new AdminInformation(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8));
        }
        return null;
    }

    @Override
    public boolean save(AdminInformation adminInformation) throws Exception {
        return CrudUtil.execute("INSERT INTO admininformation VALUES (?,?,?,?,?,?,?,?)", adminInformation.getCode(), adminInformation.getName(), adminInformation.getNic(), adminInformation.getPhysicion(),
                adminInformation.getEmergencyContact(), adminInformation.getInsuarance(), adminInformation.getInsuaranceContact(), adminInformation.getHospital());
    }

    @Override
    public boolean update(AdminInformation adminInformation) throws Exception {
        return CrudUtil.execute("UPDATE admininformation SET name=?, nic=?, personalPhisicion=?, emergencyContactNo=?, insuaranceCentre=?, insuaranceCentreContactNo=?, nearestHospital=? WHERE code=?", adminInformation.getName(), adminInformation.getNic(),
                adminInformation.getPhysicion(), adminInformation.getEmergencyContact(), adminInformation.getInsuarance(), adminInformation.getInsuaranceContact(), adminInformation.getHospital(), adminInformation.getCode());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM admininformation WHERE code=?", key);
    }
}
