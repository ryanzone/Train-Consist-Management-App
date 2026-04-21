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

        System.out.println("\n--- UC5 ---");
        Set<String> trainFormation = new LinkedHashSet<>();
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");
        trainFormation.add("AC Chair");

        System.out.println(trainFormation);

        System.out.println("\n--- UC6 ---");
        Map<String, Integer> bogieCapacityMap = new HashMap<>();
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 56);
        bogieCapacityMap.put("First Class", 24);

        System.out.println(bogieCapacityMap);

        System.out.println("\n--- UC7 ---");
        List<Bogie> passengerObjects = new ArrayList<>();
        try {
            passengerObjects.add(new Bogie("Sleeper", 72));
            passengerObjects.add(new Bogie("AC Chair", 56));
            passengerObjects.add(new Bogie("First Class", 24));
        } catch (InvalidCapacityException e) {}

        passengerObjects.sort(Comparator.comparingInt(b -> b.capacity));
        passengerObjects.forEach(System.out::println);

        System.out.println("\n--- UC15 ---");
        GoodsBogie g1 = new GoodsBogie("Cylindrical", null);
        GoodsBogie g2 = new GoodsBogie("Rectangular", null);
        g1.assignCargo("Petroleum");
        g2.assignCargo("Petroleum");

        System.out.println("\n--- UC16 ---");
        int[] capacities = {72, 56, 24, 70, 60};
        for (int i = 0; i < capacities.length - 1; i++) {
            for (int j = 0; j < capacities.length - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(capacities));

        System.out.println("\n--- UC17 ---");
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        Arrays.sort(bogieNames);
        System.out.println(Arrays.toString(bogieNames));

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- UC18 ---");
        String[] bogieIdArray = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        System.out.print("Enter Bogie ID to search: ");
        String key = sc.nextLine();

        boolean found = false;
        for (String id : bogieIdArray) {
            if (id.equals(key)) {
                found = true;
                break;
            }
        }
        System.out.println(found ? "FOUND" : "NOT FOUND");

        System.out.println("\n--- UC19: Binary Search ---");

        String[] sortedIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        Arrays.sort(sortedIds); // ensure sorted

        System.out.print("Enter Bogie ID to search (Binary): ");
        String searchKey = sc.nextLine();

        int low = 0, high = sortedIds.length - 1;
        boolean isFound = false;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = sortedIds[mid].compareTo(searchKey);

            if (cmp == 0) {
                isFound = true;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(isFound ? "Bogie FOUND (Binary Search)" : "Bogie NOT FOUND");

        sc.close();
    }
}