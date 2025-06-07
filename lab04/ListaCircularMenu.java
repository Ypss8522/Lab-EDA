
import java.util.Scanner;

class NodoCircular {
    int data;
    NodoCircular next;

    NodoCircular(int data) {
        this.data = data;
    }
}

public class ListaCircularMenu {
    NodoCircular head = null, tail = null;

    public void insertar(int data) {
        NodoCircular nuevo = new NodoCircular(data);
        if (head == null) {
            head = nuevo;
            tail = nuevo;
            nuevo.next = head;
        } else {
            tail.next = nuevo;
            tail = nuevo;
            tail.next = head;
        }
    }

    public void eliminarPorClave(int key) {
        if (head == null) return;
        NodoCircular actual = head, anterior = null;

        do {
            if (actual.data == key) {
                if (anterior != null) {
                    anterior.next = actual.next;
                    if (actual == tail) tail = anterior;
                } else {
                    // Eliminar cabeza
                    if (head == tail) {
                        head = null;
                        tail = null;
                    } else {
                        head = head.next;
                        tail.next = head;
                    }
                }
                return;
            }
            anterior = actual;
            actual = actual.next;
        } while (actual != head);
    }

    public void imprimir() {
        if (head == null) return;
        NodoCircular temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    public int tamaño() {
        if (head == null) return 0;
        int count = 0;
        NodoCircular temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    public void eliminarPrimero() {
        if (head == null) return;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
    }

    public void eliminarUltimo() {
        if (head == null || head == tail) {
            head = tail = null;
            return;
        }
        NodoCircular temp = head;
        while (temp.next != tail) temp = temp.next;
        temp.next = head;
        tail = temp;
    }

    public static void main(String[] args) {
        ListaCircularMenu lista = new ListaCircularMenu();
        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            System.out.println("1.Insertar\n2.Eliminar por clave\n3.Mostrar\n4.Tamaño\n5.Eliminar primero\n6.Eliminar último\n0.Salir");
            opc = sc.nextInt();
            switch (opc) {
                case 1 -> {
                    System.out.print("Dato: ");
                    lista.insertar(sc.nextInt());
                }
                case 2 -> {
                    System.out.print("Clave: ");
                    lista.eliminarPorClave(sc.nextInt());
                }
                case 3 -> lista.imprimir();
                case 4 -> System.out.println("Tamaño: " + lista.tamaño());
                case 5 -> lista.eliminarPrimero();
                case 6 -> lista.eliminarUltimo();
            }
        } while (opc != 0);
    }
}