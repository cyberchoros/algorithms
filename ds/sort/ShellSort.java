package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class ShellSort extends InsertionSort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) {
        for (int k = arr.size() / 2; k > 0; k /= 2) order(arr, cmp, k);
    }
}
