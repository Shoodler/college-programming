import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager manager = InventoryManager.getInstance();

        int choice;

        do {
            System.out.println("\n===== Inventory Menu =====");
            System.out.println("1. Add New Product");
            System.out.println("2. Add Legacy Product");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter product name: ");
                        String name = sc.nextLine();
                        manager.addProduct(new NewProduct(name));
                        System.out.println("New Product Added!");
                        break;

                    case 2:
                        System.out.print("Enter item ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter description: ");
                        String desc = sc.nextLine();

                        LegacyItem legacy = new LegacyItem(id, desc);
                        manager.addProduct(new ProductAdapter(legacy));
                        System.out.println("Legacy Product Added!");
                        break;

                    case 3:
                        System.out.println("\n--- Inventory List ---");
                        Iterator<Product> iterator = manager.returnInventory();

                        if (!iterator.hasNext()) {
                            System.out.println("Inventory is empty.");
                        } else {
                            while (iterator.hasNext()) {
                                Product p = iterator.next();
                                p.displayDetails();
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InventoryException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 4);

        sc.close();
    }
} 