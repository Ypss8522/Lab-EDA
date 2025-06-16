package Ejercicio1y2;

public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public BST() {
        root = null;
    }

    public void destroy() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> node, T data) {
        if (node == null) return new Node<>(data);
        if (data.compareTo(node.data) < 0)
            node.left = insertRec(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insertRec(node.right, data);
        return node;
    }

    public boolean search(T data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node<T> node, T data) {
        if (node == null) return false;
        if (data.compareTo(node.data) == 0) return true;
        if (data.compareTo(node.data) < 0)
            return searchRec(node.left, data);
        else
            return searchRec(node.right, data);
    }

    public void remove(T data) {
        root = removeRec(root, data);
    }

    private Node<T> removeRec(Node<T> node, T data) {
        if (node == null) return null;
        if (data.compareTo(node.data) < 0)
            node.left = removeRec(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = removeRec(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.data = min(node.right);
            node.right = removeRec(node.right, node.data);
        }
        return node;
    }

    public T min() {
        if (isEmpty()) return null;
        return min(root);
    }

    private T min(Node<T> node) {
        while (node.left != null)
            node = node.left;
        return node.data;
    }

    public T max() {
        if (isEmpty()) return null;
        Node<T> current = root;
        while (current.right != null)
            current = current.right;
        return current.data;
    }

    public T predecessor(T data) {
        Node<T> pred = null, current = root;
        while (current != null) {
            if (data.compareTo(current.data) > 0) {
                pred = current;
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return (pred != null) ? pred.data : null;
    }

    public T successor(T data) {
        Node<T> succ = null, current = root;
        while (current != null) {
            if (data.compareTo(current.data) < 0) {
                succ = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return (succ != null) ? succ.data : null;
    }

    public void inOrder() {
    inOrder(this.root);
    }

    private void inOrder(Node<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.getData());
            inOrder(node.right);
        }
    }

    private void inOrderRec(Node<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }

    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node<T> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }
}
