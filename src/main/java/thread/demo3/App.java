package thread.demo3;

import java.util.concurrent.Executors;

/**
 * Created by liuzhengyang on 2015/2/2.
 */
public class App {

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            public void run(){
                for(int i = 0; i < 10; i++){
                    System.out.println("Hello " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
    }
}
