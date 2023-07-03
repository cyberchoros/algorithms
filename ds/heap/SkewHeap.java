package ds.heap;

public class SkewHeap<E extends Comparable<E>> extends BinaryHeap<E, SHNode<E>> {
    @Override
    SHNode<E> getInstance (E item) { return new SHNode<>(item); }
    
    @Override
    SHNode<E> merge (SHNode<E> node, SHNode<E> temp) {     
        if (node == null) return temp;
        if (temp == null) return node;

        if (node.getItem().compareTo(temp.getItem()) > 0) return merge(temp, node);

        node.right = merge(node.right, temp);
        swap(node);

        return node;
    }
}
