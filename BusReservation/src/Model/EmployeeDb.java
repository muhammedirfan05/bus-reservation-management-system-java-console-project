package Model;
import java.io.EOFException;
// import java.sql.Connection;
// import java.sql.Connection;
// import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDb 
{
    public  static List<Employee> viewAllEmployee()
    {
        List<Employee> st = new ArrayList<>();
        try (
        PreparedStatement statement = DBconnectivity.getConnection().prepareStatement("SELECT * FROM employee");
        ResultSet resultSet = statement.executeQuery()) 
        {
            while (resultSet.next()) 
            {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String designation = resultSet.getString("designation");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                st.add(new Employee(id,email,name,age,designation));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return st;
    }
    public static Employee searchEmployee(int id) throws Exception
    {
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement("SELECT * FROM employee WHERE id = ?");) 
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    
                // int id1 = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String designation = resultSet.getString("designation");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new Employee(id,email,name,age,designation);
                    // System.out.println(" ID: " + id +" || Name: "+name +" || Email: " + email + " || Designation: " + designation);
                } 
                else 
                {
                    throw new Exception("Data not found");
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB ERROR");
        }
    }
    public static boolean removeEmployee(int idToDelete) throws Exception
    {
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(
                     "DELETE FROM employee WHERE id = ?")) 
        {
            statement.setInt(1, idToDelete);
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
    // boolean b=EmployeeFunction.addStaff(email,name,age,designation);
    public static boolean addEmployee(String email,String name,int age,String designation)throws Exception
    {
        try (
             PreparedStatement statement = DBconnectivity.getConnection().prepareStatement(
                     "INSERT INTO employee (email,name,age,designation) VALUES (?, ?, ?, ?)");) 
        {
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, designation);
            // Execute the SQL INSERT statement
            int rowsInserted = statement.executeUpdate();
            // Check if the insertion was successful
            if (rowsInserted > 0)
                return true;
            else
                return false;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new EOFException("DB error");
        }
    }
     

}
