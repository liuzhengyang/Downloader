package nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by liuzhengyang on 2015/2/13.
 */
public class Util {

    public static int transmogrify(int data){
        if(Character.isLetter(data)){
            return data ^ ' ';
        }
        return data;
    }

    public static void process(Socket socket) {
        System.out.println("Connection from " + socket.getRemoteSocketAddress());
        try(
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream()
        ){
            int data;
            while((data = in.read())!= - 1){
                data = Util.transmogrify(data);
                out.write(data);
            }
        }catch (IOException e){
            System.out.println("Connection problem - " + e);
        }
    }
}
