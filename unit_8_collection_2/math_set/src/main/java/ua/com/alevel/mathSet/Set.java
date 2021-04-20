package ua.com.alevel.mathSet;

public interface Set <T extends Number> {
    void add(T n);
    void add(T ... n);
    void join(Set ms);
    void join(Set ... ms);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(T value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(T value);
    T get(int index);
    T getMax();
    T getMin();
    Double getAverage();
    Number getMedian();
    T [] toArray();
    T [] toArray(int firstIndex, int lastIndex);
    Set<T> squash(int firstIndex, int lastIndex);
    void clear();
    void clear(T [] numbers);
}
