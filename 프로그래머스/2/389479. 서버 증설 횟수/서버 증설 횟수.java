import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        // 답: 총 서버 증설 횟수
        int answer = 0;
        
        // 우선순위 큐
        // [만료 시간, 해당 시간에 만료될 서버 수(어떤 시간에 증설한 서버 수)]
        // 만료 시간이 빠른 것부터 처리하기 위한 우선순위 큐!
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> 
            a[0] - b[0]
        );
        
        // 현재 증설된 서버 수
        int plus = 0;
        
        for (int i = 0; i < players.length; i++) {
            // 만료 서버 먼저 내리기
            while (!pq.isEmpty() && pq.peek()[0] == i) {
                plus -= pq.poll()[1];
            }
                
            // 현재 필요로 하는 서버 수
            int need = players[i]/m;
            
            // 서버 증설해야할 갯수
            int toPlus = plus - need;
            
            // 충분하지 않으면
            if (toPlus < 0) {
                plus -= toPlus;
                answer -= toPlus;
                // 새로 증설한 서버를 서버큐에 넣어주기!
                // 이 때, 만료시간(i + k)과 현재 시간에 증설한 서버 수(-toPlus)를 기록해둬야 나중에 뺄 수 있음!
                pq.add(new int[]{i + k, -toPlus});
            }
        }
            
        return answer;
    }
}