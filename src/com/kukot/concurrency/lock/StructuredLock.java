package com.kukot.concurrency.lock;

import java.util.stream.IntStream;

public class StructuredLock {


    public static class NotThreadSafeCounter implements Changeable {
        public NotThreadSafeCounter(int counter) {
            this.counter = counter;
        }

        private int counter;

        @Override
        public void increase() {
            counter++;
        }

        @Override
        public void decrease() {
            counter--;
        }

        @Override
        public String toString() {
            return "Current value is " + counter;
        }
    }

    public static class Modifier implements Runnable {

        private final Changeable data;
        private final boolean isIncrement;

        public Modifier(Changeable data, boolean isIncrement) {
            this.data = data;
            this.isIncrement = isIncrement;
        }

        @Override
        public void run() {
            IntStream.range(1, 1000000).forEach(it -> {
                if (isIncrement) {
                    data.increase();
                } else {
                    data.decrease();
                }
            });
        }
    }

    interface Changeable {
        void increase();

        void decrease();
    }

    public static class ThreadSafeCounter implements Changeable {

        public ThreadSafeCounter(int counter) {
            this.counter = counter;
        }

        private int counter;

        @Override
        synchronized public void increase() {
            counter++;
        }

        @Override
        synchronized public void decrease() {
            counter--;
        }

        @Override
        public String toString() {
            return "Current value is " + counter;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("################### Thread Safe Counter ####################");
        Changeable data = new NotThreadSafeCounter(100);
        Modifier incrementer = new Modifier(data, true);
        Modifier decrement = new Modifier(data, false);
        Thread incThread = new Thread(incrementer);
        Thread desThread = new Thread(decrement);
        incThread.start();
        desThread.start();
        incThread.join();
        desThread.join();
        System.out.println(data);

        System.out.println("################### Not Thread Safe Counter ####################");
        Changeable safeData = new ThreadSafeCounter(100);
        Modifier incrementer2 = new Modifier(safeData, true);
        Modifier decrement2 = new Modifier(safeData, false);
        Thread incThread2 = new Thread(incrementer2);
        Thread desThread2 = new Thread(decrement2);
        incThread2.start();
        desThread2.start();
        incThread2.join();
        desThread2.join();
        System.out.println(safeData);
    }
}
