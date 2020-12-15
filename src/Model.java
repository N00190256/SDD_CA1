import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class Model {

        private static Model instance = null;

        public static synchronized Model getInstance() {
            if (instance == null) {
                instance = new Model();
            }
            return instance;
        }


        private List<dayPatient> dayPatient;
        private dayPatientGateway gateway;


        private Model() {

            try {
                Connection conn = databaseConnect.getInstance();
                this.gateway = new dayPatientGateway(conn);
            }
            //Cant find reason for this error to do with not finding the methods
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Model.class.getFname()).log(Level.SEVERE, "Connection Error. Please check JDBC Driver .jar file.");
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }

        }


        public  List<dayPatient> getDayPatient() {
            return
                    gateway.getDayPatient();
        }

        public boolean adddayPatient(dayPatient p) {
            return (gateway.insertDayPatient(p));
        }

        public boolean deleteDayPatient(int patient_id)
        {
            return (gateway.deleteDayPatient(patient_id));
        }

        public boolean updateDayPatient(int patient_id, String patient_fname, String patient_lname, int patient_age, String appointment_Date, String appointment_Time) {
            return this.gateway.updateDayPatient(patient_id, patient_fname, patient_lname, patient_age, appointment_Date, appointment_Time);
        }
    }

