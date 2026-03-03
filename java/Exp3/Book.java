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

    Book(String title, double price) throws InvalidPriceException {
        this(title);
        if(price < 0){
            throw new InvalidPriceException("Price cannot be negative!");
        }
        this.price = price;
    }

    Book(String title, String author, String genre, double price) throws InvalidPriceException {
        this();
        if(price < 0){
            throw new InvalidPriceException("Price cannot be negative!");
        }
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

    public double getPrice(){
        return price;
    }

    public String getGenre(){
        return genre;
    }

    public void displayAll(){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("ID: " + id);
        System.out.println("Price: " + price);
        System.out.println("----------------------");
    }
}