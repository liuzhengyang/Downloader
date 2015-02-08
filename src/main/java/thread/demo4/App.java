package thread.demo4;

import java.util.Scanner;

/**
 * Created by liuzhengyang on 2015/2/3.
 */
class Processor extends Thread{
    private boolean running = true;
    public void run(){
        while(running){
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        this.running = false;
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {

        Processor processor = new Processor();
        processor.start();

        Scanner scanner = new Scanner(System.in);
        while(! scanner.nextLine().equals("s")){

        }
        processor.shutdown();


//        Thread.sleep(1000*3);
//
//        System.out.println("shutdown");
//        processor.shutdown();
//        System.out.println(processor.getState());
//        System.out.println(processor.isAlive());
//        System.out.println(processor.getState());

//        Thread.sleep(1000*10);
//        System.out.println("to interrupt");
//        processor.interrupt();
//        System.out.println(processor.getState());
//        System.out.println(processor.isAlive());
//        processor.join();
//        System.out.println("join");
//        System.out.println(processor.getState());
//        System.out.println(processor.isAlive());

    }
}
