import java.util.*;
import java.io.*;

class Node {
    int n;
    int w;
    Node (int n, int w) {
        this.n = n;
        this.w = w;
    }
}

public class Main {

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 헛간 (정점) 갯수
        int M = Integer.parseInt(st.nextToken()); // 길 (간선) 갯수

        boolean[] visited = new boolean[N + 1];
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<Node>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        Queue<Node> pq = new PriorityQueue<>(
            new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.w - o2.w;
                }
            }
        );

        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            visited[cur.n] = true;
            if (cur.n == N) {
                System.out.println(cur.w);
                break;
            }
            for (int i = 0; i < graph[cur.n].size(); i++) {
                Node next = graph[cur.n].get(i);
                if (visited[next.n]) continue;
                pq.offer(new Node(next.n, cur.w + next.w));
            }
        }

    }
}