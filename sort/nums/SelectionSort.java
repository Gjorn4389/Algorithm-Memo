package sort.nums;

public class SelectionSort implements Sort {
    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[k]) {
                    k = j;
                }
            }
            swap(nums, k, i);
        }
    }
}
