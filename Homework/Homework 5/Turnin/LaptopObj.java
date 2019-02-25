public class LaptopObj
{
    private String resMaker, resModel, resSpeed, resRam, resHD, resScreen, price;
    private String type = "laptop";

    public LaptopObj()
    {
        // empty default constructor
    }

    public LaptopObj(String resMaker, String resModel, String resSpeed, String resRam,
                     String resHD, String resScreen)
    {
        this.resMaker = resMaker;
        this.resModel = resModel;
        this.resSpeed = resSpeed;
        this.resRam = resRam;
        this.resHD = resHD;
        this.resScreen = resScreen;
    }

    public LaptopObj(String resMaker, String resModel, String resSpeed, String resRam,
                     String resHD, String resScreen, String price)
    {
        this.resMaker = resMaker;
        this.resModel = resModel;
        this.resSpeed = resSpeed;
        this.resRam = resRam;
        this.resHD = resHD;
        this.resScreen = resScreen;
        this.price = price;
    }



    public String getResHD() {
        return resHD;
    }

    public String getResModel() {
        return resModel;
    }

    public String getResRam() {
        return resRam;
    }

    public String getResSpeed() {
        return resSpeed;
    }

    public String getResScreen() {
        return resScreen;
    }

    public String getResMaker() {
        return resMaker;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString()
    {
        // "Maker, Type, Model, Speed, RAM, HD, Screen, Price\n";
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t", resMaker,
                type, resModel, resSpeed, resRam, resHD, resScreen, price);
    }
}
