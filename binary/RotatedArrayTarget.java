package binary;

/**
 * 寻找旋转数组中目标值下标
 * <p>
 * 可能存在重复数字
 */
public class RotatedArrayTarget {

    public static void main(String[] args) {
        RotatedArrayTarget solution = new RotatedArrayTarget();
        System.out.println(solution.search(new int[]{1, 3}, 1));
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                ++left;
                --right;
            } else if (nums[mid] >= nums[left]) {
                if (target <= nums[mid] && target >= nums[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= nums[right] && target >= nums[mid]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left] == target? left: -1;
    }
}
