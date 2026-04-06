abstract class Employee{
    final int EmpId;
    String name;
    String PANId;
    String joiningDate;
    String designation;

    Employee(int e, String n, String p, String j, String d){
        EmpId = e;
        name = n;
        PANId = p;
        joiningDate = j;
        designation = d;
    }

    Employee(){
        this(0, "", "", "", "");
    }

    abstract float calculate_CTC();

}

class FullTimeEmployee extends Employee{
    float baseSalary;
    float allowances;
    float hra;
    
    FullTimeEmployee(int e, String n, String d, float b, float a, float h) {
        super(e, n, "", "", d);
        baseSalary = b;
        allowances = a;
        hra = h;
    } 

    float calculate_CTC(){
        return this.baseSalary + this.allowances + this.hra;
    }
}

class Manager extends FullTimeEmployee {
    float bonus;

    Manager(int e, String n, String d, float b, float a, float h, float bonus) {
        super(e, n, d, b, a, h);
        this.bonus = bonus;
    }

    float calculate_CTC() {
        return super.calculate_CTC() + bonus;
    }
}

class Developer extends FullTimeEmployee {
    float overtimePay;

    Developer(int e, String n, String d, float b, float a, float h, float overtimePay) {
        super(e, n, d, b, a, h);
        this.overtimePay = overtimePay;
    }

    float calculate_CTC() {
        return super.calculate_CTC() + overtimePay;
    }
}

class HR extends FullTimeEmployee {
    float incentives;

    HR(int e, String n, String d, float b, float a, float h, float incentives) {
        super(e, n, d, b, a, h);
        this.incentives = incentives;
    }

    float calculate_CTC() {
        return super.calculate_CTC() + incentives;
    }
}

class ContractEmployee extends Employee{    
    float hourlyRate;
    float noOfHours;

    ContractEmployee(int e, String n, String d, float h, float t){
        super(e, n, "", "", d);
        hourlyRate = h;
        noOfHours = t;
    }

    float calculate_CTC(){
        return this.hourlyRate * this.noOfHours;
    }
}