package payrollsystem;
public class PermanentEmployee extends Employee implements Payable{
    private double bonus;
    
    public PermanentEmployee(String empId,String name,double basicSalary,double bonus){
        super(empId,name,basicSalary);
        this.bonus=bonus;
    }
    
    public double getBonus(){
        return bonus;
    }
    public void setBonus(double bonus){
        this.bonus=bonus;
    }
    
    @Override
    public double calculateTax(){
        return 0.10 * (getBasicSalary() + bonus);
    }
    
    @Override
    public double calculateNetSalary(){
        return (getBasicSalary() + bonus) - calculateTax();
    }
    
    @Override
    public void generatePaySlip(){
        System.out.println("\n-------------PaySlip (Permanent Employee) ---");
        System.out.println("ID: " + getEmpId());
         System.out.println("Name: " + getName());
          System.out.println("Basic Salary: " + getBasicSalary());
           System.out.println("Bonus: " + bonus);
            System.out.println("Tax: " + calculateTax());
             System.out.println("Net Salary: " + calculateNetSalary());
             System.out.println("-----------------------------------------");
    }
}