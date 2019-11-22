package ua.nure.chekhunov.practice2;

import java.util.Iterator;

/**
 * An iterator for lists that allows the programmer to traverse the list in
 * either direction, modify the list during iteration, and obtain the iterator's
 * current position in the list.<br>
 * Extends <tt>Iterator</tt> interface {@code Iterator}.
 * 
 * @author vydrya_vitaliy.
 * @version 1.0.
 */
interface ListIterator extends Iterator<Object> {
    /**
     * Returns true if this list iterator has more elements when traversing the list
     * in the reverse direction.
     * 
     * @return true - true if the iteration previous has more elements.
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the list and moves the cursor position
     * backwards.
     * 
     * @return previous element.
     * @throws NoSuchElementException if the iteration has no previous element
     */
    Object previous();

    /**
     * Replaces the last element returned by next or previous with the specified
     * element.
     * 
     * @param e - element replacement.
     * @throws IllegalStateException if neither {@code next} nor {@code previous}
     *                               have been called, or {@code remove} or
     *                               {@code add} have been called after the last
     *                               call to {@code next} or {@code previous}
     */
    void set(Object e);

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#remove()
     */
    @Override
    void remove();
}
