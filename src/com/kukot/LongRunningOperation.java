package com.kukot;

public class LongRunningOperation implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; ++i) {
                if (Thread.interrupted()) {
                    System.out.println("Thread is interrupted, no longer printing output");
                    throw new InterruptedException("Interrupted");
                }
                System.out.println("Running an expensive operation");
            }
        }catch (InterruptedException ie) {
            // TODO: do something when thread is interrupted. Eg: cleanup resources and then return
            return;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LongRunningOperation ops = new LongRunningOperation();
        Thread myThread = new Thread(ops);
        myThread.start();
        System.out.println("Main thread will sleep for 2 milliseconds");
        Thread.sleep(2);
        System.out.println("Now interrupt myThread");
        myThread.interrupt();
    }
}
