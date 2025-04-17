import java.util.*;

/*
    costs[i][0] 섬 A
    costs[i][1] 섬 B
    costs[i][2] A와 B를 연결하는 다리 건설 비용
    
    <최소 신장 트리>
    크루스칼 - 간선
    프림 - 정점
*/

class Edge {
    int from;
    int to;
    int cost;
    
    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

class Solution {
    
    static int[] parent;
    
    static void union(int a, int b) {
        int Pa = find(a);
        int Pb = find(b);
        if (Pa != Pb) parent[Pa] = Pb;
    }
    
    static int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;
        
        Queue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0]; // 섬 A
            int b = costs[i][1]; // 섬 B
            int w = costs[i][2]; // A와 B를 연결하는 다리 건설 비용
            
            pq.offer(new Edge(a, b, w));
        }
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int a = cur.from;
            int b = cur.to;
            int w = cur.cost;
            if (find(a) != find(b)) {
                union(a, b);
                answer += w;
            }
        }
        
        return answer;
    }
}