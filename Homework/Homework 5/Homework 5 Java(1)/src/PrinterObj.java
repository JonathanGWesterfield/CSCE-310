public class PrinterObj
{
    String maker, model, color, type, price;
    String productType = "printer";

    public PrinterObj(String maker, String model, String color, String type, String price)
    {
        this.maker = maker;
        this.model = model;
        this.color = color;
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public String getMaker() {
        return maker;
    }

    public String getColor() {
        return color;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public String toString()
    {
        // "\nMaker, Model, Prod Type, Color, Type, Price\n";
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t", maker,
            model, productType, color, type, price);
    }
}
