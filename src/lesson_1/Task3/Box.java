package lesson_1.Task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box<? extends Fruit>>{
    private List<T> fruit;

    public Box() {
        this.fruit = new ArrayList<>();
    }

    public List<T> getFruit() {
        return fruit;
    }

    public float getWeight(){
        if (fruit.isEmpty()){
            return 0.0f;
        }
       return fruit.get(0).getWeight() * fruit.size();
    }

    public boolean compare(Box<? extends Fruit> th) {
        return getWeight() == th.getWeight();
    }


    @Override
    public int compareTo(Box<? extends Fruit> th) {
        return Float.compare(getWeight(), th.getWeight());
    }

    public void bulk(Box<T> th){
        fruit.addAll(th.getFruit());
        th.getFruit().clear();
    }

    public void add(T fr){
        this.fruit.add(fr);
    }
}
