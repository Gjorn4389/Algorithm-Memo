package thread.printorder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionImpl {
    // 打印次数
    private static int times = 10;
    // 当前状态值: 保证三个线程交替打印
    private int state;
    private static Lock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static Condition c3 = lock.newCondition();

    private void printLetter(String name, int targetNum, Condition cur, Condition next) {
        for (int i = 0; i < times; ) {
            lock.lock();
            try {
                while (state % 3 != targetNum) {
                    cur.await();
                }
                state++;
                i++;
                System.out.print(name + " ");
                next.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ConditionImpl po = new ConditionImpl();

        new Thread(() -> {
            po.printLetter("A", 0, c1, c2);
        }, "A").start();

        new Thread(() -> {
            po.printLetter("B", 1, c2, c3);
        }, "B").start();

        new Thread(() -> {
            po.printLetter("C", 2, c3, c1);
        }, "C").start();
    }
}
