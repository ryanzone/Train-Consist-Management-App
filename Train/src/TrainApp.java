import java.util.*;

public class TrainApp {
    public static void main(String[] args) {

        ArrayList<String> passengerBogies = new ArrayList<>();
        Set <String> bogieIds = new HashSet<>();
        System.out.println("--- UC2: Adding Passenger Bogies ---");

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");

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


        System.out.println("\n\nAttempting to add duplicate ID: BG101...");
        boolean isAdded = bogieIds.add("BG101");
        if (!isAdded) {
            System.out.println("System Alert: Duplicate Bogie ID 'BG101' rejected!");
        }
        bogieIds.add("BG104");

        System.out.println("\nFinal Registered Bogie IDs (Unique):");
        System.out.println(bogieIds);

        System.out.println("Total Unique Bogies Registered: " + bogieIds.size());

        System.out.println(" === UC4 ===");
        LinkedList<String> train = new LinkedList<>();

        // 1. Building the train (addLast is default)
        train.add("Sleeper");
        train.add("AC Chair");

        train.addFirst("Engine");

        train.addLast("Guard Coach");

        System.out.println("Initial Train: " + train);


        train.add(2, "Pantry Car");
        System.out.println("After Adding Pantry: " + train);

        String removedFront = train.removeFirst();
        String removedBack = train.removeLast();

        System.out.println("\nDetached: " + removedFront + " and " + removedBack);
        System.out.println("Final Operational Consist: " + train);
    }
}
