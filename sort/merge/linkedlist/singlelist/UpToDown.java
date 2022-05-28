package sort.merge.linkedlist.singlelist;

import base.ListNode;

/**
 * 链表排序 https://leetcode.cn/problems/7WHec2/
 * <p/>
 * 示例 {-1,5,3,4,0}
 * <p/>
 * 自顶向下
 * <p/>
 * 时间: O(nlogn), 空间: O(logn)
 */
public class UpToDown {
    /**
     * 自顶向下实现
     */
    public ListNode sortDown(ListNode head) {
        // 边界条件判断
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针找中点
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode t = slow.next;
        // 需要将前后链表截断
        slow.next = null;
        ListNode l1 = sortDown(head);
        ListNode l2 = sortDown(t);
        // 合并两个有序链表
        return merge(l1, l2);
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
