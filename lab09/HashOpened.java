import java.util.ArrayList;
import java.util.Iterator;

public class HashOpened<E> {

    private ArrayList<Register<E>>[] table;
    private int size;

    public HashOpened(int size) {
        this.size = size;
        table = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<>();
        }
    }

    private int hashFunction(int key) {
        return Math.abs(key) % size;
    }

    public void insert(Register<E> register) {
        int index = hashFunction(register.getKey());
        table[index].add(register);
    }

    public void remove(int key) {
        int index = hashFunction(key);
        Iterator<Register<E>> it = table[index].iterator();
        while (it.hasNext()) {
            if (it.next().getKey() == key) {
                it.remove();
                break;
            }
        }
    }

    public E search(int key) {
        int index = hashFunction(key);
        for (Register<E> reg : table[index]) {
            if (reg.getKey() == key) {
                return reg.getValue();
            }
        }
        return null;
    }

    public void showTable() {
        System.out.println("--- Estado de la tabla (Hash Abierto) ---");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("[VACÍO]");
            } else {
                for (Register<E> reg : table[i]) {
                    System.out.print(reg + " ");
                }
                System.out.println();
            }
        }
    }
}
