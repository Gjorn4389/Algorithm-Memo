package thread.peterson;

public class Peterson {

    private StringBuilder sb = new StringBuilder();

    public final static int A = 1, B = 2, C = 3, LENGTH = 100;
    private volatile int a = 0, b = 0, c = 0;
    private volatile int turn = A;
    private volatile int state = A;

    public void threadA() {
        int idx = 0;
        while (idx < LENGTH) {
            a = 1;
            turn = B;
            while (b > 0  && turn == B);

            if (state == A) {
                sb.append("A");
                idx++;
                state = B;
            }

            a = 0;
        }
    }

    void threadB() {
        int idx = 0;
        while (idx < LENGTH) {
            b = 1;
            turn = C;
            while (a > 0  && turn == A);

            if (state == B) {
                sb.append("B");
                idx++;
                state = C;
            }

            b = 0;
        }
    }

    void threadC() {
        int idx = 0;
        while (idx < LENGTH) {
            c = 1;
            turn = A;
            while (a > 0  && turn == A);

            if (state == C) {
                sb.append("C");
                idx++;
                state = A;
            }

            c = 0;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Peterson peterson = new Peterson();
        Thread threadA = new Thread(() -> peterson.threadA());
        Thread threadB = new Thread(() -> peterson.threadB());
        Thread threadC = new Thread(() -> peterson.threadC());

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println(peterson.sb);
        System.out.println(peterson.sb.length());
    }

}
