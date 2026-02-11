package DSA.stack;

import java.util.*;

/**
 * Problem: Online Stock Span
 * <ul>
 * <li>Link: <a href="https://leetcode.com/problems/online-stock-span/description/">Online Stock Span</a></li>
 * <li>Difficulty: Medium</li>
 * <li>Tags: stack</li>
 * </ul>
 * <p>
 * Approach:
 * -
 * <p>
 * Time: O()
 * Space: O()
 */
public class __SQ15__OnlineStockSpan {
    static class StockSpanner {
        private final Deque<int[]> st;

        public StockSpanner() {
            st = new ArrayDeque<>();
        }

        public int next(int price) {
            int span = 1;
            // we are storing in span, a span will be like consecutive number, where left is smaller & right is bigger.
            // if my price is bigger that top, that means span of previous stock will be accumulated.
            while(!st.isEmpty() && st.peek()[0] <= price){
                span += st.pop()[1];
            }
            st.push(new int[]{price, span});
            return span;
        }


    }
}
