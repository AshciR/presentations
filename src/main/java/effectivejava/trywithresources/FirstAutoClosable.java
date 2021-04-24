package effectivejava.trywithresources;

public class FirstAutoClosable implements AutoCloseable {

    public FirstAutoClosable() {
        System.out.println("First Resource is created.");
    }

    public void execute() {
        System.out.println("First Resource does something.");
    }

    @Override
    public void close() throws Exception {
        System.out.println("First Resource closes.");
    }
}
