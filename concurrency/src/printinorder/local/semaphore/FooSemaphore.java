package printinorder.local.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/1 11:57 PM
 * @Version 1.0
 */
public class FooSemaphore {
    Semaphore semaphore1 = new Semaphore(0);
    Semaphore semaphore2 = new Semaphore(0);

    public void first(Runnable runnableFirst) {
        runnableFirst.run();
        semaphore1.release();
    }

    public void second(Runnable runnableSecond) throws InterruptedException {
        semaphore1.acquire();
        runnableSecond.run();
        semaphore2.release();
    }

    public void third(Runnable runnableThird) throws InterruptedException {
        semaphore2.acquire();
        runnableThird.run();
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable runnableFirst = () -> System.out.println("one");
        Runnable runnableSecond = () -> System.out.println("two");
        Runnable runnableThird = () -> System.out.println("third");

        FooSemaphore fooSemaphore = new FooSemaphore();

        executorService.execute(() -> {
            try {
                fooSemaphore.third(runnableThird);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                fooSemaphore.second(runnableSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            fooSemaphore.first(runnableFirst);
        });
    }
}