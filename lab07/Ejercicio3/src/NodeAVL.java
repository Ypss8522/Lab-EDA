package Ejercicio3.src;

public class NodeAVL<T> {
    T data;
    NodeAVL<T> left, right;
    int bf; // balance factor

    public NodeAVL(T data) {
        this.data = data;
        this.left = this.right = null;
        this.bf = 0;
    }

    public String toString() {
        return data.toString();
    }
}
