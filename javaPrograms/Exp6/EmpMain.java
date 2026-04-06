import java.util.ArrayList;
import java.util.Scanner;

public class EmpMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Using Polymorphism: A list of Employees can hold any subclass
        ArrayList<Employee> empList = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- EMPLOYEE RECORD SYSTEM ---");
            System.out.println("1. Add Full-Time Employee (Manager/Developer/HR)");
            System.out.println("2. Add Contract Employee");
            System.out.println("3. Display All Employee CTCs");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addFullTimeEmployee(sc, empList);
                    break;
                case 2:
                    addContractEmployee(sc, empList);
                    break;
                case 3:
                    displayEmployees(empList);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Helper method to keep main clean
    private static void addFullTimeEmployee(Scanner sc, ArrayList<Employee> list) {
        System.out.println("Select Role: 1. Manager  2. Developer  3. HR");
        int role = sc.nextInt();
        
        System.out.print("Enter ID: "); int id = sc.nextInt();
        sc.nextLine(); // Clear buffer
        System.out.print("Enter Name: "); String name = sc.nextLine();
        System.out.print("Base Salary: "); float base = sc.nextFloat();
        System.out.print("Allowances: "); float allow = sc.nextFloat();
        System.out.print("HRA: "); float hra = sc.nextFloat();

        if (role == 1) {
            System.out.print("Enter Bonus: "); float bonus = sc.nextFloat();
            list.add(new Manager(id, name, "Manager", base, allow, hra, bonus));
        } else if (role == 2) {
            System.out.print("Enter Overtime: "); float ot = sc.nextFloat();
            list.add(new Developer(id, name, "Developer", base, allow, hra, ot));
        } else if (role == 3) {
            System.out.print("Enter Incentives: "); float inc = sc.nextFloat();
            list.add(new HR(id, name, "HR", base, allow, hra, inc));
        }
        System.out.println("Employee Added Successfully!");
    }

    private static void addContractEmployee(Scanner sc, ArrayList<Employee> list) {
        System.out.print("Enter ID: "); int id = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter Name: "); String name = sc.nextLine();
        System.out.print("Hourly Rate: "); float rate = sc.nextFloat();
        System.out.print("Hours Worked: "); float hours = sc.nextFloat();
        
        list.add(new ContractEmployee(id, name, "Contractor", rate, hours));
        System.out.println("Contract Employee Added!");
    }

    private static void displayEmployees(ArrayList<Employee> list) {
        System.out.println("\n--- EMPLOYEE LIST ---");
        for (Employee e : list) {
            // Polymorphism in action: calls the correct calculate_CTC() for each type
            System.out.println("ID: " + e.EmpId + " | Name: " + e.name + 
                               " | Role: " + e.designation + " | CTC: " + e.calculate_CTC());
        }
    }
}