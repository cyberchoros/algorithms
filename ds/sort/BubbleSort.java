package ds.sort;

import java.util.ArrayList;
import java.util.Comparator;

public class BubbleSort extends Sort {
    @Override
    public <T> void sort (ArrayList<T> arr, Comparator<T> cmp) {
        for (int i = 0; i < arr.size(); i++)
            for (int j = arr.size() - 1; j > i; j--)
                if (cmp.compare(arr.get(j - 1), arr.get(j)) > 0) swap(arr, j - 1, j);
    }
}
