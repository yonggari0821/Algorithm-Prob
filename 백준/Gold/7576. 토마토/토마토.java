import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    private int r;
    private int c;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Main {

    public static int[][] tomatogrid;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        tomatogrid = new int[N][M];

        for (int n = 0; n < N; n++) {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                tomatogrid[n][m] = Integer.parseInt(sta.nextToken());
        }

        Queue<Node> ripe = new LinkedList<>();

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (tomatogrid[n][m] == 1)
                    ripe.offer(new Node(n, m));
            }
        }

        while (!ripe.isEmpty()) {
            Node tmp = ripe.poll();
            for (int i = 0; i < 4; i++) {
                int nr = tmp.getR() + dr[i];
                int nc = tmp.getC() + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                if (tomatogrid[nr][nc] == -1)
                    continue;
                if (tomatogrid[nr][nc] == 0) {
                    tomatogrid[nr][nc] = tomatogrid[tmp.getR()][tmp.getC()] + 1;
                    ripe.offer(new Node(nr, nc));
                }
            }
        }

        int max = -1;
        int zerocnt = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (tomatogrid[n][m] >= max)
                    max = tomatogrid[n][m];
                if (tomatogrid[n][m] == 0)
                    zerocnt++;
            }
        }

        if (zerocnt != 0)
            max = -1;
        else
            max -= 1;
        System.out.println(max);
    }
}
