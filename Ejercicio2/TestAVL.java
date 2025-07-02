
import java.util.Scanner;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        Scanner sc = new Scanner(System.in);
        int opc, x;

        do {
            System.out.println("\n--- MENÚ AVL ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Mostrar InOrder");
            System.out.println("4. Mostrar PreOrder");
            System.out.println("5. Mostrar PostOrder");
            System.out.println("6. Mínimo");
            System.out.println("7. Máximo");
            System.out.println("8. Predecesor");
            System.out.println("9. Sucesor");
            System.out.println("10. Eliminar");
            System.out.println("11. Vaciar árbol");
            System.out.println("12. Salir");
            System.out.print("Opción: ");
            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.print("Dato: ");
                    x = sc.nextInt();
                    avl.insert(x);
                    break;
                case 2:
                    System.out.print("Buscar: ");
                    x = sc.nextInt();
                    System.out.println(avl.search(x) ? "Encontrado" : "No encontrado");
                    break;
                case 3:
                    avl.inOrder();
                    break;
                case 4:
                    avl.preOrder();
                    break;
                case 5:
                    avl.postOrder();
                    break;
                case 6:
                    System.out.println("Mínimo: " + avl.min());
                    break;
                case 7:
                    System.out.println("Máximo: " + avl.max());
                    break;
                case 8:
                    System.out.print("Predecesor de: ");
                    x = sc.nextInt();
                    System.out.println("Predecesor: " + avl.predecessor(x));
                    break;
                case 9:
                    System.out.print("Sucesor de: ");
                    x = sc.nextInt();
                    System.out.println("Sucesor: " + avl.successor(x));
                    break;
                case 10:
                    System.out.print("Eliminar: ");
                    x = sc.nextInt();
                    avl.remove(x);
                    break;
                case 11:
                    avl.destroy();
                    System.out.println("Árbol destruido");
                    break;
                case 12:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opc != 12);

        sc.close();
    }
}
