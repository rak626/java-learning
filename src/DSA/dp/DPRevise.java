package DSA.dp;

import java.util.Arrays;

public class DPRevise {

    // frog jump
    // memo solution
    int minCost(int[] height) {
        int[] dp = new int[height.length];
        Arrays.fill(dp, -1);
        return solve(height.length - 1, height, dp);
    }

    int solve(int ind, int[] height, int[] dp) {
        if (ind == 0) return 0;
        if (dp[ind] != -1) return ind;

        int cost1 = solve(ind - 1, height, dp) + Math.abs(height[ind] - height[ind - 1]);
        int cost2 = Integer.MAX_VALUE;
        if (ind > 1) cost2 = solve(ind - 2, height, dp) + Math.abs(height[ind] - height[ind - 2]);

        return dp[ind] = Math.min(cost1, cost2);
    }

    // iterative soltuion

    int minCost__iterative(int[] height) {
        int[] dp = new int[height.length];
        dp[0] = 0; // no need actually

        for (int ind = 1; ind < height.length; ind++) {

            int cost1 = dp[ind - 1] + Math.abs(height[ind - 1] - height[ind]);
            int cost2 = Integer.MAX_VALUE;
            if (ind > 1) cost2 = dp[ind - 2] + Math.abs(height[ind] - height[ind - 2]);
            dp[ind] = Math.min(cost1, cost2);
        }

        return dp[height.length - 1];
    }

    // mincost space optimized
    int minCost__space_optimized(int[] height) {
        int prev = 0, pprev = 0;
        for (int ind = 1; ind < height.length; ind++) {

            int cost1 = prev + Math.abs(height[ind - 1] - height[ind]);
            int cost2 = Integer.MAX_VALUE;
            if (ind > 1) cost2 = pprev + Math.abs(height[ind] - height[ind - 2]);

            pprev = prev;
            prev = Math.min(cost1, cost2);
        }
        return prev;
    }


    // Min cost with K jumps
    int minCost__with_K_jumps(int[] height, int k) {
        int[] dp = new int[height.length];
        Arrays.fill(dp, -1);
        return solve_k_jumps(height.length - 1, k, height, dp);
    }

    int solve_k_jumps(int ind, int k, int[] height, int[] dp) {
        if (ind == 0) return 0;
        if (dp[ind] != -1) return dp[ind];
        int cost = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (ind - j > 0) {
                int curCost = solve_k_jumps(ind - j, k, height, dp) + Math.abs(height[ind - j] - height[ind]);
                cost = Math.min(cost, curCost);
            }
        }
        return dp[ind] = cost;
    }

    // minCost__with_K_jumps iterative
    int minCost__with_K_jumps_iterative(int[] height, int k) {
        int[] dp = new int[height.length];

        for (int ind = 1; ind < height.length; ind++) {
            int cost = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (ind - j > 0) {
                    int curCost = dp[ind - j] + Math.abs(height[ind - j] - height[ind]);
                    cost = Math.min(cost, curCost);
                }
            }
            dp[ind] = cost;
        }
        return dp[height.length - 1];
    }

    // minCost__with_K_jumps space optimized
    int minCost__with_K_jumps_space_optimized(int[] height, int k) {
        int[] dp = new int[k];

        for (int ind = 1; ind < height.length; ind++) {
            int cost = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (ind - j > 0) {
                    int curCost = dp[(ind - j) % k] + Math.abs(height[ind - j] - height[ind]);
                    cost = Math.min(cost, curCost);
                }
            }
            dp[ind % k] = cost;
        }
        return dp[(height.length - 1) % k];
    }


    // House Robber

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return solved(nums.length - 1, nums, dp);
    }

    int solved(int ind, int[] nums, int[] dp) {
        if (ind < 0) return 0;
        if (dp[ind] != -1) return dp[ind];

        int rob = nums[ind] + solved(ind - 2, nums, dp);
        int skip = solved(ind - 1, nums, dp);

        return dp[ind] = Math.max(rob, skip);
    }

    public int rob_iterative(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int ind = 2; ind < nums.length; ind++) {
            int rob = nums[ind] + dp[ind - 2];
            int skip = dp[ind - 1];
            dp[ind] = Math.max(rob, skip);
        }
        return dp[nums.length - 1];
    }

    public int rob_space_optimized(int[] nums) {
        int prev = Math.max(nums[0], nums[1]);
        int pprev = nums[0];

        for (int ind = 2; ind < nums.length; ind++) {
            int rob = nums[ind] + pprev;
            int skip = nums[ind - 1];
            pprev = prev;
            prev = Math.max(rob, skip);
        }
        return prev;
    }

    // house robber 2
    public int rob2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp, -1);
        // starts from n-1, ends at 1, because its circular so 0 should not be stolen
        int case1 = rob2_memo(n - 1, 1, nums, dp);

        Arrays.fill(dp, -1);
        // starts from n-2, ends at 0, n-1 will be not stolen.
        int case2 = rob2_memo(n - 2, 0, nums, dp);

        return Math.max(case1, case2);
    }

    int rob2_memo(int ind, int end, int[] nums, int[] dp) {
        if (ind < end) return 0;
        if (dp[ind] != -1) return dp[ind];
        int n = nums.length;

        int rob = nums[ind] + rob2_memo(ind - 2, end, nums, dp);
        int skip = rob2_memo(ind - 1, end, nums, dp);

        return dp[ind] = Math.max(rob, skip);
    }

     int rob2_iterative(int start, int end, int[] nums) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start]; // base case

        for (int i = start + 1; i <= end; i++) {
            int rob = nums[i];
            if (i - 2 >= start) {
                rob += dp[i - 2];
            }
            int skip = dp[i - 1];
            dp[i] = Math.max(rob, skip);
        }
        return dp[end];
    }

}
