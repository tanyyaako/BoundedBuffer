package BoundedBuffer;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException{
        BoundedBuffer<Integer> buffer= new BoundedBuffer<Integer>(2);
        int threads=100;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(threads);
        for (int i = 0; i < threads/2; i++) {
            new Thread(new Putter(buffer,startSignal,finishSignal)).start();
        }
        for(int i = 0; i<threads/2; i++){
            new Thread(new Taker(buffer,startSignal,finishSignal)).start();
        }
        System.out.println("Начало работы");
        startSignal.countDown();
        finishSignal.await();
        System.out.println("Завершение работы");
    }
}
