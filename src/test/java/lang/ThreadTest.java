package lang;

import org.junit.Test;

/**
 * Created by Administrator on 2015/2/10.
 */
public class ThreadTest {


    @Test
    public void testPause(){
        Thread thread = new Thread(()->{
            System.out.println("Thread run");
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("pause");
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
