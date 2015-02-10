package thread.demo8;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Implementing BlockingQueue using wait() and notify()
 * Created by liuzhengyang on 2015/2/10.
 */
public class App2 {

    private Queue<Integer> queue = new ArrayDeque<>(10);

    public static void main(String[] args) {
        App2 app2 = new App2();
        Thread t1 = new Thread(()->{
            while(true){
                app2.produce();
            }
        });
        Thread t2 = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(new Random().nextInt(10) == 0){
                    app2.consume();
                }
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
    }

    private void produce(){
        Random random = new Random();
        synchronized (this){
            while(queue.size() >= 10){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int num = random.nextInt(100);
            queue.add(num);
            System.out.println("Produce " + num + " Thread" + Thread.currentThread().getName());
            this.notify();
        }
    }

    private void consume(){
        synchronized (this){
            while(queue.size() <= 0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " consume " + queue.poll() + " Left " + queue.size());
            this.notify();
        }
    }
}
