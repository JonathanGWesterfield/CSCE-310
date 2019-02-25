import java.sql.*;
import java.util.*;
import java.io.*;

public class Problem2
{
    // private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    // private Connection conn;

    private String connString = "jdbc:mariadb://localhost:3306/Ships?user=root&password=root";
    Connection conn;
    private ShipClassObj newClass;
    private ArrayList<ShipsObj> newShipslist;

    public static void main(String[] args)
    {
        try
        {
            Problem2 pr = new Problem2();
            pr.getClassInfo();
            pr.getNewShipsList();
            pr.insertClass();
            pr.insertShipList();

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

        System.out.println("Information for a new Class of Ship");
        System.out.print("What is the class of ship: ");
        String shipClass = scanner.next();

        System.out.print("What is the type of the ship class: ");
        String type = scanner.next();

        System.out.print("What is the country? ");
        String country = scanner.next();

        System.out.print("How many guns for this class? ");
        int numGuns = Integer.parseInt(scanner.next());

        System.out.print("What is the bore of the guns? ");
        int bore = Integer.parseInt(scanner.next());

        System.out.print("What is the displacement of this class? ");
        int disp = Integer.parseInt(scanner.next());

        this.newClass = new ShipClassObj(shipClass, type, country, numGuns, bore, disp);
    }

    public void getNewShipsList()
    {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        this.newShipslist = new ArrayList<>();

        System.out.println("Input information for new Ships of the new class");
        while (!done)
        {
            System.out.print("Enter the ship name: ");
            String name = scanner.next();

            System.out.print("Enter launch date: ");
            int launched = Integer.parseInt(scanner.next());

            newShipslist.add(new ShipsObj(name, this.newClass.getShipClass(), launched));

            System.out.print("Done entering ships? (y/n): ");
            if(scanner.next().equalsIgnoreCase("y"))
                done = true;
        }
    }

    public void insertClass() throws SQLException
    {
        String query = "INSERT INTO classes (class, type, country, numGuns, bore, disp) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, newClass.getShipClass());
        stmt.setString(2, newClass.getType());
        stmt.setString(3, newClass.getCountry());
        stmt.setInt(4, newClass.getNumGuns());
        stmt.setInt(5, newClass.getBore());
        stmt.setInt(6, newClass.getDisp());

        int i = stmt.executeUpdate();
        System.out.println();
        System.out.println(i + " records inserted into classes table");
    }

    public void insertShipList() throws SQLException
    {
        String query = "INSERT INTO ships VALUES (?, ?, ?);";

        for (ShipsObj ship : this.newShipslist)
        {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, ship.getName());
            stmt.setString(2, ship.getShipClass());
            stmt.setInt(3, ship.getLaunched());

            int i = stmt.executeUpdate();
            System.out.println();
            System.out.println(i + " record inserted into ships table");
        }
    }
}
