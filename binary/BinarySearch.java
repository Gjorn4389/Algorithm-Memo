package binary;

/**
 * 三种二分查找写法
 * <p>
 * 需要注意 2、3种写法，mid计算 和 l、r取值的对应关系
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        BinarySearch bs = new BinarySearch();
        System.out.println(bs.binarySearch1(nums, 0));
        System.out.println(bs.binarySearch2(nums, 0));
        System.out.println(bs.binarySearch3(nums, 0));
    }

    public int binarySearch1(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int binarySearch2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l] == target ? l : -1;
    }

    public int binarySearch3(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return nums[l] == target ? l : -1;
    }
}
