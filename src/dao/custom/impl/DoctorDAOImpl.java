package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.DoctorDAO;
import entity.Doctor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public String getLastDoctorId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM doctor ORDER BY id DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public ResultSet searchDoctorByName(String dname) throws Exception {
        return CrudUtil.execute("SELECT name FROM doctor WHERE name LIKE '%" + dname + "%'");
    }

    @Override
    public List<Doctor> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM doctor");
        List<Doctor> doctors = new ArrayList<>();
        while (rst.next()) {
            doctors.add(new Doctor(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)));
        }
        return doctors;
    }

    @Override
    public Doctor find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM doctor WHERE id=?", key);
        if (rst.next()) {
            return new Doctor(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7));
        }
        return null;
    }

    @Override
    public boolean save(Doctor doctor) throws Exception {
        return CrudUtil.execute("INSERT INTO doctor VALUES (?,?,?,?,?,?,?)", doctor.getId(), doctor.getName(), doctor.getNic(), doctor.getContactNo(), doctor.getEmail(), doctor.getArea(), doctor.getHospital());
    }

    @Override
    public boolean update(Doctor doctor) throws Exception {
        return CrudUtil.execute("UPDATE doctor SET name=?, nic=?, contactNo=?, email=?, specialArea=?, hospital=? WHERE id=?", doctor.getName(), doctor.getNic(), doctor.getContactNo(), doctor.getEmail(), doctor.getArea(), doctor.getHospital(), doctor.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM doctor WHERE id=?", key);
    }
}
