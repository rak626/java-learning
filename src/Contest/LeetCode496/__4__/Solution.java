package Contest.LeetCode496.__4__;

import java.util.*;

class Solution {
    private static final long INF = (long) 4e18;

    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return 0;
        if (k > n / 2) return -1;
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            int left = nums[(i - 1 + n) % n];
            int right = nums[(i + 1) % n];
            int need = Math.max(left, right) + 1;
            cost[i] = Math.max(0, need - nums[i]);
        }
        long ans = solve(cost, 1, n - 1, k);
        if (k >= 1) {
            long t0 = solve(cost, 2, n - 2, k - 1);
            if (t0 < INF) ans = Math.min(ans, t0 + cost[0]);
        }
        return ans >= INF ? -1 : (int) ans;
    }

    private long solve(int[] cost, int l, int r, int picks) {
        if (picks < 0) return INF;
        if (l > r) return picks == 0 ? 0 : INF;
        int len = r - l + 1;
        if (picks > (len + 1) / 2) return INF;

        long[] prev2 = new long[picks + 1];
        long[] prev1 = new long[picks + 1];
        Arrays.fill(prev2, INF);
        Arrays.fill(prev1, INF);
        prev2[0] = 0;
        prev1[0] = 0;

        for (int i = l; i <= r; i++) {
            long[] cur = new long[picks + 1];
            Arrays.fill(cur, INF);
            cur[0] = 0;
            for (int j = 1; j <= picks; j++) {
                long skip = prev1[j];
                long take = prev2[j - 1] == INF ? INF : prev2[j - 1] + cost[i];
                cur[j] = Math.min(skip, take);
            }
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1[picks];
    }
}
