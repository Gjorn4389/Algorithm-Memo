package sort.nums;

public class InsertionSort implements Sort {
    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            int pivot = nums[i];
            int j = n - 1;
            while (j > i && nums[j] >= pivot) {
                j--;
            }
            for (int k = i; k < j; k++) {
                nums[k] = nums[k + 1];
            }
            nums[j] = pivot;
        }
    }
}
