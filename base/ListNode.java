package base;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode ConstructList(int[] nums) {
        if (nums.length == 0) {
            return new ListNode();
        }
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        for (int n : nums) {
            p.next = new ListNode(n);
            p = p.next;
        }
        return dummy.next;
    }
}
