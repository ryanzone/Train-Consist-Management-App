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

        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- UC18: Linear Search ---");
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
        Arrays.sort(sortedIds);

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

        System.out.println(isFound ? "FOUND (Binary)" : "NOT FOUND");

        // --- UC20 ---
        System.out.println("\n--- UC20: Exception Handling During Search ---");

        String[] emptyArray = {}; // simulate empty train

        System.out.print("Enter Bogie ID to search (with validation): ");
        String search = sc.nextLine();

        try {
            // Fail-fast check
            if (emptyArray.length == 0) {
                throw new IllegalStateException("Search not allowed: No bogies in the train.");
            }

            boolean result = false;
            for (String id : emptyArray) {
                if (id.equals(search)) {
                    result = true;
                    break;
                }
            }

            System.out.println(result ? "FOUND" : "NOT FOUND");

        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("Program continues safely...");

        sc.close();
    }
}