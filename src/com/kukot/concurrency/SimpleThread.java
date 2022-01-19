package com.kukot.concurrency;

public class SimpleThread extends Thread {

    public static void main(String[] args) {
        Thread simpleThread = new SimpleThread();
        Thread runnableThread = new Thread(new MyRunnableClass());
        Thread lambdaRunnableThread = new Thread(() -> showThreadName("lambda runner"));
        showThreadName("Main Thread");
        simpleThread.start();
        runnableThread.start();
        lambdaRunnableThread.start();
        simpleThread.interrupt();
        //lambdaRunnableThread.run();// this is, however executed by main thread

    }

    @Override
    public void run() {
        System.out.println("Sleep 5 secs before showing thread name which is " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("I was interrupted before showing my own thread name");
            return; // simply return from the run method
        }
        showThreadName("Simple Thread");
    }

    public static void showThreadName(String specialSignature) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " and is specialized by " + specialSignature);
    }

    public static class MyRunnableClass implements Runnable {
        @Override
        public void run() {
            showThreadName("My Runnable Class");
        }
    }
}
