package thread.printorder;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreImpl {
    // 打印次数
    private static int times = 10;
    // 首先打印a，许可数=1
    private static Semaphore a = new Semaphore(1);
    private static Semaphore b = new Semaphore(0);
    private static Semaphore c = new Semaphore(0);

    private void printLetter(String name, Semaphore cur, Semaphore next) {
        for (int i = 0; i < times; i++) {
            try {
                cur.acquire();
                System.out.print(name + " ");
                next.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        SemaphoreImpl po = new SemaphoreImpl();

        new Thread(() -> {
            po.printLetter("A", a, b);
        }).start();

        new Thread(() -> {
            po.printLetter("B", b, c);
        }).start();

        new Thread(() -> {
            po.printLetter("C", c, a);
        }).start();
    }
}
