package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Sort {
    public abstract <T> void sort (ArrayList<T> arr, Comparator<T> cmp);

    <T> void swap (ArrayList<T> arr, int i, int j) {
        T t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }
}
