package com.youxu.hotfix;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        while (true){
            test.print();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
