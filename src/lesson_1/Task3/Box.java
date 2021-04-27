package lesson_1.Task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruit;

    public Box() {
        this.fruit = new ArrayList<>();
    }

    public float getWeight(){
        if (fruit.isEmpty()){
            return 0.0f;
        }
       return fruit.get(0).getWeight() * fruit.size();
    }
}
