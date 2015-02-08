package thread.demo5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhengyang on 2015/2/7.
 */
public class App {

    private int count = 0;
//    private Integer count = 0;
    private synchronized void increment(){
        count++;
    }
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        App app = new App();
        app.dowork();
    }

    public void dowork(){
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                increment();
                atomicInteger.addAndGet(1);
//                synchronized (App.class){
//                    count++;
//                }
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                increment();
                atomicInteger.addAndGet(1);
//                synchronized (App.class){
//                    count++;
//                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count is " + atomicInteger);
        System.out.println("count is " + count);
    }
}
