package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PregMotherDAO;
import entity.PregMother;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PregMotherDAOImpl implements PregMotherDAO {

    @Override
    public String getLastMotherId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM pregmother ORDER BY id DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchMotherByName(String mname) throws Exception {
        return CrudUtil.execute("SELECT name FROM pregmother WHERE name LIKE '%" + mname + "%'");
    }

    @Override
    public List<PregMother> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM pregmother");
        List<PregMother> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new PregMother(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)));
        }
        return customers;
    }

    @Override
    public PregMother find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM pregmother WHERE id=?", key);
        if (rst.next()) {
            return new PregMother(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    @Override
    public boolean save(PregMother pregMother) throws Exception {
        return CrudUtil.execute("INSERT INTO pregmother VALUES (?,?,?,?,?,?)", pregMother.getId(), pregMother.getName(), pregMother.getAddress(), pregMother.getNic(), pregMother.getContactNo(), pregMother.getEmail());
    }

    @Override
    public boolean update(PregMother pregMother) throws Exception {
        return CrudUtil.execute("UPDATE pregmother SET name=?, address=?, nic=?, contactNo=?, email=? WHERE id=?", pregMother.getName(), pregMother.getAddress(), pregMother.getNic(), pregMother.getContactNo(), pregMother.getEmail(), pregMother.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM pregmother WHERE id=?", key);
    }
}
