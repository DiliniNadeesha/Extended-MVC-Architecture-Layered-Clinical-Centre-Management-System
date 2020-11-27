package business.custom.impl;

import business.custom.DoctorBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.DoctorDAO;
import entity.Doctor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorBOImpl implements DoctorBO {

    private DoctorDAO doctorDAO = DAOFactory.getInstance().getDAO(DAOType.DOCTOR);

    @Override
    public List<Doctor> getAllDoctors() throws Exception {
        List<Doctor> allDoctors = doctorDAO.findAll();
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : allDoctors) {
            doctors.add(new Doctor(doctor.getId(), doctor.getName(), doctor.getNic(), doctor.getContactNo(), doctor.getEmail(), doctor.getArea(), doctor.getHospital()));
        }
        return doctors ;
    }

    @Override
    public Doctor findDoctors(String key) throws Exception {
        return doctorDAO.find(key);
    }

    @Override
    public ResultSet searchDoctorByName(String dname) throws Exception {
        return doctorDAO.searchDoctorByName(dname);
    }

    @Override
    public boolean saveDoctor(String id, String name, String nic, String contactNo, String email, String area, String hospital) throws Exception {
        return doctorDAO.save(new Doctor(id, name, nic, contactNo, email, area, hospital));
    }

    @Override
    public boolean deleteDoctor(String doctorId) throws Exception {
        return doctorDAO.delete(doctorId);
    }

    @Override
    public boolean updateDoctor(String name, String nic, String contactNo, String email, String area, String hospital, String id) throws Exception {
        return doctorDAO.update(new Doctor(id, name, nic, contactNo, email, area, hospital));
    }

    @Override
    public String getNewDoctorId() throws Exception {
        String lastDoctorId = doctorDAO.getLastDoctorId();

        if (lastDoctorId == null) {
            return "D001";
        } else {
            int maxId = Integer.parseInt(lastDoctorId.replace("D", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "D00" + maxId;
            } else if (maxId < 100) {
                id = "D0" + maxId;
            } else {
                id = "D" + maxId;
            }
            return id;
        }
    }
}
