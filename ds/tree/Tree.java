package ds.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BiConsumer;

public interface Tree<K, V> extends Iterable<TreeNode<K, V>> {
    public V insert (K index, V value);
    public V delete (K index);
    public V update (K index, V value);
    public V search (K index);

    public void forEach (BiConsumer<K, V> consumer);

    public void clear ();
    public int size ();
}

abstract class BinaryTree<K, V, E extends RecursiveNode<K, V, E>> implements Tree<K, V> {
    E root;
    int num;

    @Override
    public Iterator<TreeNode<K, V>> iterator () {
        return new Iterator<TreeNode<K, V>>() {
            private ArrayList<E> queue;

            { bfs(); }

            private void bfs () {
                queue = new ArrayList<>();
                if (root != null) queue.add(root);

                for (int i = 0; i < queue.size(); i++) {
                    E node = queue.get(i);

                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }

            @Override
            public boolean hasNext () { return queue.size() > 0; }

            @Override
            public TreeNode<K, V> next () { return queue.remove(0); }
        };
    }

    @Override
    public void forEach (BiConsumer<K, V> consumer) { dfs(root, consumer); }

    private void dfs (E node, BiConsumer<K, V> consumer) {
        if (node == null) return;

        dfs(node.left, consumer);
        consumer.accept(node.getIndex(), node.getValue());
        dfs(node.right, consumer);
    }

    @Override
    public void clear () {
        root = null;
        num = 0;
    }

    @Override
    public int size () { return num; }
}
