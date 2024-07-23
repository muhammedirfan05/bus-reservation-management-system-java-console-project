package Model;
// import Controller.*;
import java.io.EOFException;
// import java.sql.Connection;
// import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
// import java.time.LocalDate;

public class BusDb 
{

    public static boolean addBus(String st, String des, int av, String time, String dri, String con, String localDate) throws Exception {
        String sqlInsert = "INSERT INTO bus (starting_point, destination, available_seats, timing, driver_name, conductor_name, date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(sqlInsert)) {
            statement.setString(1, st);
            statement.setString(2, des);
            statement.setInt(3, av);
            statement.setString(4, time);
            statement.setString(5, dri);
            statement.setString(6, con);
            statement.setDate(7, java.sql.Date.valueOf(localDate)); // Convert LocalDate to java.sql.Date
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("DB error: Failed to add bus record");
        }
    }
    public static boolean removeBus(int id) throws Exception
    {
        try (PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(
                     "DELETE FROM bus WHERE id = ?")) 
        {
            statement.setInt(1, id);
            // Execute the delete query
            int rowsDeleted = statement.executeUpdate();
            // Check if any rows were deleted
            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB ERROR");
        }
    }
    public static List<Bus> viewAllBus()
    {
        List<Bus> st = new ArrayList<>();
        try (
        PreparedStatement statement = DBconnectivity.getConnection().prepareStatement("SELECT * FROM bus");
        ResultSet resultSet = statement.executeQuery()) 
        {
            while (resultSet.next()) 
            {
                int id = resultSet.getInt("id");
                String startPoint = resultSet.getString("starting_point");
                String dest = resultSet.getString("destination");
                int availableSeats = resultSet.getInt("available_seats");
                String timing = resultSet.getString("timing");
                String driverName = resultSet.getString("driver_name");
                String conductorName = resultSet.getString("conductor_name");
                String date = resultSet.getDate("date").toLocalDate().toString();
                st.add(new Bus(id,startPoint,dest,timing,availableSeats,driverName,conductorName,date));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return st;
    }
     public static boolean changeTimings(int id,String time) throws Exception
    {
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(
                     "UPDATE bus SET timing = ? WHERE id = ? ")) 
        {
            statement.setString(1, time);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EOFException("DB error");
        }
    }
    public static boolean updateBusRoute(int id, String start, String dest) throws Exception {
        String sqlUpdate = "UPDATE bus SET starting_point = ?, destination = ? WHERE id = ?";
        
        try (PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(sqlUpdate)) {
            statement.setString(1, start);
            statement.setString(2, dest);
            statement.setInt(3, id);
            
            int rowsUpdated = statement.executeUpdate();
            
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB error: Failed to update bus route");
        }
    }
    
    public static boolean bookSeats(String email, String name, String phone, int busId, int count, String da)
     {
        LocalDate date = LocalDate.parse(da);
        String subtractSeatsQuery = "UPDATE bus SET available_seats = available_seats - ? WHERE id = ? AND date = ?";
        String updateCustomerQuery = "INSERT INTO customer (name, email, phone_number, bus_id, booked_tickets, date) " +
                                    "VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE booked_tickets = booked_tickets + ?";
        boolean bookingSuccessful = false;

        try (PreparedStatement subtractSeatsStatement = DBconnectivity.getConnection().prepareStatement(subtractSeatsQuery);
            PreparedStatement updateCustomerStatement = DBconnectivity.getConnection().prepareStatement(updateCustomerQuery)) {

            // Subtract count from available seats in the bus table
            subtractSeatsStatement.setInt(1, count);
            subtractSeatsStatement.setInt(2, busId);
            
            // Convert LocalDate to String using a DateTimeFormatter
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            subtractSeatsStatement.setString(3, dateString);
            subtractSeatsStatement.executeUpdate();
            updateCustomerStatement.setString(1, name);
            updateCustomerStatement.setString(2, email);
            updateCustomerStatement.setString(3, phone);
            updateCustomerStatement.setInt(4, busId);
            updateCustomerStatement.setInt(5, count);
            updateCustomerStatement.setString(6, dateString); 
            updateCustomerStatement.setInt(7, count); 
            updateCustomerStatement.executeUpdate();

            bookingSuccessful = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

    return bookingSuccessful;
}

    
    public static List<Bus> viewBus(String startingPoint, String destination) {
        List<Bus> st = new ArrayList<>();
        String query = "SELECT * FROM bus WHERE starting_point = ? AND destination = ?";
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(query)) {
            
            statement.setString(1, startingPoint);
            statement.setString(2, destination);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String startPoint = resultSet.getString("starting_point");
                String dest = resultSet.getString("destination");
                int availableSeats = resultSet.getInt("available_seats");
                String timing = resultSet.getString("timing");
                String driverName = resultSet.getString("driver_name");
                String conductorName = resultSet.getString("conductor_name");
                String date = resultSet.getDate("date").toLocalDate().toString();
                st.add(new Bus(id,startPoint,dest,timing,availableSeats,driverName,conductorName,date));
                // System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }
    public static boolean checkAvail(int id,int available,String da)
    {
        LocalDate date = LocalDate.parse(da);
        String sql = "SELECT available_seats FROM bus WHERE id = ? AND date = ?";
        try (PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setDate(2, java.sql.Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int availableSeats = resultSet.getInt("available_seats");
                return availableSeats >= available;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
