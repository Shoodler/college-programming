public class Book{
    private static int id_count = 0;
    private String title;
    private String author;
    private String genre;
    private int id;
    private double price;

    Book(){
        title = "";
        author = "";
        genre = "";
        id_count++;
        id = id_count;
    }

    Book(String title){
        this();
        this.title = title;
    }

    Book(String title, String author){
        this(title);
        this.author = author;
    }

    Book(String title, double price){
        this(title);
        this.price = price;
    }

    Book(String title, String author, String genre, double price){
        this();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    Book(Book copier){
        this.title = copier.title;
        this.author = copier.author;
        this.genre = copier.genre;
        this.id = copier.id;
        this.price = copier.price;
    }

    // Getter methods
    public void displayAll(){
        System.out.println("title: " + title);
        System.out.println("author: " + author);
        System.out.println("genre: " + genre);
        System.out.println("id: " + id);
        System.out.println("price: " + price);
    }
}