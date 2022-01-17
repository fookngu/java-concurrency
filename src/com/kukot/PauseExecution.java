package com.kukot;

public class PauseExecution {
    public static void main(String[] args) throws InterruptedException {
        String[] messages = new String[]{"First message", "Second message", "Third message", "Fourth and also the last message"};
        for (String msg : messages) {
            System.out.println(msg);
            Thread.sleep(2000);
        }
    }
}
