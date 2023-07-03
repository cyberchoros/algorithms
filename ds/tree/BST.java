package ds.tree;

public class BST<K extends Comparable<K>, V> extends RecursiveTree<K, V, BSTNode<K, V>> {
    @Override
    BSTNode<K, V> create (BSTNode<K, V> node, TreeNode<K, V> temp, K index, V value) {
        if (node == null) {
            node = new BSTNode<>(index, value);
            num += 1;
        }
        else if (node.getIndex().compareTo(index) > 0)
            node.left = create(node.left, temp, index, value);
        else if (node.getIndex().compareTo(index) < 0)
            node.right = create(node.right, temp, index, value);
        else {
            temp.setValue(node.getValue());
            node.setValue(value);
        }
        return node;
    }

    @Override
    BSTNode<K, V> remove (BSTNode<K, V> node, TreeNode<K, V> temp, K index) {
        if (node == null) return null;

        if (node.getIndex().compareTo(index) > 0) {
            node.left = remove(node.left, temp, index);
            return node;
        }
        if (node.getIndex().compareTo(index) < 0) {
            node.right = remove(node.right, temp, index);
            return node;
        }
        temp.setValue(node.getValue());
        num -= 1;

        if (node.left == null || node.right == null)
            node = node.left == null ? node.right : node.left;
        else node.right = alter(node.right, node);

        return node;
    }

    private BSTNode<K, V> alter (BSTNode<K, V> node, BSTNode<K, V> temp) {
        if (node.left == null) {
            temp.setIndex(node.getIndex());
            temp.setValue(node.getValue());

            return node.right;
        }
        node.left = alter(node.left, temp);

        return node;
    }
}

abstract class RecursiveTree<K extends Comparable<K>, V, E extends RecursiveNode<K, V, E>> extends BinaryTree<K, V, E> {
    @Override
    public V insert (K index, V value) {
        TreeNode<K, V> temp = new TreeNode<>();
        root = create(root, temp, index, value);
        return temp.getValue();
    }

    abstract E create (E node, TreeNode<K, V> temp, K index, V value);

    @Override
    public V delete (K index) {
        TreeNode<K, V> temp = new TreeNode<>();
        root = remove(root, temp, index);
        return temp.getValue();
    }

    abstract E remove (E node, TreeNode<K, V> temp, K index);

    @Override
    public V update (K index, V value) {
        TreeNode<K, V> node = iter(root, index);
        V temp = node == null ? null : node.getValue();

        if (node != null) node.setValue(value);
        return temp;
    }

    @Override
    public V search (K index) {
        TreeNode<K, V> node = iter(root, index);
        return node == null ? null : node.getValue();
    }

    private E iter (E node, K index) {
        if (node == null) return null;

        if (node.getIndex().compareTo(index) > 0)
            return iter(node.left, index);
        if (node.getIndex().compareTo(index) < 0)
            return iter(node.right, index);
        return node;
    }
}
