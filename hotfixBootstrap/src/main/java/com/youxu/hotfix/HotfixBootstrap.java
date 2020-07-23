package com.youxu.hotfix;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HotfixBootstrap {
    public static final String AGENT_PATH = "E:\\study\\hot-fix\\hotfixAgent\\target\\agent-1.0.0.jar";

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        Optional<VirtualMachineDescriptor> opt = list.stream().filter(vmd -> vmd.displayName().endsWith("Main")).findFirst();
        opt.ifPresent(vmd -> {
            try {
                //当前JVM进程与目标JVM进程建立连接
                VirtualMachine vm = VirtualMachine.attach(vmd.id());
                //加载agent
                vm.loadAgent(AGENT_PATH);
                System.out.println("agent加载成功！");
                vm.detach();
            } catch (AttachNotSupportedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AgentLoadException e) {
                e.printStackTrace();
            } catch (AgentInitializationException e) {
                e.printStackTrace();
            }
        });
    }
}
