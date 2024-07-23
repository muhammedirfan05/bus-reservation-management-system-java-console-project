package View;
import java.util.*;

// import Controller.AdminController;
// import Controller.AdminController;
// import Controller.BusController;
import Controller.CustomerController;
import Model.Customer;
// import Model.Employee;
// import Model.Employee;
public class EmployeeView 
{
    static Scanner sc=new Scanner(System.in);
    public static void menu() throws Exception
    {
        boolean b=true;
        do
        {
            System.out.println("---------------------------------------");
            System.out.println(" 1.Show List Of Customers of Buses \n 2.Show Customer Details \n 3.Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter service : ");
            int a = sc.nextInt();
            switch(a)
            {
                case 1:
                    System.out.print("Enter your Bus id : ");
                    int id=sc.nextInt();
                    List<Customer> List = CustomerController.getCustomersByBusId(id);
                    if (!List.isEmpty()) 
                    {
                        System.out.println("---------------------------------------");
                        System.out.println("        List of Employees:");
                        System.out.println("---------------------------------------");
                        for (Customer ve : List)
                            System.out.println(ve);
                    } 
                    else
                        System.out.println("No Customer found.");
                    break;
                case 2:
                    System.out.print("Enter Customer id : ");
                    int id1=sc.nextInt();
                    try
                    {
                        Customer emp = CustomerController.searchCustomer(id1);
                        System.out.println("---------------------------------------");
                        if (emp != null)
                        {
                            System.out.println("Customer Found: ");
                            System.out.println(emp);
                        } 
                        else
                            System.out.println("No matching Customer found.");
                    }
                    catch(Exception e)
                    {
                        System.out.println("No matching record found");
                    }

                    break;
                case 3:
                    System.out.println("---------------------------------------");
                    System.out.println("  Thanks for using Theatre Services");
                    System.out.println("---------------------------------------");
                    b=false;
                    break;
            }
        }
        while(b);
    } 
}
