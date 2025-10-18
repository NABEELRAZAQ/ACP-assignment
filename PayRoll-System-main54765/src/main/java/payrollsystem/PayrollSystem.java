package payrollsystem;

import java.util.*;

public class PayrollSystem{
    static Scanner input = new Scanner(System.in);
    static ArrayList<Employee> employees = new ArrayList<>();
    
    public static void main(String[] args){
        int choice;
        
            System.out.println("\n========================================== ");
            System.out.println("\n=== Employee Payroll Management System === ");
            System.out.println("\n========================================== ");
        
        do {
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employee");
            System.out.println("3. Search Employee");
            System.out.println("4. Highest Net Salary");
            System.out.println("5. Average Salary");
            System.out.println("6. Generate Payslip");
            System.out.println("7. Exit");
            System.out.println("Choose an option: ");
            choice = input.nextInt();
            
            switch(choice){
                case 1 -> addEmployee();
                case 2 -> viewAllEmployee();
                case 3 -> searchEmployee();
                case 4 -> highestSalary();
                case 5 -> averageSalary();
                case 6 -> generatePaySlip();
                case 7 -> System.out.println("Exiting.. Thank You!");
                default -> System.out.println("Invalid option! Try again");
            }
        } while (choice != 7);
    }
    
    static void addEmployee(){
        if (employees.size() >= 5){
            System.out.println("Cannot add more than 5 employees!");
            return;
        }
        System.out.println("eNTER eMPLOYEE tYPE (pERMANENT/Contract): ");
        String type = input.next();
        System.out.println("Enter ID: ");
        String id = input.next();
        
        for(Employee e: employees){
            if(e.getEmpId().equals(id)){
                System.out.println("Employee ID Already exists");
                return;
            }
        }
        
        System.out.println("Enter Name: ");
        String name = input.next();
        
        System.out.println("Enter Basic Salary: ");
        double salary = input.nextDouble();
        
        if (salary <= 0){
            System.out.println("Salary must be positive");
            return;
        }
        
        if(type.equalsIgnoreCase("Permanent")){
            System.out.println("Enter Bonus: ");
            double bonus = input.nextDouble();
            employees.add(new PermanentEmployee(id,name,salary,bonus));
            System.out.println("Permanent Employee added successfully");
        } else if (type.equalsIgnoreCase("Contract")){
            System.out.println("Enter Contract Duration (months): ");
            int months = input.nextInt();
            employees.add(new ContractEmployee(id,name,salary,months));
            System.out.println("Contract Employee add successfully");
            
        } else {
            System.out.println("Invalid Employee Type!");
        }
        
    }
    
  static void viewAllEmployee() {
    if (employees.isEmpty()) {
        System.out.println("\nNo Employees Added Yet!");
        return;
    }

    // Header
    System.out.printf("\n%-10s %-12s %-12s %-12s %-12s %-12s %-12s\n",
            "ID", "Name", "Type", "Basic", "Bonus/Dur", "Tax", "Net Salary");
    System.out.println("--------------------------------------------------------------------------");

    // Loop through all employees
    for (Employee e : employees) {
        if (e instanceof PermanentEmployee) {
            PermanentEmployee p = (PermanentEmployee) e;
            System.out.printf("%-10s %-12s %-12s %-12.2f %-12.2f %-12.2f %-12.2f\n",
                    e.getEmpId(), e.getName(), "Permanent", e.getBasicSalary(),
                    p.getBonus(), p.calculateTax(), p.calculateNetSalary());
        } else if (e instanceof ContractEmployee) {
            ContractEmployee c = (ContractEmployee) e;
            System.out.printf("%-10s %-12s %-12s %-12.2f %-12d %-12.2f %-12.2f\n",
                    e.getEmpId(), e.getName(), "Contract", e.getBasicSalary(),
                    c.getContractDuration(), c.calculateTax(), c.calculateNetSalary());
        }
    }

    System.out.println("--------------------------------------------------------------------------");
}

    
    static void searchEmployee(){
        System.out.println("Enter Employee Id to search ");
        String id = input.next();
        
        for(Employee e: employees){
             if (e.getEmpId().equalsIgnoreCase(id)) {
                ((Payable) e).generatePaySlip();
                return;
            }
        }
                System.out.println("Employee not found!");
    }
    
    static void highestSalary(){
          if (employees.isEmpty()) {
            System.out.println("No employees available!");
            return;
        }

        Employee top = employees.get(0);
        double max = ((Payable) top).calculateNetSalary();

        for (Employee e : employees) {
            double net = ((Payable) e).calculateNetSalary();
            if (net > max) {
                max = net;
                top = e;
            }
        }

        System.out.println("\nEmployee with Highest Net Salary:");
        System.out.println("Name : " + top.getName());
        System.out.println("Net  : " + max);
    }
    static void averageSalary(){
        if (employees.isEmpty()) {
            System.out.println("No employees to calculate average!");
            return;
        }

        double total = 0;
        for (Employee e : employees) {
            total += ((Payable) e).calculateNetSalary();
        }

        System.out.println("\nAverage Net Salary: " + (total / employees.size()));
    }
    
    static void generatePaySlip(){
          System.out.print("\nEnter Employee ID to print payslip: ");
        String id = input.next();

        for (Employee e : employees) {
            if (e.getEmpId().equalsIgnoreCase(id)) {
                ((Payable) e).generatePaySlip();
                return;
            }
        }

        System.out.println("Employee not found!");
    }
    
 }