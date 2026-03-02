import java.util.Scanner;

public class VectorMain {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            VectorOps v1 = new VectorOps();
            VectorOps v2 = new VectorOps();

            System.out.print("Enter number of elements for vectors: ");
            int n = sc.nextInt();

            System.out.println("Enter elements of first vector:");
            for (int i = 0; i < n; i++) {
                v1.addElement(sc.nextDouble());
            }

            System.out.println("Enter elements of second vector:");
            for (int i = 0; i < n; i++) {
                v2.addElement(sc.nextDouble());
            }

            VectorOps sum = v1.add(v2);
            VectorOps difference = v1.diff(v2);
            double dotProduct = v1.dot(v2);

            System.out.print("Addition Result: ");
            sum.display();

            System.out.print("Subtraction Result: ");
            difference.display();

            System.out.println("Dot Product: " + dotProduct);

        } 
        catch (VectorException ve) {
            System.out.println("Vector Error: " + ve.getMessage());  
        } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}