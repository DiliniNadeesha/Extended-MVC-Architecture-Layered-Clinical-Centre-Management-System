package business.custom.impl;

import business.custom.MidWifeBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.MidWifeDAO;
import entity.MidWife;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MidWifeBOImpl implements MidWifeBO {

    private MidWifeDAO midWifeDAO = DAOFactory.getInstance().getDAO(DAOType.MID_WIFE);

    @Override
    public List<MidWife> getAllMidWifes() throws Exception {
        List<MidWife> allMidWifess = midWifeDAO.findAll();
        List<MidWife> midWives = new ArrayList<>();
        for (MidWife midWife : allMidWifess) {
            midWives.add(new MidWife(midWife.getId(), midWife.getName(), midWife.getAddress(), midWife.getNic(), midWife.getContactNo(), midWife.getEmail(),
                    midWife.getAmount(), midWife.getDivision(), midWife.getHospital()));
        }
        return midWives ;
    }

    @Override
    public MidWife findMidWifes(String key) throws Exception {
        return midWifeDAO.find(key);
    }

    @Override
    public ResultSet searchMidWifeByName(String mname) throws Exception {
        return midWifeDAO.searchMidWifeByName(mname);
    }

    @Override
    public boolean saveMidWife(String id, String name, String address, String nic, String contactNo, String email, int amount, String division, String hospital) throws Exception {
        return midWifeDAO.save(new MidWife(id, name, address, nic, contactNo, email, amount, division, hospital));
    }

    @Override
    public boolean deleteMidWife(String midwifeId) throws Exception {
        return midWifeDAO.delete(midwifeId);
    }

    @Override
    public boolean updateMidWife(String name, String address, String nic, String contactNo, String email, int amount, String division, String hospital, String id) throws Exception {
        return midWifeDAO.update(new MidWife(id, name, address, nic, contactNo, email, amount, division, hospital));
    }

    @Override
    public String getNewMidWifeId() throws Exception {
        String lastMidWifeId = midWifeDAO.getLastMidWifeId();

        if (lastMidWifeId == null) {
            return "MW001";
        } else {
            int maxId = Integer.parseInt(lastMidWifeId.replace("MW", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "MW00" + maxId;
            } else if (maxId < 100) {
                id = "MW0" + maxId;
            } else {
                id = "MW" + maxId;
            }
            return id;
        }
    }
}
