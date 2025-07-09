import java.util.ArrayList;

public class BPlusTree<E extends Comparable<E>> {
    private BPlusNode<E> root;
    private int order;

    public BPlusTree(int order) {
        this.order = order;
        this.root = new BPlusNode<>(order, true);
    }

    public boolean isEmpty() {
        return root == null || root.keys.isEmpty();
    }

    public void destroy() {
        root = null;
    }

    public void insert(E key) {
        BPlusNode<E> newChild = insertRecursive(root, key);
        if (newChild != null) {
            BPlusNode<E> newRoot = new BPlusNode<>(order, false);
            newRoot.keys.add(newChild.keys.get(0));
            newRoot.children = new ArrayList<>();
            newRoot.children.add(root);
            newRoot.children.add(newChild);
            root = newRoot;
        }
    }

    private BPlusNode<E> insertRecursive(BPlusNode<E> node, E key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) i++;

        if (node.isLeaf) {
            node.keys.add(i, key);
            if (node.isOverflow(order)) {
                return splitLeaf(node);
            }
            return null;
        } else {
            BPlusNode<E> child = node.children.get(i);
            BPlusNode<E> newChild = insertRecursive(child, key);
            if (newChild != null) {
                E middleKey = newChild.keys.get(0);
                int insertPos = findInsertPosition(node.keys, middleKey);
                node.keys.add(insertPos, middleKey);
                node.children.add(insertPos + 1, newChild);
                if (node.isOverflow(order)) {
                    return splitInternal(node);
                }
            }
            return null;
        }
    }

    private int findInsertPosition(ArrayList<E> keys, E key) {
        int i = 0;
        while (i < keys.size() && key.compareTo(keys.get(i)) > 0) i++;
        return i;
    }

    private BPlusNode<E> splitLeaf(BPlusNode<E> leaf) {
        int mid = (order + 1) / 2;
        BPlusNode<E> newLeaf = new BPlusNode<>(order, true);
        newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
        leaf.keys.subList(mid, leaf.keys.size()).clear();
        newLeaf.next = leaf.next;
        leaf.next = newLeaf;
        return newLeaf;
    }

    private BPlusNode<E> splitInternal(BPlusNode<E> internal) {
        int mid = order / 2;
        BPlusNode<E> newNode = new BPlusNode<>(order, false);
        newNode.keys.addAll(internal.keys.subList(mid + 1, internal.keys.size()));
        newNode.children = new ArrayList<>(internal.children.subList(mid + 1, internal.children.size()));
        internal.keys.subList(mid, internal.keys.size()).clear();
        internal.children.subList(mid + 1, internal.children.size()).clear();
        return newNode;
    }

    public void remove(E key) {
        removeRecursive(root, key);
        if (!root.isLeaf && root.keys.isEmpty()) {
            root = root.children.get(0);
        }
    }

    private void removeRecursive(BPlusNode<E> node, E key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) i++;

        if (node.isLeaf) {
            if (i < node.keys.size() && node.keys.get(i).compareTo(key) == 0) {
                node.keys.remove(i);
            }
        } else {
            BPlusNode<E> child = node.children.get(i);
            removeRecursive(child, key);
            if (child.keys.size() < (order + 1) / 2 - 1) {
                fuzeNode(node, i);
            }
            if (i < node.keys.size() && node.children.get(i).keys.isEmpty()) {
                node.children.remove(i);
                if (i < node.keys.size()) node.keys.remove(i);
            }
        }
    }

    public void fuzeNode(BPlusNode<E> parent, int index) {
        if (index >= parent.children.size() - 1) return;
        BPlusNode<E> left = parent.children.get(index);
        BPlusNode<E> right = parent.children.get(index + 1);

        if (left.isLeaf) {
            left.keys.addAll(right.keys);
            left.next = right.next;
        } else {
            left.keys.add(parent.keys.get(index));
            left.keys.addAll(right.keys);
            left.children.addAll(right.children);
        }
        parent.children.remove(index + 1);
        parent.keys.remove(index);
    }

    public boolean search(E key) {
        BPlusNode<E> node = root;
        while (!node.isLeaf) {
            int i = 0;
            while (i < node.keys.size() && key.compareTo(node.keys.get(i)) >= 0) i++;
            node = node.children.get(i);
        }
        return node.keys.contains(key);
    }

    public E min() {
        BPlusNode<E> node = root;
        while (!node.isLeaf) node = node.children.get(0);
        return node.keys.isEmpty() ? null : node.keys.get(0);
    }

    public E max() {
        BPlusNode<E> node = root;
        while (!node.isLeaf) node = node.children.get(node.children.size() - 1);
        return node.keys.isEmpty() ? null : node.keys.get(node.keys.size() - 1);
    }

    public E predecessor(E key) {
        BPlusNode<E> node = root;
        E pred = null;
        while (!node.isLeaf) {
            int i = 0;
            while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
                pred = node.keys.get(i);
                i++;
            }
            node = node.children.get(i);
        }
        for (int i = 0; i < node.keys.size(); i++) {
            if (key.compareTo(node.keys.get(i)) <= 0) {
                return i > 0 ? node.keys.get(i - 1) : pred;
            }
        }
        return node.keys.isEmpty() ? null : node.keys.get(node.keys.size() - 1);
    }

    public E successor(E key) {
        BPlusNode<E> node = root;
        while (!node.isLeaf) {
            int i = 0;
            while (i < node.keys.size() && key.compareTo(node.keys.get(i)) >= 0) i++;
            node = node.children.get(i);
        }
        for (E k : node.keys) {
            if (key.compareTo(k) < 0) return k;
        }
        return (node.next != null && !node.next.keys.isEmpty()) ? node.next.keys.get(0) : null;
    }

    public String toString() {
        return writeTree(this.root);
    }

    public String writeTree(BPlusNode<E> node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(node.toString()).append("\n");
        if (!node.isLeaf) {
            for (BPlusNode<E> child : node.children) {
                sb.append(writeTree(child));
            }
        }
        return sb.toString();
    }

    public BPlusNode<E> getRoot() {
        return root;
    }
}

