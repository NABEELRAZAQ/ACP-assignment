package payrollsystem;
public abstract class Employee {
    private String empId;
    private String name;
    private double basicSalary;
    
    public Employee(String empId,String name,Double basicSalary){
        this.empId=empId;
        this.name=name;
        this.basicSalary=basicSalary;
    }
    
    public String getEmpId(){
        return empId;
    }
    public void setEmpId(String empId){
        this.empId=empId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){this.name=name;}
    public double getBasicSalary(){
        return basicSalary;
    }
    
    public void setBasicSalary(double basicSalary){
        this.basicSalary = basicSalary;
    }
    public abstract double calculateTax();
    
}