package thread.printorder;

public class WaitNotifyImpl {
    // 打印次数
    private static int times = 10;
    // 当前状态值: 保证三个线程交替打印
    private int state;
    private static final Object LOCK = new Object();

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times; i++) {
            synchronized (LOCK) {
                while (state % 3 != targetNum) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                state++;
                System.out.print(name + " ");
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyImpl po = new WaitNotifyImpl();

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
