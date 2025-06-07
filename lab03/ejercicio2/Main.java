package ejercicio2;

import java.util.Scanner;

public class Main {

    static <T extends Number> double suma(T valor1, T valor2) {
        return valor1.doubleValue() + valor2.doubleValue();
    }

    static <T extends Number> double resta(T valor1, T valor2) {
        return valor1.doubleValue() - valor2.doubleValue();
    }

    static <T extends Number> double producto(T valor1, T valor2) {
        return valor1.doubleValue() * valor2.doubleValue();
    }

    static <T extends Number> double division(T valor1, T valor2) {
        if (valor2.doubleValue() == 0) throw new ArithmeticException("División por cero.");
        return valor1.doubleValue() / valor2.doubleValue();
    }

    static <T extends Number> double potencia(T valor1, T valor2) {
        return Math.pow(valor1.doubleValue(), valor2.doubleValue());
    }

    static <T extends Number> double raizCuadrada(T valor) {
        return Math.sqrt(valor.doubleValue());
    }

    static <T extends Number> double raizCubica(T valor) {
        return Math.cbrt(valor.doubleValue());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Raíz Cúbica");
            System.out.println("8. Salir del Programa");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 5) {
                System.out.print("Ingrese el primer valor (entero o decimal): ");
                double v1 = scanner.nextDouble();
                System.out.print("Ingrese el segundo valor (entero o decimal): ");
                double v2 = scanner.nextDouble();

                switch (opcion) {
                    case 1 -> System.out.println("Resultado: " + suma(v1, v2));
                    case 2 -> System.out.println("Resultado: " + resta(v1, v2));
                    case 3 -> System.out.println("Resultado: " + producto(v1, v2));
                    case 4 -> {
                        try {
                            System.out.println("Resultado: " + division(v1, v2));
                        } catch (ArithmeticException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    case 5 -> System.out.println("Resultado: " + potencia(v1, v2));
                }
            } else if (opcion == 6 || opcion == 7) {
                System.out.print("Ingrese un valor (entero o decimal): ");
                double v = scanner.nextDouble();

                if (opcion == 6)
                    System.out.println("Raíz Cuadrada: " + raizCuadrada(v));
                else
                    System.out.println("Raíz Cúbica: " + raizCubica(v));
            } else if (opcion == 8) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción inválida.");
            }

        } while (opcion != 8);

        scanner.close();
    }
}
