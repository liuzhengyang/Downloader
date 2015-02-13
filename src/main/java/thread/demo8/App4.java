package thread.demo8;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementing BlockingQueue using wait() and notify()
 * Created by liuzhengyang on 2015/2/10.
 */
public class App4 {

    private Queue<Integer> queue = new ArrayDeque<>(10);
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        App4 app2 = new App4();
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
        try{
            lock.lock();
            while(queue.size() == 10){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int num = random.nextInt(100);
            queue.add(num);
            System.out.println("Produce " + num + " Thread" + Thread.currentThread().getName());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    private void consume(){
        try{
            lock.lock();
            while(queue.size() == 0){
                try {
                    condition.await();
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
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
