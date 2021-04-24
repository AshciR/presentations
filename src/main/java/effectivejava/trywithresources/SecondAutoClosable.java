package effectivejava.trywithresources;

public class SecondAutoClosable implements AutoCloseable {

    public SecondAutoClosable() {
        System.out.println("Second Resource is created.");
    }

    public void execute() {
        System.out.println("Second Resource does something.");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Second Resource closes.");
    }
}
