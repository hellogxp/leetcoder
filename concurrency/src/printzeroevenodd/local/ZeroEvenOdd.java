package printzeroevenodd.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/10 9:38 PM
 * @Version 1.0
 */
class ZeroEvenOdd {
    private int n;
    private Semaphore semaphore0 = new Semaphore(1);
    private Semaphore semaphoreEven = new Semaphore(0);
    private Semaphore semaphoreOdd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphore0.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                semaphoreEven.release();
            } else {
                semaphoreOdd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n ; i += 2) {
            semaphoreEven.acquire();
            printNumber.accept(i);
            semaphore0.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            semaphoreOdd.acquire();
            printNumber.accept(i);
            semaphore0.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        IntConsumer intConsumer0 = x -> System.out.println(x);
        IntConsumer intConsumerOdd = x -> System.out.println(x);
        IntConsumer intConsumerEven = x -> System.out.println(x);
        executorService.execute(() -> {
            try {
                zeroEvenOdd.zero(intConsumer0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                zeroEvenOdd.odd(intConsumerOdd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                zeroEvenOdd.even(intConsumerEven);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}