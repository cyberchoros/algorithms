package ds.tree;

import java.util.ArrayList;

public class SPL<K extends Comparable<K>, V> extends IterativeTree<K, V, SPLNode<K, V>> {
    @Override
    public V insert (K index, V value) {
        ArrayList<SPLNode<K, V>> arr = select(index);
        SPLNode<K, V> node = arr.get(0);

        V temp = node == null ? null : node.getValue();

        if (node == null)
            node = create(arr.get(1), index, value);
        else node.setValue(value);

        root = splay(node);

        return temp;
    }

    private SPLNode<K, V> create (SPLNode<K, V> temp, K index, V value) {
        SPLNode<K, V> node = new SPLNode<>(index, value);
        node.top = temp;

        if (temp == null)
            root = node;
        else if (temp.getIndex().compareTo(index) > 0)
            temp.left = node;
        else temp.right = node;

        num += 1;

        return node;
    }

    @Override
    public V delete (K index) {
        SPLNode<K, V> node = select(index).get(0);

        if (node == null) return null;

        root = splay(node);
        V temp = node.getValue();
        num -= 1;

        if (node.left == null || node.right == null) {
            root = node.left == null ? node.right : node.left;
            if (root != null) root.top = null;
        }
        else alter();

        return temp;
    }

    private void alter () {
        SPLNode<K, V> temp = root.left, node = root.right;
        node.top = null;

        while (node.left != null) node = node.left;
        root = splay(node);

        root.left = temp;
        temp.top = root;
    }

    @Override
    public V update (K index, V value) {
        SPLNode<K, V> node = select(index).get(0);
        V temp = node == null ? null : node.getValue();

        if (node != null) {
            root = splay(node);
            node.setValue(value);
        }
        return temp;
    }

    @Override
    public V search (K index) {
        SPLNode<K, V> node = select(index).get(0);
        
        if (node == null) return null;
        root = splay(node);

        return node.getValue();
    }

    private SPLNode<K, V> splay (SPLNode<K, V> node) {
        if (node.top == null) return node;

        if (node.top.top == null) return node.top.left == node ? leftRotate(node) : rightRotate(node);

        if (node.top.top.left == node.top)
            node = node.top.left == node ? leftLeft(node) : leftRight(node);
        else node = node.top.right == node ? rightRight(node) : rightLeft(node);

        return splay(node);
    }

    private SPLNode<K, V> leftLeft (SPLNode<K, V> node) {
        leftRotate(node.top);
        return leftRotate(node);
    }

    private SPLNode<K, V> leftRight (SPLNode<K, V> node) {
        rightRotate(node);
        return leftRotate(node);
    }

    private SPLNode<K, V> rightRight (SPLNode<K, V> node) {
        rightRotate(node.top);
        return rightRotate(node);
    }

    private SPLNode<K, V> rightLeft (SPLNode<K, V> node) {
        leftRotate(node);
        return rightRotate(node);
    }
}

abstract class IterativeTree<K extends Comparable<K>, V, E extends IterativeNode<K, V, E>> extends BinaryTree<K, V, E> {
    ArrayList<E> select (K index) {
        E node = root, temp = null;

        while (node != null && node.getIndex().compareTo(index) != 0) {
            temp = node;
            node = node.getIndex().compareTo(index) > 0 ? node.left : node.right;
        }
        ArrayList<E> arr = new ArrayList<>();

        arr.add(node);
        arr.add(temp);

        return arr;
    }

    E leftRotate (E node) {
        E temp = rotate(node);

        temp.left = node.right;
        node.right = temp;
        if (temp.left != null) temp.left.top = temp;

        return node;
    }

    E rightRotate (E node) {
        E temp = rotate(node);

        temp.right = node.left;
        node.left = temp;
        if (temp.right != null) temp.right.top = temp;

        return node;
    }

    private E rotate (E node) {
        E temp = node.top;
        node.top = temp.top;
        temp.top = node;

        if (node.top == null)
            root = node;
        else if (node.top.left == temp)
            node.top.left = node;
        else node.top.right = node;

        return temp;
    }
}
