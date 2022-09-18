package sort.nums;

public class BubbleSort implements Sort {
    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            boolean done = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    done = false;
                }
            }
            // 没有发生交换，说明已经顺序
            if (done) {
                break;
            }
        }
    }
}
