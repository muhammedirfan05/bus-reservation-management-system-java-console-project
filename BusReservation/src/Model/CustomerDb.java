package Model;

// import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDb 
{
     public static Customer getCustomerById(int customerId) throws SQLException {
        Customer customer = null;
        
        String sql = "SELECT * FROM customer WHERE id = ?";
        
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(sql)) {
            
            statement.setInt(1, customerId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String phoneNumber = resultSet.getString("phone_number");
                    int busId = resultSet.getInt("bus_id");
                    int bookedTickets = resultSet.getInt("booked_tickets");
                    customer = new Customer(id, name, email, phoneNumber, busId, bookedTickets);
                }
            }
        }
        
        return customer;
    }
    public static List<Customer> getCustomersByBusId(int busId) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        
        String sql = "SELECT * FROM customer WHERE bus_id = ?";
        
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(sql)) {
            
            statement.setInt(1, busId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String phoneNumber = resultSet.getString("phone_number");
                    int bookedTickets = resultSet.getInt("booked_tickets");
                    customers.add(new Customer(id, name, email, phoneNumber, busId, bookedTickets));
                }
            }
        }
        return customers;
    }
    
}
