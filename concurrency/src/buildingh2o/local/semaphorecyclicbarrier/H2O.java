package buildingh2o.local.semaphorecyclicbarrier;

import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/16 7:07 PM
 * @Version 1.0
 */
class H2O {
    private static Semaphore semaphoreH;
    private static Semaphore semaphoreO;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        semaphoreH.release(2);
        semaphoreO.release(1);
    });

    public H2O() {
        semaphoreH = new Semaphore(2);
        semaphoreO = new Semaphore(1);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable runnableH = () -> System.out.println("H");
        Runnable runnableO = () -> System.out.println("O");
        H2O h2O = new H2O();
        int n = 3;
        for (int i = 0; i < 2 * n; i++) {
            executorService.execute(() -> {
                try {
                    semaphoreH.acquire();
                    h2O.hydrogen(runnableH);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < n; i++) {
            executorService.execute(() -> {
                try {
                    semaphoreO.acquire();
                    h2O.oxygen(runnableO);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
    }
}