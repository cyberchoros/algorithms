package ds.tree;

import java.util.ArrayList;

public class RBT<K extends Comparable<K>, V> extends IterativeTree<K, V, RBTNode<K, V>> {
    @Override
    public V insert (K index, V value) {
        ArrayList<RBTNode<K, V>> arr = select(index);
        RBTNode<K, V> node = arr.get(0);

        V temp = node == null ? null : node.getValue();

        if (node == null)
            create(arr.get(1), index, value);
        else node.setValue(value);

        return temp;
    }

    private void create (RBTNode<K, V> temp, K index, V value) {
        RBTNode<K, V> node = new RBTNode<>(index, value);
        node.top = temp;
        node.color = Color.RED;

        if (temp == null)
            root = node;
        else if (temp.getIndex().compareTo(index) > 0)
            temp.left = node;
        else temp.right = node;

        insertHandle(node);
        num += 1;
    }

    private RBTNode<K, V> insertHandle (RBTNode<K, V> node) {
        if (node.top == null) {
            node.color = Color.BLACK;
            root = node;
            return node;
        }
        if (node.top.color == Color.BLACK) return node;

        RBTNode<K, V> temp = node.top.top;

        if (temp != null)
            return temp.left == node.top ? leftInsert(node) : rightInsert(node);
        node.top.color = Color.BLACK;

        return node;
    }

    private RBTNode<K, V> leftInsert (RBTNode<K, V> node) {
        RBTNode<K, V> temp = node.top.top;

        if (temp.right != null && temp.right.color == Color.RED) {
            node.top.color = temp.right.color = Color.BLACK;
            temp.color = Color.RED;
            return insertHandle(temp);
        }
        if (node.top.right == node) {
            rightRotate(node);
            return leftInsert(node.left);
        }
        node.top.color = Color.BLACK;
        temp.color = Color.RED;

        return leftRotate(node.top);
    }

    private RBTNode<K, V> rightInsert (RBTNode<K, V> node) {
        RBTNode<K, V> temp = node.top.top;

        if (temp.left != null && temp.left.color == Color.RED) {
            node.top.color = temp.left.color = Color.BLACK;
            temp.color = Color.RED;
            return insertHandle(temp);
        }
        if (node.top.left == node) {
            leftRotate(node);
            return rightInsert(node.right);
        }
        node.top.color = Color.BLACK;
        temp.color = Color.RED;
        
        return rightRotate(node.top);
    }

    @Override
    public V delete (K index) {
        RBTNode<K, V> node = select(index).get(0);
        if (node == null) return null;

        V temp = node.getValue();
        if (node.left != null && node.right != null) node = alter(node);

        deleteHandle(node);
        remove(node);

        return temp;
    }

    private void remove (RBTNode<K, V> node) {
        RBTNode<K, V> temp = node;

        node = node.left == null ? node.right : node.left;
        if (node != null) node.top = temp.top;

        if (temp.top == null)
            root = node;
        else if (temp.top.left == temp)
            temp.top.left = node;
        else temp.top.right = node; 

        num -= 1;
    }

    private RBTNode<K, V> deleteHandle (RBTNode<K, V> node) {
        if (node.top != null && node.color == Color.BLACK)
            return node.top.left == node ? leftDelete(node) : rightDelete(node);
        node.color = Color.BLACK;

        return node;
    }

    private RBTNode<K, V> leftDelete (RBTNode<K, V> node) {
        RBTNode<K, V> temp = node.top.right;

        if (temp.color == Color.RED) {
            temp.color = Color.BLACK;
            node.top.color = Color.RED;
            rightRotate(temp);
            return leftDelete(node);
        }
        if (temp.right != null && temp.right.color == Color.RED) {
            temp.color = node.top.color;
            node.top.color = temp.right.color = Color.BLACK;
            return rightRotate(temp);
        }
        if (temp.left != null && temp.left.color == Color.RED) {
            temp.color = Color.RED;
            temp.left.color = Color.BLACK;
            leftRotate(temp.left);
            return leftDelete(node);
        }
        temp.color = Color.RED;

        return deleteHandle(node.top);
    }

    private RBTNode<K, V> rightDelete (RBTNode<K, V> node) {
        RBTNode<K, V> temp = node.top.left;

        if (temp.color == Color.RED) {
            temp.color = Color.BLACK;
            node.top.color = Color.RED;
            leftRotate(temp);
            return rightDelete(node);
        }
        if (temp.left != null && temp.left.color == Color.RED) {
            temp.color = node.top.color;
            node.top.color = temp.left.color = Color.BLACK;
            return leftRotate(temp);
        }
        if (temp.right != null && temp.right.color == Color.RED) {
            temp.color = Color.RED;
            temp.right.color = Color.BLACK;
            rightRotate(temp.right);
            return rightDelete(node);
        }
        temp.color = Color.RED;

        return deleteHandle(node.top);
    }

    private RBTNode<K, V> alter (RBTNode<K, V> temp) {
        RBTNode<K, V> node = temp.right;
        while (node.left != null) node = node.left;

        temp.setIndex(node.getIndex());
        temp.setValue(node.getValue());

        return node;
    }

    @Override
    public V update (K index, V value) {
        RBTNode<K, V> node = select(index).get(0);
        V temp = node == null ? null : node.getValue();

        if (node != null) node.setValue(value);
        return temp;
    }

    @Override
    public V search (K index) {
        RBTNode<K, V> node = select(index).get(0);
        return node == null ? null : node.getValue();
    }
}
