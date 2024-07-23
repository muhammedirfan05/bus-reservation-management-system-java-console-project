package View;
// import java.time.LocalDate;
// import java.time.format.DateTimeParseException;
import java.util.*;
import Controller.*;
import Model.Bus;
// import java.time.LocalDate;
// import java.time.format.DateTimeParseException;
// import java.time.LocalDate;
public class BusView
{
    public static Scanner sc=new Scanner(System.in);
    public static void modifyBus()
    {
        boolean b=true;
        do
        {
            System.out.println("---------------------------------------");
            System.out.println(" 1.Update Bus \n 2.Add Bus \n 3.Remove Bus \n 4.View All Buses \n 5.Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter choice : ");
            int z = Integer.parseInt(sc.nextLine());
            switch (z) 
            {
                case 1:
                    updateBus();
                    break;
                case 2:
                    System.out.print("Enter Starting point : ");
                    String st = sc.next();
                    System.out.print("Enter Destination  : ");
                    String des = sc.next();
                    System.out.print("Enter Available seats  : ");
                    int av = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Timing [00:00 AM/PM]: ");
                    String time = sc.next();
                    System.out.print("Enter a date (YYYY-MM-DD): ");
                    String da = sc.next();
                    System.out.print("Enter Driver name  : ");
                    String dri = sc.next();
                    System.out.print("Enter Conductor name   : ");
                    String con = sc.next();
                    try
                    {
                        if(BusController.addBus(st,des,av,time,dri,con,da))
                            System.out.println("Bus has been added");
                        else
                            System.out.println("Error during adding Bus");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error during adding Bus");
                    }
                    break;
                case 3:
                    System.out.print("Enter the Bus ID to delete: ");
                    int idToDelete = sc.nextInt();
                    try
                    {
                        if(BusController.removeBus(idToDelete))  
                        System.out.println("Bus has been removed from the database.");
                        else    
                        System.out.println("Failed to remove Bus from the database.");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Failed to remove Bus from the database.");
                    }
                    break;
                case 4:
                    List<Bus> sList = BusController.viewAllBus();;
                    if (!sList.isEmpty()) 
                    {
                        System.out.println("---------------------------------------");
                        System.out.println("        List of Buses:");
                        System.out.println("---------------------------------------");
                        for (Bus ve : sList)
                            System.out.println(ve);
                    } 
                    else
                        System.out.println("No Buses");
                    break;
                case 5:
                    System.out.println("---------------------------------------");
                    System.out.println("Enter a valid choice");
                    b=false;
                    break;
                default:
                    System.out.println("enter bro");
                    break;
            }
        }
        while(b);
    }
    public static void updateBus()
    {
        System.out.println("---------------------------------------");
        System.out.println("  What Do You Want to change   ");
        System.out.println(" 1.change Bus Timing \n 2.change route of bus ");
        System.out.println("---------------------------------------");
        System.out.print("Enter choice : ");
        int a=sc.nextInt();
        sc.nextLine();
        if(a==1)
            changeTiming();   
        else if(a==2)
            changeRoute();
        else
            System.out.println("Enter a valid choice");
    }
     public static void changeTiming()
    {
        System.out.print("Enter the Bus Id : ");
        int a=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Bus Timing you need to change [hr:min AM/PM] : ");
        String c=sc.nextLine();
        try
        {
            if(BusController.changeTimings(a,c))
                System.out.println("Bus has been updated");
            else    System.out.println("Error during updation");
        }
        catch(Exception e)
        {
            System.out.println("Error during updation");
        }
    }
    public static void changeRoute()
    {
        System.out.print("Enter the Show Id : ");
        int a=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the starting point: ");
        String st=sc.next();
        System.out.print("Enter the destination point : ");
        String des=sc.next();
        try
        {
            if(BusController.updateBusRoute(a,st,des))
                System.out.println("Bus has been updated");
            else    System.out.println("Error during updation");
        }
        catch(Exception e)
        {
            System.out.println("Error during updation");
        }
    }
}