package thread.demo8;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liuzhengyang on 2015/2/9.
 */
public class App {
    // Thread safe queue
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);


    public static void main(String[] args) {
        App app = new App();
        Thread t1 = new Thread(()->{app.produce();});
        Thread t2 = new Thread(()->{app.consume();});
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void produce(){
        Random random = new Random();
        while(true){
            try {
                queue.put(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume(){
        Random random = new Random();
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(random.nextInt(10) == 0){
                try {
                    System.out.println(Thread.currentThread().getName() + " Get Data from queue " + queue.take() + " left " + queue.size() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
