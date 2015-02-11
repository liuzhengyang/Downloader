package thread.demo8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhengyang on 2015/2/11.
 */
public class App5 {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{});
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private void increment(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }

    private void firstThread(){
        lock.lock();
        try{
            increment();
        }finally {
            lock.unlock();
        }
    }

    public void secondThread(){
        lock.lock();
        try{
            increment();
        }finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Count is " + count);
    }
}
