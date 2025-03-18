import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean ans = true;
        Stack<Boolean> stack = new Stack();
        char val;
        for (int i = 0; i < s.length(); i++) {
            val = s.charAt(i);
            if (val == '(') stack.push(true);
            else {
                if (stack.isEmpty()) {
                    ans = false;
                    break;
                }
                else stack.pop();
            }
        }
        if (!stack.isEmpty()) ans = false;
        return ans;
    }
}