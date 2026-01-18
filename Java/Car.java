import java.lang.reflect.Field;

public class Car {
    // Attributes
    public String model;
    public String color;
    public int seats;
    public double price;
    public boolean auto;
    public String variant;
    public int curSpeed = 1;

    // Methods
    public void start() {
        System.out.println("Damn! This ride is nice");
    }

    public void accelerate(int inc) {
        curSpeed += inc;
        System.out.println("Speed is now " + curSpeed);
    }

    public void stop() {
        System.out.println("You've arrived");
        curSpeed = 1;
    }

    public void displayAllAttributes() {
        System.out.println("\n--- Car Attribute Report ---");
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            try {
                System.out.println(field.getName() + ": " + field.get(this));
            } catch (IllegalAccessException e) {
                System.err.println("Could not access field: " + field.getName());
            }
        }
        System.out.println("----------------------------\n");
    }

    public static void main(String[] args) {
        // Initialization
        Car car1 = new Car();
        car1.model = "Y26-11B2";
        car1.color = "red";
        car1.seats = 5;
        car1.price = 4560000.00;
        car1.auto = true;
        car1.variant = "Luxury";
        car1.curSpeed = 2;

        // Logic Execution
        car1.start();
        car1.displayAllAttributes();
        car1.accelerate(20);
        car1.stop();
    }
}