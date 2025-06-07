import java.util.Scanner;

class NodoDoble {
    int data;
    NodoDoble prev, next;

    NodoDoble(int data) {
        this.data = data;
    }
}

public class ListaDobleMenu {
    NodoDoble head;

    public void insertarAlInicio(int data) {
        NodoDoble nuevo = new NodoDoble(data);
        nuevo.next = head;
        if (head != null) head.prev = nuevo;
        head = nuevo;
    }

    public void insertarAlFinal(int data) {
        NodoDoble nuevo = new NodoDoble(data);
        if (head == null) {
            head = nuevo;
            return;
        }
        NodoDoble temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = nuevo;
        nuevo.prev = temp;
    }

    public void eliminarPorClave(int key) {
        NodoDoble temp = head;
        while (temp != null && temp.data != key)
            temp = temp.next;
        if (temp == null) return;

        if (temp.prev != null) temp.prev.next = temp.next;
        else head = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
    }

    public void imprimir() {
        NodoDoble temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int tamaño() {
        int count = 0;
        NodoDoble temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void eliminarPrimero() {
        if (head != null) {
            head = head.next;
            if (head != null) head.prev = null;
        }
    }

    public void eliminarUltimo() {
        if (head == null || head.next == null) {
            head = null;
            return;
        }
        NodoDoble temp = head;
        while (temp.next != null) temp = temp.next;
        temp.prev.next = null;
    }

    public static void main(String[] args) {
        ListaDobleMenu lista = new ListaDobleMenu();
        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            System.out.println("1.Insertar al inicio\n2.Insertar al final\n3.Eliminar por clave\n4.Mostrar\n5.Tamaño\n6.Eliminar primero\n7.Eliminar último\n0.Salir");
            opc = sc.nextInt();
            switch (opc) {
                case 1 -> {
                    System.out.print("Dato: ");
                    lista.insertarAlInicio(sc.nextInt());
                }
                case 2 -> {
                    System.out.print("Dato: ");
                    lista.insertarAlFinal(sc.nextInt());
                }
                case 3 -> {
                    System.out.print("Clave: ");
                    lista.eliminarPorClave(sc.nextInt());
                }
                case 4 -> lista.imprimir();
                case 5 -> System.out.println("Tamaño: " + lista.tamaño());
                case 6 -> lista.eliminarPrimero();
                case 7 -> lista.eliminarUltimo();
            }
        } while (opc != 0);
    }
}