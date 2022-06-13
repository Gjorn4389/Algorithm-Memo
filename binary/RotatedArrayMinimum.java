package binary;

/**
 * 寻找旋转数组中最小值
 * <p>
 * 可能存在重复数字
 */
public class RotatedArrayMinimum {

    public static void main(String[] args) {
        RotatedArrayMinimum solution = new RotatedArrayMinimum();
        System.out.println(solution.findMin(new int[]{3, 4, 5, 1, 2}));
    }

    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r--;
            }
        }
        return nums[l];
    }
}
