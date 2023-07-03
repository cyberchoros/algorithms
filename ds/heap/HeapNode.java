package ds.heap;

import java.util.ArrayList;

public abstract class HeapNode<E> {
    private E item;

    HeapNode () {}
    HeapNode (E item) { this.item = item; }

    E getItem () { return item; }
    void setItem (E item) { this.item = item; }
}

class BQNode<E> extends HeapNode<E> {
    ArrayList<BQNode<E>> child;
    int degree;

    BQNode (E item) {
        super(item);
        child = new ArrayList<>();
    }
}

abstract class BHNode <E, T extends BHNode<E, T>> extends HeapNode<E> {
    T left, right;

    BHNode (E item) { super(item); }
}

class SHNode<E> extends BHNode<E, SHNode<E>> { SHNode (E item) { super(item); } }

class LHNode<E> extends BHNode<E, LHNode<E>> {
    int npl;

    LHNode (E item) { super(item); }
}

class FHNode<E> extends BHNode<E, FHNode<E>> {
    FHNode<E> top, next;
    int degree;
    boolean marked;

    FHNode (E item) { super(item); }
}
