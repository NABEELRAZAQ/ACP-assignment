package payrollsystem;

public class ContractEmployee extends Employee implements Payable {
    private int contractDuration;
    
    public ContractEmployee(String empId,String name,double basicSalary,int contractDuration){
        super(empId,name,basicSalary);
        this.contractDuration=contractDuration;
    }
    public int getContractDuration(){
        return contractDuration;
    }
    public void setContractDuration(int contractDuration){
        this.contractDuration=contractDuration;
    }
    
    @Override
    public double calculateTax(){
        return 0.05 * getBasicSalary();
    }
    @Override
    public double calculateNetSalary(){
        return getBasicSalary() - calculateTax();
    }
    
    @Override
    public void generatePaySlip(){
        System.out.println("\n---- PaySlip Contract Employee--------");
        System.out.println("ID:" + getEmpId());
        System.out.println("nAME:" + getName());
        System.out.println("Basic Salary:" + getBasicSalary());
        System.out.println("Contract Duration:" +  contractDuration + " months");
        System.out.println("Tax:" + calculateTax());
        System.out.println("Net Salary:" + calculateNetSalary());
        System.out.println("---------------------------");
    
}
}