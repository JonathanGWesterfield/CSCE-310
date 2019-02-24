public class PcObj
{
    String maker;
    String model;
    double price;
    double speed;

    public PcObj(String maker, String model, double price, double speed)
    {
        this.maker = maker;
        this.model = model;
        this.price = price;
        this.speed = speed;
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
}