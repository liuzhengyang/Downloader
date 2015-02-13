package nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by liuzhengyang on 2015/2/13.
 */
public class SimpleExecutorServiceServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
//        ExecutorService pool = new ThreadPoolExecutor(
//                0, 1000, 60L, TimeUnit.SECONDS,
//                new SynchronousQueue<>(),
//                new ThreadPoolExecutor.CallerRunsPolicy()
//        );
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        while(true){
            Socket s = ss.accept();
            pool.submit(()->Util.process(s));
        }

    }
}
