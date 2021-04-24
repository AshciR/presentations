package effectivejava.staticfactories;

class Movie implements Watchable {

    private final String name;
    private final Rating rating;

    @Override
    public String watch() {
        return "" + name + " is being watched";
    }

    public Movie(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }

    public static Movie getGRatedMovie() {
        return new Movie("Finding Nemo", Rating.G);
    }

    public static Movie getRRatedMovie() {
        return new Movie("Deadpool", Rating.R);
    }

    public String getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
