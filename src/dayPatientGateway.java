import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dayPatientGateway {
    private static final String TABLE_NAME = "sdd";
    private static final String COLUMN_ID = "patient_id";
    private static final String COLUMN_FNAME= "patient_fname";
    private static final String COLUMN_LNAME = "patient_lname";
    private static final String COLUMN_AGE = "patient_age";
    private static final String COLUMN_DATE = "appointment_Date";
    private static final String COLUMN_TIME = "appointment_Time";

    private Connection mConnection;

    public dayPatientGateway(Connection connection) {
        mConnection = connection;
    }

    public boolean insertDayPatient(dayPatient p) {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_FNAME + ", " +
                COLUMN_LNAME + ", " +
                COLUMN_AGE + ", " +
                COLUMN_DATE + ", " +
                COLUMN_TIME + ") VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getFname());
            stmt.setString(2, p.getLname());
            stmt.setInt(3, p.getAge());
            stmt.setString(4, p.getDate());
            stmt.setString(5, p.getTime());

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getFname()).log(Level.SEVERE, "SQL Exception in dayPatientTableGateway : insertdayPatient(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }

    public List<dayPatient> getDayPatient()  {
        String query;
        Statement stmt;
        ResultSet rs;
        List<dayPatient> dayPatients;

        String patient_fname, patient_lname, appointment_Date, appointment_Time;
        int patient_id, patient_age;

        dayPatient p;

        dayPatients = new ArrayList<dayPatient>();

        try {
            query = "SELECT * FROM " + TABLE_NAME;
            stmt = this.mConnection.createStatement();
            rs = stmt.executeQuery(query);



            while (rs.next()) {
                patient_fname = rs.getString(COLUMN_FNAME);
                patient_lname = rs.getString(COLUMN_LNAME);
                appointment_Date = rs.getString(COLUMN_DATE);
                appointment_Time = rs.getString(COLUMN_TIME);

                patient_id = rs.getInt(COLUMN_ID);
                patient_age = rs.getInt(COLUMN_AGE);

                p = new dayPatient(patient_id, patient_fname, patient_lname, patient_age, appointment_Date, appointment_Time);
                dayPatients.add(p);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getFname()).log(Level.SEVERE, "SQL Exception");
        }

        return dayPatients;
    }

    public boolean deleteDayPatient(int patient_id)    {

        int numRowsAffected;



        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= ?";


        try {
            PreparedStatement stmt;

            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, patient_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getFname()).log(Level.SEVERE, "SQL Exception in GameGateway : deleteGame(), Check the SQL you have created to see where your error is", e);
        }

        return false;
    }

    public boolean updateDayPatient(int patient_id, String patient_fname, String patient_lname, int patient_age,  String appointment_Date, String appointment_Time) {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "UPDATE dayPatient SET patient_fname = (?), patient_lname = (?), patient_age = (?), appointment_Date = (?), appointment_Time = (?) WHERE patient_id = ?";
        try {

            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient_fname);
            stmt.setString(2, patient_lname);
            stmt.setInt(3, patient_age);
            stmt.setString(4, appointment_Date);
            stmt.setString(5, appointment_Time);

            stmt.setInt(6, patient_id);

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Model.class.getFname()).log(Level.SEVERE, "SQL Exception in DayPatientGateway : updateDayPatient(), check the SQL you have created to see where your error is", e);
        }

        return false;
    }
}
