package buildingh2o.online;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/5/16 8:08 PM
 * @Version 1.0
 */
class H2O {
    private Semaphore semaphoreH;
    private Semaphore semaphoreO;

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        semaphoreH.release(2);
        semaphoreO.release(1);
    });

    public H2O() {
        this.semaphoreH = new Semaphore(2);
        this.semaphoreO = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}