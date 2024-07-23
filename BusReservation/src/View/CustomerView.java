package View;

import java.util.*;
import Controller.BusController;
import Model.*;
public class CustomerView 
{  
    public static void menu()
    {
        Scanner sc=new Scanner(System.in);
        boolean b=true;
        do
        {
            System.out.println("---------------------------------------");
            System.out.println(" 1.Display Buses \n 2.Book Tickets \n 3.Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter service : ");
            int a = sc.nextInt();
            sc.nextLine();
            switch(a)
            {
                case 1:
                    System.out.print("Enter Your Starting point: ");
                    String start=sc.next();
                    System.out.print("Enter Your Destination point: ");
                    String des=sc.next();
                    List<Bus> sList = BusController.viewBus(start,des);
                    if (!sList.isEmpty()) 
                    {
                        System.out.println("---------------------------------------");
                        System.out.println("        List of Shows:");
                        System.out.println("---------------------------------------");
                        for (Bus ve : sList)
                            System.out.println(ve);
                    } 
                    else
                        System.out.println("No Shows for today");
                    break;
                case 2:
                    boolean t=true;
                    int id=0,n=0;
                    String da="";
                    while(t)
                    {
                        System.out.print("Enter Bus id: ");
                        id=sc.nextInt();
                        System.out.print("Enter how many tickets do you need: ");
                        n=sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter a date (YYYY-MM-DD): ");
                        da = sc.next();
                        try {
                            if(BusController.checkAvail(id,n,da))
                            {
                                System.out.println("You can Proceed");
                                t=false;
                            }
                            else
                                System.out.println("no available tickets for ur need//RE-Enter your details");
                            } 
                            catch (Exception e) {
                                System.out.println("Error During checking");
                            // e.printStackTrace();
                        }
                    }
                    // sc.nextLine();
                    System.out.print("Enter your Email: ");
                    String em=sc.next();
                    System.out.print("Enter your name: ");
                    String na=sc.next();
                    System.out.print("Enter your phone no: ");
                    String no=sc.next();
                    try
                    {
                        if(BusController.updateAvailableSeats(em,na,no,id,n,da)) 
                        {
                            System.out.println("---------------------------------------");
                            System.out.println("-------------Ticket booked-------------");
                            System.out.println("---------------------------------------");
                        }
                        else    System.out.println("Error during booking");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error during booking1");
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
        sc.close();
    }
}
