import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner keyboard = new Scanner(System.in);
    static Model model = Model.getInstance();

    public static void main(String[] args) {

        dayPatient p;

        int opt;
        do {
            //This is the menu that the user sees when they first run this code
            System.out.println("\n------- Hello There! -------");
            System.out.println("1. View all Patient Appointments");
            System.out.println("2. Add an appointment to the list");
            System.out.println("3. Delete an appointment from the list by Appointment ID");
            System.out.println("4: Update an existing Patient Appointment");
            System.out.println("5. Exit List");

            System.out.print("\n------- Please enter your selection -------\n");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            //This is the code for the action depending on what number is given
            switch (opt) {
                case 1: {
                    viewDayPatient();

                    break;
                }

                case 2: {
                    p = readDayPatient();

                    boolean created = model.adddayPatient(p);
                    if (created)
                        System.out.println("***** Game has been added to the Database *****");
                    else
                        System.out.println("***** Game has NOT been Added to the Database *****");

                    break;
                }

                case 3: {
                    deleteDayPatient();
                    break;
                }

                case 4: {
                    updateDayPatient();
                    break;
                }
            }
        }
        while (opt != 5);
        System.out.println("Have a nice day!");
    }

    //The readDayPatient function is called when there is a request to add another appointment to the list
    private static dayPatient readDayPatient() {
        String patient_fname, patient_lname, appointment_Date, appointment_Time;
        int patient_age;

        System.out.print("\nGame name: ");
        patient_fname = keyboard.nextLine();

        System.out.print("Platform: ");
        patient_lname = keyboard.nextLine();

        System.out.print("Genre: ");
        patient_age = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("PEGI Rating: ");
        appointment_Date = keyboard.nextLine();

        System.out.print("Difficulty: ");
        appointment_Time = keyboard.nextLine();


        dayPatient p =
                new dayPatient(patient_fname, patient_lname, patient_age, appointment_Date, appointment_Time);
        return p;
    }

    //This calls the Model class to display the list of all appointments in the database
    private static void viewDayPatient() {

        List<dayPatient> dayPatients = model.getDayPatient();

        for (dayPatient dp : dayPatients) {
            System.out.println(dp);
        }

    }

    //This calls on the model class and removes any appointment that matches the given ID
    private static void deleteDayPatient() {
        System.out.print("Enter the ID of the Game to delete: ");
        int patient_id = Integer.parseInt(keyboard.nextLine());

        if (model.deleteDayPatient(patient_id)) {
            System.out.println("\nGame has been deleted");
        }
        else {
            System.out.println("\nGame has not been deleted, check your database to see if a game with this ID actually exists");
        }
    }

    //The updateDayPatient function asks for multiple inputs based on the needed information for an appointment, if they wish to leave it as it was they will need to enter the exact same info again
    private static void updateDayPatient() {

        String patient_fname, patient_lname, appointment_Date, appointment_Time;
        int patient_id, patient_age;

        System.out.println("Enter the ID of the game: ");
        patient_id = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter the name of the game: ");
        patient_fname = keyboard.nextLine();

        System.out.println("Enter the platform of the game: ");
        patient_lname = keyboard.nextLine();

        System.out.println("Enter the genre of the game: ");
        patient_age = keyboard.nextInt();

        System.out.println("Enter the age rating of the game: ");
        appointment_Date = keyboard.nextLine();

        System.out.println("Enter the difficulty of the game: ");
        appointment_Time = keyboard.nextLine();

        keyboard.nextLine();

        if (model.updateDayPatient(patient_id, patient_fname, patient_lname, patient_age, appointment_Date, appointment_Time)) {
            System.out.println("\n***** Game has been updated! *****");
        }else{
            System.out.println("\n***** Game has not been updated *****");
        }
    }


}
