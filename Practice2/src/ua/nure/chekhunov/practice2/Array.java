package ua.nure.chekhunov.practice2;

/**
 * @author macbookpro
 *
 */
public interface Array extends Container {

    /**
     * Add the specified element to the end.
     * 
     * @param element - add item.
     */
    void add(Object element);

    /**
     * Sets the element at the specified position.
     * 
     * @param index   - find count add element
     * @param element - add Object element
     * @see ua.nure.chekhunov.Practice2.Array#set(java.lang.Object)
     */
    void set(int index, Object element);

    /**
     * Returns the element at the specified position.
     * 
     * @param index
     * @return - (java.lang.Object)
     */
    Object get(int index);

    /**
     * Returns the index of the first occurrence of the specified element, or -1 if
     * this array does not contain the element. (use 'equals' method to check an
     * occurrence)
     * 
     * @param element
     * @return < -int >
     */
    int indexOf(Object element);

    /**
     * Removes the element at the specified position.
     * @param index
     */
    void remove(int index);

    /**
     * clear
     */
    void clear();

    /**
     * @return Object[]
     */
    Object[] toArray();

}
