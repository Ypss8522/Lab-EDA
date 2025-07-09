import java.util.ArrayList;

public class BPlusNode<E extends Comparable<E>> {
    public boolean isLeaf;
    public ArrayList<E> keys;
    public ArrayList<BPlusNode<E>> children; // solo si es nodo interno
    public BPlusNode<E> next; // solo si es hoja

    public BPlusNode(int order, boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        if (!isLeaf) {
            this.children = new ArrayList<>();
        } else {
            this.next = null;
        }
    }

    public boolean isOverflow(int order) {
        return keys.size() >= order;
    }

    public boolean isUnderflow(int minKeys) {
        return keys.size() < minKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(isLeaf ? "Leaf[" : "Internal[");
        for (int i = 0; i < keys.size(); i++) {
            sb.append(keys.get(i));
            if (i < keys.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
