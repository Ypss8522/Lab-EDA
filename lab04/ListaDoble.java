class NodoDoble {
    int data;
    NodoDoble anterior, siguiente;

    NodoDoble(int data) {
        this.data = data;
    }
}

public class ListaDoble {
    NodoDoble head;

    public void insertarFinal(int data) {
        NodoDoble nuevo = new NodoDoble(data);
        if (head == null) {
            head = nuevo;
            return;
        }
        NodoDoble temp = head;
        while (temp.siguiente != null) temp = temp.siguiente;
        temp.siguiente = nuevo;
        nuevo.anterior = temp;
    }

    public void imprimir() {
        NodoDoble temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.siguiente;
        }
    }

    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();
        for (int i = 1; i <= 10; i++) lista.insertarFinal(i);
        lista.imprimir();
    }
}