package business.custom.impl;

import business.custom.ClinicCardBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ClinicCardDAO;
import entity.ClinicCard;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClinicCardBOImpl implements ClinicCardBO {

    private ClinicCardDAO clinicCardDAO = DAOFactory.getInstance().getDAO(DAOType.CLINIC_CARD);

    @Override
    public List<ClinicCard> getAllCards() throws Exception {
        List<ClinicCard> allCards = clinicCardDAO.findAll();
        List<ClinicCard> clinicCard = new ArrayList<>();
        for (ClinicCard card : allCards) {
            clinicCard.add(new ClinicCard(card.getId(), card.getName(), card.getAge(), card.getAddress(), card.getNic(), card.getContactNo(), card.getEmail(),
                    card.getHusband(), card.getHusbandContact(), card.getBlood(), card.getWeight(), card.getHeight(), card.getSituation(), card.getMethod(),
                    card.getPhysician(), card.getHospital()));
        }
        return clinicCard ;
    }

    @Override
    public ClinicCard findCards(String key) throws Exception {
        return clinicCardDAO.find(key);
    }

    @Override
    public ResultSet searchCardByPatientName(String pname) throws Exception {
        return clinicCardDAO.searchPatientByName(pname);
    }

    @Override
    public boolean saveCard(String id, String name, String age, String address, String nic, String contactNo, String email, String husband, String husbandContact, String blood, String weight, String height, String situation, String method, String physician, String hospital) throws Exception {
        return clinicCardDAO.save(new ClinicCard(id, name, age, address, nic, contactNo, email, husband, husbandContact, blood, weight, height, situation, method, physician, hospital));
    }

    @Override
    public boolean deleteCard(String cardId) throws Exception {
        return clinicCardDAO.delete(cardId);
    }

    @Override
    public boolean updateCard(String name, String age, String address, String nic, String contactNo, String email, String husband, String husbandContact, String blood, String weight, String height, String situation, String method, String physician, String hospital, String id) throws Exception {
        return clinicCardDAO.update(new ClinicCard(name, age, address, nic, contactNo, email, husband, husbandContact, blood, weight, height, situation, method, physician, hospital, id));
    }

    @Override
    public String getNewCardId() throws Exception {
        String lastCardId = clinicCardDAO.getLastCardId();

        if (lastCardId == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastCardId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }
}
