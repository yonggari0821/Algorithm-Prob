import java.io.*;
import java.util.*;

class Node {
    int r;
    int c;
    int wallBroken;

    public Node(int r, int c, int wallBroken) {
        this.r = r;
        this.c = c;
        this.wallBroken = wallBroken;
    }
}

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dijkstra = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
                dijkstra[r][c] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.wallBroken - o2.wallBroken;
            }
        });
        pq.offer(new Node(0, 0, 0));
        dijkstra[0][0] = 0;
        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int r = cur.r;
            int c = cur.c;
            if (r == N - 1 && c == M - 1) {
                ans.append(cur.wallBroken);
                break;
            }
            if (visited[r][c]) continue;
            visited[r][c] = true;
            int wb = cur.wallBroken;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;
                if (map[nr][nc] == 0) pq.offer(new Node(nr, nc, wb));
                else {
                    if (dijkstra[r][c] + 1 < dijkstra[nr][nc]) {
                        dijkstra[nr][nc] = dijkstra[r][c] + 1;
                        pq.offer(new Node(nr, nc, wb + 1));
                    }
                }
            }
        }
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}