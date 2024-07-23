package Controller;

import java.util.List;

import Model.Customer;
import Model.CustomerDb;
// import Model.Employee;
// import Model.EmployeeDb;

public class CustomerController 
{
    public static List<Customer> getCustomersByBusId(int id) throws Exception
    {
        try
        {
            List<Customer> st =CustomerDb.getCustomersByBusId(id);
            return st;
        }
        catch(Exception e)
        {
            throw new Exception("Error");
        }
    }
     public static Customer searchCustomer(int id) throws Exception
    {
        try
        {
            Customer em=CustomerDb.getCustomerById(id);
            return em;
        }
        catch(Exception e)
        {
            throw new Exception("Error during searching");
        }
    } 

}
