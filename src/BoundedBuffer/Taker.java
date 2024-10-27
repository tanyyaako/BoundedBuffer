package BoundedBuffer;

import java.util.concurrent.CountDownLatch;

public class Taker implements Runnable {
    private final BoundedBuffer<Integer> boundedBuffer;
    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;

    public Taker(BoundedBuffer<Integer> boundedBuffer, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.boundedBuffer = boundedBuffer;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            System.out.println("Took element "+boundedBuffer.take());
            finishSignal.countDown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
