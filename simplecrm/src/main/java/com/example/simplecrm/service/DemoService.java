package com.example.simplecrm.service;

public class DemoService {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        // if (b == 0)
        // return 1;
        return a / b;
    }

    public boolean isEven(int a) {
        // return true;
        return a % 2 == 0;
    }
}
