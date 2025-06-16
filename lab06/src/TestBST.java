package src;
import java.util.Scanner;

public class TestBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST<Integer> tree = new BST<>();

        while (true) {
            System.out.println("\n1. Ingresar palabra");
            System.out.println("2. Mostrar árbol (InOrder)");
            System.out.println("3. Dibujar árbol");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Ingrese palabra: ");
                    String palabra = sc.nextLine();
                    int suma = calcularAscii(palabra);
                    tree.insert(suma);
                    System.out.println("Valor ASCII insertado: " + suma);
                    break;
                case 2:
                    System.out.println("Recorrido InOrder:");
                    tree.inOrder();
                    break;
                case 3:
                    tree.draw();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static int calcularAscii(String palabra) {
        int total = 0;
        for (char c : palabra.toCharArray())
            total += (int) c;
        return total;
    }
}

