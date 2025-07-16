
import java.util.Objects;

public class Register<E> implements Comparable<Register<E>> {

    private E value;
    private int key;

    public Register(int key, E value) {
        this.key = key;
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Register)) {
            return false;
        }
        Register<?> other = (Register<?>) obj;
        return key == other.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public int compareTo(Register<E> other) {
        return Integer.compare(this.key, other.key);
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }
}
