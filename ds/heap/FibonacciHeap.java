package ds.heap;

import java.util.HashMap;

public class FibonacciHeap<E extends Comparable<E>> implements Heap<E> {
    FHNode<E> min;
    int num;

    @Override
    public HeapNode<E> push (E item) {
        FHNode<E> node = new FHNode<>(item);

        if (min == null)
            node.left = node.right = node;
        else concat(min.left, min, node, node);

        if (min == null || node.getItem().compareTo(min.getItem()) < 0) min = node;
        num++;

        return node;
    }

    private void concat (FHNode<E> root, FHNode<E> next, FHNode<E> node, FHNode<E> temp) {
        attach(root, node);
        attach(temp, next);
    }

    private void attach (FHNode<E> node, FHNode<E> temp) {
        node.right = temp;
        temp.left = node;
    }

    public void decreaseKey (HeapNode<E> node, E item) {
        if (!(node instanceof FHNode<E>)) return;

        FHNode<E> temp = ((FHNode<E>)node).top;
        node.setItem(item);

        if (temp != null && temp.getItem().compareTo(node.getItem()) <= 0) return;
        if (temp != null) update((FHNode<E>)node);
        if (node.getItem().compareTo(min.getItem()) < 0) min = (FHNode<E>)node;
        if (temp == null) return;

        upgrade(temp);
    }

    private void upgrade (FHNode<E> node) {
        while (node.top != null && node.marked) {
            FHNode<E> temp = node.top;

            update(node);
            node = temp;
        }
        node.marked = node.top == null ? false : true;
    }

    private void update (FHNode<E> node) {
        if (node.top.next == node) node.top.next = node.left == node ? null : node.left;

        attach(node.left, node.right);

        node.top.degree--;
        node.top = null;
        node.marked = false;

        concat(min.left, min, node, node);
    }

    @Override
    public E pop () {
        if (num == 0) return null;

        FHNode<E> temp = min;

        min = extractMin();
        num--;

        if (min != null) {
            min.left.right = null;
            heapMerge();
        }
        return temp.getItem();
    }

    private FHNode<E> extractMin () {
        if (min == min.left) return min.next;

        if (min.next == null) {
            attach(min.left, min.right);
            return min.left;
        }
        concat(min.left, min.right, min.next, min.next.left);

        return min.next;
    }

    private void heapMerge () {
        HashMap<Integer, FHNode<E>> map = new HashMap<>();
        FHNode<E> root = new FHNode<>(null), temp = min;

        attach(root, root);

        while (min != null) {
            FHNode<E> node = min;

            min = min.right;
            node = merge(map, root, node);

            if (node.getItem().compareTo(temp.getItem()) <= 0) temp = node;
        }
        min = temp;

        attach(root.left, root.right);
    }

    private FHNode<E> merge (HashMap<Integer, FHNode<E>> map, FHNode<E> root, FHNode<E> node) {
        attach(node, node);

        while (map.get(node.degree) != null) {
            FHNode<E> temp = map.remove(node.degree);

            attach(temp.left, temp.right);
            attach(temp, temp);

            node = nodeMerge(node, temp);
        }
        map.put(node.degree, node);

        node.top = null;
        concat(root.left, root, node, node);

        return node;
    }

    private FHNode<E> nodeMerge (FHNode<E> node, FHNode<E> temp) {
        if (temp.getItem().compareTo(node.getItem()) < 0) return nodeMerge(temp, node);

        temp.top = node;
        node.degree++;

        if (node.next == null)
            node.next = temp;
        else concat(node.next.left, node.next, temp, temp);

        return node;
    }

    @Override
    public void clear () {
        min = null;
        num = 0;
    }

    @Override
    public int size () { return num; }
}
