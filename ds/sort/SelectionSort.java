package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class SelectionSort extends Sort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) {
        for (int i = 0; i < arr.size(); i++)
            for (int j = i + 1; j < arr.size(); j++)
                if (cmp.compare(arr.get(i), arr.get(j)) > 0) swap(arr, i, j);
    }
}
