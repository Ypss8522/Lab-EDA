import java.util.Scanner;

public class TestBPlusTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el orden del Árbol B+: ");
        int order = scanner.nextInt();
        BPlusTree<Integer> tree = new BPlusTree<>(order);

        int option, value;
        do {
            System.out.println("\n===== Menú Árbol B+ =====");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Buscar");
            System.out.println("4. Mínimo");
            System.out.println("5. Máximo");
            System.out.println("6. Predecesor");
            System.out.println("7. Sucesor");
            System.out.println("8. Mostrar Árbol");
            System.out.println("9. Destruir Árbol");
            System.out.println("10. Visualizar Árbol Gráficamente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingrese valor a insertar: ");
                    value = scanner.nextInt();
                    tree.insert(value);
                    break;
                case 2:
                    System.out.print("Ingrese valor a eliminar: ");
                    value = scanner.nextInt();
                    tree.remove(value);
                    break;
                case 3:
                    System.out.print("Ingrese valor a buscar: ");
                    value = scanner.nextInt();
                    System.out.println("Resultado: " + (tree.search(value) ? "Encontrado" : "No encontrado"));
                    break;
                case 4:
                    System.out.println("Mínimo: " + tree.min());
                    break;
                case 5:
                    System.out.println("Máximo: " + tree.max());
                    break;
                case 6:
                    System.out.print("Ingrese valor para hallar predecesor: ");
                    value = scanner.nextInt();
                    System.out.println("Predecesor: " + tree.predecessor(value));
                    break;
                case 7:
                    System.out.print("Ingrese valor para hallar sucesor: ");
                    value = scanner.nextInt();
                    System.out.println("Sucesor: " + tree.successor(value));
                    break;
                case 8:
                    System.out.println("\nEstructura del árbol:");
                    System.out.println(tree);
                    break;
                case 9:
                    tree.destroy();
                    System.out.println("Árbol destruido.");
                    break;
                case 10:
                    BPlusTreeVisualizer.showTree(tree);
                     break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);

        scanner.close();
    }
}
