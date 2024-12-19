package com.lab1.exception;



public class GlobalExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("出错啦:" + e.getMessage());
        e.printStackTrace();
    }
}
