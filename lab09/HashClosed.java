

public class HashClosed<E> {

    private Register<E>[] table;
    private int size;
    private static final Register<?> DELETED = new Register<>(-1, null);

    @SuppressWarnings("unchecked")
    public HashClosed(int size) {
        this.size = size;
        this.table = new Register[size];
    }

    private int hashFunction(int key) {
        return Math.abs(key) % size;
    }

    private int rehashFunction(int key, int i) {
        return (hashFunction(key) + i) % size; // Linear probing
    }

    public void insert(Register<E> register) {
        int key = register.getKey();
        for (int i = 0; i < size; i++) {
            int index = rehashFunction(key, i);
            if (table[index] == null || table[index].equals(DELETED)) {
                table[index] = register;
                return;
            }
        }
        System.out.println("Tabla llena. No se pudo insertar " + register);
    }

    public boolean remove(int key) {
        for (int i = 0; i < size; i++) {
            int index = rehashFunction(key, i);
            if (table[index] == null) {
                return false; // No se encontró
            }
            if (table[index].getKey() == key) {
                table[index] = (Register<E>) DELETED;
                return true;
            }
        }
        return false;
    }

    public E search(int key) {
        for (int i = 0; i < size; i++) {
            int index = rehashFunction(key, i);
            if (table[index] == null) {
                return null;
            }
            if (table[index].getKey() == key) {
                return table[index].getValue();
            }
        }
        return null;
    }

    public void showTable() {
        System.out.println("--- Estado de la tabla (Hash Cerrado) ---");
        for (int i = 0; i < size; i++) {
            if (table[i] == null) {
                System.out.println(i + ": [VACÍO]");
            } else if (table[i].equals(DELETED)) {
                System.out.println(i + ": [ELIMINADO]");
            } else {
                System.out.println(i + ": " + table[i]);
            }
        }
    }
}
