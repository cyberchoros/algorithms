package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class InsertionSort extends Sort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) { order(arr, cmp, 1); }

    <T> void order (ArrayList<T> arr, Comparator<T> cmp, int step) {
        for (int j = step; j < arr.size(); j++) {
            T t = arr.get(j);
            int i = j - step;

            while (i >= 0 && cmp.compare(arr.get(i), t) > 0) {
                arr.set(i + step, arr.get(i));
                i -= step;
            }
            arr.set(i + step, t);
        }
    }
}
