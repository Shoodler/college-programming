import java.util.InputMismatchException;
import java.util.Scanner;

public class VectorMain {

    // Function to input a vector
    public static VectorOps inputVector(Scanner sc, String vectorName) {
        VectorOps vector = new VectorOps();

        int n = 0;
        while (true) {
            try {
                System.out.print("Enter number of elements for " + vectorName + ": ");
                n = sc.nextInt();
                
                if (n <= 0) {
                    throw new IllegalArgumentException("Size must be positive.");
                }
                break;
            } 
            catch (InputMismatchException ime) {
                System.out.println("Invalid input! Please enter an integer.");
                sc.next(); // clear wrong input
            } 
            catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }

        System.out.println("Enter elements of " + vectorName + ":");
        for (int i = 0; i < n; i++) {
            while (true) {
                try {
                    double value = sc.nextDouble();
                    vector.addElement(value);
                    break;
                } 
                catch (InputMismatchException ime) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    sc.next(); // clear wrong input
                }
            }
        }

        return vector;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input vectors separately
        VectorOps v1 = inputVector(sc, "Vector 1");
        VectorOps v2 = inputVector(sc, "Vector 2");

        // Try vector operations separately
        try {
            VectorOps sum = v1.add(v2);
            System.out.print("Addition Result: ");
            sum.display();
        } 
        catch (VectorException ve) {
            System.out.println("Addition Error: " + ve.getMessage());
        }

        try {
            VectorOps difference = v1.diff(v2);
            System.out.print("Subtraction Result: ");
            difference.display();
        } 
        catch (VectorException ve) {
            System.out.println("Subtraction Error: " + ve.getMessage());
        }

        try {
            double dotProduct = v1.dot(v2);
            System.out.println("Dot Product: " + dotProduct);
        } 
        catch (VectorException ve) {
            System.out.println("Dot Product Error: " + ve.getMessage());
        }

        sc.close();
    }
}