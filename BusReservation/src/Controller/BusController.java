package Controller;

import java.util.*;
import Model.*;

public class BusController
{
    public static boolean addBus(String st,String des,int av,String time,String dri,String con,String da) throws Exception
    {
        try
        {
            boolean b=BusDb.addBus(st,des,av,time,dri,con,da);
            return b;
        }
        catch(Exception e)
        {
           throw new Exception("Error During Insertion");
        }
    }
    public static boolean removeBus(int idToDelete) throws Exception
    {
        try
        {
            boolean b=BusDb.removeBus(idToDelete);
            return b;
        }
        catch(Exception e)
        {
           throw new Exception("Error During Insertion");
        }
    }
    public static List<Bus> viewAllBus()
    {
        List<Bus> st =BusDb.viewAllBus();
        return st;
    }
    public static List<Bus> viewBus(String start,String des)
    {
        List<Bus> st =BusDb.viewBus(start,des);
        return st;
    }
    public static boolean changeTimings(int a,String c) throws Exception
    {
        try
        {
            boolean b=BusDb.changeTimings(a,c);
            return b;
        }
        catch(Exception e)
        {
           throw new Exception("Error During Insertion");
        }
    }
    public static boolean updateBusRoute(int id,String a,String c) throws Exception
    {
        try
        {
            boolean b=BusDb.updateBusRoute(id,a,c);
            return b;
        }
        catch(Exception e)
        {
           throw new Exception("Error During Insertion");
        }
    }
    public static boolean updateAvailableSeats(String email, String name, String phone, int busId, int count,String date) throws Exception
    {
        try{
            if(BusDb.bookSeats(email,name,phone,busId,count,date)) 
                return true;
            else    return false;
            }
            catch(Exception e)
            {
                throw new Exception("Error During Booking");
            }
    } 
    public static boolean checkAvail(int id,int available,String date) throws Exception
    {
        try
        {
            boolean b=BusDb.checkAvail(id,available,date);
            return b;
        }
        catch(Exception e)
        {
           throw new Exception("Error During checking");
        }
    }
}