import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int nodeNum;
    int dist;

    public Node(int nodeNum, int dist) {
        this.nodeNum = nodeNum;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.dist, other.dist);
    }
}

public class Main {
    static String result(boolean isGunwooOnTheWay) {
        if (isGunwooOnTheWay) return "SAVE HIM";
        return "GOOD BYE";
    }

    static int V, E, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) graph.add(new ArrayList<Node>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, dist));
            graph.get(to).add(new Node(from, dist));
        }

        int[] distFromStart = new int[V + 1];
        int[] distFromGunwoo = new int[V + 1];
        int[] distFromEnd = new int[V + 1];

        Arrays.fill(distFromStart, Integer.MAX_VALUE);
        Arrays.fill(distFromGunwoo, Integer.MAX_VALUE);
        Arrays.fill(distFromEnd, Integer.MAX_VALUE);

        dijkstra(1, distFromStart, graph);
        dijkstra(P, distFromGunwoo, graph);
        dijkstra(V, distFromEnd, graph);

        int distStartToGunwoo = distFromStart[P];
        int distGunwooToEnd = distFromGunwoo[V];

        if (distStartToGunwoo + distGunwooToEnd <= distFromStart[V]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    static void dijkstra(int start, int[] dist, ArrayList<ArrayList<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int nodeNum = currentNode.nodeNum;
            int distance = currentNode.dist;

            if (distance > dist[nodeNum]) {
                continue;
            }

            for (Node neighbor : graph.get(nodeNum)) {
                int newDist = distance + neighbor.dist;
                if (newDist < dist[neighbor.nodeNum]) {
                    dist[neighbor.nodeNum] = newDist;
                    pq.add(new Node(neighbor.nodeNum, newDist));
                }
            }
        }
    }
}
