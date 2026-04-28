import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class RestaurantFXApp extends Application {

    static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
    static final String USER = "root";
    static final String PASS = "Mothersister_101";

    TableView<String> table = new TableView<>();
    TextArea output = new TextArea();

    // ───────────────────────── DB Connection ─────────────────────────
    Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ───────────────────────── UI Start ─────────────────────────
    @Override
    public void start(Stage stage) {

        Button viewRestaurants = new Button("View Restaurants");
        Button viewMenu = new Button("View Menu Items");
        Button insertRestaurant = new Button("Insert Restaurant");
        Button insertMenu = new Button("Insert Menu Item");
        Button updateMenu = new Button("Update Price");
        Button deleteMenu = new Button("Delete Menu Item");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        HBox menu = new HBox(10,
                viewRestaurants,
                viewMenu,
                insertRestaurant,
                insertMenu,
                updateMenu,
                deleteMenu
        );

        root.getChildren().addAll(menu, output);

        // ───────── BUTTON ACTIONS ─────────

        viewRestaurants.setOnAction(e -> showRestaurants());
        viewMenu.setOnAction(e -> showMenu());

        insertRestaurant.setOnAction(e -> insertRestaurantUI());
        insertMenu.setOnAction(e -> insertMenuUI());

        updateMenu.setOnAction(e -> updateMenuUI());
        deleteMenu.setOnAction(e -> deleteMenuUI());

        stage.setScene(new Scene(root, 900, 500));
        stage.setTitle("Restaurant JDBC JavaFX App");
        stage.show();
    }

    // ───────────────────────── VIEW RESTAURANTS ─────────────────────────
    void showRestaurants() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Restaurant")) {

            output.clear();
            output.appendText("ID | Name | Address\n");

            while (rs.next()) {
                output.appendText(
                        rs.getInt(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getString(3) + "\n"
                );
            }

        } catch (Exception ex) {
            output.setText(ex.getMessage());
        }
    }

    // ───────────────────────── VIEW MENU ─────────────────────────
    void showMenu() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM MenuItem")) {

            output.clear();
            output.appendText("ID | Name | Price | ResId\n");

            while (rs.next()) {
                output.appendText(
                        rs.getInt(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getInt(3) + " | " +
                        rs.getInt(4) + "\n"
                );
            }

        } catch (Exception ex) {
            output.setText(ex.getMessage());
        }
    }

    // ───────────────────────── INSERT RESTAURANT ─────────────────────────
    void insertRestaurantUI() {
        TextInputDialog id = new TextInputDialog();
        id.setHeaderText("Restaurant ID");

        TextInputDialog name = new TextInputDialog();
        name.setHeaderText("Restaurant Name");

        TextInputDialog addr = new TextInputDialog();
        addr.setHeaderText("Address");

        id.showAndWait().ifPresent(i -> {
            name.showAndWait().ifPresent(n -> {
                addr.showAndWait().ifPresent(a -> {

                    try (Connection con = getConnection();
                         PreparedStatement ps = con.prepareStatement(
                                 "INSERT INTO Restaurant VALUES (?, ?, ?)")) {

                        ps.setInt(1, Integer.parseInt(i));
                        ps.setString(2, n);
                        ps.setString(3, a);
                        ps.executeUpdate();

                        output.setText("Restaurant Inserted");

                    } catch (Exception ex) {
                        output.setText(ex.getMessage());
                    }
                });
            });
        });
    }

    // ───────────────────────── INSERT MENU ITEM ─────────────────────────
    void insertMenuUI() {
        try {
            TextInputDialog id = new TextInputDialog();
            id.setHeaderText("Menu ID");

            TextInputDialog name = new TextInputDialog();
            name.setHeaderText("Item Name");

            TextInputDialog price = new TextInputDialog();
            price.setHeaderText("Price");

            TextInputDialog rid = new TextInputDialog();
            rid.setHeaderText("Restaurant ID");

            id.showAndWait().ifPresent(i ->
                    name.showAndWait().ifPresent(n ->
                            price.showAndWait().ifPresent(p ->
                                    rid.showAndWait().ifPresent(r -> {

                                        try (Connection con = getConnection();
                                             PreparedStatement ps = con.prepareStatement(
                                                     "INSERT INTO MenuItem VALUES (?, ?, ?, ?)")) {

                                            ps.setInt(1, Integer.parseInt(i));
                                            ps.setString(2, n);
                                            ps.setInt(3, Integer.parseInt(p));
                                            ps.setInt(4, Integer.parseInt(r));

                                            ps.executeUpdate();
                                            output.setText("Menu Item Inserted");

                                        } catch (Exception ex) {
                                            output.setText(ex.getMessage());
                                        }

                                    }))));
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }

    // ───────────────────────── UPDATE MENU PRICE ─────────────────────────
    void updateMenuUI() {
        TextInputDialog id = new TextInputDialog();
        id.setHeaderText("Menu ID to update");

        TextInputDialog price = new TextInputDialog();
        price.setHeaderText("New Price");

        id.showAndWait().ifPresent(i ->
                price.showAndWait().ifPresent(p -> {

                    try (Connection con = getConnection();
                         PreparedStatement ps = con.prepareStatement(
                                 "UPDATE MenuItem SET Price=? WHERE Id=?")) {

                        ps.setInt(1, Integer.parseInt(p));
                        ps.setInt(2, Integer.parseInt(i));

                        int rows = ps.executeUpdate();
                        output.setText(rows + " row(s) updated");

                    } catch (Exception ex) {
                        output.setText(ex.getMessage());
                    }

                }));
    }

    // ───────────────────────── DELETE MENU ITEM ─────────────────────────
    void deleteMenuUI() {
        TextInputDialog id = new TextInputDialog();
        id.setHeaderText("Menu ID to delete");

        id.showAndWait().ifPresent(i -> {
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "DELETE FROM MenuItem WHERE Id=?")) {

                ps.setInt(1, Integer.parseInt(i));

                int rows = ps.executeUpdate();
                output.setText(rows + " row(s) deleted");

            } catch (Exception ex) {
                output.setText(ex.getMessage());
            }
        });
    }

    // ───────────────────────── MAIN ─────────────────────────
    public static void main(String[] args) {
        launch(args);
    }
}