package dao;
import Model.Employee;

import java.util.*;
import java.sql.*;
public class EmployeeDao {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/employees";
    private static String jdbcUsername = "root";

    // provide your appropriate password  here
    private static String jdbcPassword = "";

    public static Connection getConnection(){
        Connection con=null;
        try {
            // This is the way of connecting MySQL
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("Message.. " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Message.. " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
    public static int save(Employee employee)
    {
        int status = 0;
        try {
            Connection connection  = EmployeeDao.getConnection();
            String sql = "INSERT INTO employees (id, full_name, birthday, address, position, department) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFullName());
            preparedStatement.setString(3, employee.getBirthday());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getPosition());
            preparedStatement.setString(6, employee.getDepartment());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        }
        catch (Exception ex) {
            System.out.println("Message.." + ex.getMessage());
            ex.printStackTrace();
        }

        return status;
    }
    public static List<Employee> getAllEmployee()
    {
        List<Employee> list = new ArrayList<Employee>();

        try {
            Connection con = EmployeeDao.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select * from employees");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setFullName(rs.getString(2));
                e.setBirthday(rs.getString(3));
                e.setAddress(rs.getString(4));
                e.setPosition(rs.getString(5));
                e.setDepartment(rs.getString(6));
                list.add(e);
            }
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}