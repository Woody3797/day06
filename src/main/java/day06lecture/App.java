package day06lecture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\tRunnable ..." + i);
                }                
            }
            
        });
        //thread1.start();

        MyRunnableImplementation mRI1 = new MyRunnableImplementation("task 1");
        MyRunnableImplementation mRI2 = new MyRunnableImplementation("task 2");
        MyRunnableImplementation mRI3 = new MyRunnableImplementation("task 3");
        MyRunnableImplementation mRI4 = new MyRunnableImplementation("task 4");
        MyRunnableImplementation mRI5 = new MyRunnableImplementation("task 5");


        // Thread thread2 = new Thread(mRI);
        // thread2.start();

        // Thread thread3 = new Thread(mRI);
        // thread3.start();

        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // executorService.execute(mRI1);
        // executorService.execute(mRI2);
        // executorService.shutdown();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(mRI1);
        executorService.execute(mRI2);
        executorService.execute(mRI3);
        executorService.execute(mRI4);
        executorService.execute(mRI5);

        executorService.shutdown();

    }
}
