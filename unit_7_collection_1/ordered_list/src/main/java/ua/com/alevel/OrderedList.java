package ua.com.alevel;

import java.util.*;

public class OrderedList<T extends Comparable<T>> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementList;

    @SuppressWarnings("unchecked")
    public OrderedList() {
        this.elementList = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public OrderedList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementList = (T[]) new Comparable[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementList = (T[]) new Comparable[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }


    @SuppressWarnings("unchecked")
    public OrderedList(Collection<? extends T> c) {
        final int collectionSize = c.size();
        elementList = (T[]) new Comparable[collectionSize * 2];
        int i = 0;
        for (T t : c) {
            elementList[i] = t;
            i++;
        }
        size = collectionSize;
        if (elementList.length != 1) {
            sortList();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object b : elementList) {
            if (b != null) {
                if (b.equals(o))
                    return true;
            }else return false;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new OrderedListIterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        T[] list = (T[]) new Comparable[size];
        if (size >= 0) System.arraycopy(this.elementList, 0, list, 0, size);
        return list;
    }

    @Override
    public boolean add(T t) {
        if (size == elementList.length - 1)
            resize(size * 2);
        elementList[size++] = t;
        if (size != 1)
            sortList();
        return true;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newLength) {
        T[] newList = (T[]) new Comparable[newLength];
        System.arraycopy(elementList, 0, newList, 0, size);
        elementList = newList;
    }


    @Override
    public boolean remove(Object o) {
        final Object[] el = elementList;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++)
                    if (el[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(el[i]))
                        break found;
            }
            return false;
        }
        removeByIndexAndObject(el, i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] collection = c.toArray();
        for (Object b : collection) {
            if (!contains(b)) {
                return false;
            }
        }
        return true;
    }

    private void removeByIndexAndObject(Object[] el, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(el, index + 1, el, index, newSize - index);
        el[size = newSize] = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection c) {
        for (Object t : c) {
            add((T) t);
        }
        return true;
    }

    @Override
    @Deprecated
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection c) {
        Objects.checkIndex(index, size);
        for (Object t : c) {
            add((T) t);
        }
        return true;
    }

    @Override
    public void clear() {
        final Object[] el = elementList;
        for (int to = size, i = size = 0; i < to; i++)
            el[i] = null;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementList[index];
    }

    @Override
    @Deprecated
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldValue = elementList[index];
        elementList[index] = element;
        if (elementList.length != 1)
            sortList();
        return oldValue;
    }

    @Override
    @Deprecated
    public void add(int index, T element) {
        Objects.checkIndex(index, size);
        if (size == elementList.length - 1)
            resize(size * 2);
        elementList[size++] = element;
        if (size != 1)
            sortList();
    }

    private void sortList() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (elementList[j].compareTo(elementList[j + 1]) > 0) {
                    T tmp = elementList[j];
                    elementList[j] = elementList[j + 1];
                    elementList[j + 1] = tmp;
                }
            }
        }
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] el = elementList;
        @SuppressWarnings("unchecked") T oldValue = (T) el[index];
        removeByIndexAndObject(el, index);
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementList[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementList[i])) {
                    return i;
                }
            }
        }
        return -1;

    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementList[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementList[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new OrderedListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new OrderedListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> sub = new OrderedList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            sub.add(elementList[i]);
        }
        return sub;
    }

    @Override
    public boolean retainAll(Collection c) {
        for (int i = 0; i < size; i++) {
            boolean present = false;
            for (Object o : c) {
                if (elementList[i].equals(o)) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                remove(elementList[i]);
                i--;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        for (int i = 0; i < size; i++) {
            boolean present = false;
            for (Object o : c) {
                if (elementList[i].equals(o)) {
                    present = true;
                    break;
                }
            }
            if (present) {
                remove(elementList[i]);
                i--;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(elementList, size, a.getClass());
        else {
            System.arraycopy(elementList, 0, elementList, 0, size);
            return a;
        }
    }


    private class OrderedListIterator extends Itr implements ListIterator<T> {

        public OrderedListIterator(int i) {
            this.cursor = i;
        }

        public OrderedListIterator() {

        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            cursor--;
            return OrderedList.this.elementList[cursor + 1];
        }

        @Override
        public int nextIndex() {
            return cursor++;
        }

        @Override
        public int previousIndex() {
            return cursor--;
        }

        @Override
        @Deprecated
        public void set(T t) {
            OrderedList.this.set(this.lastElement, t);
        }

        @Override
        @Deprecated
        public void add(T t) {
            OrderedList.this.add(t);
        }
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int lastElement = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementList = OrderedList.this.elementList;
            if (i >= elementList.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) elementList[lastElement = i];
        }

        @Override
        public void remove() {
            if (lastElement < 0)
                throw new IllegalStateException();
            try {
                OrderedList.this.remove(lastElement);
                cursor = lastElement;
                lastElement = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}