/**
 * practice2
 */
package ua.nure.chekhunov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author macbookpro ListImpl implements List private static class Node private
 *         class IteratorImpl implements Iterator<Object>
 */
public class ListImpl implements List {

    /**
     * first number
     */
    private Node first;

    /**
     * last number
     */
    private Node last;

    /**
     * size
     */
    private int size;

    /**
     * {@link Object}.
     */
    private Object[] arr = {};

    /**
     * constructor
     */
    public ListImpl() {
        this.first = this.last = null;
        this.size = 0;
    }

    /**
     * clear
     */
    @Override
    public void clear() {

        arr = new Object[] {};
        this.size = 0;
    }

    /**
     * size
     */
    @Override
    public int size() {

        return size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov.Practice2.ListIterable#listIterator()
     */
    @Override
    public Iterator<Object> iterator() {

        return new IteratorImpl();
    }

    /**
     * addFirst
     */
    @Override
    public void addFirst(Object element) {

        Object[] tempArr = new Object[arr.length + 1];
        System.arraycopy(arr, 0, tempArr, 1, size);
        tempArr[0] = element;
        arr = tempArr;
        size++;
    }

    /**
     * addLast
     */
    @Override
    public void addLast(Object element) {

        Object[] arr2 = new Object[arr.length + 1];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        arr2[arr2.length - 1] = element;
        arr = arr2;
        size++;
    }

    /**
     * removeFirst
     */
    @Override
    public void removeFirst() {

        if (this.first == null) {
            this.first = this.last = null;
        }
        removed(0);

    }

    /**
     * removeLast
     */
    @Override
    public void removeLast() {

        int count = arr.length - 1;
        removed(count);

    }

    /**
     * removed
     * 
     * @param index
     */
    public void removed(int index) {
        Object[] arr2 = new Object[arr.length - 1];
        System.arraycopy(arr, 0, arr2, 0, index);
        System.arraycopy(arr, index + 1, arr2, index, arr2.length - index);
        arr = arr2;
        size--;
    }

    /**
     * getFirst
     */
    @Override
    public Object getFirst() {

        return arr[0];

    }

    /**
     * isEmpty
     */
    public void isEmpty() {
        if (this.first != null) {
            System.out.println("Список не пуст");
        }
        System.out.println("Список пуст");
    }

    /**
     * getLast return {@link Object}
     */
    @Override
    public Object getLast() {
        return arr[arr.length - 1];

    }

    /**
     * search
     */
    @Override
    public Object search(Object element) {
        Object str = "";
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == element) {
                str = arr[index];
            }
        }
        return str;
    }

    /**
     * remove
     */
    @Override
    public boolean remove(Object element) {

        Object[] arr2 = new Object[arr.length - 1];
        System.arraycopy(arr, 0, arr2, 0, 0);
        System.arraycopy(arr, 0 + 1, arr2, 0, arr2.length - 0);
        arr = arr2;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public String toString() {
        Iterator<Object> i = iterator();
        if (!i.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            Object e = i.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!i.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(", ");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    public Object[] toArray() {

        return this.arr.clone();

    }

    /**
     * @author macbookpro
     *
     */
    private static class Node {

        private Object element;
        private Node next;
        private Node prev;

        public Node(Object element) {
            this.element = element;
            this.next = null;
        }

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }

        public void setData(Object element) {
            this.element = element;
        }

        public Object getData() {
            return this.element;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    /**
     * @author macbookpro
     *
     */
    private class IteratorImpl implements Iterator<Object> {
        
        private boolean flag = true;
        
        private int pointer = -1;
        
        private boolean nextOrPrevious;
        
        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getPointer() {
            return pointer;
        }

        public void setPointer(int pointer) {
            this.pointer = pointer;
        }

        public boolean isNextOrPrevious() {
            return nextOrPrevious;
        }

        public void setNextOrPrevious(boolean nextOrPrevious) {
            this.nextOrPrevious = nextOrPrevious;
        }

        public boolean hasNext() {

            return (pointer < toArray().length - 1);

        }

        public Object next() {
            if (pointer >= size()) {
                throw new NoSuchElementException("Error No Such Element from next");
            }

            flag = false;
            nextOrPrevious = false;
            return toArray()[++pointer];
        }

        @Override
        public void remove() {

            if (isFlag()) {
                throw new IllegalStateException();
            }
            int i = 0;
            if (!isNextOrPrevious()) {
                i = pointer--;
            } else {
                i = ++pointer;
            }

            Object[] arr2 = new Object[arr.length - 1];
            System.arraycopy(arr, 0, arr2, 0, i);
            System.arraycopy(arr, i + 1, arr2, i, arr2.length - i);
            arr = arr2;

            flag = true;
            if (isNextOrPrevious()) {
                --pointer;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    public static void main(String[] args) {
        List list = new ListImpl();

        // add
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        System.out.println(list);
        System.out.println(list.size());

        list.clear();
        System.out.println(list);
        System.out.println(list.size());

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        System.out.println(list);
        System.out.println(list.size());

        list.addFirst("A");
        list.addLast("B");
        list.addFirst("C");

        System.out.println(list);
        System.out.print(list.getFirst());
        System.out.print(list.getLast());

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        Iterator<Object> it = list.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(list);

        System.out.println(list);
        System.out.println(list.search(1));
        System.out.println(list.search(2));
        System.out.println(list.getLast());
        System.out.println(list);
        Iterator<Object> it1 = list.iterator();

        list.addLast(1);
        list.addLast(2);
        list.addLast(1);

        list.remove(1);
        System.out.println(list);

        list.remove(2);
        System.out.println(list);

        System.out.println(it1.next());

    }

}
