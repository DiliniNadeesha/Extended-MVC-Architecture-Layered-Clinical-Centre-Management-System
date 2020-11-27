package business.custom.impl;

import business.custom.AdminInformationBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.AdminInformationDAO;
import entity.AdminInformation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminInformationBOImpl implements AdminInformationBO {

    private AdminInformationDAO adminInformationDAO = DAOFactory.getInstance().getDAO(DAOType.ADMIN_INFORMATION);

    @Override
    public List<AdminInformation> getAllInfo() throws Exception {
        List<AdminInformation> allInfos = adminInformationDAO.findAll();
        List<AdminInformation> adminInformations = new ArrayList<>();
        for (AdminInformation adminInformation : allInfos) {
            adminInformations.add(new AdminInformation(adminInformation.getCode(), adminInformation.getName(), adminInformation.getNic(), adminInformation.getPhysicion(),
                    adminInformation.getEmergencyContact(), adminInformation.getInsuarance(), adminInformation.getInsuaranceContact(), adminInformation.getHospital()));
        }
        return adminInformations ;
    }

    @Override
    public AdminInformation findInfo(String key) throws Exception {
        return adminInformationDAO.find(key);
    }

    @Override
    public ResultSet searchInfoByName(String pname) throws Exception {
        return adminInformationDAO.searchPatientByName(pname);
    }

    @Override
    public boolean saveInfo(String code, String name, String nic, String physicion, String emergencyContact, String insuarance, String insuaranceContact, String hospital) throws Exception {
        return adminInformationDAO.save(new AdminInformation(code, name, nic, physicion, emergencyContact, insuarance, insuaranceContact, hospital));
    }

    @Override
    public boolean deleteInfo(String infocode) throws Exception {
        return adminInformationDAO.delete(infocode);
    }

    @Override
    public boolean updateInfo(String name, String nic, String physicion, String emergencyContact, String insuarance, String insuaranceContact, String hospital, String code) throws Exception {
        return adminInformationDAO.update(new AdminInformation(code, name, nic, physicion, emergencyContact, insuarance, insuaranceContact, hospital));
    }

    @Override
    public String getNewInfoCode() throws Exception {
        String lastInfoCode = adminInformationDAO.getLastInfoCode();

        if (lastInfoCode == null) {
            return "AI001";
        } else {
            int maxId = Integer.parseInt(lastInfoCode.replace("AI", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "AI00" + maxId;
            } else if (maxId < 100) {
                id = "AI0" + maxId;
            } else {
                id = "AI" + maxId;
            }
            return id;
        }
    }
}
