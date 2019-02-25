public class ShipsObj
{
    private String name, shipClass;
    private int launched;

    public ShipsObj()
    {
        // empty default constructor
    }

    public ShipsObj(String name, String shipClass, int launched)
    {
        this.name = name;
        this.shipClass = shipClass;
        this.launched = launched;
    }

    public String getShipClass() {
        return shipClass;
    }

    public int getLaunched() {
        return launched;
    }

    public String getName() {
        return name;
    }
}
