package refactoring;

import java.util.LinkedList;
import java.util.Queue;

public class Dog extends Animal {

    public Dog(String name) {
        super.name = name;
        super.stomach = new LinkedList<>();
    }

    public Dog(String name, Queue<String> stomach) {
        super.name = name;
        super.stomach = stomach;
    }
}
