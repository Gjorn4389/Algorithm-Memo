package snowflake;

public class IdWorker {

    // 10位的工作机器id
    // 工作id (MAC地址)
    private long workerId;
    // 数据id (线程Id)
    private long datacenterId;

    // 12位序列号
    private long sequence;

    public IdWorker(long workerId, long datacenterId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }


    // 初始时间戳
    private long twepoch = 1288834974657L;
    // 工作id长度
    private long workerIdBits = 5L;
    // 数据id长度
    private long datacenterIdBits = 5L;
    // 最大值
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << workerIdBits);

    // 序列号id长度
    private long sequenceBits = 12L;
    // 序列号最大值
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    // 工作id需要左移的位数=12
    private long workerIdShift = sequenceBits;
    // 数据id需要左移的位数=12+5=17
    private long datacenterIdShift = sequenceBits + workerIdBits;
    // 时间戳id需要左移的位数=12+5+5=22
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    // 上次时间戳，初始值为负数
    private long lastTimestamp = -1L;

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    // 获取系统时间戳
    private long getTimestamp(){
        return System.currentTimeMillis();
    }

    // 下一个ID生成算法
    public synchronized long nextId() {
        long timestamp = getTimestamp();
        // 获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一
        // 否则序列号赋值为0，从0开始
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = nextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        // 刷新上次时间戳
        lastTimestamp = timestamp;

        /**
         * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
         * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
         * (workerId << workerIdShift) 表示将工作id左移相应位数
         */
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    // 循环等待到下一个毫秒
    private long nextMillis(long lastTimestamp) {
        long timestamp = getTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getTimestamp();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println(idWorker.nextId());
        }
    }

}
