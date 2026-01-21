public class Vehicle{
    public static void main(String[] args) {

        //Create and test Object
        VehicleMethods v = new VehicleMethods(); 

        v.brandName = "Toyota";
        v.modelName = "Corolla";
        v.color = "Blackslate";
        v.yearOfMfg = java.time.Year.of(2022);
        v.noOfSeats = 5;
        v.weight = 1300.5;
        v.fuelCapacity = 45.0;
        v.fuelType = 'P';
        v.price = 1200000;

        // Display vehicle details
        v.displayDetails();

        // Execute methods
        v.drive();
        v.stop();

        float mileage = v.calcMilage(450.0f, 30.0f);
        System.out.println("Mileage: " + mileage + " km/l");


        VehicleMethods v1 = new VehicleMethods(); 

        v1.brandName = "Honda";
        v1.modelName = "Civic";
        v1.color = "Bleu";
        v1.yearOfMfg = java.time.Year.of(2024);
        v1.noOfSeats = 5;
        v1.weight = 1200.0;
        v1.fuelCapacity = 40.0;
        v1.fuelType = 'P';
        v1.price = 2000000;

        // Display vehicle details
        v1.displayDetails();

        // Execute methods
        v1.drive();
        v1.stop();

        float mileage1 = v1.calcMilage(450.0f, 25.0f);
        System.out.println("Mileage: " + mileage1 + " km/l");
    }
}

class VehicleSpecs{
    // Identification
    public String brandName, modelName, color;

    // Manufacturing
    public java.time.Year yearOfMfg;

    // Capacity
    public long noOfSeats;

    // Physical attributes
    public double weight, fuelCapacity;
    public char fuelType; //P - petrol, D - diesel, E - electric, M - manPower, F - food

    // Financial
    public double price;

    void displayDetails() {
        System.err.println("");
        System.out.println("----- Vehicle Details -----");
        System.out.println("Brand Name      : " + brandName);
        System.out.println("Model Name      : " + modelName);
        System.out.println("Color           : " + color);
        System.out.println("Year of Mfg     : " + yearOfMfg);
        System.out.println("No. of Seats    : " + noOfSeats);
        System.out.println("Weight          : " + weight + " kg");
        System.out.println("Fuel Capacity   : " + fuelCapacity);
        System.out.println("Fuel Type       : " + getFuelType());
        System.out.println("Price           : Rs" + price);
        System.out.println("----------------------------");
    }

    private String getFuelType() {
    switch (fuelType) {
        case 'P':
            return "Petrol";
        case 'D':
            return "Diesel";
        case 'E':
            return "Electric";
        case 'M':
            return "Man Power";
        case 'F':
            return "Food";
        default:
            return "Unknown";
    }
}

}

class VehicleMethods extends VehicleSpecs{
    int speed = 0;

    static void start(){
        System.out.println("Vehicle started using the start button");
    }

    void stop(){
        System.out.println("Vehicle stopped using killSwitch");
        System.out.println("Vehicle speed is now 0");
    }

    int accelerate(int initSP){
        System.err.println("Your speed is now " + (initSP+10));
        return (initSP+10);
    }

    void drive(){
        System.err.println("Lets go!");
        start();    
        speed = accelerate(speed);
    }

    float calcMilage(float dist, float fuelAmt){
        if (fuelAmt <=0 ) return 0;
        return dist/fuelAmt;
    }
}