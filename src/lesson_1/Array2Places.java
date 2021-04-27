package lesson_1;

public class Array2Places<T> {

    private T [] mass;
    private T id0;

    public Array2Places(T[] mass) {
        this.mass = mass;
    }

    public T[] getMass() {
        return mass;
    }

    public T[] massMestami (){
        id0 = mass[0];
        mass[0] = mass[1];
        mass[1]=id0;
        return mass;
    }
}
