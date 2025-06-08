package Ejercicio3Y4;

import java.util.Scanner;

public class MainCola {
    public static void main(String[] args) {
        Cola<Integer> cola = new Cola<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- MENÚ COLA ---");
            System.out.println("1. Encolar (Agregar 1 al 10)");
            System.out.println("2. Desencolar");
            System.out.println("3. Frente");
            System.out.println("4. Final");
            System.out.println("5. isEmpty");
            System.out.println("6. isFull");
            System.out.println("7. Destroy Queue");
            System.out.println("8. Imprimir Cola");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    for (int i = 1; i <= 10; i++) cola.enqueue(i);
                    break;
                case 2:
                    System.out.println("Elemento eliminado: " + cola.dequeue());
                    break;
                case 3:
                    System.out.println("Frente: " + cola.front());
                    break;
                case 4:
                    System.out.println("Final: " + cola.back());
                    break;
                case 5:
                    System.out.println("¿Cola vacía? " + cola.isEmpty());
                    break;
                case 6:
                    System.out.println("¿Cola llena? " + cola.isFull());
                    break;
                case 7:
                    cola.destroyQueue();
                    System.out.println("Cola destruida.");
                    break;
                case 8:
                    cola.printQueue();
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}