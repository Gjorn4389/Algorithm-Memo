package sort.heap;

public class HeapSort {

    public static void main(String[] args) {
        HeapSort test = new HeapSort();
        int[] nums = {2, 4, 1, 5, 3};
        int[] ans = test.heapSort(nums);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public int[] heapSort(int[] arr) {
        if (arr.length < 2) return arr;
        heapify(arr, arr.length - 1); // 构建大顶堆
        for (int i = arr.length - 1; i > 0; i--) { // i > 0即可，无需写成i >= 0，当n - 1个元素排序时，最后一个元素也已排序
            swap(arr, 0, i); // 交换堆顶和当前未排序部分最后一个元素
            // 此时除当前堆顶元素外都是保持堆序的，只需要对该堆顶调用一次下滤操作
            siftDown(arr, 0, i - 1); // i - 1是未排序部分最后一个元素下标，确保下滤不会超过此范围
        }
        return arr;
    }

    private void heapify(int[] arr, int endIdx) {
        // (endIdx - 1) / 2伪最后一个非叶子节点下标
        for (int hole = (endIdx - 1) / 2; hole >= 0; hole--) {
            siftDown(arr, hole, endIdx);
        }
    }

    private void siftDown(int[] arr, int hole, int endIdx) {
        int target = arr[hole]; // target是要下滤的节点
        int child = hole * 2 + 1;
        while (child <= endIdx) {
            // 满足第一个条件child < endIdx表示hole有右孩子，不满足则hole无右孩子，跳过
            // 第二个条件arr[child + 1] > arr[child]只在第一个条件成立前提下进行判断（因此不必担心arr[child + 1]越界），
            // 若满足，表示hole有右孩子且右孩子更大，令child为右孩子下标。
            // 因此此if过后使得child是hole的孩子中较大的那个
            if (child < endIdx && arr[child + 1] > arr[child]) {
                child++;
            }
            // 若child大于target，则child上移到当前hole，hole下滤到child位置
            if (arr[child] > target) {
                arr[hole] = arr[child];
                hole = child;
                child = hole * 2 + 1; // 当然也可以写成child = child * 2 + 1
            } else break; // 若无需交换hole与child，说明hole已经满足堆序(无需/无法再下滤)，退出while
        }
        arr[hole] = target; // 将target填入hole中
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
