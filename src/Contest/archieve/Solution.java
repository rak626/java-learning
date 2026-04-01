package Contest.archieve;


import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int ans = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            boolean ok = true;
            for (int i = 0; i < n; i += k) {
                if (!isRotation(nums, sorted, i, k)) {
                    ok = false;
                    break;
                }
            }
            if (ok) ans += k;
        }
        return ans;
    }

    private boolean isRotation(int[] nums, int[] sorted, int start, int k) {
        int[] pattern = new int[k];
        for (int i = 0; i < k; i++) {
            pattern[i] = sorted[start + i];
        }
        int[] text = new int[2 * k];
        for (int i = 0; i < k; i++) {
            text[i] = nums[start + i];
            text[i + k] = nums[start + i];
        }
        return kmpSearch(text, pattern);
    }

    private boolean kmpSearch(int[] text, int[] pattern) {
        int[] lps = buildLPS(pattern);
        int i = 0, j = 0;
        while (i < text.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
                if (j == pattern.length) return true;
            } else {
                if (j > 0) j = lps[j - 1];
                else i++;
            }
        }
        return false;
    }

    private int[] buildLPS(int[] pattern) {
        int n = pattern.length;
        int[] lps = new int[n];
        for (int i = 1, j = 0; i < n; ) {
            if (pattern[i] == pattern[j]) {
                lps[i++] = ++j;
            } else {
                if (j > 0) j = lps[j - 1];
                else lps[i++] = 0;
            }
        }
        return lps;
    }
}

//class Solution {
//    int[] parent, rank, parity;
//
//    public int numberOfEdgesAdded(int n, int[][] edges) {
//        parent = new int[n];
//        rank = new int[n];
//        parity = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            parent[i] = i;
//            parity[i] = 0;
//        }
//
//        int count = 0;
//        for (int[] e : edges) {
//            int u = e[0], v = e[1], w = e[2];
//            if (union(u, v, w)) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    private int find(int x) {
//        if (parent[x] != x) {
//            int p = parent[x];
//            parent[x] = find(parent[x]);
//            parity[x] ^= parity[p];
//        }
//        return parent[x];
//    }
//
//    private boolean union(int u, int v, int w) {
//        int ru = find(u);
//        int rv = find(v);
//
//        if (ru == rv) {
//            return (parity[u] ^ parity[v] ^ w) == 0;
//        }
//        if (rank[ru] < rank[rv]) {
//            parent[ru] = rv;
//            parity[ru] = parity[u] ^ parity[v] ^ w;
//        } else {
//            parent[rv] = ru;
//            parity[rv] = parity[u] ^ parity[v] ^ w;
//            if (rank[ru] == rank[rv]) rank[ru]++;
//        }
//        return true;
//    }
//}