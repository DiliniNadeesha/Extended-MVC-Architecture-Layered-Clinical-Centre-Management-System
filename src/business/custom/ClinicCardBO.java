package business.custom;

import business.SuperBO;
import entity.ClinicCard;

import java.sql.ResultSet;
import java.util.List;

public interface ClinicCardBO extends SuperBO {

    public List<ClinicCard> getAllCards() throws Exception;

    public ClinicCard findCards(String key) throws Exception;

    public ResultSet searchCardByPatientName(String pname) throws Exception;

    public boolean saveCard(String id, String name, String age, String address, String nic, String contactNo, String email, String husband, String husbandContact, String blood, String weight, String height, String situation, String method, String physician, String hospital)throws Exception;

    public boolean deleteCard(String cardId)throws Exception;

    public boolean updateCard(String name, String age, String address, String nic, String contactNo, String email, String husband, String husbandContact, String blood, String weight, String height, String situation, String method, String physician, String hospital, String id)throws Exception;

    public String getNewCardId()throws Exception;

}
