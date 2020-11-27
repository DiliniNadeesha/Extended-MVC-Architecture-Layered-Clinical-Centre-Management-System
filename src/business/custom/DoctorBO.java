package business.custom;

import business.SuperBO;
import entity.Doctor;

import java.sql.ResultSet;
import java.util.List;

public interface DoctorBO extends SuperBO {

    public List<Doctor> getAllDoctors() throws Exception;

    public Doctor findDoctors(String key) throws Exception;

    public ResultSet searchDoctorByName(String dname) throws Exception;

    public boolean saveDoctor(String id, String name, String nic, String contactNo, String email, String area, String hospital)throws Exception;

    public boolean deleteDoctor(String doctorId)throws Exception;

    public boolean updateDoctor(String name, String nic, String contactNo, String email, String area, String hospital, String id)throws Exception;

    public String getNewDoctorId()throws Exception;

}
