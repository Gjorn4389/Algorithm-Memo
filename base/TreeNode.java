package base;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode ConstructTree(int[] nums) {
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode root = new TreeNode(nums[0]);
        q.addLast(root);
        int idx = 1, n = nums.length;
        while (idx < n) {
            TreeNode cur = q.pollFirst();
            if (idx < n) {
                if (nums[idx] != -1) {
                    cur.left = new TreeNode(nums[idx]);
                    q.addLast(cur.left);
                }
                idx++;
            }
            if (idx < n) {
                if (nums[idx] != -1) {
                    cur.right = new TreeNode(nums[idx]);
                    q.addLast(cur.right);
                }
                idx++;
            }
        }
        return root;
    }
}
