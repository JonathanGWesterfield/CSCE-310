import java.sql.*;
import java.util.*;
import java.io.*;


public class Problem1
{
    private String connString = "jdbc:mariadb://localhost:3306/Computers?user=root&password=root";
    Connection conn;

    public static void main(String[] args)
    {
        try
        {
            Problem1 problem1 = new Problem1();
            // problem1.findByPrice();
            // problem1.getUserLaptopMinimums();
            // problem1.getByMaker();
            problem1.closeConn();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Problem1() throws SQLException
    {
        this.conn = DriverManager.getConnection(connString);
        System.out.println("Connected to Database");
    }

    public void closeConn() throws SQLException
    {
        this.conn.close();
        System.out.println("\n\nClosed DB Connection");
    }

    public Double getUserPrice()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a Price of the computer you are looking for: ");
        return Double.parseDouble(scanner.next());
    }

    public PcObj getCorrectModel(double userPrice, ArrayList<PcObj> pcList)
    {
        PcObj closestMatchObj = new PcObj();
        double bestPriceDiff = Double.MAX_VALUE;
        System.out.println();
        double priceDiff;

        for(PcObj element : pcList)
        {
            priceDiff = Math.abs(userPrice - element.getPrice());
            // System.out.printf("Price diff: %f - %f = %f\n", userPrice, element.getPrice(), priceDiff);
            if (priceDiff < bestPriceDiff)
            {
                bestPriceDiff = priceDiff;
                closestMatchObj = element;
            }
        }

        return closestMatchObj;
    }

    /**
     * Returns the pc Model to get the information for
     * @return
     * @throws SQLException
     */
    public PcObj execPriceStmt() throws SQLException
    {
        Double userPrice = getUserPrice();
        String stmt = "SELECT product.maker, product.model, pc.speed, pc.price FROM product " +
                "JOIN pc ON product.model=pc.model";
        Statement getPC = conn.createStatement();
        ResultSet result = getPC.executeQuery(stmt);

        // Parallel arrays for storing pcModels and prices
        ArrayList<PcObj> pcList = new ArrayList<>();

        System.out.println();
        System.out.println("Maker\tModel\tPrice\tSpeed");
        while (result.next())
        {
            String maker = result.getString("maker");
            String model = result.getString("model");
            double speed = result.getDouble("speed");
            double price = result.getDouble("price");

            pcList.add(new PcObj(maker, model, price, speed));

            System.out.printf("%s\t%s\t%f\t%f\n", maker, model, price, speed);
        }

        PcObj priceModelMatch = getCorrectModel(userPrice, pcList);

        return priceModelMatch;
    }

    public void findByPrice() throws SQLException
    {
        PcObj bestPriceMatch = execPriceStmt();

        System.out.printf("Closest price match is: %s, %s, %f, %f\n",
                bestPriceMatch.getMaker(), bestPriceMatch.getModel(),
                bestPriceMatch.getSpeed(), bestPriceMatch.getPrice());
    }

    public void getUserLaptopMinimums() throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nEnter minimum laptop speed: ");
        double minSpeed = Double.parseDouble(scanner.next());

        System.out.print("Enter minimum laptop RAM: ");
        int minRam = Integer.parseInt(scanner.next());

        System.out.println("Enter minimum laptop Hard drive size: ");
        int minHDSize = Integer.parseInt(scanner.next());

        System.out.println("Enter minimum laptop screen size: ");
        double minScreenSize = Double.parseDouble(scanner.next());

        String query = "SELECT product.maker, laptop.model, laptop.speed, laptop.ram, laptop.hd, " +
                "laptop.screen FROM laptop JOIN product ON laptop.model=product.model " +
                "WHERE laptop.speed>=? AND laptop.ram>=? AND laptop.hd>=? AND laptop.screen>=?;";

        PreparedStatement prepStat = conn.prepareStatement(query);
        prepStat.setDouble(1, minSpeed);
        prepStat.setInt(2, minRam);
        prepStat.setInt(3, minHDSize);
        prepStat.setDouble(4, minScreenSize);

        ResultSet rs = prepStat.executeQuery();

        String resMaker, resModel, resSpeed, resRam, resHD, resScreen;
        resModel = resSpeed = resRam = resHD = resScreen = "";

        ArrayList<LaptopObj> laptopList = new ArrayList<>();

        if (rs.next() == false)
            System.out.println("There were no Laptops matching your specifications!");
        else
        {
            do {
                resMaker = rs.getString("maker");
                resModel = rs.getString("model");
                resSpeed = rs.getString("speed");
                resRam = rs.getString("ram");
                resHD = rs.getString("hd");
                resScreen = rs.getString("screen");
                laptopList.add(new LaptopObj(resMaker, resModel, resSpeed, resRam,
                        resHD, resScreen));
            } while (rs.next());

            System.out.println("Maker Model, Speed, RAM, HD Size, Screen");
            for (LaptopObj lp : laptopList)
            {
                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", lp.getResMaker(), lp.getResModel(),
                        lp.getResSpeed(), lp.getResRam(), lp.getResHD(), lp.getResScreen());
            }
        }
    }

    public String getChoiceMaker()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the manufacturer to get all products from that manf: ");
        return scanner.next();
    }

    public ArrayList<PcObj> getPCByManf(String choiceManf) throws SQLException
    {
        String query = "SELECT product.maker, product.model, product.type, " +
                "pc.speed, pc.ram, pc.hd, pc.price FROM product JOIN pc ON " +
                "product.model = pc.model WHERE product.maker='" + choiceManf + "';";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        String maker, model;
        maker = model = "";

        double speed, price;
        speed = price = 0.0;

        int ram, hd;
        ram = hd = 0;

        ArrayList<PcObj> pcList = new ArrayList<>();

        if (rs.next() == false)
            System.out.println("\n\nThere were no PC Products from that manufacturer!!");
        else
        {
            do {
                maker = rs.getString("maker");
                model = rs.getString("model");
                speed = rs.getDouble("speed");
                ram = rs.getInt("ram");
                hd = rs.getInt("hd");
                price = rs.getDouble("price");
                pcList.add(new PcObj(maker, model, speed, ram, hd, price));
            } while (rs.next());
        }

        return pcList;
    }

    public ArrayList<LaptopObj> getLaptopByManf(String choiceManf) throws SQLException
    {
        String query = "SELECT product.maker, product.model, " +
                "laptop.speed, laptop.ram, laptop.hd, laptop.screen, laptop.price " +
                "FROM product JOIN laptop ON product.model = laptop.model " +
                "WHERE product.maker='" + choiceManf + "';";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        String maker, model, speed, ram, hd, screen, price;
        maker = model = speed = ram = hd = screen = price = "";

        ArrayList<LaptopObj> laptopList = new ArrayList<>();

        if (rs.next() == false)
            System.out.println("\n\nThere were no Laptop Products from that manufacturer!!");
        else
        {
            do {
                maker = rs.getString("maker");
                model = rs.getString("model");
                speed = rs.getString("speed");
                ram = rs.getString("ram");
                hd = rs.getString("hd");
                screen = rs.getString("screen");
                price = rs.getString("price");

                laptopList.add(new LaptopObj(maker, model, speed, ram, hd, screen, price));
            } while (rs.next());
        }

        return laptopList;
    }

    public ArrayList<PrinterObj> getPrinterByManf(String choiceManf) throws SQLException
    {
        String query = "SELECT product.maker, product.model, printer.color, printer.type," +
                " printer.price FROM product JOIN printer ON product.model = printer.model " +
                "WHERE product.maker='" + choiceManf + "';";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        String maker, model, color, type, price;
        maker = model = color = type = price = "";

        ArrayList<PrinterObj> printerList = new ArrayList<>();

        if (rs.next() == false)
            System.out.println("\n\nThere were no Printer Products from that manufacturer!!");
        else
        {
            do {
                maker = rs.getString("maker");
                model = rs.getString("model");
                color = rs.getString("color");
                type = rs.getString("type");
                price = rs.getString("price");

                printerList.add(new PrinterObj(maker, model, color, type, price));
            } while (rs.next());
        }

        return printerList;
    }

    public void getByMaker() throws SQLException
    {
        String choice = getChoiceMaker();
        choice = choice.toUpperCase();

        ArrayList<PcObj> pcList = getPCByManf(choice);
        ArrayList<LaptopObj> laptopList = getLaptopByManf(choice);
        ArrayList<PrinterObj> printerList = getPrinterByManf(choice);

        System.out.println();
        if (pcList.isEmpty() && laptopList.isEmpty() && printerList.isEmpty())
            System.out.println("This manufacturer had no products in database\n");
        else
        {
            if (!pcList.isEmpty())
            {
                System.out.println("PC's from Manf " + choice + ": ");
                System.out.println("Maker, Type, Model, Speed, RAM, HD, Price");
                for (PcObj obj : pcList)
                    System.out.println(obj.toString());
                System.out.println();
            }

            if (!laptopList.isEmpty())
            {
                System.out.println("Laptops from Manf " + choice + ": ");
                System.out.println("Maker, Type, Model, Speed, RAM, HD, Screen, Price");
                for (LaptopObj obj : laptopList)
                    System.out.println(obj.toString());
                System.out.println();
            }

            if (!printerList.isEmpty())
            {
                System.out.println("Printers from Manf " + choice + ": ");
                System.out.println("Maker, Model, Prod Type, Color, Type, Price");
                for (PrinterObj obj : printerList)
                    System.out.println(obj.toString());
                System.out.println();
            }
        }
    }
}
