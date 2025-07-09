
public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void destroy() {
        this.root = null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean found = current.searchNode(cl, pos);
            if (found) {
                System.out.println("Elemento duplicado.");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
                return mediana;
            }
            return null;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<>(this.orden);

        for (int i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    public void remove(E key) {
        remove(root, key);
        if (root.count == 0 && root.childs.get(0) != null) {
            root = root.childs.get(0);
        }
    }

    private void remove(BNode<E> node, E key) {
        int i = 0;
        while (i < node.count && key.compareTo(node.keys.get(i)) > 0) i++;

        if (i < node.count && key.compareTo(node.keys.get(i)) == 0) {
            if (node.childs.get(i) == null) {
                for (int j = i; j < node.count - 1; j++) {
                    node.keys.set(j, node.keys.get(j + 1));
                    node.childs.set(j + 1, node.childs.get(j + 2));
                }
                node.count--;
            } else {
                BNode<E> predNode = node.childs.get(i);
                while (predNode.childs.get(predNode.count) != null)
                    predNode = predNode.childs.get(predNode.count);
                E pred = predNode.keys.get(predNode.count - 1);
                node.keys.set(i, pred);
                remove(node.childs.get(i), pred);
            }
        } else if (node.childs.get(i) != null) {
            remove(node.childs.get(i), key);
            if (node.childs.get(i).count < (orden - 1) / 2) {
                fuzeNode(node, i);
            }
        }
    }

    private void fuzeNode(BNode<E> parent, int idx) {
        BNode<E> left = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        left.keys.set(left.count, parent.keys.get(idx));
        left.count++;

        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count, right.childs.get(i));
            left.count++;
        }
        left.childs.set(left.count, right.childs.get(right.count));

        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.count--;
    }

    public boolean search(E key) {
        return search(this.root, key);
    }

    private boolean search(BNode<E> node, E key) {
        if (node == null) return false;
        int i = 0;
        while (i < node.count && key.compareTo(node.keys.get(i)) > 0) i++;
        if (i < node.count && key.compareTo(node.keys.get(i)) == 0) return true;
        return search(node.childs.get(i), key);
    }

    public E min() {
        if (root == null) return null;
        BNode<E> current = root;
        while (current.childs.get(0) != null)
            current = current.childs.get(0);
        return current.keys.get(0);
    }

    public E max() {
        if (root == null) return null;
        BNode<E> current = root;
        while (current.childs.get(current.count) != null)
            current = current.childs.get(current.count);
        return current.keys.get(current.count - 1);
    }

    public String toString() {
        return isEmpty() ? "BTree está vacío." : writeTree(this.root);
    }

    private String writeTree(BNode<E> node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(node.toString()).append("\n");
        for (int i = 0; i <= node.count; i++) {
            sb.append(writeTree(node.childs.get(i)));
        }
        return sb.toString();
    }

    public E predecessor(E key) {
        BNode<E> node = root;
        E pred = null;
        while (node != null) {
            int i = 0;
            while (i < node.count && key.compareTo(node.keys.get(i)) > 0) {
                pred = node.keys.get(i);
                i++;
            }
            node = node.childs.get(i);
        }
        return pred;
    }

    public E successor(E key) {
        BNode<E> node = root;
        E succ = null;
        while (node != null) {
            int i = 0;
            while (i < node.count && key.compareTo(node.keys.get(i)) >= 0) i++;
            if (i < node.count) succ = node.keys.get(i);
            node = node.childs.get(i);
        }
        return succ;
    }

    public BNode<E> getRoot() {
        return this.root;
    }
}
