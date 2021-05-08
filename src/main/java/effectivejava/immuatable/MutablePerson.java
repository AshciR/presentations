package effectivejava.immuatable;

/**
 * This is a naive mutable implementation of a person class
 */
public class MutablePerson implements Runnable {

    // Note that the fields aren't final
    private String firstName;
    private String lastName;
    private String title;

    public MutablePerson(String firstName, String lastName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    // The addition of these setters introduce unnecessary mutability
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " is running on Thread: " + Thread.currentThread().getId());

        } catch (Exception ex) {
            System.out.println(ex + "Exception is caught");
        }
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + ", " + this.title;
    }
}
