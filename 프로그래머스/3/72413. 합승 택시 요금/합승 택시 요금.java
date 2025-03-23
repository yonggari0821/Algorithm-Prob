// 무방향 가중치
// 
import java.util.*;

class Node {
    int n;
    int w;
    
    Node(int n, int w) {
        this.n = n;
        this.w = w;
    }
}

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int ans = Integer.MAX_VALUE;
        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<Node>();
        int[] visitedFromS = new int[n + 1];
        int[] visitedFromA = new int[n + 1];
        int[] visitedFromB = new int[n + 1];
        Arrays.fill(visitedFromS, Integer.MAX_VALUE);
        visitedFromS[s] = 0;
        Arrays.fill(visitedFromA, Integer.MAX_VALUE);
        visitedFromA[a] = 0;
        Arrays.fill(visitedFromB, Integer.MAX_VALUE);
        visitedFromB[b] = 0;
        for (int r = 0; r < fares.length; r++) {
            int from = fares[r][0];
            int to = fares[r][1];
            int fare = fares[r][2];
            graph[from].add(new Node(to, fare));
            graph[to].add(new Node(from, fare));
        }
        
        Queue<Node> pq = new PriorityQueue<>(
            (o1, o2) -> o1.w - o2.w
        );
        
        // A부터
        pq.offer(new Node(a, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visitedFromA[cur.n] < cur.w) continue;
            visitedFromA[cur.n] = cur.w;
            for (int i = 0; i < graph[cur.n].size(); i++) {
                Node next = graph[cur.n].get(i);
                pq.offer(new Node(next.n, cur.w + next.w));
            }
        }
        // 비워주고
        pq.clear();
        
        // 이번엔 B
        pq.offer(new Node(b, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visitedFromB[cur.n] < cur.w) continue;
            visitedFromB[cur.n] = cur.w;
            for (int i = 0; i < graph[cur.n].size(); i++) {
                Node next = graph[cur.n].get(i);
                pq.offer(new Node(next.n, cur.w + next.w));
            }
        }
        
        // 마지막 시작점
        pq.offer(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visitedFromS[cur.n] < cur.w) continue;
            visitedFromS[cur.n] = cur.w;
            for (int i = 0; i < graph[cur.n].size(); i++) {
                Node next = graph[cur.n].get(i);
                pq.offer(new Node(next.n, cur.w + next.w));
            }
        }
        
        int[] finalFare = new int[n + 1];
        for (int i = 1; i <= n; i++){
            finalFare[i] += visitedFromS[i] == Integer.MAX_VALUE? 0 : visitedFromS[i];
            finalFare[i] += visitedFromA[i] == Integer.MAX_VALUE? 0 : visitedFromA[i];
            finalFare[i] += visitedFromB[i] == Integer.MAX_VALUE? 0 : visitedFromB[i];
            if (finalFare[i] != 0) ans = Math.min(ans, finalFare[i]);
        }
        
        return ans;
    }
}