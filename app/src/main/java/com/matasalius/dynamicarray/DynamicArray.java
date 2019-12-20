package com.matasalius.dynamicarray;

public class DynamicArray{
    private Object[] array;
    private int length = 0;     // filled array size
    private int capacity = 0;   // real array size

    public DynamicArray() {
        capacity = 1;
        array = new Object[1];
    }

    public DynamicArray(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Illegal capacity: " + size);

        capacity = size;
        array = new Object[capacity];
    }

    /**
     * @return filled array size
     */
    public int size() {
        return length;
    }

    /**
     * @return real array size
     */
    public int realSize() {
        return capacity;
    }

    /**
     * @return checks if array is empty
     */
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }

    /**
     * @param index
     * @return index position value
     */
    public Object get(int index) {
        return array[index];
    }

    /**
     * replace index position value with new value
     * @param index
     * @param value
     */
    public void set(int index, Object value) {
        array[index] = value;
    }

    /**
     * add value to the end of array
     * @param value
     */
    public void add(Object value) {
        if (length + 1 >= capacity) {
            if (capacity == 0)
                capacity = 1;
            else
                capacity *= 2;

            Object[] newArray = new Object[capacity];

            for (int i = 0; i < length; i++)
                newArray[i] = array[i];

            array = newArray;
        }

        array[length++] = value;
    }

    /**
     * deletes array
     */
    public void clearAll() {
        for (int i = 0; i < length; i++)
            array[i] = null;

        length = 0;
    }

    /**
     * removes index position value
     * @param index
     * @return deleted value
     */
    public Object removeAt(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException();

        Object data = array[index];
        Object[] newArray = new Object[length - 1];

        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i == index)
                j--;
            else
                newArray[j] = array[i];
        }
        array = newArray;
        capacity = --length;

        return data;
    }

    /**
     * removes wanted value from array
     * @param lap
     * @return
     */
    public boolean remove(Object lap) {
        int index = indexOf(lap);
        if (index == -1)
            return false;

        removeAt(index);
        return true;
    }

    /**
     * @param lap
     * @return value position in array
     */
    public int indexOf(Object lap) {
        for (int i = 0; i < length; i++) {
            if (lap == null) {
                if (array[i] == null)
                    return i;
            }
            else {
                if (lap.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * checks if array contains value
     * @param lap
     * @return
     */
    public boolean contains(Object lap) {
        return indexOf(lap) != -1;
    }

    @Override
    public String toString() {
        if (length == 0)
            return "[] - array is empty";
        else {
            StringBuilder sb = new StringBuilder(length);

            for (int i =0; i< length; i++) {
                if (array[i] != null)
                    sb.append(array + "\n");
            }
            return sb.toString();
        }
    }

}
