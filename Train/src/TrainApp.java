import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

// --- Custom Checked Exception (UC14) ---
class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

// --- Custom Runtime Exception (UC15) ---
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
    String shape;
    String cargo;

    GoodsBogie(String shape) {
        this.shape = shape;
    }

    void assignCargo(String cargo) {
        try {
            if (shape.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException("Unsafe Assignment: Petroleum cannot be loaded in Rectangular bogie");
            }
            this.cargo = cargo;
            System.out.println("Cargo assigned successfully: " + shape + " -> " + cargo);
        } catch (CargoSafetyException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("Assignment attempt completed for " + shape + " bogie.");
        }
    }

    @Override
    public String toString() {
        return shape + " carrying " + cargo;
    }
}

public class TrainApp {
    public static void main(String[] args) {

        // --- UC15: Safe Cargo Assignment ---
        System.out.println("\n--- UC15: Safe Cargo Assignment with Exception Handling ---");

        GoodsBogie g1 = new GoodsBogie("Cylindrical");
        GoodsBogie g2 = new GoodsBogie("Rectangular");

        g1.assignCargo("Petroleum");   // valid
        g2.assignCargo("Petroleum");   // invalid -> exception handled

        System.out.println("\nProgram continues safely after handling exception.");
    }
}