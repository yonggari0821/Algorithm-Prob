import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        
        // 피로도 == 남은 일들의 제곱값의 합
        // 이걸 최소화하려면 최대한 비슷하게 맞춰야 함
        // Ex) 1 3 1 / 2 2 1 / 0 0 5
        //     1 9 1 / 4 4 1 / 0 0 25
        // 최댓값부터 빼가면 됨!
        
        Queue<Integer> pq = new PriorityQueue<>( (a, b) -> b - a);
        for (int i = 0; i < works.length; i++) pq.offer(works[i]);
        
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            int next;
            if (!pq.isEmpty()) next = pq.peek();
            else next = 0;

            int gap = cur - next;
            cur -= Math.min(gap, n);
            n -= Math.min(gap, n);
            
            if (n <= 0) {
                pq.offer(cur);
                break;
            }
            if (cur > 0) {
                pq.offer(cur - 1);
                n--;
            }
        }
        
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            
            // System.out.println(cur);
            
            answer += (cur * cur);
        }
        
        return answer;
    }
}