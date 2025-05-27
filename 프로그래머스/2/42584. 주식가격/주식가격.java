import java.util.*;



class Node {
    int v;
    int idx;
    
    public Node (int v, int idx) {
        this.v = v;
        this.idx = idx;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Node> stack = new Stack<>();
        
        // 1  2 2 1 2 3 1 2 3 2 1 
        // 10 2 1 7 2 1 4 2 1 1 1
        
        // 1 1 1 1
        int tmp;
        stack.add(new Node(prices[0], 0));
        for (int i = 0; i < prices.length; i++) {
            // 현재 값이 더 크거나 같은 경우 => 스택에 넣기
            while (!stack.isEmpty() && stack.peek().v > prices[i]) {
                Node past = stack.pop();
                answer[past.idx] = i - past.idx;
            }
            stack.add(new Node(prices[i], i));
        }
        
        while (!stack.isEmpty()) {
            Node past = stack.pop();
            answer[past.idx] = prices.length - 1 - past.idx;
        }
        
        return answer;
    }
}