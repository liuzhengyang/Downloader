package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Takekaway
 * threading, concurence, Java NIO:BUffers, Channels
 * 1.Simple single threaded server - old IO
 * 2.Multiple threaded server - old IO
 * 3.ExecutorServices - fixed thread pools,
 *  cached thread pools, configure with queues and rejected execution handlers
 * 4. New IO - NIO - 12 years old
 * 5. Nonblocking IO - single threaded with polling
 * 6. Nonblocking IO - Selector
 * 7. Nonblocking IO - Selector with separate thread pool processing the result
 *
 * Created by liuzhengyang on 2015/2/12.
 */
public class WriteReadFileTest {

    public static void main(String[] args) {
        System.out.println("Hello Beijing");
    }

    @Test
    public void testWriteCode() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("hello1");
        lines.add("hello2");

        Path pathToFile = Paths.get("file.txt");

        Files.write(pathToFile, lines);

        List<String> line2 = Files.readAllLines(pathToFile);

        line2.forEach(System.out::println);
    }
}
