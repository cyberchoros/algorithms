package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class QuickSort extends Sort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) { iter(arr, cmp, 0, arr.size()); }

    private <T> void iter (ArrayList<T> arr, Comparator<T> cmp, int m, int n) {
        if (n - m <= 1) return;

        int i = m + 1, j = n - 1;

        while (i < j) {
            while (i < j && cmp.compare(arr.get(j), arr.get(m)) >= 0) j--;
            while (i < j && cmp.compare(arr.get(i), arr.get(m)) < 0) i++;
            swap(arr, i, j);
        }
        if (cmp.compare(arr.get(j), arr.get(m)) < 0)
            swap(arr, m, j);
        else j--;

        iter(arr, cmp, m, j);
        iter(arr, cmp, j + 1, n);
    }
}
