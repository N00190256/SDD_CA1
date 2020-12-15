public class dayPatient {

    private int patient_id;
    private String patient_fname;
    private String patient_lname;
    private int patient_age;
    private String appointment_Date;
    private String appointment_Time;

    public dayPatient(int i, String f, String l, int a, String d, String t) {
        this.patient_id = i;
        this.patient_fname = f;
        this.patient_lname = l;
        this.patient_age = a;
        this.appointment_Date = d;
        this.appointment_Time = t;
    }

    public dayPatient(String f, String l, int a, String d, String t) {
        this.patient_fname = f;
        this.patient_lname = l;
        this.patient_age = a;
        this.appointment_Date = d;
        this.appointment_Time = t;
    }

    public int getId() { return patient_id; }
    public void setId(int id) { this.patient_id = patient_id; }

    public String getFname() {
        return patient_fname;
    }
    public void setFname(String patient_fname) {
        this.patient_fname = patient_fname;
    }

    public String getLname() {
        return patient_lname;
    }
    public void setLname(String patient_lname) {
        this.patient_lname = patient_lname;
    }

    public int getAge() {
        return patient_age;
    }
    public void setAge(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getDate() {
        return appointment_Date;
    }
    public void setDate(String appointment_Date) {
        this.appointment_Date = appointment_Date;
    }

    public String getTime() { return appointment_Time; }
    public void setAppointment_Time(String appointment_Time) { this.appointment_Time = appointment_Time; }

    @Override
    public String toString(){
        return "\n\nID: " + this.patient_id +
                "\nFNAME: " + this.patient_fname +
                "\nLNAME: " + this.patient_lname +
                "\nAGE: " + this.patient_age +
                "\nDATE: " + this.appointment_Date +
                "\nTIME: " + this.appointment_Time; }
}
