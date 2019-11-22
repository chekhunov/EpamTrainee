package ua.nure.chekhunov.practice2;

import java.util.Iterator;

/**
 * @author macbookpro Container extends Iterable<Object>
 */
public interface Container extends Iterable<Object> {

    /**
     * Removes all of the elements.
     */
    void clear();

    /**
     * @return Returns the number of elements.
     */
    int size();

    /**
     * @return Returns a string representation of this container.
     */
    String toString();

    /**
     * Returns an iterator over elements. Iterator must implements the remove
     * method.
     */
    Iterator<Object> iterator();

}
