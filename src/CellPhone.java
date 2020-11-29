import java.util.Scanner;

public class CellPhone {
    private long serialNum;
    private String brand;
    private int year;
    private double price;

    // Parameterized constructor accepts four variables
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    // Copy constructor takes two parameters
    public CellPhone(CellPhone object, long value) {
        this.serialNum = value;
        this.brand = object.brand;
        this.year = object.year;
        this.price = object.price;
    }

    public CellPhone clone() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a new serial number: ");
        long serialNum = sc.nextLong();
        CellPhone copyCellphone = new CellPhone(this, serialNum);
        return copyCellphone;
    }

    @Override
    public String toString() {
        return serialNum +
                ": " + brand +
                "  " + year +
                "  " + price;
    }

    public boolean equals(CellPhone object) {
        if (this.price == object.price && this.year == object.year && this.brand.equals(object.brand))
            return true;
        return false;
    }


    public long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
