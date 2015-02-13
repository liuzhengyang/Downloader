package util.concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/2/11.
 */
public class ThreadPoolExecutorTest {


    @Test
    public void testBasic(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        threadPool.submit(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!threadPool.isTerminated()){
            try {
                Thread.sleep(100*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait");
            System.out.println(threadPool.getQueue());
        }
    }
}
