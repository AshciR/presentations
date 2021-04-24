package effectivejava.staticfactories;

public class TvShow implements Watchable {

    private final String name;
    private final Rating rating;
    private final Integer seasons;

    @Override
    public String watch() {
        return "" + name + " is being watched and it has " + seasons + " seasons";
    }

    public TvShow(String name, Rating rating, Integer seasons) {
        this.name = name;
        this.rating = rating;
        this.seasons = seasons;
    }

    public String getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public Integer getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return "TvShow{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", seasons=" + seasons +
                '}';
    }
}
