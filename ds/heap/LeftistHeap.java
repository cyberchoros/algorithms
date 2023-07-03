package ds.heap;

public class LeftistHeap<E extends Comparable<E>> extends BinaryHeap<E, LHNode<E>> {
    @Override
    LHNode<E> getInstance (E item) { return new LHNode<>(item); }

    @Override
    LHNode<E> merge (LHNode<E> node, LHNode<E> temp) {
        if (node == null) return temp;
        if (temp == null) return node;

        if (node.getItem().compareTo(temp.getItem()) > 0) return merge(temp, node);

        if (node.left != null) {
            node.right = merge(node.right, temp);
            if (node.left.npl < node.right.npl) swap(node);
            node.npl = node.right.npl + 1;
        }
        else node.left = temp;

        return node;
    }
}
