package com.youxu.hotfix;

public class Test {
    private String name = "ni hao";
    public void print(){
//        name = "123";
//        System.out.println(name);
        for (int i = 1; i < 10; i++){
            for (int j = 1; j < 10; j++){
                System.out.print(i*j + "\t");
            }
            System.out.println();
        }
    }
}
