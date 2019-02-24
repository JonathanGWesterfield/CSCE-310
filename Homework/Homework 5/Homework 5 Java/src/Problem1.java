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
            problem1.findByPrice();
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

    public Double getUserPrice()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a Price of the computer you are looking for: ");
        return Double.parseDouble(scanner.next());
    }

    public String getCorrectModel(double userPrice, ArrayList<String> model, ArrayList<Double> price)
    {
        int closestMatchIndex = 0;
        double bestPriceDiff = Double.MAX_VALUE;
        double priceDiff;
        if(model.size() != price.size())
        {
            System.out.println("ERROR: Models and Prices did not return the same number of results");
            System.exit(-1);
        }
        for(int i = 0; i < price.size(); i++)
        {
            priceDiff = Math.abs(userPrice - price.get(i));
            if (priceDiff < bestPriceDiff)
            {
                bestPriceDiff = priceDiff;
                closestMatchIndex = i;
            }
        }

        return model.get(closestMatchIndex);
    }

    /**
     * Returns the pc Model to get the information for
     * @return
     * @throws SQLException
     */
    public String execPriceStmt() throws SQLException
    {
        Double userPrice = getUserPrice();
        String stmt = "SELECT product.maker, product.model, pc.speed, pc.price FROM product " +
                "JOIN pc ON product.model=pc.model";
        Statement getPC = conn.createStatement();
        ResultSet result = getPC.executeQuery(stmt);

        // Parallel arrays for storing pcModels and prices
        ArrayList<String> pcModels = new ArrayList<>();
        ArrayList<Double> pcPrices = new ArrayList<>();

        System.out.println();
        while (result.next())
        {
            String maker = result.getString("maker");


            String model = result.getString("model");
            pcModels.add(model);

            double price = result.getDouble("price");
            pcPrices.add(price);

            System.out.printf("%s\t%f\n", model, price);
        }

        String priceModelMatch = getCorrectModel(userPrice, pcModels, pcPrices);
        System.out.println("Closest price match model is: " + priceModelMatch);

        return priceModelMatch;
    }

    public void findByPrice() throws SQLException
    {
        String model = execPriceStmt();
        String query = "SELECT maker, model FROM Product WHERE model='" + model + "' UNION " +
                "SELECT speed FROM PC WHERE model='" + model + "';";
        Statement getPCInfo = conn.createStatement();
        ResultSet result = getPCInfo.executeQuery(query);

        String resultMaker, resultModel, resultSpeed;
        resultMaker = resultModel = resultSpeed = "";

        while (result.next())
        {
            resultMaker = result.getString("maker");
            resultModel = result.getString("model");
            resultSpeed = result.getString("speed");
        }

        System.out.printf("Closest match is: %s, %s, %s\n", resultMaker, resultModel, resultSpeed);
    }


}
