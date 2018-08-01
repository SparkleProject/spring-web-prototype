package com.lintech.core;

public class ThreadTest implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("Test");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Thread thread=new Thread(new ThreadTest());
        thread.start();
    }

}
