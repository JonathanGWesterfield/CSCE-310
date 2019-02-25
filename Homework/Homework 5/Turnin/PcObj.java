public class PcObj
{
    String maker;
    String model;
    double price;
    double speed;
    double hd;
    int ram;
    String type = "pc";

    public PcObj()
    {
        // empty default constructor
    }

    public PcObj(String maker, String model, double price, double speed)
    {
        this.maker = maker;
        this.model = model;
        this.price = price;
        this.speed = speed;
    }

    public PcObj(String maker, String model, double speed, int ram, int hd, double price)
    {
        this.maker = maker;
        this.model = model;
        this.price = price;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
    }


    public String getMaker() {
        return this.maker;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return this.price;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getHd() {
        return hd;
    }

    public int getRam() {
        return ram;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString()
    {
        // String string = "Maker, Type, Model, Speed, RAM, HD, Price\n";
        return String.format("%s\t%s\t%s\t%f\t%d\t%f\t%f\t",
                maker, type, model, speed, ram, hd, price);
    }
}