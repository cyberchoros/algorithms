package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort extends Sort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) { divide(arr, cmp, 0, arr.size()); }

    private <T> void divide (ArrayList<T> arr, Comparator<T> cmp, int m, int n) {
        if (n - m <= 1) return;

        divide(arr, cmp, m, (n + m) / 2);
        divide(arr, cmp, (n + m) / 2, n);

        merge(arr, cmp, m, n);
    }

    private <T> void merge (ArrayList<T> arr, Comparator<T> cmp, int m, int n) {
        int i = m, j = (m + n) / 2;
        ArrayList<T> buffer = new ArrayList<>();

        while (i < (m + n) / 2 && j < n) {
            if (cmp.compare(arr.get(i), arr.get(j)) < 0)
                buffer.add(arr.get(i++));
            else buffer.add(arr.get(j++));
        }
        while (i < (m + n) / 2) buffer.add(arr.get(i++));
        while (j < n) buffer.add(arr.get(j++));

        for (int k = m; k < n; k++) arr.set(k, buffer.get(k - m));
    }
}
