package View;

import java.util.*;
// import View.BusView;
import Controller.AdminController;
import Controller.UserController;
import Model.*;

public class AdminView 
{
    static Scanner sc=new Scanner(System.in);
    public static void menu()
    {
        boolean b=true;
        do
        {
            System.out.println("---------------------------------------");
            System.out.println(" 1.View List of all Employee \n 2.View List of all Users \n 3.Search a Employee with their ID \n 4.Add Employee \n 5.Remove Employee \n 6.Modify Bus \n 7.Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter service : ");
            int a = sc.nextInt();
            // sc.nextLine();
            switch(a)
            {
                case 1:
                    List<Employee> employeesList = AdminController.viewAllEmployees();;
                    if (!employeesList.isEmpty()) 
                    {
                        System.out.println("---------------------------------------");
                        System.out.println("        List of Employees:");
                        System.out.println("---------------------------------------");
                        for (Employee ve : employeesList)
                            System.out.println(ve);
                    } 
                    else
                        System.out.println("No Employees found.");
                    break;
                case 2:
                    List<User> List = UserController.viewAllUsers();;
                    if (!List.isEmpty()) 
                    {
                        System.out.println("---------------------------------------");
                        System.out.println("        List of Users:");
                        System.out.println("---------------------------------------");
                        for (User ve : List)
                            System.out.println(ve);
                    } 
                    else
                        System.out.println("No Users found.");
                    break;
                case 3:
                    System.out.print("Enter the employee ID: ");
                    int id =sc.nextInt();
                    try
                    {
                        Employee emp = AdminController.searchEmployee(id);
                        System.out.println("---------------------------------------");
                        if (emp != null)
                        {
                            System.out.println("Employee Found: ");
                            System.out.println(emp);
                        } 
                        else
                            System.out.println("No matching Employee found.");
                    }
                    catch(Exception e)
                    {
                        System.out.println("No matching record found");
                    }
                    break;
                case 4:
                    System.out.print("Enter email: ");
                    String email = sc.next();
                    System.out.print("Enter password: ");
                    String password = sc.next();
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter Designation[Driver/Conduction]: ");
                    String designation = sc.next();
                    System.out.print("Age : ");
                    int age = sc.nextInt();
                    try
                    {
                        boolean uc=UserController.addUser(email, password,"employee");
                        boolean bt=AdminController.addEmployee(email,name,age,designation);
                        if(uc&&bt)  System.out.println("A new employee has been inserted into the database.");
                        else    
                        System.out.println("Failed to insert employee into the database.");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Failed to insert employee into the database.");
                    }
                    break;
                case 5:
                    System.out.print("Enter the employee ID to delete: ");
                    int idToDelete = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter the employee email to delete: ");
                    String email1 = sc.next();
                    try
                    {
                        boolean ad=AdminController.removeEmployee(idToDelete);
                        boolean uc=UserController.removeUser(email1);
                        if(ad&&uc)  System.out.println("employee has been removed from the database.");
                        else    
                        System.out.println("Failed to remove employee from the database.");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Failed to remove employee from the database.");
                    }
                    break;
                case 6:
                    System.out.println("---------------------------------------");
                    BusView.modifyBus();
                    // break;
                case 7:
                    System.out.println("---------------------------------------");
                    System.out.println("  Thanks for using Our Bus Services");
                    System.out.println("---------------------------------------");
                    b=false;
                    break;
            }
        }
        while(b);
    }
}
