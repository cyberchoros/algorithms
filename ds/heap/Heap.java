package ds.heap;

public interface Heap<E> {
    public HeapNode<E> push (E item);
    public E pop ();

    public int size ();
    public void clear ();
}

abstract class BinaryHeap<E, T extends BHNode<E, T>> implements Heap<E> {
    T min;
    int num;

    @Override
    public HeapNode<E> push (E item) {
        T node = getInstance(item);
        
        min = merge(min, node);
        num += 1;

        return node;
    }

    abstract T getInstance (E item);

    @Override
    public E pop () {
        if (min == null) return null;

        E item = min.getItem();
        min = merge(min.left, min.right);
        num -= 1;

        return item;
    }

    abstract T merge (T node, T temp);

    void swap (T node) {
        T temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    @Override
    public void clear () {
        min = null;
        num = 0;
    }

    @Override
    public int size () { return num; }
}
