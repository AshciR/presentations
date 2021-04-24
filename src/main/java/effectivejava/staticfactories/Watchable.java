package effectivejava.staticfactories;

/**
 * Things that can be watched or viewed
 */
public interface Watchable {

    /**
     * Implementers should provide some proof that
     * the class is being watched as a String message.
     *
     * @return the message that the class is being watched.
     */
    String watch();

    // Fun fact: static method implementations are possible
    // since Java 8
    static Watchable getPGRatedMovie() {
        return new Movie("Life of Pi", Rating.PG);
    }

    static Watchable getGRatedMovie() {
        return new Movie("Finding Nemo", Rating.G);
    }

    static Watchable getRRatedMovie() {
        return new Movie("Deadpool", Rating.R);
    }

    static Watchable getTvShow() {
        return new TvShow("The Office", Rating.PG, 9);
    }

    static Watchable getMeSomethingToWatch(int freeTimeInMinutes) {
        // If we have less than 30 minutes to watch something, let's watch a TV show
        return freeTimeInMinutes < 30 ? getTvShow() : getPGRatedMovie();
    }

}
