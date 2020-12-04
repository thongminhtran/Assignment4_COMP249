import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Names and ID: Thong Minh Tran (40072745)
 * Quan Nguyen The(40108890)
 * COMP249
 * Assignment #4 Part 2
 * Deadline: Dec 4 2020
 */

/**
 * This is a Main class for part 2
 */
public class CellListUtilization {

    public static void main(String[] args) {
        // Create at least two empty lists from CellList class

        // CellList 1 will contain any duplicate cellphones.
        CellList cellList1 = new CellList();

        //CellList 2 will contain only unique cellphone
        CellList cellList2 = new CellList();

        String path = "Cell_Info.txt";
        Scanner scanner = null;

        try {
            scanner = new Scanner(new FileInputStream(path));

        } catch (FileNotFoundException e) {
            System.out.println("File " + path + " cannot be found. The system will be closed");
            e.printStackTrace();
            // Exit the program
            System.exit(0);
        }
        while (scanner.hasNextLine()) {
            // Using this while loop to record all data separately.
            long serialNum = scanner.nextLong();
            String brand = scanner.next();
            double price = scanner.nextDouble();
            int year = scanner.nextInt();
            // Create a Cellphone class to store all data inside.
            CellPhone newCellPhone = new CellPhone(serialNum, brand, year, price);
            // If there is no duplicate item, then add
            if (!cellList1.hasDuplicateItems(newCellPhone))
                cellList1.addToStart(newCellPhone); //List1 has unique items only
            else {
                cellList2.addToStart(newCellPhone); //List2 only contains duplicate items
            }
        }

        System.out.println("------Welcome to Thong Tran's Cell List Utilization program------");
        System.out.println();

        showContent(cellList1, "List 1 (Unique List)");
        showContent(cellList2, "List 2 (Duplicate List)");
        System.out.println("----------------");

        // Prompt the user to enter a few serial number to check
        System.out.println("Enter a serial number to check in the list: ");
        Scanner sc = new Scanner(System.in);
        long serialNumByUser = sc.nextLong();

        // Following called methods are used for testing
        findItemBySerialNum(serialNumByUser, cellList1);
        testReplaceAtIndex(cellList1);
        testEqualsFunction(cellList1, cellList2);

        CellList cellList3 = new CellList(cellList1);
        System.out.println("==========This is the content of CellList 3============");
        cellList3.showContents();
        System.out.println("*************************************");
        System.out.println("==========This is the content of CellList 1============");
        cellList1.showContents();
        testEqualsFunction(cellList1, cellList3);
        testEqualsFunction(cellList1, cellList2);

    }

    /**
     * Create a temporary node to store the found item if exists.
     *
     * @param serialNum
     * @param cellList
     */
    public static void findItemBySerialNum(long serialNum, CellList cellList) {

        CellList.CellNode tempNode = null;
        System.out.println("=======This section is used for Find Item By Serial# Test========");
        if (!cellList.contains(serialNum)) {
            System.out.println("There is no cellphone with serial# " + serialNum);
        } else {
            tempNode = cellList.find(serialNum);
            System.out.println("Cellphone with serial# " + serialNum + " has been found:"
                    + tempNode.getCellPhone().toString());
        }
        System.out.println();
    }


    /**
     * This method showing the content of cellphone list
     *
     * @param cellList
     * @param listName
     */
    public static void showContent(CellList cellList, String listName) {
        System.out.println("--------Displaying content of Cellphone Lists-------");
        System.out.println("Show content of " + listName + ": ");
        cellList.showContents();
        System.out.println();
    }

    /**
     * This method is testing equals function of cell list class
     *
     * @param cellList1
     * @param cellList2
     */
    public static void testEqualsFunction(CellList cellList1, CellList cellList2) {
        System.out.println("============Testing Equals Function of CellList class==========");
        System.out.println();
        if (cellList1.equals(cellList2))
            System.out.println("Two lists are exactly the same!!!");
        else {
            System.out.println("Two lists are different!");
        }
        System.out.println("Equals Test Completed.");
    }

    /**
     * This method is testing equals function of cell list class
     *
     * @param cellList
     */
    public static void testReplaceAtIndex(CellList cellList) {
        System.out.println("========Here is Replace At Index test=======");
        System.out.println("Please enter the following information of item to replace: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter serial number: ");
        long serialNum = sc.nextLong();
        System.out.println("Enter brand: ");
        String brand = sc.next();
        System.out.println("Enter release year: ");
        int year = sc.nextInt();
        System.out.println("Enter price: ");
        double price = sc.nextDouble();

        // Store those value into cellphone
        CellPhone enteredCellPhone = new CellPhone(serialNum, brand, year, price);

        System.out.println("What position would you like to replace: ");
        int position = sc.nextInt();
        System.out.println("Replacing position " + position + " with item: " + enteredCellPhone.toString() + " to the list.");

        // Call replaceAtIndex method with index = position - 1;
        cellList.replaceAtIndex(enteredCellPhone, position - 1);
        cellList.showContents();
        System.out.println("Replace At Position Test Completed.");
        System.out.println();
    }

}