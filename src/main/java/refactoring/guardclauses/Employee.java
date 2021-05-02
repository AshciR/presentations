package refactoring.guardclauses;

public class Employee {

    final private Long id;
    final private String name;
    final private Boolean isAlive;
    final private Boolean isEmployed;
    final private Boolean isTerminated;
    final private Boolean isRetired;

    /*
    Check out the presentation under src/main/java/effectivejava/staticfactories to learn
    more about static factory methods
     */
    public static Employee createWorkingEmployee(long id, String name) {
        return new Employee(id, name, true, true, false, false);
    }

    public static Employee createRetiredEmployee(long id, String name) {
        return new Employee(id, name, true, false, false, true);
    }

    public static Employee createTerminatedEmployee(long id, String name) {
        return new Employee(id, name, true, false, true, false);
    }

    public static Employee createDeadEmployee(long id, String name) {
        return new Employee(id, name, false, false, false, false);
    }

    public static Employee createResignedEmployee(long id, String name) {
        return new Employee(id, name, true, false, false, false);
    }

    private Employee(Long id, String name, Boolean isAlive,
                     Boolean isEmployed, Boolean isTerminated, Boolean isRetired) {
        this.id = id;
        this.name = name;
        this.isAlive = isAlive;
        this.isEmployed = isEmployed;
        this.isTerminated = isTerminated;
        this.isRetired = isRetired;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public Boolean isEmployed() {
        return isEmployed;
    }

    public Boolean isTerminated() {
        return isTerminated;
    }

    public Boolean isRetired() {
        return isRetired;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAlive=" + isAlive +
                ", isEmployed=" + isEmployed +
                ", isTerminated=" + isTerminated +
                ", isRetired=" + isRetired +
                '}';
    }
}
