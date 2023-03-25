package cs1302.p2;

import cs1302.adt.FancyStringList;
import cs1302.adt.StringList;
import cs1302.adt.Node;
import java.io.IOException;

/**
 * Class LinkedStringList.
 */
public class LinkedStringList extends BaseStringList {

    private Node head; // head node

    /**
     * Construct an LinkedStringList with head node points to null.
     */
    public LinkedStringList() {
        this.size = 0; // initialise size
    }

    /**
     * Slice the String List from a to b and return the items
       in a new list from position 0.
     *
     * @param a the starting position of the slice
     * @param b the ending position of the slice
     * @throws IndexOutOfBoundsException if index is less than 0
       or larger than size or a is larger than b
     * @return a new list that contains the items
     */
    @Override
    public LinkedStringList slice(int a, int b) {
        if (a < 0 || b > this.size || a > b) {
            throw new IndexOutOfBoundsException("The indexes are out of bound");
        } // throw index exception
        LinkedStringList newList = new LinkedStringList();
        int i;
        newList.head = this.head; // in case int a is 0
        for (i = 0; i < a; i++) {
            newList.head = newList.head.getNext();
        } // update the head to index a
        Node curr1 = newList.head;
        Node curr2 = newList.head;
        for (i = 0; i < (b - a); i++) {
            if (curr2.hasNext()) {
                curr2 = curr2.getNext(); // get the next item
                Node temp = new Node(curr2.getItem());  // created to cut the link
                curr1 = new Node(curr1.getItem(),temp); // link is up to temp
                curr1 = curr1.getNext(); // keep loop
            }
        }
        newList.size = (b - a);
        return newList;
    }

    /**
     * Clear all the items in the list.
     */
    @Override
    public void clear() {
        this.head = new Node("");
        this.size = 0;
    }

    /**
     * Remove the item at index a and return the item.
     * 
     * @param a index of the item to be removed
     * @throws IndexOutOfBoundsException if the index is less than 0 or larger than size
     * @return string item removed
     */
    @Override
    public String remove(int a) {
        if (a > this.size - 1 || a < 0) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        String item;
        if (this.size == 1) {
            // for list with 1 node
            item = this.head.getItem();
            this.head.setItem(null);
            this.size = this.size - 1;
            return item;
        }
        if (a == 0) {
            // for list need to remove the head
            item = this.head.getItem();
            this.head = this.head.getNext();
            this.size = this.size - 1;
            return item;
        } else {
            // remove something in the middle of the list
            Node prev = head;
            Node curr = head.getNext();
            int i;
            for (i = 0; i < a - 1; i++) {
                prev = prev.getNext();
                curr = curr.getNext();
            }
            item = curr.getItem();
            prev.setNext(curr.getNext());
            this.size = this.size - 1;
            return item;
        }
    }

    /**
     * Get an item at index a.
     * 
     * @param a index of the item to be visited
     * @throws IndexOutofBoundsException if the index is less than 0 or larger than list size
     * @return String item
     */
    @Override
    public String get(int a) {
        if (a < 0 || a > this.size - 1) {
            throw new IndexOutOfBoundsException("Not yet implemented");
        } // throw exception
        int i;
        Node item = head;
        for (i = 0; i < a; i++) {
            item = item.getNext();
        } // store the item
        return item.getItem();
    }

    /**
     * Add an item to the list.
     * 
     * @param index the position of the item to be added in the list.
     * @param item the string item to be added
     * @throws NullPointerException if the item is null
     * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
     * @throws IllegalArgumentException if item is an empty string
     * @return true if successfully added
     */
    @Override
    public boolean add(int index, String item) {
        if (item == null) {
            throw new NullPointerException("The item cannot be null");
        } // throw exception if item is null
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("The index is out of bounce");
        } // throw exception if index is wrong
        if (item.equals("")) {
            throw new IllegalArgumentException("The item cannot be empty string");
        } // throw exception if empty string

        Node itemnode = new Node(item);
        if (index == 0) {
            // for creating new head
            Node curr0 = this.head;
            itemnode.setNext(curr0);
            this.head = itemnode;
        } else {
            // to add node in middle
            Node curr1 = this.head;
            int i;
            for (i = 0; i < index; i++) {
                curr1 = curr1.getNext();
            }
            itemnode.setNext(curr1);
            Node curr2 = this.head;
            for (i = 0; i < index - 1; i++) {
                curr2 = curr2.getNext();
            }
            curr2.setNext(itemnode);
        }
        this.size = this.size + 1;
        return true;
    }

    /**
     * A copy constructor for this class. It should make the new list a deep copy of the other list.
     * 
     * @param other another string list
     */
    public LinkedStringList(StringList other) {
        this.size = 0;
        int i;
        for (i = 0; i < other.size(); i++) {
            this.add(0, other.get(other.size() - 1 - i));
        }
    }


    /**
     * Returns a new fancy string list that contains the items from 
       this list between the specified start index (inclusive) and 
       stop index (exclusive) by step.
     * 
     * @param start left endpoint (inclusive) of the slice
     * @param stop right endpoint (exclusive) of the slice
     * @param step step amount
     * @return a new fancy string list with the items 
       from this list from start (inclusive) to stop (exclusive) by step
     * @throws IndexOutOfBoundsException for an illegal endpoint index or step value 
       (start < 0 || stop > size() || start > stop || step < 1)
     */
    @Override
    public FancyStringList slice(int start, int stop, int step) {
        if (start < 0 || stop > this.size || start > stop || step < 1) {
            throw new IndexOutOfBoundsException("Incorrect input for the method");
        }
        FancyStringList newList = new LinkedStringList();
        int i;
        for (i = 0; i < (stop - start); i++) {
            if (i % step == 0) {
                newList.append(this.get(i + start));
            }
        }
        return newList;
    }

    /**
     * Returns a new fancy string list that contains the items from this list in reverse order.
     * 
     * @return a new fancy string list with items from this list in reverse order
     */
    @Override 
    public FancyStringList reverse() {
        FancyStringList newList = new LinkedStringList();
        int i;
        for (i = 0; i < this.size(); i++) {
            newList.add(i, this.get(size - 1 - i));
        }
        return newList;
    }

} // LinkedStringList
