package sort.merge.linkedlist.multylists;

import base.ListNode;

import java.util.PriorityQueue;

/**
 * 合并多个有序链表 https://leetcode.cn/problems/vvXgSW/
 * <p/>
 * 示例 {{1, 4, 5}, {1, 3, 4}, {2, 6}}
 * <p/>
 * 分治
 */
public class DivideConquer {
    /**
     * 分治合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        if (begin > end) {
            return null;
        }
        int mid = begin + (end - begin) / 2;
        ListNode l1 = divide(lists, begin, mid);
        ListNode l2 = divide(lists, mid + 1, end);
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
