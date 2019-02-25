import java.sql.*;
import java.util.*;
import java.io.*;

public class Problem2
{
    // private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    // private Connection conn;

    private String connString = "jdbc:mariadb://localhost:3306/Computers?user=root&password=root";
    Connection conn;

    public static void main(String[] args)
    {
        try
        {
            Problem2 pr = new Problem2();

            pr.closeConn();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    /*public Problem2() throws SQLException
    {
        String protocol = "jdbc:derby:Ships";
        conn = DriverManager.getConnection(protocol);
        System.out.println("Connected to Database");
    } */

    public Problem2() throws SQLException
    {
        this.conn = DriverManager.getConnection(connString);
        System.out.println("Connected to Database");
    }

    public void closeConn() throws SQLException
    {
        this.conn.close();
        System.out.println("\n\nClosed DB Connection");
    }

    public void getClassInfo()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is the class of ship: ");
        String shipClass = scanner.next();

        System.out.print("What is the type of the ship class: ");
        String type = scanner.next();

        System.out.println("What is the country? ");
        String country = scanner.next();


    }
}
