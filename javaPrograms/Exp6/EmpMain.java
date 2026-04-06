public class EmpMain {
    public static void main(String[] args) {
        Manager m = new Manager(1, "Alice", "Manager", 50000, 10000, 8000, 15000);
        Developer d = new Developer(2, "Bob", "Developer", 40000, 8000, 6000, 5000);
        HR h = new HR(3, "Charlie", "HR", 30000, 5000, 4000, 3000);

        System.out.println("Manager CTC: " + m.calculate_CTC());
        System.out.println("Developer CTC: " + d.calculate_CTC());
        System.out.println("HR CTC: " + h.calculate_CTC());
    }
}