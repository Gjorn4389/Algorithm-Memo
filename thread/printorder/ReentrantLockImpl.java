package thread.printorder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImpl {
    // 打印次数
    private static int times = 10;
    // 当前状态值: 保证三个线程交替打印
    private int state;
    private Lock lock = new ReentrantLock();

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name + " ");
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockImpl po = new ReentrantLockImpl();

        new Thread(() -> {
            po.printLetter("A", 0);
        }, "A").start();

        new Thread(() -> {
            po.printLetter("B", 1);
        }, "B").start();

        new Thread(() -> {
            po.printLetter("C", 2);
        }, "C").start();

    }
}
