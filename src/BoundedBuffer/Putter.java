package BoundedBuffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Putter implements Runnable {
    private final BoundedBuffer<Integer> boundedBuffer;

    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;

    public Putter(BoundedBuffer<Integer> boundedBuffer, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.boundedBuffer = boundedBuffer;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            startSignal.await();
            boundedBuffer.put(random.nextInt(0, 10));
            finishSignal.countDown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
