import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    Edge(int f, int t, int w) {
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    } // 오름차순
}

public class Main {

    static long total;
    static long usedEdgesWeights;
    static int[] parent;

    static void makeSet(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra != rb) parent[rb] = ra;
    }

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 건물(정점) 갯수
        int M = Integer.parseInt(st.nextToken()); // 도로(간선) 갯수
        total = 0; // 간선의 총 비용
        usedEdgesWeights = 0; // 사용하는 간선의 총 비용

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            total += weight;
            edges.add(new Edge(from, to, weight));
        }

        makeSet(N);
        Collections.sort(edges);
        int usedEdgeCount = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge cur = edges.get(i);
            if (find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);
                usedEdgesWeights += cur.weight;
                usedEdgeCount++;
            }
        }

//        System.out.println("total = " + total);
//        System.out.println("usedEdgesWeights = " + usedEdgesWeights);

        if (usedEdgeCount == N - 1) System.out.println(total - usedEdgesWeights);
        else System.out.println(-1);
    }
}