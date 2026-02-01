package DSA.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <h2>Expression Conversion Using Stack — Full Mental Model Guide</h2>
 *
 * <p>
 * Expressions can be represented in three main notations:
 * </p>
 *
 * <ul>
 *   <li><b>Infix</b>  → A + B (operator between operands)</li>
 *   <li><b>Postfix</b> → AB+  (operator after operands)</li>
 *   <li><b>Prefix</b>  → +AB  (operator before operands)</li>
 * </ul>
 *
 * <p>
 * Stack is used because operators must be applied in reverse encounter order
 * relative to operands and precedence rules.
 * </p>
 *
 * <hr>
 *
 * <h3>Universal Stack Pattern to Remember</h3>
 *
 * <pre>
 * If scanning LEFT → RIGHT:
 *     pop second operand first (B)
 *     pop first operand second (A)
 *
 * If scanning RIGHT → LEFT:
 *     pop first operand first (A)
 *     pop second operand second (B)
 *
 * Build expression based on target format.
 * </pre>
 *
 * <hr>
 *
 * <h3>Operator Precedence Rule</h3>
 *
 * <pre>
 * ^   → 3 (right associative)
 * * / → 2
 * + - → 1
 * </pre>
 *
 * <p>
 * Right associativity of ^ means equal precedence should NOT pop existing ^
 * </p>
 */

public class __SQ3__InfixPrefixPostfix {
    static int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    /**
     * <h3>Infix → Postfix Conversion</h3>
     *
     * <p><b>Goal:</b> Remove parentheses and reorder operators so evaluation becomes linear.</p>
     *
     * <h4>Core Intuition</h4>
     *
     * <ul>
     *   <li>Operands go directly to output</li>
     *   <li>Operators wait on stack until safe to emit</li>
     *   <li>Stack maintains precedence ordering</li>
     * </ul>
     *
     * <h4>Algorithm Thought Process</h4>
     *
     * <pre>
     * operand  → output immediately
     * '('      → push (acts as boundary)
     * ')'      → pop until '(' encountered
     * operator → pop while stack has higher precedence operator
     * </pre>
     *
     * <h4>Critical Rule — Right Associativity</h4>
     *
     * <pre>
     * For '^':
     * do NOT pop when precedence is equal
     * </pre>
     *
     * <h4>Why Stack Works</h4>
     *
     * <p>
     * Stack delays operators until their operands are fully seen.
     * Higher precedence operators must be applied earlier → popped first.
     * </p>
     *
     * <h4>Complexity</h4>
     * O(N) time, O(N) space
     */
    static String infixToPostfix(String s) {
        StringBuilder out = new StringBuilder();
        Deque<Character> st = new ArrayDeque<>();

        for (char c : s.toCharArray()) {

            if (Character.isLetterOrDigit(c)) out.append(c);

            else if (c == '(') st.push(c);

            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') out.append(st.pop());
                st.pop();
            } else {
                while (!st.isEmpty() && st.peek() != '(' && (precedence(c) < precedence(st.peek()) || (precedence(c) == precedence(st.peek()) && c != '^')))
                    out.append(st.pop());

                st.push(c);
            }
        }

