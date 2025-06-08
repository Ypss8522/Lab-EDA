package Ejercicio1Y2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- MENÚ PILA ---");
            System.out.println("1. Push (Agregar 1 al 10)");
            System.out.println("2. Pop");
            System.out.println("3. Top");
            System.out.println("4. isEmpty");
            System.out.println("5. isFull");
            System.out.println("6. Destroy Stack");
            System.out.println("7. Imprimir Pila");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    for (int i = 1; i <= 10; i++) pila.push(i);
                    break;
                case 2:
                    System.out.println("Elemento eliminado: " + pila.pop());
                    break;
                case 3:
                    System.out.println("Elemento en top: " + pila.top());
                    break;
                case 4:
                    System.out.println("¿Pila vacía? " + pila.isEmpty());
                    break;
                case 5:
                    System.out.println("¿Pila llena? " + pila.isFull());
                    break;
                case 6:
                    pila.destroyStack();
                    System.out.println("Pila destruida.");
                    break;
                case 7:
                    pila.printStack();
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}
