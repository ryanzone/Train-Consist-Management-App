import java.util.*;

public class TrainApp {
    public static void main(String[] args) {

        ArrayList<String> passengerBogies = new ArrayList<>();

        System.out.println("--- UC2: Adding Passenger Bogies ---");

        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("Current Train Consist: " + passengerBogies);
        System.out.println("Total Bogies: " + passengerBogies.size());

        System.out.println("\n--- Removing AC Chair for Maintenance ---");
        passengerBogies.remove("AC Chair");
        System.out.println("Updated Consist: " + passengerBogies);

        System.out.println("\n--- Checking Bogie Status ---");
        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Status: Sleeper bogie is attached and ready.");
        } else {
            System.out.println("Status: Sleeper bogie not found.");
        }

        System.out.println("\nFinal Train Departure Consist: " + passengerBogies);
    }
}