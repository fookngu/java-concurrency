package com.kukot.concurrency;

public class Interrupts {

    public static void main(String[] args) throws InterruptedException {
        SleepAndInterrupt mySleepInterrupt = new SleepAndInterrupt("Kevin Nguyen");
       Thread myThread = new Thread(mySleepInterrupt);
        myThread.start();
        Thread.sleep(16000);
        myThread.interrupt();
        System.out.println("Sent interrupt to myThread");
    }


    public static class SleepAndInterrupt implements Runnable {

        private String name;
        public SleepAndInterrupt(String name) {
            this.name = name;
        }
        /**
         * this simply print its name every 5 seconds
         * if its own thread is interrupted, return
         */
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("My name is " + this.name);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Someone interrupted me!! Bye bye");
                    return;
                }
            }

        }
    }


}
