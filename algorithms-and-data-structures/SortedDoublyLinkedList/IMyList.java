public interface IMyList {
    /**
     * Returns the current size of the list by returning the number of elements of the list in O(1).
     */
    int getSize();


    /**
     * Clears the list (in O(1))
     */
    void clear();


    /**
     * Adds a node containing the value val to the list, keeping the list sorted in ascending order.
     * In case of duplicates the new element is inserted either before or after the existing duplicates.
     *
     * @param val to be inserted into the list.
     */
    void insertElem(int val);


    /**
     * Returns (no removal!) the value val of the element at specific list index position (0 ≤ index < size).
     * If the index position is out of range, an Exception must be thrown.
     *
     * @param index position in the list
     * @return the value of the element at the given index position
     * @throws IllegalArgumentException if the given index position is out of range.
     */
    int getElem(int index) throws IllegalArgumentException;


    /**
     * Removes the first occurrence of the value val from the list and returns true if successful,
     * otherwise returns false.
     *
     * @param val to be removed
     * @return true if element was found and removed, false otherwise.
     */
    boolean removeFirst(int val);


    /**
     * Removes all occurrences of the value val from the list and returns true if successful,
     * otherwise returns false.
     *
     * @param val to be removed
     * @return true if elements were found and removed, false otherwise.
     */
    boolean removeAll(int val);


    /**
     * Removes all duplicates in the list.
     */
    void removeDuplicates();


    /**
     * Filters the list to the last numOfElements values (0 < numOfElements ≤ size).
     * If the list contains less elements, an Exception must be thrown.
     *
     * @param numOfElems to be used
     */
    void filterNMax(int numOfElements) throws IllegalArgumentException;


    /**
     * Removes all even values from the list, so the resulting list only contains odd values.
     */
    void filterOdd();


    /**
     * Searches for the element in the list, containing the value given, and returns the position.
     * If the given element is not found in the list, -1 is returned.
     *
     * @param val to be found
     * @return position of found element
     */
    int searchValue(int val);


    MyListNode getHead();            //Returns the head of your list

    MyListNode getTail();            //Returns the tail of your list
}
