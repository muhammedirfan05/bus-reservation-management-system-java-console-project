package View;
import java.util.*;
import Controller.*;
public class UserView 
{
    static Scanner sc=new Scanner(System.in);
    static UserController uc=new UserController();
    // sc.nextLine();
    public static void loginPage()
    {
        // sc.nextLine();
        System.out.println("------------------------------------------");
        System.out.print("Enter your Email    : ");
        String email=sc.next();
        System.out.print("Enter your password : ");
        String pass=sc.next();
        // sc.nextLine();
        
        try
        {
            if(uc.validateUser(email,pass)==true)
            {
                uc.chooseMenu(email);
            }
        }
        catch (Exception e)
        {
            System.out.println("--- Enter valid Details1---");
        }
    }
    public static void signin()
    {
        System.out.println("------------------------------------------");
        System.out.print("Enter a Email    : ");
        String email=sc.next();
        System.out.print("Enter a password : ");
        String pass=sc.next();
        try
        {
            if(UserController.addUser(email,pass,"customer"))
                System.out.println("Your Data has been stored...you can LogIn");
            else
                System.out.println("LogIn Error");
        }
        catch(Exception e)
        {
            System.out.println("LogIn Error");
            // System.out.println("");
        }
    }
}
