import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

        displayWelcomeMessage();

        showContent(cellList1, "List 1 (Unique List)");
        showContent(cellList2, "List 2 (Duplicate List)");
        System.out.println("----------------");

        // Prompt the user to enter a few serial number to check
        System.out.println("Enter a serial number to check in the list: ");
        Scanner sc = new Scanner(System.in);
        long serialNumByUser = sc.nextLong();

        // Test findItem() method
        findItemBySerialNum(serialNumByUser, cellList1);
        CellPhone tempCellPhone = new CellPhone(123444, "Sony Xperia 10", 2010, 300);
        testReplaceAtIndex(cellList1, tempCellPhone, 3);
        testEqualsFunction(cellList1, cellList2);

        CellList cellList3 = new CellList(cellList1);
        testEqualsFunction(cellList1, cellList3);

    }

    public static void displayWelcomeMessage() {
        System.out.println("------Welcome to Thong Tran's Cell List Utilization program------");
        System.out.println();
    }

    public static void findItemBySerialNum(long serialNum, CellList cellList) {
        // Create a temporary node to store the found item if exists.
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


    public static void showContent(CellList cellList, String listName) {
        System.out.println("--------Displaying content of Cellphone Lists-------");
        System.out.println("Show content of " + listName + ": ");
        cellList.showContents();
        System.out.println();
    }

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

    public static void testReplaceAtIndex(CellList cellList, CellPhone cellPhone, int index) {
        System.out.println("========Here is Replace At Index test=======");
        System.out.println("Replacing index " + index + " with item: " + cellPhone.toString() + " to the list.");
        cellList.replaceAtIndex(cellPhone, index);
        cellList.showContents();
        System.out.println("Replace At Index Test Completed.");
        System.out.println();
    }


}


