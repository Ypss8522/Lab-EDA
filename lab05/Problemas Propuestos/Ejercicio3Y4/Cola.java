package Ejercicio3Y4;

public class Cola<E> {
    private Node<E> front, rear;

    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public E front() {
        return isEmpty() ? null : front.data;
    }

    public E back() {
        return isEmpty() ? null : rear.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public boolean isFull() {
        return false;
    }

    public void destroyQueue() {
        front = rear = null;
    }

    public void printQueue() {
        Node<E> current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}