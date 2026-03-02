import java.util.*;

public class BookMain{
    private static ArrayList<Book> library = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);    

    public static void main(String[] args) {        
        
        System.out.println("Enter number of inputs: ");
        int n = scan.nextInt();
        initArray(n);

        for(Book b : library){
            b.displayAll();
        }
    }

    private static void initArray(int n){
        for(int i = 0; i < n; i++){
            library.add(createObj());
        }
    }
    
    private static Book createObj(){
        Book temp;

        System.out.println("Enter Title: ");
        String title = scan.nextLine();

        System.out.println("Enter Author Name: ");
        String author = scan.nextLine();
        
        System.out.println("Enter Genre: ");
        String genre = scan.nextLine();
        
        System.out.println("Enter Price: ");
        double price = scan.nextDouble();

        temp = new Book(title, author, genre, price);
        return temp;
    }
}