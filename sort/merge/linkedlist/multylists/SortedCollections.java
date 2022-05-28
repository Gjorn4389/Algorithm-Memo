package sort.merge.linkedlist.multylists;

import base.ListNode;

import java.util.PriorityQueue;

/**
 * 合并多个有序链表 https://leetcode.cn/problems/vvXgSW/
 * <p/>
 * 示例 {{1, 4, 5}, {1, 3, 4}, {2, 6}}
 * <p/>
 * 优先队列
 */
public class SortedCollections {
    /**
     * 优先队列 作弊
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 优先队列
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode l : lists) {
            if (l != null) {
                pq.add(l);
            }
        }
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            p.next = cur;
            if (cur.next != null) {
                pq.add(cur.next);
            }
            p = p.next;
        }
        return dummy.next;
    }
}
