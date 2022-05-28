package sort.merge.linkedlist.singlelist;

import base.ListNode;

/**
 * 链表排序 https://leetcode.cn/problems/7WHec2/
 * <p/>
 * 示例 {-1,5,3,4,0}
 * <p/>
 * 自顶向下
 * <p/>
 * 时间: O(nlogn), 空间: O(1)
 */
public class DownToUp {
    /**
     * 自底向上实现
     */
    public ListNode sortUp(ListNode head) {
        // 边界条件判断
        if (head == null || head.next == null) {
            return head;
        }
        // 计算链表长度
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        // 哑节点 连上 head
        ListNode dummy = new ListNode();
        dummy.next = head;
        // l := 当前轮次的`子链表长度`
        // l < len := 保证一定有两段子链表
        for (int l = 1; l < len; l *= 2) {
            ListNode pre = dummy, cur = dummy.next;
            while (cur != null) {
                ListNode l1 = cur;
                for (int i = 1; i < l && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode l2 = cur.next;
                // 分割两段 子链表
                cur.next = null;
                cur = l2;
                for (int i = 1; i < l && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 此时cur可能为null
                ListNode nextTurn = null;
                if (cur != null) {
                    nextTurn = cur.next;
                    cur.next = null;
                }
                // 合并两段子链表
                pre.next = merge(l1, l2);
                // pre到当前链表的最后一个节点
                while (pre.next != null) {
                    pre = pre.next;
                }
                // cur 变成下一轮的起点
                cur = nextTurn;
            }
        }
        return dummy.next;
    }

    /**
     * 将两个有序链表进行排序合并
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        // l1 或 l2 还有node没有连上
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return dummy.next;
    }
}
