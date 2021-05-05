package printinorder.local.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/1 9:13 PM
 * @Version 1.0
 */
public class FooCountDownLatch {
    CountDownLatch countDownLatch1 = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(1);

    public void first(Runnable runnableFirst) throws InterruptedException{
        runnableFirst.run();
        countDownLatch1.countDown();
    }

    public void second(Runnable runnableSecond) throws InterruptedException{
        countDownLatch1.await();
        runnableSecond.run();
        countDownLatch2.countDown();
    }

    public void third(Runnable runnableThird) throws InterruptedException{
        countDownLatch2.await();
        runnableThird.run();
    }

    public static void main(String[] args) {
        Runnable runnableFirst = () -> System.out.println(Thread.currentThread().getName() + ": first");
        Runnable runnableSecond = () -> System.out.println(Thread.currentThread().getName()+ ": Second");
        Runnable runnableThird = () -> System.out.println(Thread.currentThread().getName() + ": Third");

        FooCountDownLatch fooCountDownLatch = new FooCountDownLatch();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            try {
                fooCountDownLatch.third(runnableThird);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                fooCountDownLatch.first(runnableFirst);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                fooCountDownLatch.second(runnableSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}