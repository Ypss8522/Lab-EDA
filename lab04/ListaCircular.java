class NodoCircular {
    int data;
    NodoCircular siguiente;

    NodoCircular(int data) {
        this.data = data;
    }
}

public class ListaCircular {
    NodoCircular head = null, tail = null;

    public void insertar(int data) {
        NodoCircular nuevo = new NodoCircular(data);
        if (head == null) {
            head = nuevo;
            tail = nuevo;
            nuevo.siguiente = head;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
            tail.siguiente = head;
        }
    }

    public void imprimir() {
        NodoCircular temp = head;
        if (head != null) {
            do {
                System.out.print(temp.data + " ");
                temp = temp.siguiente;
            } while (temp != head);
        }
    }

    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();
        for (int i = 1; i <= 12; i++) lista.insertar(i);
        lista.imprimir();
    }
}