import java.time.Year;

public class VehicleMain {
    public static void main(String[] args) {
        VehicleMethods[] fleet = new VehicleMethods[3];

        fleet[0] = new VehicleMethods("Tesla", "Model S");
        fleet[0].color = "Red";
        fleet[0].price = 8000000.0;

        fleet[1] = new VehicleMethods("Ford", "Mustang", 7500000.0, Year.of(1969));
        fleet[1].color = "Yellow";

        // Use Copy Constructor
        fleet[2] = new VehicleMethods(fleet[1]);
        fleet[2].modelName = "Mustang Mach-E";
        fleet[2].price = 6500000.0;

        // Displaying Records
        displayTable(fleet);
        processVehicle(fleet[0], 100.0f, 10.0f);
    }

    private static void displayTable(VehicleMethods[] vehicles) {

        // Table Header: %-15s means string, 15 chars wide, left-aligned
        System.out.printf("%-15s | %-15s | %-10s | %-6s | %-12s | %-12s%n", 
                          "Brand", "Model", "Color", "Year", "Fuel Type", "Price (INR)");
        System.out.println("-".repeat(95));
        
        for (VehicleMethods v : vehicles) {
            if (v != null) {
                System.out.printf("%-15s | %-15s | %-10s | %-6s | %-12s | %-12.2f%n", 
                                  v.brandName, 
                                  v.modelName, 
                                  v.color, 
                                  v.yearOfMfg, 
                                  getFuelName(v.fuelType), 
                                  v.price);
            }
        }
    }

    private static void processVehicle(VehicleMethods v, float dist, float fuel) {
        v.displayDetails();
        v.drive();
        float mpg = v.calcMilage(dist, fuel);
        System.out.println("Efficiency Check: " + mpg + " km/l");
        v.stop();
        System.out.println();
    }

    private static String getFuelName(char type) {
        return switch (type) {
            case 'P' -> "Petrol";
            case 'D' -> "Diesel";
            case 'E' -> "Electric";
            default -> "Other";
        };
    }
}