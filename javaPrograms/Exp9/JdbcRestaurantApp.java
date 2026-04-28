import java.sql.*;
import java.util.Scanner;

/**
 * JDBC Restaurant Database Application
 * Tables:
 *   Restaurant(Id INT, Name VARCHAR, Address VARCHAR)
 *   MenuItem(Id INT, Name VARCHAR, Price INT, ResId INT)
 *
 * Prerequisites:
 *   - MySQL running
 *   - database: restaurant_db
 *   - mysql-connector-j in classpath
 */

public class JdbcRestaurantApp {

    static final String URL  =
            "jdbc:mysql://localhost:3306/restaurant_db?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "Mothersister_101"; // change if needed

    // ─────────────────────────────────────────────
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ─────────────────────────────────────────────
    static void createTables() {

        String restaurantTable =
                "CREATE TABLE IF NOT EXISTS Restaurant (" +
                "Id INT PRIMARY KEY, " +
                "Name VARCHAR(50), " +
                "Address VARCHAR(100))";

        String menuTable =
                "CREATE TABLE IF NOT EXISTS MenuItem (" +
                "Id INT PRIMARY KEY, " +
                "Name VARCHAR(50), " +
                "Price INT, " +
                "ResId INT)";

        try (Connection con = getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(restaurantTable);
            st.executeUpdate(menuTable);

            System.out.println("Tables ready.");

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void insertRestaurant(int id, String name, String addr) {
        String sql = "INSERT INTO Restaurant VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, addr);

            ps.executeUpdate();
            System.out.println("Restaurant inserted.");

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void insertMenuItem(int id, String name, int price, int resId) {
        String sql = "INSERT INTO MenuItem VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setInt(4, resId);

            ps.executeUpdate();
            System.out.println("Menu item inserted.");

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void viewRestaurants() {
        String sql = "SELECT * FROM Restaurant ORDER BY Id";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n+----+----------------+-------------------+");
            System.out.println("| ID | Name           | Address           |");
            System.out.println("+----+----------------+-------------------+");

            boolean any = false;

            while (rs.next()) {
                System.out.printf("| %-2d | %-14s | %-17s |%n",
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Address"));
                any = true;
            }

            if (!any) System.out.println("No restaurants found.");

            System.out.println("+----+----------------+-------------------+");

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void viewCheapItems() {
        String sql = "SELECT * FROM MenuItem WHERE Price <= 100";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nCheap Menu Items (<=100)");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("Id") + " | " +
                        rs.getString("Name") + " | " +
                        rs.getInt("Price") + " | " +
                        rs.getInt("ResId")
                );
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void viewCafeJavaItems() {
        String sql =
                "SELECT m.* FROM MenuItem m " +
                "JOIN Restaurant r ON m.ResId = r.Id " +
                "WHERE r.Name = 'Cafe Java'";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nCafe Java Items:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("Id") + " | " +
                        rs.getString("Name") + " | " +
                        rs.getInt("Price")
                );
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void updatePrices() {
        String sql = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";

        try (Connection con = getConnection();
             Statement st = con.createStatement()) {

            int rows = st.executeUpdate(sql);
            System.out.println(rows + " rows updated.");

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    static void deleteItems() {
        String sql = "DELETE FROM MenuItem WHERE Name LIKE 'P%'";

        try (Connection con = getConnection();
             Statement st = con.createStatement()) {

            int rows = st.executeUpdate(sql);
            System.out.println(rows + " rows deleted.");

        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    public static void main(String[] args) {

        createTables();

        Scanner sc = new Scanner(System.in);

        System.out.println("=== JDBC Restaurant Database ===");

        while (true) {

            System.out.println("\n1.Insert Restaurant");
            System.out.println("2.Insert Menu Item");
            System.out.println("3.View Restaurants");
            System.out.println("4.View Cheap Items");
            System.out.println("5.View Cafe Java Items");
            System.out.println("6.Update Prices");
            System.out.println("7.Delete Items starting with P");
            System.out.println("0.Exit");

            System.out.print("Choice: ");

            int ch;
            try {
                ch = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            try {
                switch (ch) {

                    case 1:
                        System.out.print("ID: ");
                        int rid = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Name: ");
                        String rname = sc.nextLine().trim();

                        System.out.print("Address: ");
                        String addr = sc.nextLine().trim();

                        insertRestaurant(rid, rname, addr);
                        break;

                    case 2:
                        System.out.print("ID: ");
                        int mid = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Name: ");
                        String mname = sc.nextLine().trim();

                        System.out.print("Price: ");
                        int price = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Restaurant ID: ");
                        int resId = Integer.parseInt(sc.nextLine().trim());

                        insertMenuItem(mid, mname, price, resId);
                        break;

                    case 3:
                        viewRestaurants();
                        break;

                    case 4:
                        viewCheapItems();
                        break;

                    case 5:
                        viewCafeJavaItems();
                        break;

                    case 6:
                        updatePrices();
                        break;

                    case 7:
                        deleteItems();
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid number input.");
            }
        }
    }
}