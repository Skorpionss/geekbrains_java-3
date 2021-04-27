package lesson_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MassVarray<M>{
    private M[] mass;


    public MassVarray(M[] mass) {
        this.mass = mass;
    }

    public ArrayList transformation (){
        List<M> massList = new ArrayList<M>();
        Collections.addAll(massList, mass);
        return (ArrayList) massList;

    }
}
