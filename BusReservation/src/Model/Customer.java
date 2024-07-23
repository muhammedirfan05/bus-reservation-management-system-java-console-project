package Model;

public class Customer 
{
    private int id;
    private String email;
    private String name;
    private String phno;
    private int busid;
    private int booked_tickets;
    // private String designation;
    // customers.add(new Customer(id, name, email, phoneNumber, busId, bookedTickets));
    public Customer(int id,String name,String email,String phno,int busid,int booked_tickets) 
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.busid = busid;
        this.booked_tickets = booked_tickets;
    }
    
    @Override
    public String toString() {
        return " [-Employees-" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Bus Id=" + busid +
                ", Phone number=" + phno+
                ", Booked Tickets='" + booked_tickets + '\'' +
                "-]";
    }
}
