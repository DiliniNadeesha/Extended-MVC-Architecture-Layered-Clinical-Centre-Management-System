package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.MidWifeDAO;
import entity.MidWife;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MidWifeDAOImpl implements MidWifeDAO {

    @Override
    public String getLastMidWifeId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM midwife ORDER BY id DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchMidWifeByName(String mname) throws Exception {
        return CrudUtil.execute("SELECT name FROM midwife WHERE name LIKE '%" + mname + "%'");
    }

    @Override
    public List<MidWife> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM midwife");
        List<MidWife> midWives = new ArrayList<>();
        while (rst.next()) {
            midWives.add(new MidWife(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getString(8),
                    rst.getString(9)));
        }
        return midWives;
    }

    @Override
    public MidWife find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM midwife WHERE id=?", key);
        if (rst.next()) {
            return new MidWife(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getString(8),
                    rst.getString(9));
        }
        return null;
    }

    @Override
    public boolean save(MidWife midWife) throws Exception {
        return CrudUtil.execute("INSERT INTO midwife VALUES (?,?,?,?,?,?,?,?,?)", midWife.getId(), midWife.getName(), midWife.getAddress(), midWife.getNic(),
                midWife.getContactNo(), midWife.getEmail(), midWife.getAmount(), midWife.getDivision(), midWife.getHospital());
    }

    @Override
    public boolean update(MidWife midWife) throws Exception {
        return CrudUtil.execute("UPDATE midwife SET name=?, address=?, nic=?, contactNo=?, email=?, ptAmount=?, division=?, hospital=? WHERE id=?", midWife.getName(), midWife.getAddress(), midWife.getNic(),
                midWife.getContactNo(), midWife.getEmail(), midWife.getAmount(), midWife.getDivision(), midWife.getHospital(), midWife.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM midwife WHERE id=?", key);
    }
}
