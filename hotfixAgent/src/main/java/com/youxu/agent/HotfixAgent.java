package com.youxu.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class HotfixAgent {
    private static final String HOTFIX_CLASS = "Test";
    private static final String HOTFIX_CLASS_PATH = "E:\\study\\hot-fix\\hotfixTest\\target\\classes\\com\\youxu\\hotfix\\Test.class";
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("agent启动成功，开始热更新！");
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class c : allLoadedClasses){
            if (c.getName().endsWith(HOTFIX_CLASS)){
                File file = new File(HOTFIX_CLASS_PATH);
                try {
                    byte[] bytes = fileToBytes(file);
                    System.out.printf("当前文件的大小为：%d字节\n",bytes.length);
                    ClassDefinition classDefinition = new ClassDefinition(c, bytes);
                    inst.redefineClasses(classDefinition);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnmodifiableClassException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("热更新成功！");
            }
        }
    }

    private static byte[] fileToBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        int read;
        while (true){
            read = fis.read(bytes);
            if(read == -1){
                break;
            }
        }
        fis.close();
        return bytes;
    }
}
