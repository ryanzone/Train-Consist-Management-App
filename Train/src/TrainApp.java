import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

// --- UC14: Custom Checked Exception ---
class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

// --- UC15: Custom Runtime Exception ---
class CargoSafetyException extends RuntimeException {
    CargoSafetyException(String message) {
        super(message);
    }
}

class Bogie {
    String type;
    int capacity;

    Bogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than 0 for bogie: " + type);
        }
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + " [Capacity: " + capacity + "]";
    }
}

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    void assignCargo(String cargo) {
        try {
            if (type.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException(
                        "Unsafe Assignment: Petroleum cannot be loaded in Rectangular bogie");
            }
            this.cargo = cargo;
            System.out.println("Cargo assigned successfully: " + type + " -> " + cargo);
        } catch (CargoSafetyException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("Assignment attempt completed for " + type + " bogie.");
        }
    }

    @Override
    public String toString() {
        return type + " carrying " + cargo;
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

        try {
            passengerObjects.add(new Bogie("Sleeper", 72));
            passengerObjects.add(new Bogie("First Class", 24));
            passengerObjects.add(new Bogie("AC Chair", 56));
            passengerObjects.add(new Bogie("Invalid Bogie", -10));
        } catch (InvalidCapacityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }

        System.out.println("Unsorted Consist: " + passengerObjects);

        passengerObjects.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("\nSorted Consist (Ascending Capacity):");
        for (Bogie b : passengerObjects) {
            System.out.println("-> " + b);
        }

        passengerObjects.sort(Comparator.comparingInt((Bogie b) -> b.capacity).reversed());
        System.out.println("\nSorted Consist (Descending Capacity):");
        passengerObjects.forEach(b -> System.out.println("-> " + b));

        System.out.println("\n--- UC8: Filtering Bogies with Capacity > 60 ---");
        List<Bogie> filteredBogies = passengerObjects.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        filteredBogies.forEach(b -> System.out.println("-> " + b));

        System.out.println("\n--- UC9: Grouping Bogies by Type ---");
        Map<String, List<Bogie>> groupedBogies = passengerObjects.stream()
                .collect(Collectors.groupingBy(b -> b.type));

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nCategory: " + entry.getKey());
            entry.getValue().forEach(b -> System.out.println("-> " + b));
        }

        System.out.println("\n--- UC10: Total Seating Capacity ---");
        int totalCapacity = passengerObjects.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + totalCapacity);

        System.out.println("\n--- UC11: Train ID & Cargo Code Validation ---");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train ID (format TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code (format PET-AB): ");
        String cargoCode = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println(trainPattern.matcher(trainId).matches() ? "Train ID is VALID" : "Train ID is INVALID");
        System.out.println(cargoPattern.matcher(cargoCode).matches() ? "Cargo Code is VALID" : "Cargo Code is INVALID");

        System.out.println("\n--- UC12: Safety Validation for Goods Bogies ---");

        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Box", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        System.out.println(isSafe ? "Train is SAFE for operation" : "Train is NOT SAFE");

        System.out.println("\n--- UC13: Loop vs Stream Performance ---");

        List<Bogie> testBogies = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            try {
                testBogies.add(new Bogie("Type" + i, i % 100 + 1));
            } catch (InvalidCapacityException e) {}
        }

        long startLoop = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : testBogies) {
            if (b.capacity > 60) loopResult.add(b);
        }
        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();
        List<Bogie> streamResult = testBogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();

        System.out.println("Loop Time (ns): " + (endLoop - startLoop));
        System.out.println("Stream Time (ns): " + (endStream - startStream));

        // --- UC15 ---
        System.out.println("\n--- UC15: Safe Cargo Assignment with Exception Handling ---");

        GoodsBogie g1 = new GoodsBogie("Cylindrical", null);
        GoodsBogie g2 = new GoodsBogie("Rectangular", null);

        g1.assignCargo("Petroleum");
        g2.assignCargo("Petroleum");

        System.out.println("Program continues safely after handling exception.");

        // --- UC16 ---
        System.out.println("\n--- UC16: Sorting Passenger Bogie Capacities (Bubble Sort) ---");

        int[] capacities = {72, 56, 24, 70, 60};

        System.out.print("Original Capacities: ");
        for (int cap : capacities) {
            System.out.print(cap + " ");
        }

        int n = capacities.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.print("\nSorted Capacities: ");
        for (int cap : capacities) {
            System.out.print(cap + " ");
        }

        System.out.println("\nProgram continues...");

        sc.close();
    }
}