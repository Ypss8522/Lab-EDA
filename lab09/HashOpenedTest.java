import java.util.Scanner;

public class HashOpenedTest {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option, size;
        System.out.println("Ingrese el tamaño de la tabla:");
        size = sc.nextInt();
        sc.nextLine(); // limpiar salto de línea
        HashOpened<String> table = new HashOpened<>(size);

        do {
            System.out.println("\n--- Tabla Hash Abierta ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Mostrar");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();
            options(option, table);
        } while (option != 5);
    }

    public static void options(int option, HashOpened<String> hash) {
        int key;
        switch (option) {
            case 1 -> {
                System.out.print("Ingrese la clave (Integer): ");
                key = sc.nextInt();
                sc.nextLine(); // limpiar salto de línea
                System.out.print("Ingrese el valor (String): ");
                String value = sc.nextLine();
                Register<String> register = new Register<>(key, value);
                hash.insert(register);
            }
            case 2 -> {
                System.out.print("Ingrese la clave a buscar (Integer): ");
                key = sc.nextInt();
                sc.nextLine();
                String result = hash.search(key);
                System.out.println(result == null ? "No encontrado" : "Valor encontrado: " + result);
            }
            case 3 -> {
                System.out.print("Ingrese la clave a eliminar: ");
                key = sc.nextInt();
                sc.nextLine();
                hash.remove(key);
                System.out.println("Clave eliminada (si existía).");
            }
            case 4 ->
                hash.showTable();
            case 5 ->
                System.out.println("Terminado.");
            default ->
                System.out.println("Ingrese una opción válida.");
        }
    }
}
