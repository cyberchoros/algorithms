Sample
```
import ds.sort.*;

public class Item {
    private String value;
    public String getVal () { return value; }
}

ArrayList<Item> arr = new ArrayList<>();

...

new QuickSort().sort(arr, (item1, item2) -> { return item1.compareTo(item2); });
```

Note:

The second parameter should implement `java.util.Comparator` interface [implement compare() method]
