package ds.tree;

public class AVL<K extends Comparable<K>, V> extends RecursiveTree<K, V, AVLNode<K, V>> {
    @Override
    AVLNode<K, V> create (AVLNode<K, V> node, TreeNode<K, V> temp, K index, V value) {
        if (node == null) {
            num += 1;
            return new AVLNode<>(index, value);
        }
        if (node.getIndex().compareTo(index) > 0) {
            node.left = create(node.left, temp, index, value);
            return leftHandle(node);
        }
        if (node.getIndex().compareTo(index) < 0) {
            node.right = create(node.right, temp, index, value);
            return rightHandle(node);
        }
        temp.setValue(node.getValue());
        node.setValue(value);

        return node;
    }

    @Override
    AVLNode<K, V> remove (AVLNode<K, V> node, TreeNode<K, V> temp, K index) {
        if (node == null) return null;

        if (node.getIndex().compareTo(index) > 0) {
            node.left = remove(node.left, temp, index);
            return rightHandle(node);
        }
        if (node.getIndex().compareTo(index) < 0) {
            node.right = remove(node.right, temp, index);
            return leftHandle(node);
        }
        temp.setValue(node.getValue());
        num -= 1;

        if (node.left == null || node.right == null)
            return node.left == null ? node.right : node.left;
        node.right = alter(node.right, node);

        return leftHandle(node);
    }

    private AVLNode<K, V> alter (AVLNode<K, V> node, AVLNode<K, V> temp) {
        if (node.left == null) {
            temp.setIndex(node.getIndex());
            temp.setValue(node.getValue());
            
            return node.right;
        }
        node.left = alter(node.left, temp);

        return rightHandle(node);
    }

    private AVLNode<K, V> leftHandle (AVLNode<K, V> node) {
        if (height(node.left) - height(node.right) > 1)
            return height(node.left.left) < height(node.left.right) ? leftRight(node) : leftRotate(node);
        node.height = max(height(node.left), height(node.right)) + 1;

        return node;
    }

    private AVLNode<K, V> rightHandle (AVLNode<K, V> node) {
        if (height(node.right) - height(node.left) > 1)
            return height(node.right.right) < height(node.right.left) ? rightLeft(node) : rightRotate(node);
        node.height = max(height(node.left), height(node.right)) + 1;

        return node;
    }

    private AVLNode<K, V> leftRight (AVLNode<K, V> node) {
        node.left = rightRotate(node.left);
        return leftRotate(node);
    }

    private AVLNode<K, V> rightLeft (AVLNode<K, V> node) {
        node.right = leftRotate(node.right);
        return rightRotate(node);
    }

    private AVLNode<K, V> leftRotate (AVLNode<K, V> node) {
        AVLNode<K, V> temp = node.left;
        node.left = temp.right;
        temp.right = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        temp.height = max(height(temp.left), node.height) + 1;

        return temp;
    }

    private AVLNode<K, V> rightRotate (AVLNode<K, V> node) {
        AVLNode<K, V> temp = node.right;
        node.right = temp.left;
        temp.left = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        temp.height = max(node.height, height(temp.right)) + 1;

        return temp;
    }

    private int max (int a, int b) { return a > b ? a : b; }

    private int height (AVLNode<K, V> node) { return node == null ? -1 : node.height; }
}
