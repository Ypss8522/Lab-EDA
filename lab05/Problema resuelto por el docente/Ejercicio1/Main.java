package Ejercicio1;

public class Main {
    public static void main(String[] args) {      
        StackList<Integer> pila = new StackList<>();
        for (int i = 1; i <= 8; i++) {
            pila.push(i);
        }
        pila.mostrar();
    }
}