package sort.nums;

public interface Sort {
    void sort(int[] nums);

    default void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }

    public static void main(String[] args) {
        Sort test = new BubbleSort();
        int[] nums = {8, 2, 6, 9, 7, 4, 3, 1, 5};
        test.sort(nums);
        for (int x : nums) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
