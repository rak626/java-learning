package Contest.LeetCode496.__2__;

import java.util.List;

import java.util.*;

class Solution {
    public List<Integer> findGoodIntegers(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int limit = (int) Math.cbrt(n) + 1;
        for (int a = 1; a <= limit; a++) {
            int a3 = a * a * a;
            if (a3 > n) break;
            for (int b = a; b <= limit; b++) {
                int sum = a3 + b * b * b;
                if (sum > n) break;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() >= 2) {
                result.add(e.getKey());
            }
        }
        Collections.sort(result);
        return result;
    }
}