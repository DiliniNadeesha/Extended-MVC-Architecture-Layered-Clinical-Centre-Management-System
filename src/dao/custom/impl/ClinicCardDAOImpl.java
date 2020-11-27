package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ClinicCardDAO;
import entity.ClinicCard;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClinicCardDAOImpl implements ClinicCardDAO {

    @Override
    public String getLastCardId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM cliniccard ORDER BY id DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchPatientByName(String pname) throws Exception {
        return CrudUtil.execute("SELECT name FROM cliniccard WHERE name LIKE '%" + pname + "%'");
    }

    @Override
    public List<ClinicCard> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM pregmother");
        List<ClinicCard> clinicCards = new ArrayList<>();
        while (rst.next()) {
            clinicCards.add(new ClinicCard(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14),
                    rst.getString(15),
                    rst.getString(16)));
        }
        return clinicCards;
    }

    @Override
    public ClinicCard find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM cliniccard WHERE id=?", key);
        if (rst.next()) {
            return new ClinicCard(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14),
                    rst.getString(15),
                    rst.getString(16));
        }
        return null;
    }

    @Override
    public boolean save(ClinicCard clinicCard) throws Exception {
        return CrudUtil.execute("INSERT INTO cliniccard VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", clinicCard.getId(), clinicCard.getName(),
                clinicCard.getAge(), clinicCard.getAddress(), clinicCard.getNic(), clinicCard.getContactNo(), clinicCard.getEmail(), clinicCard.getHusband(),
                clinicCard.getHusbandContact(), clinicCard.getBlood(), clinicCard.getWeight(), clinicCard.getHeight(),
                clinicCard.getSituation(), clinicCard.getMethod(), clinicCard.getPhysician(), clinicCard.getHospital());
    }

    @Override
    public boolean update(ClinicCard clinicCard) throws Exception {
        return CrudUtil.execute("UPDATE cliniccard SET name=?, age=?, address=?, nic=?, contactNo=?, email=?, husbandName=?, husbandContactNo=?, bloodGroup=?, weight=?," +
                "height=?, situation=?, delMethod=?, personalPhisicion=?, nearestHospital=? WHERE id=?", clinicCard.getName(),
                clinicCard.getAge(), clinicCard.getAddress(), clinicCard.getNic(), clinicCard.getContactNo(), clinicCard.getEmail(), clinicCard.getHusband(),
                clinicCard.getHusbandContact(), clinicCard.getBlood(), clinicCard.getWeight(), clinicCard.getHeight(),
                clinicCard.getSituation(), clinicCard.getMethod(), clinicCard.getPhysician(), clinicCard.getHospital(), clinicCard.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM cliniccard WHERE id=?", key);
    }
}
