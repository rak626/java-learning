package interview;

import DSA.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1 {
    /**
     * arr[] = [2,2,5,5] -> 9
     *
     * flg = false;
     * int startidx, endindx,
     * for i = 0 -> n -1
     *      for j = i, n-1
     *          sum += a[j]
     *          if sum > target
     *              break;
     *           if sum == target
     *              startind = i
     *              endind = j
     *              flg = true;
     *              break;
     *      if flg == true
     *          break;
     *
     *  t.c -> O(N^2)
     *  s.c -> O(1)
     *
     *  i -> 0th,
     *  j -> 0th,
     *  j increase till sum > target
     *  i -> i + 1,
     *
     */

    private static List<Integer> findSubarray(int[] arr, int target) {
        int left = 0, right = 0, n = arr.length;
        int sum = 0;
        while (right < n) {
            sum += arr[right];
            if (sum > target) {
                while (sum > target && left < right) {
                    sum -= arr[left];
                    left++;
                }
            }
            if (sum == target) {
                break;
            }
            right++;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 20, 5, 10, 5};
        int k = 35;
        List<Integer> subarray = Solution1.findSubarray(arr, k);
//        System.out.println(subarray);
        Solution1.Sample();
    }

    private static int maximumWidthOfBinaryTree(TreeNode node) {
        return 0;
    }

    private static void Sample() {
        List<List<String>> mainList = Arrays.asList(
                Arrays.asList("abc", "ab"),
                Arrays.asList("jab", "jj", "hh"),
                Arrays.asList("amo", "mo", "ef")
        );

        List<String> result = mainList.stream().flatMap(e -> e.stream().filter(f -> f.startsWith("a"))).collect(Collectors.toList());
        System.out.println(result);
    }

}
