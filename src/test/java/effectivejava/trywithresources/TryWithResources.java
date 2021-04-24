package effectivejava.trywithresources;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Item 9: Prefer try-with-resources to try-finally
 */
public class TryWithResources {

    /*
    There are Java libraries/classes that include resources that
    must be closed manually by calling a .close() method.
    E.g. InputStream, OutputStream, Statement, Connection.

    Prior to Java 7, we'd have to write code to close the resources manually.
     */
    @Test
    void manuallyCloseResources() {

        final String pathToFile = "src/main/resources/helloworld.txt";
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(pathToFile));
            printFileContents(scanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close(); // We're manually closing the file here
            }
        }

    }

    /*
    It gets the job done, but there's a boilerplate code.
    If can avoid writing boilerplate code that distracts from
    our intent we should.

    Fortunately, in Java 7 the added the try-with-resources feature.
    Let's take a look on how the above code will look using it.
     */
    @Test
    void automaticallyCloseResources() {

        final String pathToFile = "src/main/resources/helloworld.txt";

        // Notice that you define your resource within the try parenthesis
        // and the finally block is removed.
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            printFileContents(scanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    The try-with-resources version is shorter and more readable.
    Any resource that implements the Closeable interface (has a .close() method)
    can use the feature.
     */

    /*
    If you're not convinced yet, let's take a look if we had multiple resources
     */
    @Test
    void manuallyCloseMultipleResources() {

        final String pathToSrcFile = "src/main/resources/helloworld.txt";
        Scanner scanner = null;

        final String pathToDestinationFile = "src/main/resources/bye-bye-cruel-world.txt";
        PrintWriter writer = null;

        // We would have nested try blocks.
        // Starting to look like JS callback hell,
        // and we're not front-end devs 🙃
        try {
            scanner = new Scanner(new File(pathToSrcFile));

            try {
                writer = new PrintWriter(pathToDestinationFile);
                writeContentsToFile(scanner, writer, " Bye bye cruel world.");
            } finally {
                // We're manually closing the output file here
                writer.close(); // Fun fact: If we forgot to close this, the file would be empty...
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close(); // We're manually closing the input file here
            }
        }

    }

    /*
    Let's see how the same code looks with the try-with-resources
     */
    @Test
    void automaticallyCloseMultipleResources() {

        final String pathToSrcFile = "src/main/resources/helloworld.txt";
        final String pathToDestinationFile = "src/main/resources/bye-bye-sweet-world.txt";

        // We can declare all our closable resources within the try block
        try (Scanner scanner = new Scanner(new File(pathToSrcFile)); // N.B. it's a ; not a ,
             PrintWriter writer = new PrintWriter(pathToDestinationFile)) {

            // Now we only have the logic we care about
            writeContentsToFile(scanner, writer, " Bye bye sweet world.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    Lastly, let's take a look at the closing order when we use multiple resources
     */
    @Test
    void closeOrderForMultipleResources() throws Exception {
        try (FirstAutoClosable first = new FirstAutoClosable();
             SecondAutoClosable second = new SecondAutoClosable()) {

            first.execute();
            second.execute();
        }
    }

    /*
    Notice that the resource that was defined first is closed last.
    First in, last out (FILO).

    So be cognizant of the declaration order!
     */

    // --- References ---
    // https://www.oreilly.com/library/view/effective-java/9780134686097/
    // https://www.baeldung.com/java-try-with-resources

    /// --- Helper methods ---

    private void printFileContents(Scanner scanner) {
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    private void writeContentsToFile(Scanner scanner, PrintWriter writer, String content) {
        while (scanner.hasNext()) {
            writer.print(scanner.nextLine() + content);
        }
    }

}
