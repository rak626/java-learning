package interview;

import java.util.*;

class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> freq1 = new HashMap<>();

        // Count frequencies in nums1
        for (int x : nums1) {
            freq1.put(x, freq1.getOrDefault(x, 0) + 1);
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        // Count frequencies in nums2 and update freq
        for (int x : nums2) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        long diff = 0;
        long ans = 0;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int key = entry.getKey();
            int totalFreq = entry.getValue();

            if (totalFreq % 2 != 0) return -1;

            // Each array must eventually have totalFreq / 2 occurrences
            int targetFreq = totalFreq / 2;
            int currentFreqIn1 = freq1.getOrDefault(key, 0);

            if (currentFreqIn1 == targetFreq) continue;

            diff += (currentFreqIn1 - targetFreq);
            ans += Math.abs(currentFreqIn1 - targetFreq);
        }

        // In a valid swap scenario, the net difference should be zero
        if (diff == 0) {
            return (int) (ans / 2);
        }

        return -1;
    }
}