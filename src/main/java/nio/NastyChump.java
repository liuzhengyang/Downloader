package nio;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by liuzhengyang on 2015/2/13.
 */
public class NastyChump {

    public static void main(String[] args) {
        for (int i = 0; i < 3000; i++) {
            try {
                new Socket("localhost", 8080);
                System.out.println(i);
            } catch (IOException e) {
                System.out.println("Could not connect - " + e);
            }
        }
    }
}
