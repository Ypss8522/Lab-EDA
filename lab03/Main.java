
class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) {
        MiLista<String> list = new MiLista<>();
        list.add("Uno");
        list.add("Dos");
        list.add("Tres");
        list.add(1, "Insertado");

        System.out.println("Tamaño: " + list.size());          
        System.out.println("Elemento en posición 1: " + list.get(1));  
        list.remove("Dos");
        System.out.println("Contiene 'Dos': " + list.contains("Dos")); 
        System.out.println("Sublista (0 a 2):");
        MiLista<String> sub = list.subList(0, 2);
        for (int i = 0; i < sub.size(); i++) {
            System.out.println(sub.get(i));
        }
    }
}

class MiLista<T> {
    private Node<T> root;
    private int size;

    public MiLista() {
        this.root = null;
        this.size = 0;
    }

    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            root = newNode;
        } else {
            Node<T> current = root;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    public void add(int index, T element) {
        checkIndexForAdd(index);
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = root;
            root = newNode;
        } else {
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    public boolean addAll(java.util.Collection<? extends T> c) {
        for (T item : c) {
            add(item);
        }
        return true;
    }

    public boolean addAll(int index, java.util.Collection<? extends T> c) {
        checkIndexForAdd(index);
        for (T item : c) {
            add(index++, item);
        }
        return true;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean containsAll(java.util.Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) return false;
        }
        return true;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MiLista)) return false;
        MiLista<?> other = (MiLista<?>) o;
        if (this.size != other.size) return false;

        Node<T> current = this.root;
        Node<?> otherCurrent = other.root;

        while (current != null) {
            if (!current.data.equals(otherCurrent.data)) return false;
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        return true;
    }

    public T get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    public int indexOf(Object o) {
        Node<T> current = root;
        int index = 0;
        while (current != null) {
            if (current.data.equals(o)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        Node<T> current = root;
        int index = 0;
        int lastFound = -1;
        while (current != null) {
            if (current.data.equals(o)) lastFound = index;
            current = current.next;
            index++;
        }
        return lastFound;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T remove(int index) {
        checkIndex(index);
        Node<T> removed;
        if (index == 0) {
            removed = root;
            root = root.next;
        } else {
            Node<T> prev = getNode(index - 1);
            removed = prev.next;
            prev.next = removed.next;
        }
        size--;
        return removed.data;
    }

    public boolean remove(Object o) {
        Node<T> current = root;
        Node<T> prev = null;
        while (current != null) {
            if (current.data.equals(o)) {
                if (prev == null) {
                    root = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public boolean removeAll(java.util.Collection<?> c) {
        boolean changed = false;
        for (Object item : c) {
            while (remove(item)) {
                changed = true;
            }
        }
        return changed;
    }

    public T set(int index, T element) {
        checkIndex(index);
        Node<T> node = getNode(index);
        T old = node.data;
        node.data = element;
        return old;
    }

    public int size() {
        return size;
    }

    public MiLista<T> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex - 1);
        MiLista<T> sub = new MiLista<>();
        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(get(i));
        }
        return sub;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private Node<T> getNode(int index) {
        Node<T> current = root;
        for (int i = 0; i < index; i++) current = current.next;
        return current;
    }
}
