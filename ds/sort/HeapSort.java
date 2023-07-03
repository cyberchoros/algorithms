package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapSort extends Sort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) {
        for (int k = arr.size() / 2 - 1; k >= 0; k--) percolate(arr, cmp, k, arr.size());

        for (int k = arr.size() - 1; k > 0; k--) {
            swap(arr, 0, k);
            percolate(arr, cmp, 0, k);
        }
    }

    public <T> void percolate (ArrayList<T> arr, Comparator<T> cmp, int m, int n) {
        T t = arr.get(m);

        while (2 * m + 1 < n) {
            int i = 2 * m + 1, j = 2 * (m + 1);
            int k = j < n && cmp.compare(arr.get(i), arr.get(j)) < 0 ? j : i;

            if (cmp.compare(arr.get(k), t) <= 0) break;

            arr.set(m, arr.get(k));
            m = k;
        }
        arr.set(m, t);
    }
}
