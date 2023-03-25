package cs1302.p2;

import java.io.IOException;
import cs1302.adt.FancyStringList;
import cs1302.adt.StringList;

/**
 * Class ArrayStringList.
 */
public class ArrayStringList extends BaseStringList {

    private static final int DEFAULT_CAPACITY = 10;
    private String[] items;


    /**
     * Construct a new arraystringlist with initial capacity.
     * 
     * @param capacity initial capactity of this array
     * @throws IllegalArgumentException if capacity is negative
     */
    public ArrayStringList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity of the list cannot be negative value.");
        } // throw exception
        items = new String[capacity];
        this.size = 0;
    }

    /**
     * Construct an ArrayStringList with Default Capactity.
     */
    public ArrayStringList() {
        this(DEFAULT_CAPACITY);
        this.size = 0;
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
    public ArrayStringList slice(int a, int b) {
        if (a < 0 || b > this.size || a > b) {
            throw new IndexOutOfBoundsException("The indexes are out of bound");
        } // throw index exception
        ArrayStringList newList = new ArrayStringList(Math.max((b - a), DEFAULT_CAPACITY));
        newList.size = (b - a);
        System.arraycopy(this.items, a, newList.items, 0, (b - a));
        return newList;
    }

    /**
     * Clear all the items in the list.
     */
    @Override
    public void clear() {
        this.items = new String[this.size];
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
        } // throw exception
        this.size = this.size - 1; // minus the size variable
        String item;
        item = this.items[a]; // record the item
        String[] newdata = new String[Math.max(this.size,DEFAULT_CAPACITY)];
        int i;
        for (i = 0; i < a; i++) {
            newdata[i] = items[i];
        } // copy the items from 0 to a
        for (i = a; i < this.size; i++) {
            if (items[i + 1] == null) {
                break; // if there is no more item after a, break loop
            } else {
                newdata[i] = items[i + 1];
            } // copy rest of the item after a
        }
        this.items = newdata;
        return item;
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
        }
        return items[a];
    }

    /**
     * Add an item to the list, if the list is full, create a new list and store the items.
     * 
     * @param index the position of the item to be added in the list.
     * @param item the string item to be added
     * @throws NullPointerException if the item is null
     * @throws IndexOutOfBoundsException if index is less than 0 or larger than size
     * @throws IllegalArgumentException if item is an empty string
     * @return
     */
    @Override
    public boolean add(int index, String item) {
        if (item == null) {
            throw new NullPointerException("The item cannot be null");
        } // throw nullpointerexception
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("The index is out of bounce");
        } // throw index exception
        if (item.equals("")) {
            throw new IllegalArgumentException("The item cannot be empty string");
        } // throw illegal argument exception
        this.size = this.size + 1;
        if (this.size < items.length) {
            // if the size of the array is enough
            String[] newdata = new String[this.items.length];
            int i;
            for (i = 0; i < index; i++) {
                newdata[i] = items[i];
            } // copy item till index
            newdata[index] = item;
            for (i = index + 1; i < this.size; i++) {
                newdata[i] = items[i - 1];
            } // copy item after index
            this.items = newdata;
        } else {
            // size of the array is not enough
            // create a new array with 120% storage
            String[] newdata = new String[(int)Math.ceil(this.items.length * 1.2)];
            int i;
            for (i = 0; i < index; i++) {
                newdata[i] = items[i];
            } // copy item till index
            newdata[index] = item;
            for (i = index + 1; i < this.size; i++) {
                newdata[i] = items[i - 1];
            } // copy item after index
            this.items = newdata;
        }
        return true;
    }

    /**
     * A copy constructor for this class. It should make the new list a deep copy of the other list.
     * 
     * @param other another string list
     */
    public ArrayStringList(StringList other) {
        this(Math.max(DEFAULT_CAPACITY, other.size()));
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
        FancyStringList newList = new ArrayStringList();
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
        FancyStringList newList = new ArrayStringList();
        int i;
        for (i = 0; i < this.size(); i++) {
            newList.add(i, this.get(size - 1 - i));
        }
        return newList;
    }

} // ArrayStringList
