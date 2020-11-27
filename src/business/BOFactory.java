package business;

import business.custom.impl.*;

public class BOFactory {

    //Start of Singleton Design Pattern
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance() {

        return (boFactory == null) ? boFactory = new BOFactory(): boFactory;
    }
    //ENd of Singleton Design Pattern

    public <T extends SuperBO> T getBO(BOType boType) {
        switch (boType){
            case ADMIN_INFORMATION:
                return (T) new AdminInformationBOImpl();
            case CLINIC_CARD:
                return (T) new ClinicCardBOImpl();
            case DOCTOR:
                return (T) new DoctorBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case LAB_TEST:
                return (T) new LabTestBOImpl();
            case MID_WIFE:
                return (T) new MidWifeBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
//            case ORDER_DETAIL:
//                return (T) new OrderDetailBOImpl();
            case PREG_MOTHER:
                return (T) new PregMotherBOImpl();
            case TREATMENT:
                return (T) new TreatmentBOImpl();
            default:
                return null;
        }
    }
}
