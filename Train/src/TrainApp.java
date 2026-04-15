import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

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

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
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

        System.out.println("\n--- UC8: Filtering Bogies with Capacity > 60 ---");
        List<Bogie> filteredBogies = passengerObjects.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered Bogies:");
        filteredBogies.forEach(b -> System.out.println("-> " + b));

        System.out.println("\n--- UC9: Grouping Bogies by Type ---");
        Map<String, List<Bogie>> groupedBogies = passengerObjects.stream()
                .collect(Collectors.groupingBy(b -> b.type));

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nCategory: " + entry.getKey());
            for (Bogie b : entry.getValue()) {
                System.out.println("-> " + b);
            }
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

        if (trainPattern.matcher(trainId).matches()) {
            System.out.println("Train ID is VALID");
        } else {
            System.out.println("Train ID is INVALID");
        }

        if (cargoPattern.matcher(cargoCode).matches()) {
            System.out.println("Cargo Code is VALID");
        } else {
            System.out.println("Cargo Code is INVALID");
        }

        System.out.println("\n--- UC12: Safety Validation for Goods Bogies ---");

        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Box", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));

        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> {
                    if (b.type.equals("Cylindrical")) {
                        return b.cargo.equals("Petroleum");
                    }
                    return true;
                });

        if (isSafe) {
            System.out.println("Train is SAFE for operation");
        } else {
            System.out.println("Train is NOT SAFE");
        }

        // --- UC13: Performance Comparison ---
        System.out.println("\n--- UC13: Loop vs Stream Performance ---");

        List<Bogie> testBogies = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            testBogies.add(new Bogie("Type" + i, i % 100));
        }

        // Loop-based filtering
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : testBogies) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // Stream-based filtering
        long startStream = System.nanoTime();

        List<Bogie> streamResult = testBogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        System.out.println("Loop Time (ns): " + loopTime);
        System.out.println("Stream Time (ns): " + streamTime);

        sc.close();
    }
}