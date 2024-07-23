package Main;
import java.util.*;

import View.*;
public class BusReservation
{
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) 
    {
        boolean b=true;
        do
        {
            System.out.println("------------------------------------------");
            System.out.print("Press 1 for login || Press 2 for Signin : ");
            int n=sc.nextInt();
            sc.nextLine();
            if(n==1)
            {
                UserView.loginPage();
            }
            else if(n==2)
            {
                UserView.signin();
            }
            else
                System.out.println("----Enter only 1 || 2 ---- ");
        }
        while(b);
    }
}