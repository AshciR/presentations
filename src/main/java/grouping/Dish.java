package grouping;

import java.util.Objects;
import java.util.function.Function;

public class Dish {

    final private String name;
    final private Integer calories;
    final private Type type;

    enum Type {
        MEAT, SEAFOOD, VEGETARIAN
    }

    public Dish(String name, Integer calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public static final Function<Dish, CaloricLevel> determineDishCaloricLevel = dish -> {
        if (dish.getCalories() <= 300) return CaloricLevel.DIET;
        else if (dish.getCalories() > 300 && dish.getCalories() <= 500) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    };

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return name.equals(dish.name) && calories.equals(dish.calories) && type == dish.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, type);
    }
}
