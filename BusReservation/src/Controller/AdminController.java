package Controller;
// import View.*;
import java.util.List;

import Model.*;

public class AdminController 
{
    public static List<Employee> viewAllEmployees()
    {
        List<Employee> st =EmployeeDb.viewAllEmployee();
        return st;
    }
    public static Employee searchEmployee(int id) throws Exception
    {
        try
        {
            Employee em=EmployeeDb.searchEmployee(id);
            return em;
        }
        catch(Exception e)
        {
            throw new Exception("Error during searching");
        }
    } 
    public static boolean removeEmployee(int id) throws Exception
    {
        boolean b=EmployeeDb.removeEmployee(id);
        return b;
    }
    public static boolean addEmployee(String email,String name,int age,String designation) throws Exception
    {
        boolean b=EmployeeDb.addEmployee(email,name,age,designation);
        return b;
    }
}
