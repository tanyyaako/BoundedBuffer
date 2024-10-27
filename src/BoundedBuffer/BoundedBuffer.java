package BoundedBuffer;

import java.util.Arrays;

public class BoundedBuffer<T> {
    //wait
    //notifyALL
    private final T[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size) {
        buffer = (T[]) new Object[size];
    }

    public synchronized void put(T item) throws InterruptedException {
        while (count == buffer.length) {
            System.out.println("Буфер полон");
            wait();
        }
        buffer[count++] = item;
        System.out.println("putting "+ item);
        System.out.println(Arrays.toString(buffer));
        notifyAll();
    }
    public synchronized T take() throws InterruptedException {
        T item;
        while (count == 0) {
            System.out.println("В буфере нет элементов");
            wait();
        }
        System.out.println("Before taking");
        System.out.println(Arrays.toString(buffer));
        item = buffer[--count];
        buffer[count] = null;
        System.out.println("After taking");
        System.out.println(Arrays.toString(buffer));
        notifyAll();
        return item;
    }
}
