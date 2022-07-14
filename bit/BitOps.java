package bit;

public class BitOps {
    /**
     * 获取 a 的第 b 位
     */
    private int getBit(int a, int b) {
        return (a >> b) & 1;
    }

    /**
     * 将 a 的第 b 位设置为 0
     */
    private int unsetBit(int a, int b) {
        return a & ~(1 << b);
    }

    /**
     * 将 a 的第 b 位设置为 1
     */
    private int setBit(int a, int b) {
        return a | (1 << b);
    }

    /**
     * 将 a 的第 b 位取反
     */
    private int flapBit(int a, int b) {
        return a ^ (1 << b);
    }
}
