package ds.heap;

import java.util.ArrayList;

public class BinomialQueue<E extends Comparable<E>> implements Heap<E> {
    private ArrayList<BQNode<E>> queue;
    private int num;

    public BinomialQueue () { queue = new ArrayList<>(); }

    @Override
    public HeapNode<E> push (E item) {
        BQNode<E> node = new BQNode<>(item);
        ArrayList<BQNode<E>> arr = new ArrayList<>();

        arr.add(node);
        queue = queueMerge(queue, arr);
        num += 1;

        return node;
    }

    @Override
    public E pop () {
        if (num == 0) return null;

        BQNode<E> node = extractMin();
        queue = queueMerge(queue, node.child);
        num -= 1;

        return node.getItem();
    }

    private BQNode<E> extractMin () {
        int min = 0;

        for (int i = min + 1; i < queue.size(); i++)
            if (queue.get(i).getItem().compareTo(queue.get(min).getItem()) < 0) min = i;
        BQNode<E> node = queue.remove(min);

        return node;
    }

    private ArrayList<BQNode<E>> queueMerge(ArrayList<BQNode<E>> arr, ArrayList<BQNode<E>> tmp) {
        BQNode<E> node, temp, root = null;
        ArrayList<BQNode<E>> res = new ArrayList<>();

        for (int degree = 0; arr.size() > 0 || tmp.size() > 0; degree++) {
            node = arr.size() > 0 && arr.get(0).degree == degree ? arr.remove(0) : null;
            temp = tmp.size() > 0 && tmp.get(0).degree == degree ? tmp.remove(0) : null;

            if (node == null && temp == null || node != null && temp != null) {
                if (root != null) res.add(root);
                root = heapMerge(node, temp);
            }
            else if (root != null)
                root = node == null ? heapMerge(root, temp) : heapMerge(root, node);
            else res.add(node == null ? temp : node);
        }
        if (root != null) res.add(root);

        return res;
    }

    private BQNode<E> heapMerge (BQNode<E> node, BQNode<E> temp) {
        if (node == null) return temp;
        if (temp == null) return node;

        if (node.getItem().compareTo(temp.getItem()) > 0)
            return heapMerge(temp, node);
        node.child.add(temp);
        node.degree++;

        return node;
    }

    @Override
    public void clear () {
        queue = new ArrayList<>();
        num = 0;
    }

    @Override
    public int size () { return num; }
}
