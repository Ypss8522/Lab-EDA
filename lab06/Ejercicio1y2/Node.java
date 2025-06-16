package Ejercicio1y2;
class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this.data = data;
        left = right = null;
    }

    public T getData() {
        return data;
    }
}