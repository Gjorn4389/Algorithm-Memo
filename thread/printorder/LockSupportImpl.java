package thread.printorder;

import java.util.concurrent.locks.LockSupport;

public class LockSupportImpl {
    // 打印次数
    private static int times = 10;
    private static Thread threadA, threadB, threadC;

    public static void main(String[] args) {
        threadA = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                System.out.print("A ");
                LockSupport.unpark(threadB);
                LockSupport.park();
            }
        });
        threadB = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                System.out.print("B ");
                LockSupport.unpark(threadC);
            }
        });
        threadC = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                System.out.print("C ");
                LockSupport.unpark(threadA);
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
