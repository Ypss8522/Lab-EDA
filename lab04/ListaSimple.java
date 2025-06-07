import java.util.Scanner;

class Nodo {
    int data;
    Nodo next;

    Nodo(int data) {
        this.data = data;
    }
}

public class ListaSimple {
    Nodo head;

    public void insert(int data) {
        addLast(data);
    }

    public void printList() {
        Nodo current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void deleteByKey(int key) {
        if (head == null) return;
        if (head.data == key) {
            head = head.next;
            return;
        }
        Nodo current = head;
        while (current.next != null && current.next.data != key)
            current = current.next;
        if (current.next != null) current.next = current.next.next;
    }

    public void deleteAtPosition(int position) {
        if (position == 0) {
            head = head.next;
            return;
        }
        Nodo current = head;
        for (int i = 0; current != null && i < position - 1; i++)
            current = current.next;
        if (current != null && current.next != null)
            current.next = current.next.next;
    }

    public int size() {
        int count = 0;
        Nodo current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void removeFirst() {
        if (head != null) head = head.next;
    }

    public void removeLast() {
        if (head == null || head.next == null) {
            head = null;
            return;
        }
        Nodo current = head;
        while (current.next.next != null)
            current = current.next;
        current.next = null;
    }

    public void addFirst(int data) {
        Nodo nuevo = new Nodo(data);
        nuevo.next = head;
        head = nuevo;
    }

    public void addLast(int data) {
        Nodo nuevo = new Nodo(data);
        if (head == null) {
            head = nuevo;
            return;
        }
        Nodo temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = nuevo;
    }

    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();
        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            System.out.println("1.Insertar\n2.Eliminar por clave\n3.Eliminar por posición\n4.Mostrar\n5.Tamaño\n6.Eliminar primero\n7.Eliminar último\n8.Agregar al inicio\n9.Agregar al final\n0.Salir");
            opc = sc.nextInt();
            switch (opc) {
                case 1 -> {
                    System.out.print("Dato: ");
                    lista.insert(sc.nextInt());
                }
                case 2 -> {
                    System.out.print("Clave: ");
                    lista.deleteByKey(sc.nextInt());
                }
                case 3 -> {
                    System.out.print("Posición: ");
                    lista.deleteAtPosition(sc.nextInt());
                }
                case 4 -> lista.printList();
                case 5 -> System.out.println("Tamaño: " + lista.size());
                case 6 -> lista.removeFirst();
                case 7 -> lista.removeLast();
                case 8 -> {
                    System.out.print("Dato: ");
                    lista.addFirst(sc.nextInt());
                }
                case 9 -> {
                    System.out.print("Dato: ");
                    lista.addLast(sc.nextInt());
                }
            }
        } while (opc != 0);
    }
}