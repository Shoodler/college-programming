import java.util.*;

// Product Interface
interface Product {
    void displayDetails();
}

// LegacyItem Class
class LegacyItem {
    private int itemId;
    private String description;

    public LegacyItem(int itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public void print() {
        System.out.println("Legacy Item -> ID: " + itemId + ", Description: " + description);
    }
}

// Adapter Class
class ProductAdapter implements Product {
    private LegacyItem legacyItem;

    public ProductAdapter(LegacyItem legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void displayDetails() {
        legacyItem.print();
    }
}

// New Product Class
class NewProduct implements Product {
    private String name;

    public NewProduct(String name) {
        this.name = name;
    }

    @Override
    public void displayDetails() {
        System.out.println("New Product -> Name: " + name);
    }
}

// Singleton Inventory Manager
class InventoryManager {
    private static InventoryManager instance;
    private List<Product> productList;

    private InventoryManager() {
        productList = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) throws InventoryException {
        if (product == null) {
            throw new InventoryException("Product cannot be null");
        }
        productList.add(product);
    }

    public Iterator<Product> returnInventory() {
        return productList.iterator();
    }
}