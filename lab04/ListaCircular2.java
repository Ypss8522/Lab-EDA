import java.util.LinkedList;

public class ListaCircular2 {
    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        for (int i = 1; i <= 12; i++) lista.add(i);

        int ciclos = 1; 
        for (int i = 0; i < lista.size() * ciclos; i++) {
            System.out.print(lista.get(i % lista.size()) + " ");
        }
    }
}