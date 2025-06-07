import java.util.LinkedList;

public class ListaDoble2 {
    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        for (int i = 1; i <= 10; i++) lista.add(i);
        lista.forEach(e -> System.out.print(e + " "));
    }
}