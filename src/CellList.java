import java.util.NoSuchElementException;


/**
 * This is a CellList class
 */
public class CellList {
    private CellNode head;
    private int size;

    /**
     * Constructor
     *
     * @param head
     * @param size
     */
    public CellList(CellNode head, int size) {
        this.head = head;
        this.size = size;
    }


    /**
     * Default constructor, which creates an empty list
     */
    public CellList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Copy constructor, which accepts a CellList object and creates a copy of it
     *
     * @param cellList
     */
    public CellList(CellList cellList) {

        CellNode position = cellList.head;
        while (position != null) {
            addToStart(position.cellPhone);
            position = position.next;
            size++;
        }
        // The algorithm above will return a list with reversed order, so I reverse back.
        this.reverse();

    }

    /**
     * This method will reverse the order of a list
     * Source of this code: https://stackoverflow.com/questions/22605050/reverse-singly-linked-list-java
     */

    public void reverse() {
        CellNode previous = null;
        CellNode current = head;
        CellNode next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }


    /**
     * This method creates a node with that passed object and inserts this node at the head of the list;
     *
     * @param cellPhone
     */
    public void addToStart(CellPhone cellPhone) {
        head = new CellNode(cellPhone, head);
        size = size + 1;
    }

    /**
     * The method check if the index is not valid,
     * the method must throw a NoSuchElementException and terminate the program.
     *
     * @param index
     */
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


    /**
     * This method will find the object by its serial number
     *
     * @param serialNum
     * @return
     */
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


    /**
     * This method will check if serial number exists or not
     *
     * @param serialNum
     * @return
     */
    public boolean contains(long serialNum) {
        if (find(serialNum) == null)
            return false;
        return true;
    }


    /**
     * A method called showContents() which display the content of the list
     */
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


    /**
     * deleteFromStart() method
     */
    public void deleteFromStart() {
        if (size > 1) {
            head = head.next;
            size--;
        } else {
            System.out.println("Cannot delete because the array is empty.");
        }
    }


    /**
     * Replace at index method
     *
     * @param cellPhone
     * @param index
     */
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


    /**
     * a method called equals(), accepts one parameter of type CellList.
     */
    public boolean equals(CellList cellList) {
        if (cellList == null || cellList.size == 0) {
            return false;
        }
        CellNode thisCurrentNode = head;
        CellNode comparingCurrentNode = cellList.head;
        while (thisCurrentNode != null && comparingCurrentNode != null) {
            if (!thisCurrentNode.getCellPhone().equals(comparingCurrentNode.getCellPhone())) {
                return false;
            }
            thisCurrentNode = thisCurrentNode.getNext();
            comparingCurrentNode = comparingCurrentNode.getNext();
        }
        return true;
    }

    /**
     * Using this method as a variable to record if there are any duplicate items in the list.
     *
     * @param cellPhone
     * @return
     */
    public boolean hasDuplicateItems(CellPhone cellPhone) {

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

    /**
     * This method is going to insert an element to an index of the cell list
     *
     * @param cellPhone
     * @param index
     * @throws NoSuchElementException
     */
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


        /**
         * Default Constructor, which assigns both attributes to null
         */
        public CellNode() {
            this.cellPhone = null;
            this.next = null;
        }


        /**
         * Parameterized constructor that accepts two parameters
         *
         * @param c
         * @param n
         */
        public CellNode(CellPhone c, CellNode n) {
            this.cellPhone = c;
            this.next = n;
        }


        /**
         * Copy constructor
         *
         * @param newCellNode
         */
        public CellNode(CellNode newCellNode) {
            this.cellPhone = newCellNode.cellPhone;
            this.next = newCellNode.next;
        }


        /**
         * Clone method
         */
        public CellNode clone() {
            CellNode cloneCellNode = new CellNode(this.cellPhone, this.next);
            return cloneCellNode;
        }

        //Other mutator and accessor methods

        /**
         * Get the cellphone
         *
         * @return
         */
        public CellPhone getCellPhone() {
            return cellPhone;
        }

        /**
         * Set the cellphone
         *
         * @param cellPhone
         */
        public void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone;
        }

        /**
         * Get the next
         *
         * @return
         */
        public CellNode getNext() {
            return next;
        }

        /**
         * Set the next
         *
         * @param next
         */
        public void setNext(CellNode next) {
            this.next = next;
        }
    }
}