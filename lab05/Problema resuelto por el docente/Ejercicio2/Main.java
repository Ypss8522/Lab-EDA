package Ejercicio2;

public class Main {
    public static void main(String[] args) {
        QueueList<Integer> cola = new QueueList<>();
        for (int i = 1; i <= 8; i++) {
            cola.enqueue(i);
        }
        cola.printQueue();
    }
}