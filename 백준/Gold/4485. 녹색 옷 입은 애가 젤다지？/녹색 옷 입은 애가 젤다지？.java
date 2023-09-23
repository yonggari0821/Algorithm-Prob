import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int val;

    public Node(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        int test_case = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            StringTokenizer st;
            ans.append("Problem " + test_case++ + ": ");
            int[][] grid = new int[N][N];
            int[][] dijkstra = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                    dijkstra[r][c] = Integer.MAX_VALUE;
                }
            }
            Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.val - o2.val;
                }
            });
            queue.offer(new Node(0, 0, grid[0][0]));
            dijkstra[0][0] = grid[0][0];
            while (!queue.isEmpty())
            {
                Node cur = queue.poll();
                if (visited[cur.row][cur.col]) continue;
                visited[cur.row][cur.col] = true;
                for (int i = 0; i < 4; i++) {
                    int nr = cur.row + dr[i];
                    int nc = cur.col + dc[i];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc] || dijkstra[nr][nc] < dijkstra[cur.row][cur.col] + grid[nr][nc]) continue;
                    dijkstra[nr][nc] = dijkstra[cur.row][cur.col] + grid[nr][nc];
                    queue.offer(new Node(nr, nc, dijkstra[nr][nc]));
                }
            }
            int min = dijkstra[N - 1][N - 1];
            ans.append(min).append('\n');
        }
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}