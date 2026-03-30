import java.util.*;

class Bogie {
    String type;
    int capacity;

    Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + " [Capacity: " + capacity + "]";
    }
}

public class TrainApp {
    public static void main(String[] args) {

        ArrayList<String> passengerNames = new ArrayList<>();
        Set<String> bogieIds = new HashSet<>();

        System.out.println("--- UC2: Adding Passenger Bogies ---");

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");

        passengerNames.add("Sleeper");
        passengerNames.add("AC Chair");
        passengerNames.add("First Class");

        System.out.println("Current Train Consist: " + passengerNames);
        System.out.println("Total Bogies: " + passengerNames.size());

        System.out.println("\n--- Removing AC Chair for Maintenance ---");
        passengerNames.remove("AC Chair");
        System.out.println("Updated Consist: " + passengerNames);

        System.out.println("\n--- Checking Bogie Status ---");
        if (passengerNames.contains("Sleeper")) {
            System.out.println("Status: Sleeper bogie is attached and ready.");
        } else {
            System.out.println("Status: Sleeper bogie not found.");
        }
        System.out.println("\nFinal Train Departure Consist: " + passengerNames);

        System.out.println("\n\nAttempting to add duplicate ID: BG101...");
        boolean isAdded = bogieIds.add("BG101");
        if (!isAdded) {
            System.out.println("System Alert: Duplicate Bogie ID 'BG101' rejected!");
        }
        bogieIds.add("BG104");

        System.out.println("\nFinal Registered Bogie IDs (Unique):");
        System.out.println(bogieIds);
        System.out.println("Total Unique Bogies Registered: " + bogieIds.size());

        System.out.println("\n=== UC4 ===");
        LinkedList<String> train = new LinkedList<>();
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

        System.out.println("\n--- UC5: Building Ordered Train Formation ---");
        Set<String> trainFormation = new LinkedHashSet<>();
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        System.out.println("Attempting to attach duplicate: 'Sleeper'...");
        if (!trainFormation.add("Sleeper")) {
            System.out.println("Safety Alert: Duplicate 'Sleeper' bogie rejected!");
        }

        trainFormation.add("AC Chair");
        System.out.println("\nFinal Train Formation (Order Preserved):");
        System.out.println(trainFormation);
        System.out.println("Total unique bogies in formation: " + trainFormation.size());

        System.out.println("\n--- UC6: Mapping Bogie Types to Capacities ---");
        Map<String, Integer> bogieCapacityMap = new HashMap<>();
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 56);
        bogieCapacityMap.put("First Class", 24);
        bogieCapacityMap.put("Cargo", 5000);
        bogieCapacityMap.put("Engine", 0);

        if (bogieCapacityMap.containsKey("Sleeper")) {
            System.out.println("Quick Lookup: Sleeper capacity is " + bogieCapacityMap.get("Sleeper") + " seats.");
        }

        System.out.println("\n--- Full Consist Capacity Report ---");
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println("Bogie Type: " + entry.getKey() + " | Capacity: " + entry.getValue());
        }
        bogieCapacityMap.put("Sleeper", 80);
        System.out.println("\nUpdated Sleeper Capacity: " + bogieCapacityMap.get("Sleeper"));

        System.out.println("\n--- UC7: Sorting Bogies by Capacity ---");
        List<Bogie> passengerObjects = new ArrayList<>();
        passengerObjects.add(new Bogie("Sleeper", 72));
        passengerObjects.add(new Bogie("First Class", 24));
        passengerObjects.add(new Bogie("AC Chair", 56));

        System.out.println("Unsorted Consist: " + passengerObjects);

        passengerObjects.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("\nSorted Consist (Ascending Capacity):");
        for (Bogie b : passengerObjects) {
            System.out.println("-> " + b);
        }

        passengerObjects.sort(Comparator.comparingInt((Bogie b) -> b.capacity).reversed());
        System.out.println("\nSorted Consist (Descending Capacity):");
        passengerObjects.forEach(b -> System.out.println("-> " + b));
    }
}