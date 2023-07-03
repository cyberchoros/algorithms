package ds.tree;

public class TreeNode<K, V> {
    private K index;
    private V value;

    TreeNode () {}

    TreeNode (K index, V value) {
        this.index = index;
        this.value = value;
    }

    public K getIndex () { return index; }
    public V getValue () { return value; }
    
    void setIndex (K index) { this.index = index; }
    void setValue (V value) { this.value = value; }
}

abstract class RecursiveNode<K, V, E extends RecursiveNode<K, V, E>> extends TreeNode<K, V> {
    E left, right;

    RecursiveNode (K index, V value) { super(index, value); }
}

abstract class IterativeNode<K, V, E extends IterativeNode<K, V, E>> extends RecursiveNode<K, V, E> {
    E top;

    IterativeNode (K index, V value) { super(index, value); }
}

class BSTNode<K, V> extends RecursiveNode<K, V, BSTNode<K, V>> { BSTNode (K index, V value) { super(index, value); } }

class AVLNode<K, V> extends RecursiveNode<K, V, AVLNode<K, V>> {
    int height;

    AVLNode (K index, V value) { super(index, value); }
}

class SPLNode<K, V> extends IterativeNode<K, V, SPLNode<K, V>> { SPLNode (K index, V value) { super(index, value); } }

class RBTNode<K, V> extends IterativeNode<K, V, RBTNode<K, V>> {
    Color color;

    RBTNode (K index, V value) { super(index, value); }    
}

enum Color { RED, BLACK; }
