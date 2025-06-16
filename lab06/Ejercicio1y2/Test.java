package Ejercicio1y2;
public class Test {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        System.out.println("Insertando elementos...");
        for (int i = 10; i >= 1; i--) {
            tree.insert(i);
        }

        System.out.print("InOrder: ");
        tree.inOrder();

        System.out.print("PreOrder: ");
        tree.preOrder();

        System.out.print("PostOrder: ");
        tree.postOrder();

        System.out.println("¿Está vacío?: " + tree.isEmpty());
        System.out.println("¿Contiene el 5?: " + tree.search(5));
        System.out.println("Mínimo: " + tree.min());
        System.out.println("Máximo: " + tree.max());
        System.out.println("Predecesor de 5: " + tree.predecessor(5));
        System.out.println("Sucesor de 5: " + tree.successor(5));

        System.out.println("Eliminando 5...");
        tree.remove(5);
        System.out.print("InOrder tras eliminar 5: ");
        tree.inOrder();

        System.out.println("Destruyendo árbol...");
        tree.destroy();
        System.out.println("¿Está vacío?: " + tree.isEmpty());
    }
}
