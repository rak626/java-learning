package Contest.LeetCode496.__1__;

class Solution {
    public int mirrorFrequency(String s) {
        int[] freq = new int[36];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                freq[c - 'a']++;
            } else {
                freq[26 + (c - '0')]++;
            }
        }
        boolean[] visited = new boolean[36];
        int result = 0;
        for (char c : s.toCharArray()) {
            int idx = findI(c);
            if (visited[idx]) continue;
            char mirrorChar = findM(c);
            int mirrorIdx = findI(mirrorChar);
            int f1 = freq[idx];
            int f2 = freq[mirrorIdx];
            result += Math.abs(f1 - f2);
            visited[idx] = true;
            visited[mirrorIdx] = true;
        }
        return result;
    }

    private int findI(char c) {
        if (Character.isLetter(c)) {
            return c - 'a';
        } else {
            return 26 + (c - '0');
        }
    }

    private char findM(char c) {
        if (Character.isLetter(c)) {
            return (char) ('a' + 'z' - c);
        } else {
            return (char) ('0' + '9' - c);
        }
    }
}
