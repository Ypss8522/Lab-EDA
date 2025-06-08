package Ejercicio1Y2;

public class Pila<E> {
    private Node<E> top;

    public void push(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    public E pop() {
        if (isEmpty()) return null;
        E data = top.data;
        top = top.next;
        return data;
    }

    public E top() {
        return isEmpty() ? null : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void destroyStack() {
        top = null;
    }

    public boolean isFull() {
        return false;
    }

    public void printStack() {
        Node<E> current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}