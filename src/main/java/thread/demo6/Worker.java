package thread.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhengyang on 2015/2/8.
 */
public class Worker {

    private Random random = new Random();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock1){
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock2){
            list2.add(random.nextInt(100));
        }
    }

    public void process(){
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting....");

        long start = System.currentTimeMillis();
        Thread t1 = new Thread(()->{
            process();
        });
        t1.start();
        Thread t2 = new Thread(()->{
            process();
        });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("list1: " + list1.size() +"; list2: " + list2.size());
    }
}
