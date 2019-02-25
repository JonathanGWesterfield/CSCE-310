public class ShipClassObj
{
    private String shipClass, type, country;
    private int numGuns, bore, disp;

    public ShipClassObj()
    {
        // empty default constructor
    }

    public ShipClassObj(String shipClass, String type, String country,
                        int numGuns, int bore, int disp)
    {
        this.shipClass = shipClass;
        this.type = type;
        this.country = country;
        this.numGuns = numGuns;
        this.bore = bore;
        this.disp = disp;
    }

    public String getShipClass() {
        return shipClass;
    }

    public String getType() {
        return type;
    }

    public int getBore() {
        return bore;
    }

    public int getDisp() {
        return disp;
    }

    public int getNumGuns() {
        return numGuns;
    }

    public String getCountry() {
        return country;
    }
}
