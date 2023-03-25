package cs1302.p2;

import cs1302.adt.FancyStringList;
import cs1302.adt.StringList;
import java.io.IOException;

/**
 * Abstruct class BaseStringList.
 */
public abstract class BaseStringList implements FancyStringList {

    protected int size;

    /**
     * Constructor of BaseStringList.
     */
    public BaseStringList() {
        this.size = 0;
    }

    /**
     * Add a String item to the list.
     * 
     * @param item the item to be append to the list
     * @return true if operated successfully
     */
    // @Override
    public boolean append(String item) {
        this.add(size, item);
        return true;
    }

    /**
     * Check if the list is empty with size 0.
     * 
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of this string list that 
       begins with start and ends with end, 
       with every string in the string list separated by sep.
     *
     * @param start the starting string of the String
     * @param sep the seperation string of the String
     * @param end the ending string of the String
     * @return string representation
     */
    // @Override
    public String makeString(String start, String sep, String end) {
        String result = start;
        if (this.size == 0) {
            result = result + end;
        } else {
            int i;
            result = result + this.get(0);
            for (i = 1; i < this.size; i++) {
                result = result + sep + this.get(i);
            }
            result = result + end;
        }
        return result;
    }

    /**
     * Prepends an item to this string list 
       (i.e., it inserts the item at index 0).
     * 
     * @param item the item to be added in the list.
     * @return true if operated successfully
     */
    // @Override
    public boolean prepend(String item) {
        this.add(0, item);
        return true;
    }

    /**
     * Return the size of the array.
     * 
     * @return int size of the array
     */
    @Override
    public int size() {
        return size;
    }

    
    /**
     * Returns makeString("[", ", ", "]").
     * 
     * @return String all string in string list
     */
    @Override
    public String toString() {
        return makeString("[", ", ", "]");
    }

    /**
     * Inserts items into this string list at the specified index position.
     * 
     * @param index the specified index to be insert
     * @throws NullPointerException if items is null
     * @throws IndexOutOfBoundsException  if index is out of range 
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean add(int index, StringList items) {
        if (items.isEmpty()) {
            throw new NullPointerException("The items cannot be null!");
        } // throw nullpointerexception
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("The index is out of bounds!");
        }
        int i;
        for (i = 0; i < items.size(); i++) {
            this.add(index,items.get(items.size() - 1 - i));
        }
        return true;
    } // new add method

    /**
     * Appends items to this string list (i.e., it inserts the item at index size()).
       If items were already at that or subsequent positions,
       then those items are shifted to the right 
       (i.e., items.size() one is added to their indices).
     * 
     * @param items - string list of items to be inserted
     * @return true if this list changed as a result of the call
     * @throws NullPointerException - if items is null
     */
    @Override
    public boolean append(StringList items) {
        if (items.isEmpty()) {
            throw new NullPointerException("The items cannot be null!");
        } // NullPointerException
        this.add(this.size, items);
        return true;
    }

    /**
     * Returns true if exists an item at or after the start index that equals the target string.
       If no item, then false is returned. Unlike methods like prepend, add, and append 
       that throw exceptions when called with bad arguments,
       this contains method simply returns false instead.
     * 
     * @param start the index from which to start the search
     * @param target the item to search for
     * @return true if there exists an item at or after start such that item.equals(target), 
       or false if there is no such occurrence
     */
    @Override
    public boolean contains(int start, String target) {
        int i;
        for (i = start; i < this.size; i++) {
            if (target.equals(this.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first item at or after the start index.
     * If no such value of k exists, then -1 is returned.
     * 
     * @param start
     * @param target
     * @return the index of the first item at or after start that equals target,
       starting at start, or -1 if there is no such occurrence
     */
    @Override
    public int indexOf(int start, String target) {
        int x = start;
        int i;
        for (i = start; i < this.size; i++) {
            if (target.equals(this.get(i))) {
                return x;
            } else {
                x++;
            }
        }
        return -1;
    }
    
    /**
     * Prepends items to this string list (i.e., it inserts the item at index 0).
       If items were already at that or subsequent positions, 
       then those items are shifted to the right 
       (i.e., items.size() one is added to their indices).
     * 
     * @param items string list of items to be inserted
     * @
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean prepend(StringList items) {
        if (items.isEmpty()) {
            throw new NullPointerException("The items cannot be null");
        }
        this.add(0, items);
        return true;
    }
}
