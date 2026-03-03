import java.util.*;

public class BookMain{
    private static ArrayList<Book> library = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);    

    public static void main(String[] args) {        
        
        System.out.println("Enter number of inputs: ");
    int n = scan.nextInt();
    scan.nextLine();

    if(n <= 0){
        System.out.println("Number of books must be positive.");
        return;
    }       

        initArray(n);

        System.out.println("\n--- All Books ---");
        for(Book b : library){
            b.displayAll();
        }

        calculateAverage();

        System.out.println("\n--- Fiction Books ---");
        library.forEach(book -> {
            if(book.getGenre().equalsIgnoreCase("Fiction")){
                book.displayAll();
            }
        });
    }

    private static void initArray(int n){
        for(int i = 0; i < n; i++){
            library.add(createObj());
        }
    }
    
    private static Book createObj(){
        Book temp = null;

        System.out.println("Enter Title: ");
        String title = scan.nextLine();

        System.out.println("Enter Author Name: ");
        String author = scan.nextLine();
        
        System.out.println("Enter Genre: ");
        String genre = scan.nextLine();
        
        System.out.println("Enter Price: ");
        double price = scan.nextDouble();
        scan.nextLine(); // consume newline

        try{
            temp = new Book(title, author, genre, price);
        }
        catch(InvalidPriceException e){
            System.out.println("Error: " + e.getMessage());
            System.out.println("Setting price to 0.");
            try {
                temp = new Book(title, author, genre, 0);
            } catch (InvalidPriceException ex) {
                ex.printStackTrace();
            }
        }

        return temp;
    }

    private static void calculateAverage(){
        double sum = 0;

        for(Book b : library){
            sum += b.getPrice();
        }

        double avg = (library.size() > 0) ? sum / library.size() : 0;

        System.out.println("\nAverage Price of Books: " + avg);
    }
}