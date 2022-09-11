# Algorithm-Memo
Copy for remember

# Base
一些需要的数据结构

# 查找
1. 二分法查找: binary.BinarySearch
2. 寻找旋转数组中最小值: binary.RotatedArrayMinimum
3. 寻找旋转数组中目标值: binary.RotatedArrayTarget
4. kmp: string.KMP

# 排序
1. 归并排序
   1. 链表排序
      + 自顶向下: sort.merge.linkedlist.singlelist.UpToDown
      + 自底向上: sort.merge.linkedlist.singlelist.DownToUp
   2. 合并多个有序链表
      + 分治: sort.merge.linkedlist.multylists.DivideConquer
      + 优先队列: sort.merge.linkedlist.multylists.SortedCollections
2. 堆排序: sort.heap.HeapSort

# 图论
1. 并查集: graph.UnionFind
2. 最短路径
   1. Dijkstra: graph.shortestpath.Dijkstra

# 页面置换算法
1. 最近最少使用(LRU): replace.LRUCache
2. 最不经常使用(LFU): replace.LFUCache

# 多线程
1. 按序打印
   + 可重入锁: thread.printorder.ReentrantLockImpl
   + waitNotify: thread.printorder.WaitNotifyImpl
   + Condition: thread.printorder.ConditionImpl
   + Semaphore: thread.printorder.SemaphoreImpl
   + LockSupport: thread.printorder.LockSupportImpl

# 位操作
1. 操作一个数的二进制位: bit.BitOps

# 设计模式
1. 单例模式: design.Singleton

# 唯一ID
1. 雪花算法: snowflake.IdWorker