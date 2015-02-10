package thread.demo8;

import java.util.Scanner;

/**
 * Created by liuzhengyang on 2015/2/10.
 */
public class App3 {

    public static void main(String[] args) {
        App3 app3 = new App3();
        Thread t1 = new Thread(()->{
            try {
                app3.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                app3.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

    }

    private void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer Thread running...");
            wait();
            System.out.println("Resumed after waiting");
        }
    }

    private void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this){
            System.out.println("Waiting for return key .   ");
            scanner.nextLine();
            System.out.println("Return key pressed . ");
            this.notify();
            Thread.sleep(1000*5);
        }
    }
}
