import java.sql.*;
import java.util.*;
import java.io.*;

public class Problem2
{
    private String connString = "jdbc:mariadb://localhost:3306/Computers?user=root&password=root";
    private Connection conn;

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
}
