package ds.heap;

import java.util.ArrayList;
import ds.sort.HeapSort;

public class PriorityQueue<E extends Comparable<E>> implements Heap<E> {
    private ArrayList<E> queue;

    public PriorityQueue () { queue = new ArrayList<>(); }

    @Override
    public HeapNode<E> push (E item) {
        queue.add(item);
        percolate(queue.size() - 1);

        return null;
    }

    @Override
    public E pop () {
        if (queue.size() == 0) return null;

        E item = queue.remove(0);

        if (queue.size() > 0) {
            E elem = queue.remove(queue.size() - 1);
            queue.add(0, elem);

            new HeapSort().percolate(queue, (node, temp) -> { return temp.compareTo(node); }, 0, queue.size());
        }
        return item;
    }

    private void percolate (int i) {
        E item = queue.get(i);

        while (i > 0) {
            int j = (i - 1) / 2;
            if (queue.get(j).compareTo(item) <= 0) break;

            queue.set(i, queue.get(j));
            i = j;
        }
        queue.set(i, item);
    }

    @Override
    public void clear () { queue = new ArrayList<>(); }

    @Override
    public int size () { return queue.size(); }
}
