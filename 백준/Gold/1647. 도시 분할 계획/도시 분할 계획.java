import java.io.*;
import java.util.*;

/*
백준 1647 도시 분할 계획

어떤 망을 연결하는 최소 비용을 구한다 => MST
크루스칼 또는 프림 알고리즘 사용 가능

 */

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {

    static int[] parent;
    static int find(int a)
    {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b)
    {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[bRoot] = aRoot;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        int min = 0;
        int maxCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }

        while(!pq.isEmpty())
        {
            Edge cur = pq.poll();
            // 아직 안이어진 상태라면
            if (find(cur.from) != find(cur.to))
            {
//                System.out.println(cur.from + " -> " + cur.to + " [" + cur.cost + "]");
                min += cur.cost;
                maxCost = cur.cost;
                union(cur.from, cur.to);
            }
        }

        System.out.println(min - maxCost);
    }
}

/*
[1]
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4

8
 */