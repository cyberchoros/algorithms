```
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ds.heap.*;

public class Test {
    private static final int SIZE = 100000;

    public static void main (String[] args) {
        HashMap<String, Heap<Integer>> map = new HashMap<>();

        map.put("pq", new PriorityQueue<>());
        map.put("bq", new BinomialQueue<>());
        map.put("sh", new SkewHeap<>());
        map.put("lh", new LeftistHeap<>());
        map.put("fh", new FibonacciHeap<>());

        String param = args.length > 0 && map.containsKey(args[0]) ? args[0] : "fh";
        Heap<Integer> bheap = map.get(param);
                
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < 3; j++) bheap.push(new Random().nextInt(10 * SIZE));

            int temp = new Random().nextInt(9 * SIZE) + SIZE;
            HeapNode<Integer> node = bheap.push(temp);
            for (int j = 0; j < 3; j++) bheap.push(new Random().nextInt(10 * SIZE));

            if (bheap instanceof FibonacciHeap<Integer>)
                ((FibonacciHeap<Integer>)bheap).decreaseKey(node, new Random().nextInt(temp));
            for (int j = 0; j < 3; j++) bheap.push(new Random().nextInt(10 * SIZE));

            for (int j = 0; j < 5; j++) bheap.pop();
        }

        int prev = 0;

        while (bheap.size() > 10) {
            int curr = bheap.pop();
            if (prev > curr) System.out.println("error");
            prev = curr;
        }

        while (bheap.size() > 0) System.out.println(bheap.pop());

        bheap.clear();
    }
}
```

Note: `key` should implements Comparable, and basic data type should be replaced with `wrapper class`.
