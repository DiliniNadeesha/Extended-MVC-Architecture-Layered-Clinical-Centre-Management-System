package business.custom.impl;

import business.custom.TreatmentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.TreatmentDAO;
import entity.Treatment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TreatmentBOImpl implements TreatmentBO {

    private TreatmentDAO treatmentDAO = DAOFactory.getInstance().getDAO(DAOType.TREATMENT);

    @Override
    public List<Treatment> getAllTreatments() throws Exception {
        List<Treatment> allTreatments = treatmentDAO.findAll();
        List<Treatment> treatments = new ArrayList<>();
        for (Treatment treatment : allTreatments) {
            treatments.add(new Treatment(treatment.getCode(), treatment.getDescription(), treatment.getDocotorName(), treatment.getPatientName(), treatment.getPatientContact(),
                    treatment.getCost(), treatment.getStartDate(), treatment.getEndDate(), treatment.getDocId()));
        }
        return treatments ;
    }

    @Override
    public Treatment findTreatments(String key) throws Exception {
        return treatmentDAO.find(key);
    }

    @Override
    public ResultSet searchTreatmentByDescription(String description) throws Exception {
        return treatmentDAO.searchTreatmentByDescription(description);
    }

    @Override
    public boolean saveTreatment(String code, String description, String docotorName, String patientName, String patientContact, BigDecimal cost, Date startDate, Date endDate, String docId) throws Exception {
        return treatmentDAO.save(new Treatment(code, description, docotorName, patientName, patientContact, cost, startDate, endDate, docId));
    }

    @Override
    public boolean deleteTreatment(String treatmentcode) throws Exception {
        return treatmentDAO.delete(treatmentcode);
    }

    @Override
    public boolean updateTreatment(String description, String docotorName, String patientName, String patientContact, BigDecimal cost, Date startDate, Date endDate, String docId, String code) throws Exception {
        return treatmentDAO.update(new Treatment(code, description, docotorName, patientName, patientContact, cost, startDate, endDate, docId));
    }

    @Override
    public String getNewTreatmentCode() throws Exception {
        String lastTreatmentCode = treatmentDAO.getLastTreatmentCode();

        if (lastTreatmentCode == null) {
            return "TR001";
        } else {
            int maxId = Integer.parseInt(lastTreatmentCode.replace("TR", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "TR00" + maxId;
            } else if (maxId < 100) {
                id = "TR0" + maxId;
            } else {
                id = "TR" + maxId;
            }
            return id;
        }
    }
}
