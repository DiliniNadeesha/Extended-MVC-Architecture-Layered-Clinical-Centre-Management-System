package dao;

import dao.custom.impl.*;

public class DAOFactory {

    //Start of Singleton Design Pattern
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance() {

        return (daoFactory == null) ? daoFactory = new DAOFactory(): daoFactory;
    }
    //ENd of Singleton Design Pattern

    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case ADMIN_INFORMATION:
                return (T) new AdminInformationDAOImpl();
            case CLINIC_CARD:
                return (T) new ClinicCardDAOImpl();
            case DOCTOR:
                return (T) new DoctorDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case LAB_TEST:
                return (T) new LabTestDAOImpl();
            case MID_WIFE:
                return (T) new MidWifeDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case PREG_MOTHER:
                return (T) new PregMotherDAOImpl();
            case TREATMENT:
                return (T) new TreatmentDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
