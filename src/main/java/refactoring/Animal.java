package refactoring;

import java.util.Queue;

abstract public class Animal {

    protected String name;

    // We'll use the to represent the food our pet eats throughout the day
    protected Queue<String> stomach;

    /**
     * Adds food the the pet's stomach
     * @param food the food our pet will eat
     * @return the same pet with food added to it's stomach
     */
    public Animal feed(String food) {
        this.stomach.offer(food);
        return this;
    }

    /**
     * The pet will remove things from it's stomach in
     * the order they ate it.
     * @return the same pet with a lighter stomach
     */
    public Animal poop() {
        this.stomach.poll(); // Retrieves and removes the head of this queue, or returns null if this queue is empty.
        return this;
    }

    public String getName() {
        return name;
    }

    public Queue<String> getStomach() {
        return stomach;
    }

}
