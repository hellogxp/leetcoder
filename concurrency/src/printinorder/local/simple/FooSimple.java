package printinorder.local.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/4/30 11:39 PM
 * @Version 1.0
 */
public class FooSimple {
    AtomicInteger flag = new AtomicInteger(0);

    public void first(Runnable runnerFirst) {
        runnerFirst.run();
        flag.incrementAndGet();
    }

    public void second (Runnable runnerSecond) {
        while (flag.get() != 1) {
        }
        runnerSecond.run();
        flag.incrementAndGet();
    }

    public void third(Runnable runnerThird) {
        while (flag.get() != 2) {
        }
        runnerThird.run();
    }

    public static void main(String[] args) {
        FooSimple fooSimple = new FooSimple();

        Runnable runnableFirst = () -> System.out.println(Thread.currentThread().getName() + ": first");
        Runnable runnableSecond = () -> System.out.println(Thread.currentThread().getName() + ": second");
        Runnable runnableThird = () -> System.out.println(Thread.currentThread().getName() + ": third");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> fooSimple.third(runnableThird));
        executorService.execute(() -> fooSimple.second(runnableSecond));
        executorService.execute(() -> fooSimple.first(runnableFirst));

    }
}