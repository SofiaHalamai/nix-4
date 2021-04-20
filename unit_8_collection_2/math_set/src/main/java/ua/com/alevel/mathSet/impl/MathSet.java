package ua.com.alevel.mathSet.impl;

import ua.com.alevel.mathSet.Set;

import java.util.Objects;

public class MathSet<T extends Number> implements Set<T> {

    private static final int DEFAULT_CAPACITY = 20;
    private int size;
    private T[] elementSet;

    @SuppressWarnings("unchecked")
    public MathSet() {
        elementSet = (T[]) new Number[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MathSet(int capacity) {
        if (capacity >= 0) {
            elementSet = (T[]) new Number[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @SuppressWarnings("unchecked")
    public MathSet(T[] numbers) {
        elementSet = (T[]) new Number[DEFAULT_CAPACITY];
        for (T number : numbers) {
            add(number);
        }
    }

    @SuppressWarnings("unchecked")
    @SafeVarargs
    public MathSet(T[]... numbers) {
        elementSet = (T[]) new Number[DEFAULT_CAPACITY];
        for (T[] number : numbers) {
            for (T t : number) {
                add(t);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public MathSet(Set numbers) {
        elementSet = (T[]) new Number[DEFAULT_CAPACITY];
        joinSet(numbers);
    }

    @SuppressWarnings("unchecked")
    public MathSet(Set... numbers) {
        elementSet = (T[]) new Number[DEFAULT_CAPACITY];
        for (Set number : numbers) {
            joinSet(number);
        }
    }

    @SuppressWarnings("unchecked")
    private void joinSet(Set values) {
        T[] tmp = (T[]) values.toArray();
        for (T t : tmp) {
            add(t);
        }
    }

    @Override
    public void add(T n) {
        if (!checkForUniqueness(n) || size == 0) {
            if (size == elementSet.length - 1)
                resize(size * 2);
            elementSet[size++] = n;
        }
    }

    @SafeVarargs
    @Override
    public final void add(T... n) {
        for (T t : n)
            add(t);
    }

    @Override
    public void join(Set ms) {
        joinSet(ms);
    }

    @Override
    public void join(Set... ms) {
        for (Set set : ms) {
            joinSet(set);
        }
    }

    @Override
    public void sortDesc() {
        int size = this.size;
        sortDesc(0, size - 1);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        T tmp;
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = firstIndex; j <= lastIndex; j++) {
                if (elementSet[j].doubleValue() < elementSet[i].doubleValue()) {
                    tmp = elementSet[i];
                    elementSet[i] = elementSet[j];
                    elementSet[j] = tmp;
                }
            }
        }
    }

    @Override
    public void sortDesc(T value) {
        int size = this.size;
        if (getIndexByValue(value) != -1) {
            sortDesc(getIndexByValue(value), size - 1);
        } else throw new RuntimeException("No such element");
    }

    @Override
    public void sortAsc() {
        int size = this.size;
        sortAsc(0, size - 1);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        T tmp;
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = firstIndex; j <= lastIndex; j++) {
                if (elementSet[j].doubleValue() >= elementSet[i].doubleValue()) {
                    tmp = elementSet[i];
                    elementSet[i] = elementSet[j];
                    elementSet[j] = tmp;
                }
            }
        }
    }

    @Override
    public void sortAsc(T value) {
        int size = this.size;
        if (getIndexByValue(value) != -1) {
            sortAsc(getIndexByValue(value), size - 1);
        } else throw new RuntimeException("No such element");
    }

    private int getIndexByValue(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementSet[i].equals(value)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementSet[index];
    }

    @Override
    public T getMax() {
        int maxElement = 0;
        double tmp;
        double max = elementSet[0].doubleValue();
        for (int i = 0; i < size; ++i) {
            tmp = elementSet[i].doubleValue();
            if (tmp > max) {
                max = tmp;
                maxElement = i;
            }
        }
        return elementSet[maxElement];
    }

    @Override
    public T getMin() {
        int minElement = 0;
        double tmp;
        double min = elementSet[0].doubleValue();
        for (int i = 0; i < size; ++i) {
            tmp = elementSet[i].doubleValue();
            if (tmp < min) {
                min = tmp;
                minElement = i;
            }
        }
        return elementSet[minElement];
    }

    @Override
    public Double getAverage() {
        double sum = 0.;
        for (int i = 0; i < size; i++)
            sum += elementSet[i].doubleValue();
        return sum / size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Number getMedian() {
        int size = this.size;
        T[] tmpArr = (T[]) new Number[size];
        if (size >= 0) System.arraycopy(this.elementSet, 0, tmpArr, 0, size);
        T tmp;
        size--;
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (tmpArr[j].doubleValue() >= tmpArr[i].doubleValue()) {
                    tmp = tmpArr[i];
                    tmpArr[i] = tmpArr[j];
                    tmpArr[j] = tmp;
                }
            }
        }
        size = this.size;
        if (size % 2 != 0)
            return  tmpArr[this.size / 2];
        else
            return (tmpArr[this.size / 2].doubleValue() + tmpArr[this.size / 2 - 1].doubleValue()) / 2;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] set = (T[]) new Number[size];
        if (size >= 0) System.arraycopy(this.elementSet, 0, set, 0, size);
        return set;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        final int count = lastIndex - firstIndex + 1;
        T[] set = (T[]) new Number[count];
        if (size >= 0) System.arraycopy(this.elementSet, firstIndex, set, 0, count);
        return set;
    }

    @Override
    public Set<T> squash(int firstIndex, int lastIndex) {
        Objects.checkIndex(firstIndex, size);
        Objects.checkIndex(lastIndex, size);
        Set<T> tmp = new MathSet<>();
        for (int i = 0; i < firstIndex; i++) {
            tmp.add(elementSet[i]);
        }
        for (int i = lastIndex; i < size; i++) {
            tmp.add(elementSet[i]);
        }
        return tmp;
    }

    @Override
    public void clear() {
        final Object[] el = elementSet;
        for (int to = size, i = size = 0; i < to; i++)
            el[i] = null;
    }

    @Override
    public void clear(T[] numbers) {
        for (int i = 0; i < size; i++) {
            boolean present = false;
            for (T t : numbers) {
                if (elementSet[i].equals(t)) {
                    present = true;
                    break;
                }
            }
            if (present) {
                remove(elementSet[i]);
                i--;
            }
        }
    }

    private boolean remove(T t) {
        final T[] set = elementSet;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (t == null) {
                for (; i < size; i++)
                    if (set[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (t.equals(set[i]))
                        break found;
            }
            return false;
        }
        removeByIndexAndObject(set, i);
        return true;
    }

    private void removeByIndexAndObject(T[] set, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(set, index + 1, set, index, newSize - index);
        set[size = newSize] = null;
    }

    private boolean checkForUniqueness(T t) {
        for (int i = 0; i < size; i++) {
            if (elementSet[i].equals(t))
                return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newLength) {
        T[] newSet = (T[]) new Number[newLength];
        System.arraycopy(elementSet, 0, newSet, 0, size);
        elementSet = newSet;
    }
}
