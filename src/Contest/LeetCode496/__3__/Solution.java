package Contest.LeetCode496.__3__;

class Solution {
    static class State {
        int cnt;
        long cost;
        State(int cnt, long cost) {
            this.cnt = cnt;
            this.cost = cost;
        }
    }

    private State better(State a, State b) {
        if (a.cnt != b.cnt) return a.cnt > b.cnt ? a : b;
        return a.cost <= b.cost ? a : b;
    }

    public long minIncrease(int[] nums) {
        int n = nums.length;
        long[] cost = new long[n];
        for (int i = 1; i < n - 1; i++) {
            long need = Math.max(nums[i - 1], nums[i + 1]) + 1L;
            cost[i] = Math.max(0L, need - nums[i]);
        }
        State skip = new State(0, 0L);
        State take = new State(Integer.MIN_VALUE / 4, Long.MAX_VALUE / 4);

        for (int i = 1; i < n - 1; i++) {
            State nskip = better(skip, take);
            State ntake = new State(skip.cnt + 1, skip.cost + cost[i]);
            skip = nskip;
            take = ntake;
        }

        return better(skip, take).cost;
    }
}