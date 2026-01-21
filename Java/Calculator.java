import java.util.Scanner;

public class Calculator{
    public int n1, n2;

    public Calculator (){
        this.n1 = 0;
        this.n2 = 0;
    }
    public Calculator(int x, int y){
        this.n1 = x; 
        this.n2 = y;
    }
    
    public long addNums(int x, int y){
        return x + y;
    }

    public long subNums(int x, int y){
        return x - y;
    }
    
    public long prodNums(int x, int y){
        return x * y;
    }
    
    public double divNums(int x, int y){
        if (y == 0) {
        throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double)x/ y;
    }

    public long modNums(int x, int y){
        return x % y;
    }

    public void displayNums(int x, int y){
        System.out.println("First num: " + x + "Second num: " + y + "\n");
    }

    public void enterNums (int x, int y){
        this.n1 = x;
        this.n2 = y;
    }

    public void displayMenu(){
        String menu = "Calculator Menu \n" +
                    "1. Add\n" +
                    "2. Subtract\n" +
                    "3. Multiply\n" +
                    "4. Divide\n" +
                    "5. Modulus\n" +
                    "6. Exit\n" +
                    "Enter your choice: ";
        System.out.print(menu);
    }


    public static void main(String[] args){
        Calculator user = new Calculator();
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to calculator\nEnter your numbers:");
        int x = scan.nextInt(); int y = scan.nextInt();
        user.enterNums(x,y);

        boolean exit = false;
        while(!exit){
            user.displayMenu();
            int choice = scan.nextInt();
            System.out.flush();


            switch (choice) {
                case 1:
                    System.out.println("Sum: " + user.addNums(user.n1, user.n2));
                    break;

                case 2:
                    System.out.println("Diff: " + user.subNums(user.n1, user.n2));
                    break;

                case 3:
                    System.out.println("Product: " + user.prodNums(user.n1, user.n2));
                    break;

                case 4:
                    System.out.println("Quotient: " + user.divNums(user.n1, user.n2));
                    break;

                case 5:
                    System.out.println("Remainder: " + user.modNums(user.n1, user.n2));
                    break;

                case 6:
                    System.out.println("Thank you for using Calculator!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }

        }
        scan.close();
    }
}