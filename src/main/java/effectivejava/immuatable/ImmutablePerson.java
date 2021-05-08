package effectivejava.immuatable;

/**
 * This is a immutable implementation of a person class
 */
// Note that we made this class final
public final class ImmutablePerson implements Runnable {

    public static final ImmutablePerson JON_SNOW_BASTARD = new ImmutablePerson("Jon", "Snow", "Bastard");
    public static final ImmutablePerson LORD_COMMANDER_SNOW = new ImmutablePerson("Jon", "Snow", "Lord Commander");
    public static final ImmutablePerson KING_OF_THE_NORTH = new ImmutablePerson("Jon", "Snow", "King of the North");

    // Note that the fields are final
    private final String firstName;
    private final String lastName;
    private final String title;

    // The only way to construct a person
    public ImmutablePerson(String firstName, String lastName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    // From my experiences, it's convenient to add a copy constructor at time
    // especially when you want to create defensive copies of an object,
    // or use Stream APIs.
    public ImmutablePerson(ImmutablePerson that) {
        this.firstName = that.firstName;
        this.lastName = that.lastName;
        this.title = that.title;
    }

    // If we want to change the title, we'll return a new immutable
    // object with the updated info
    public ImmutablePerson changeTitle(String title) {
        return new ImmutablePerson(this.firstName, this.lastName, title);
    }

    // Note that there are no setters.
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    // Implements Runnable which allows us to make this multi-threaded.
    @Override
    public void run() {
        System.out.println(this + " is running on Thread: " + Thread.currentThread().getId());
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + ", " + this.title;
    }
}
