package Ejercicio1;

public class StackList<T> {
    private Node<T> top;

    public StackList() {
        this.top = null;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) return null;
        T data = top.data;
        top = top.next;
        return data;
    }

    public T peek() {
        return (isEmpty()) ? null : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void mostrar() {
        Node<T> current = top;
        System.out.print("Pila: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}