        while (!st.isEmpty()) out.append(st.pop());
        return out.toString();
    }

    /**
     * <h3>Infix → Prefix Conversion</h3>
     *
     * <p><b>Key Trick:</b> Convert problem into postfix.</p>
     *
     * <h4>Transformation Strategy</h4>
     *
     * <pre>
     * Step 1 → Reverse expression
     * Step 2 → Swap '(' and ')'
     * Step 3 → Run infixToPostfix
     * Step 4 → Reverse result
     * </pre>
     *
     * <h4>Why This Works</h4>
     *
     * <p>
     * Prefix is mirror form of postfix under reversed input.
     * Reversal flips operator direction naturally.
     * </p>
     *
     * <h4>Memory Hook</h4>
     *
     * <pre>
     * Prefix = Reverse(Postfix(Reverse(Infix)))
     * </pre>
     *
     * <h4>Complexity</h4>
     * O(N)
     */
    static String infixToPrefix(String s) {
        StringBuilder rev = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') rev.append(')');
            else if (c == ')') rev.append('(');
            else rev.append(c);
        }

        String post = infixToPostfix(rev.toString());
        return new StringBuilder(post).reverse().toString();
    }

    /**
     * <h3>Postfix → Infix Conversion</h3>
     *
     * <p><b>Traversal:</b> Left → Right</p>
     *
     * <h4>Core Intuition</h4>
     *
     * <p>
     * In postfix, operator appears AFTER operands.
     * When operator appears — operands are already on stack.
     * </p>
     *
     * <h4>Stack Behavior</h4>
     *
     * <pre>
     * operand → push as string
     * operator → pop B, pop A
     *           form "(A op B)"
     *           push back
     * </pre>
     *
     * <h4>Why Pop Order Matters</h4>
     *
     * <pre>
     * Stack top = second operand
     * Next      = first operand
     * </pre>
     *
     * <h4>Parentheses</h4>
     *
     * <p>
     * Always add parentheses to preserve original evaluation order.
     * </p>
     *
     * <h4>Complexity</h4>
     * O(N)
     */
    static String postfixToInfix(String s) {
        Deque<String> st = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                st.push(c + "");
            } else {
                String b = st.pop();
                String a = st.pop();
                st.push("(" + a + c + b + ")");
            }
        }
        return st.peek();
    }

    /**
     * <h3>Prefix → Infix Conversion</h3>
     *
     * <p><b>Traversal:</b> Right → Left</p>
     *
     * <h4>Core Intuition</h4>
     *
     * <p>
     * In prefix, operator appears BEFORE operands.
     * When scanning from right, operands appear first.
     * </p>
     *
     * <h4>Stack Behavior</h4>
     *
     * <pre>
     * operand → push
     * operator → pop A, pop B
     *           form "(A op B)"
     *           push
     * </pre>
     *
     * <h4>Why Direction Changes Pop Order</h4>
     *
     * <p>
     * Because we scan reverse, first pop is left operand.
     * </p>
     *
     * <h4>Memory Hook</h4>
     *
     * <pre>
     * Prefix = reverse traversal version of postfix logic
     * </pre>
     */
    static String prefixToInfix(String s) {
        Deque<String> st = new ArrayDeque<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                st.push(c + "");
            } else {
                String a = st.pop();
                String b = st.pop();
                st.push("(" + a + c + b + ")");
            }
        }
        return st.peek();
    }

    /**
     * <h3>Postfix → Prefix Conversion</h3>
     *
     * <p><b>Traversal:</b> Left → Right</p>
     *
     * <h4>Expression Shape Difference</h4>
     *
     * <pre>
     * Postfix: A B op
     * Prefix : op A B
     * </pre>
     *
     * <h4>Stack Behavior</h4>
     *
     * <pre>
     * operand → push
     * operator → pop B, pop A
     *           push (op + A + B)
     * </pre>
     *
     * <h4>Key Insight</h4>
     *
     * <p>
     * Only output arrangement changes — stack logic same as postfix→infix.
     * </p>
     */
    static String postfixToPrefix(String s) {
        Deque<String> st = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                st.push(c + "");
            } else {
                String b = st.pop();
                String a = st.pop();
                st.push(c + a + b);
            }
        }
        return st.peek();
    }

    /**
     * <h3>Prefix → Postfix Conversion</h3>
     *
     * <p><b>Traversal:</b> Right → Left</p>
     *
     * <h4>Expression Shape Difference</h4>
     *
     * <pre>
     * Prefix : op A B
     * Postfix: A B op
     * </pre>
     *
     * <h4>Stack Behavior</h4>
     *
     * <pre>
     * operand → push
     * operator → pop A, pop B
     *           push (A + B + op)
     * </pre>
     *
     * <h4>Memory Hook</h4>
     *
     * <pre>
     * Prefix/Postfix conversions are mirror operations.
     * Only traversal direction differs.
     * </pre>
     */
    static String prefixToPostfix(String s) {
        Deque<String> st = new ArrayDeque<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                st.push(c + "");
            } else {
                String a = st.pop();
                String b = st.pop();
                st.push(a + b + c);
            }
        }
        return st.peek();
    }
}
