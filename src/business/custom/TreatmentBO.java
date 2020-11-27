package business.custom;

import business.SuperBO;
import entity.Treatment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

public interface TreatmentBO extends SuperBO {

    public List<Treatment> getAllTreatments() throws Exception;

    public Treatment findTreatments(String key) throws Exception;

    public ResultSet searchTreatmentByDescription(String description) throws Exception;

    public boolean saveTreatment(String code, String description, String docotorName, String patientName, String patientContact, BigDecimal cost, Date startDate, Date endDate, String docId)throws Exception;

    public boolean deleteTreatment(String treatmentcode)throws Exception;

    public boolean updateTreatment(String description, String docotorName, String patientName, String patientContact, BigDecimal cost, Date startDate, Date endDate, String docId, String code)throws Exception;

    public String getNewTreatmentCode()throws Exception;

}
