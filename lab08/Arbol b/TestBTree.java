import java.util.Scanner;

public class TestBTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BTree<Integer> tree = new BTree<>(4);
        int option;
        do {
            System.out.println("\n--- MENÚ ÁRBOL B ---");
            System.out.println("1. Insertar");
            System.out.println("2. Mostrar Árbol");
            System.out.println("3. Buscar");
            System.out.println("4. Verificar si está vacío");
            System.out.println("5. Destruir Árbol");
            System.out.println("6. Mínimo");
            System.out.println("7. Máximo");
            System.out.println("8. Predecesor");
            System.out.println("9. Sucesor");
            System.out.println("10. Eliminar");
            System.out.println("11. Mostrar árbol graficado");
            System.out.println("12. Salir");
            System.out.print("Opción: ");
            option = sc.nextInt();
            int val;
            switch (option) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    val = sc.nextInt();
                    tree.insert(val);
                    break;
                case 2:
                    System.out.println(tree.toString());
                    break;
                case 3:
                    System.out.print("Valor a buscar: ");
                    val = sc.nextInt();
                    System.out.println(tree.search(val) ? "Encontrado" : "No encontrado");
                    break;
                case 4:
                    System.out.println("¿Vacío? " + tree.isEmpty());
                    break;
                case 5:
                    tree.destroy();
                    System.out.println("Árbol destruido.");
                    break;
                case 6:
                    System.out.println("Mínimo: " + tree.min());
                    break;
                case 7:
                    System.out.println("Máximo: " + tree.max());
                    break;
                case 8:
                    System.out.print("Clave para predecesor: ");
                    val = sc.nextInt();
                    System.out.println("Predecesor: " + tree.predecessor(val));
                    break;
                case 9:
                    System.out.print("Clave para sucesor: ");
                    val = sc.nextInt();
                    System.out.println("Sucesor: " + tree.successor(val));
                    break;
                case 10:
                    System.out.print("Valor a eliminar: ");
                    val = sc.nextInt();
                    tree.remove(val);
                    break;
                case 11:
                    VisualizerLauncher.showTree(tree);
                    break;
                case 12:
                    System.out.println("Fin.");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (option != 12);
        sc.close();
    }
}
