import java.util.NoSuchElementException;

public class CellList {
    private CellNode head;
    private int size;

    public CellList(CellNode head, int size) {
        this.head = head;
        this.size = size;
    }

    //Default constructor, which creates an empty list
    public CellList() {
        this.head = null;
        this.size = 0;
    }

    //Copy constructor, which accepts a CellList object and creates a copy of it

    //TODO
    public CellList(CellList cellList) {
        CellNode currentNode = cellList.head;
        while (currentNode != null) {
            addToStart(currentNode.cellPhone);
            currentNode = currentNode.next;
            size = size + 1;
        }
    }

    //addToStart() method
    public void addToStart(CellPhone cellPhone) {
        head = new CellNode(cellPhone, head);
        size = size + 1;
    }

    //insertAtIndex() method

    //deleteFromIndex() method
    public void deleteFromIndex(int index) {
        if (index < 0 || index > size - 1) {
            System.out.println("Invalid Index");
            throw new NoSuchElementException();
        }
        int temp = 1;
        CellNode previousNode = head;
        CellNode currentNode = head.next;

        //Make the pointer to the next index
        while (index != temp) {
            previousNode = previousNode.next;
            currentNode = currentNode.next;
            temp = temp + 1;
        }
        previousNode.next = currentNode.next;
        size = size - 1;        // Minus the size of array by 1 after removing element at index

    }

    //find() method, accepts one parameter (long serialNum).
    public CellNode find(long serialNum) {
        boolean nodeFound = false;
        int times = 1;
        CellNode testNode = head;
        while (testNode != null) {

            // When two serial numbers are similar, break while loop and return nodeFound = true
            if (testNode.cellPhone.getSerialNum() == serialNum) {
                nodeFound = true;
                break;
            }
            // If not found, continue the while loop by plus temp by 1
            testNode = testNode.next;
            times++;
        }
        if (nodeFound == true) {
            System.out.println("Search Iteration Number: " + times + " times.");
            return testNode.clone();
        } else {
            return null;
        }
    }

    //A method called contains()
    public boolean contains(long serialNum) {
        if (find(serialNum) == null)
            return false;
        return true;
    }

    //A method called showContents() which display the content of the list
    public void showContents() {
        int nodeCount = 0;
        CellNode currentNode = head;
        while (currentNode != null) {
            System.out.print("[" + currentNode.cellPhone.toString() + "]---> ");
            nodeCount++;
            if (nodeCount % 3 == 0 && nodeCount != 0) {
                System.out.println();
            }
            //Move forward to the next position
            currentNode = currentNode.next;
        }

        //After all, the list ---> X
        System.out.println("X");
    }

    //deleteFromStart() method
    public void deleteFromStart() {
        if (size > 1) {
            head = head.next;
            size--;
        } else {
            System.out.println("Cannot delete because the array is empty.");
        }
    }

    //replaceAtIndex() method, accepts two parameters (cellphone, index).
    public void replaceAtIndex(CellPhone cellPhone, int index) {
        //Test whether index is valid or not.
        if (index < 0 || index > size - 1) {
            System.out.println("Invalid index.");
            //If the index is not valid, simply returns
            return;
        }
        deleteFromIndex(index);
        insertAtIndex(cellPhone, index);
    }

    //a method called equals(), accepts one parameter of type CellList.
    public boolean equals(Object newObject) {

        // If two objects are same, return true
        if (this == newObject) {
            return true;
        }
        //
        if (newObject == null || getClass() != newObject.getClass()) {
            return false;
        } else {
            CellList tempList = (CellList) newObject;
            CellNode currentNode = head;
            CellNode tempNode = tempList.head;
            boolean isEqual = true;
            if (tempList.size != this.size)
                isEqual = false;
            else {
                while (currentNode != null) {
                    if (!currentNode.cellPhone.equals(tempNode.cellPhone)) {
                        isEqual = false;
                    }
                    currentNode = currentNode.next;
                    tempNode = tempNode.next;
                }
            }
            return isEqual;
        }
    }

    public boolean hasDuplicateItems(CellPhone cellPhone) {
        // Using hasDuplicate as a variable to record if there are any duplicate items in the list.
        boolean hasDuplicate = false;
        CellNode currentNode = head;
        while (currentNode != null) {
            // If there are any similar items, return true;
            if (currentNode.cellPhone.equals(cellPhone)) {
                hasDuplicate = true;
                break;
            }
            currentNode = currentNode.next;
        }
        return hasDuplicate;
    }

    public void insertAtIndex(CellPhone cellPhone, int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) {
            System.out.println("Invalid Index. Terminate the program!!!");
            throw new NoSuchElementException();
        } else {
            //If size = 0, call the addToStart() method to initialize new head.
            if (size == 0)
                addToStart(cellPhone);
            if (index == 0) {
                addToStart(cellPhone);
                size = size + 1;
                return;
            }
            // After all, insert a new cellphone at index
            CellNode previousNode = head;
            CellNode currentNode = head.next;
            int temp = 1;
            CellNode tempNode = new CellNode(cellPhone, null);
            while (index != temp) {
                previousNode = previousNode.next;
                currentNode = currentNode.next;
                temp++;
            }
            previousNode.next = tempNode;
            tempNode.next = currentNode;
            size = size + 1;
        }
    }

    // Inner Class CellNode
    public class CellNode {
        //Declare two private attributes
        private CellPhone cellPhone;
        private CellNode next;

        public CellNode(CellPhone c) {
            this.cellPhone = c;
            this.next = null;
        }

        //Default Constructor, which assigns both attributes to null
        public CellNode() {
            this.cellPhone = null;
            this.next = null;
        }

        //Parameterized constructor that accepts two parameters
        public CellNode(CellPhone c, CellNode n) {
            this.cellPhone = c;
            this.next = n;
        }

        //Copy constructor
        public CellNode(CellNode newCellNode) {
            this.cellPhone = newCellNode.cellPhone;
            this.next = newCellNode.next;
        }

        //Clone method
        public CellNode clone() {
            CellNode cloneCellNode = new CellNode(this.cellPhone, this.next);
            return cloneCellNode;
        }

        //Other mutator and accessor methods
        public CellPhone getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone;
        }

        public CellNode getNext() {
            return next;
        }

        public void setNext(CellNode next) {
            this.next = next;
        }
    }
}
