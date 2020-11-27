package business.custom.impl;

import business.custom.PregMotherBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.PregMotherDAO;
import entity.PregMother;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PregMotherBOImpl implements PregMotherBO {

    private PregMotherDAO pregMotherDAO = DAOFactory.getInstance().getDAO(DAOType.PREG_MOTHER);

    @Override
    public List<PregMother> getAllMothers() throws Exception {
        List<PregMother> allMothers = pregMotherDAO.findAll();
        List<PregMother> pregMother = new ArrayList<>();
        for (PregMother mother : allMothers) {
            pregMother.add(new PregMother(mother.getId(), mother.getName(), mother.getAddress(),mother.getNic(),mother.getContactNo(),mother.getEmail()));
        }
        return pregMother ;
    }

    @Override
    public PregMother findMothers(String key) throws Exception {
        return pregMotherDAO.find(key);
    }

    @Override
    public ResultSet searchMotherByName(String mname) throws Exception {
        return pregMotherDAO.searchMotherByName(mname);
    }

    @Override
    public boolean saveMother(String id, String name, String address, String nic, String contactNo, String email) throws Exception {
        return pregMotherDAO.save(new PregMother(id,name,address,nic,contactNo,email));
    }

    @Override
    public boolean deleteMother(String motherId) throws Exception {
        return pregMotherDAO.delete(motherId);
    }

    @Override
    public boolean updateMother(String name, String address, String nic, String contactNo, String email, String motherId) throws Exception {
        return pregMotherDAO.update(new PregMother(motherId, name, address, nic, contactNo, email));
    }

    @Override
    public String getNewMotherId() throws Exception {
        String lastMotherId = pregMotherDAO.getLastMotherId();

        if (lastMotherId == null) {
            return "M001";
        } else {
            int maxId = Integer.parseInt(lastMotherId.replace("M", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "M00" + maxId;
            } else if (maxId < 100) {
                id = "M0" + maxId;
            } else {
                id = "M" + maxId;
            }
            return id;
        }
    }
}
