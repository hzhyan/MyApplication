package com.example.javalib;

public class MyClass {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                printStr();
            }
        });
        thread2.start();

    }

    private synchronized static void lock() {
        try {
            System.out.println("start sleep");
            Thread.currentThread().sleep(3000);
            System.out.println("sleep finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printStr() {
        System.out.println("print something");
    }
}
