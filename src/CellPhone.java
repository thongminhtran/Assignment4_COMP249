import java.util.Scanner;

/**
 * This is a CellPhone class
 */
public class CellPhone {
    private long serialNum;
    private String brand;
    private int year;
    private double price;


    /**
     * This method parameterizes constructor accepts four variables
     *
     * @param serialNum
     * @param brand
     * @param year
     * @param price
     */
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }


    /**
     * This method does copy constructor takes two parameters
     *
     * @param object
     * @param value
     */
    public CellPhone(CellPhone object, long value) {
        this.serialNum = value;
        this.brand = object.brand;
        this.year = object.year;
        this.price = object.price;
    }

    /**
     * This method is clone
     */
    public CellPhone clone() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a new serial number: ");
        long serialNum = sc.nextLong();
        CellPhone copyCellphone = new CellPhone(this, serialNum);
        return copyCellphone;
    }

    /**
     * This is toString method
     */
    @Override
    public String toString() {
        return serialNum +
                ": " + brand +
                "  " + year +
                "  " + price;
    }

    /**
     * This method will compare 2 objects
     *
     * @param object
     * @return check if 2 objects are equals or not
     */
    public boolean equals(CellPhone object) {
        if (this.price == object.price && this.year == object.year && this.brand.equals(object.brand))
            return true;
        return false;
    }


    /**
     * This method get serial number
     *
     * @return
     */
    public long getSerialNum() {
        return serialNum;
    }

    /**
     * This method set serial number
     *
     * @param serialNum
     */
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * This method get brand
     *
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method get brand
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This method get year
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * This method set year
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This method get price
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method set price
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
