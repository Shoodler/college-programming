import java.time.Year;

class VehicleSpecs {
    public String brandName, modelName, color;
    public Year yearOfMfg;
    public long noOfSeats;
    public double weight, fuelCapacity;
    public char fuelType;
    public double price;

    //Default Constructor
    public VehicleSpecs() {
        this.brandName = "Standard";
        this.modelName = "Base";
        this.color = "White";
        this.yearOfMfg = Year.now();
        this.noOfSeats = 4;
        this.weight = 1000.0;
        this.fuelCapacity = 40.0;
        this.fuelType = 'P';
        this.price = 500000.0;
    }

    private String getFuelTypeString() {
        return switch (fuelType) {
            case 'P' -> "Petrol";
            case 'D' -> "Diesel";
            case 'E' -> "Electric";
            case 'M' -> "Man Power";
            case 'F' -> "Food";
            default -> "Unknown";
        };
    }
    
    // Display Function
    void displayDetails() {
        System.out.println("\n----- Vehicle Details -----");
        System.out.println("Brand Name      : " + brandName);
        System.out.println("Model Name      : " + modelName);
        System.out.println("Color           : " + color);
        System.out.println("Year of Mfg     : " + yearOfMfg);
        System.out.println("No. of Seats    : " + noOfSeats);
        System.out.println("Fuel Type       : " + getFuelTypeString());
        System.out.println("Price           : Rs " + price);
        System.out.println("----------------------------");
    }
}

class VehicleMethods extends VehicleSpecs {
    int speed = 0;

    // Parameterised (partial) Constructor
    public VehicleMethods(String brand, String model) {
        super(); //default constructor from super class 😎😎
        this.brandName = brand;
        this.modelName = model;
    }

    public VehicleMethods(String brand, String model, double price, Year year) {
        super();
        this.brandName = brand;
        this.modelName = model;
        this.price = price;
        this.yearOfMfg = year;
    }

    // Copy Constructor
    public VehicleMethods(VehicleMethods src) {
        this.brandName = src.brandName;
        this.modelName = src.modelName;
        this.color = src.color;
        this.yearOfMfg = src.yearOfMfg;
        this.noOfSeats = src.noOfSeats;
        this.weight = src.weight;
        this.fuelCapacity = src.fuelCapacity;
        this.fuelType = src.fuelType;
        this.price = src.price;
        this.speed = src.speed; 
    }
    static void start() {
        System.out.println("Vehicle started using the start button");
    }

    void stop() {
        this.speed = 0;
        System.out.println("Vehicle stopped using killSwitch. Speed is now 0.");
    }

    int accelerate(int initSP) {
        int newSpeed = initSP + 10;
        System.out.println("Your speed is now " + newSpeed);
        return newSpeed;
    }

    void drive() {
        System.out.println("Action: Lets go!");
        start();    
        this.speed = accelerate(this.speed);
    }

    float calcMilage(float dist, float fuelAmt) {
        return (fuelAmt <= 0) ? 0 : dist / fuelAmt;
    }
}