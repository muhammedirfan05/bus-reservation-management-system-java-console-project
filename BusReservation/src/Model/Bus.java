package Model;
public class Bus
{
    private int id;
    // private String startpoint;
    // private String endpoint;
    private String timing;
    private int availableSeats;
    private String driver;
    private String conductor;
    private String date;
    public Bus(int id,String startpoint,String endpoint, String timing,int availableSeats,String driver,String conductor,String date) 
    {
        this.id = id;
        // this.startpoint = startpoint;
        // this.endpoint = endpoint;
        this.timing = timing;
        this.driver = driver;
        this.conductor= conductor;
        this.availableSeats=availableSeats;
        this.date=date;
    }
    public String toString() {
        return "[-Buses- " +
                "id='" + id + '\'' +
                ", timing='" + timing + '\'' +
                ",Driver name='" + driver + '\'' +
                ", Conductor name='" + conductor + '\'' +
                ", Date='" + date + '\'' +
                ", Available seats='" + availableSeats+
                " -]";
    }
}