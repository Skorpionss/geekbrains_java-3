package lesson_1;

public class Main {
    public static void main(String[] args) {
        String[] mass = {"Я первый"," Я второй"};
        Integer[] intmass = {1,2};
        for (int i = 0; i< mass.length; i++ ) System.out.println(mass[i]);
        for (int i = 0; i< intmass.length; i++ ) System.out.println(intmass[i]);

        Array2Places<String> s1 = new Array2Places<>(mass);
        Array2Places<Integer> i1 = new Array2Places<>(intmass);

        s1.massMestami();
        i1.massMestami();

        for (int i = 0; i< mass.length; i++ ) System.out.println(mass[i]);
        for (int i = 0; i< intmass.length; i++ ) System.out.println(intmass[i]);
    }
}
