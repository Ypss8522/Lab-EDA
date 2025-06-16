package Ejercicio1y2;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST<Integer> tree = new BST<>();
        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ingresar palabra");
            System.out.println("2. Mostrar árbol InOrder");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese una palabra: ");
                    String palabra = sc.nextLine();
                    int valorAscii = calcularSumaAscii(palabra);
                    tree.insert(valorAscii);
                    System.out.println("Valor ASCII total insertado: " + valorAscii);
                    break;
                case 2:
                    System.out.println("Recorrido InOrder del árbol:");
                    tree.inOrder();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
        sc.close();
    }

    public static int calcularSumaAscii(String palabra) {
        int suma = 0;
        for (char c : palabra.toCharArray()) {
            suma += (int) c;
        }
        return suma;
    }
}
