package Model;

public class User 
{
    private String email;
    private String password;
    private String role;
    public User(String email,String password,String role)
    {
        this.email=email;
        this.password=password;
        this.role=role;
    }
    public String getEmail() 
    {
        return email;
    }
    public String getPassword() 
    {
        return password;
    }
    public String getRole() 
    {
        return role;
    }
    public String toString() {
        return " Users - {" +
                " email='" + email + '\'' +
                ", Password=" + password +
                ", Role=" + role +
                '}';
    }
}
