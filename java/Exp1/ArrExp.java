import java.util.Scanner;

class ArrExp{
    public static void main(String[] args) {
        int[] newArr;
        System.err.println("Elements in your array are: ");
        newArr = new int[]{20,2,1,5,20};
        for (int e : newArr){
            System.err.println(e);
        }
        System.err.println("___________________________\n");

        String[] months = new String[] {"Jan", "Feb","Mar","Apr","May"};
        Scanner read = new Scanner(System.in);
        System.err.println("Enter your month");
        String month = read.nextLine();

        boolean valid = false;
        for(String m : months){
            if (m.equals(month)){
                valid = true;
                System.err.println("You entered the right month");
            }
        }
        if(!valid){
            System.err.println("You entered the wrong month");
        }
        read.close();
    }
}