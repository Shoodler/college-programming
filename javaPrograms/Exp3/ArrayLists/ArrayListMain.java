import java.util.*;

public class ArrayListMain{
    public static void main(String[] args) {
        ArrayList<Integer> arr_lis = new ArrayList<>();
        arr_lis.add(3);
        arr_lis.add(22);
        arr_lis.add(54);
        arr_lis.add(9);

        arr_lis.remove(4);

        for(Integer i : arr_lis){
            System.err.println(i);
        }        

        ArrayList<Integer> arr_lis_2 = new ArrayList<>();
        arr_lis.add(43);
        arr_lis.add(62);
        arr_lis.add(6);
        
        arr_lis.addAll(arr_lis_2);
        arr_lis.forEach(i -> System.err.println(i));

        ArrayList<String> arr_lis_s = new ArrayList<>();
        arr_lis_s.add("Hello World");
        arr_lis_s.add("Fishy Business");
        arr_lis_s.add("GoodLord");
        arr_lis_s.add("");

        arr_lis_s.forEach(w->{
            System.err.println(w);
        });
    }
}