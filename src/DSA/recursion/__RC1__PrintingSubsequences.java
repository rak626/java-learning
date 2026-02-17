package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

public class __RC1__PrintingSubsequences {

    public static void main(String[] args) {
        __RC1__PrintingSubsequences cl = new __RC1__PrintingSubsequences();
        cl.generateAllSubsequences(new int[]{1, 2, 3});
    }

    public void generateAllSubsequences(int[] arr) {
        List<Integer> subArray = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        solve(0, arr, subArray, result);

        System.out.println(result);
    }

    private void solve(int cur, int[] arr, List<Integer> subArray, List<List<Integer>> result) {

        // Base Case
        if (cur == arr.length) {
            // IMPORTANT: Add copy, not original reference
            result.add(new ArrayList<>(subArray));
            return;
        }

        // TAKE
        subArray.add(arr[cur]);
        solve(cur + 1, arr, subArray, result);

        // BACKTRACK
        subArray.removeLast();

        // NOT TAKE
        solve(cur + 1, arr, subArray, result);
    }
}
