package nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liuzhengyang on 2015/2/13.
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        while(true){
            Socket socket = ss.accept();  // blocking call never n
            Util.process(socket);
        }
    }
}
