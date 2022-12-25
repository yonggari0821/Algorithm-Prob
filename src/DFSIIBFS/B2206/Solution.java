package B2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node
{
    private int r;
    private int c;
    private int len;
    private int broken;

    public Node (int r, int c, int len, int broken)
    {
        this.r = r;
        this.c = c;
        this.len = len;
        this.broken = broken;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
    public int getLen() {return this.len;}
    public int getBroken() {return this.broken;}
}

public class Solution {

    public static int ans = Integer.MAX_VALUE;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static int N, M;
    public static int[][] map;
    public static int[][] passed;

    public static void bfs (int r, int c)
    {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c, 1, 0));
        passed[r][c] = 0;

        while(!q.isEmpty())
        {
            Node tmp = q.poll();
            int pr = tmp.getR();
            int pc = tmp.getC();
            int plen = tmp.getLen();
            int pbroken = tmp.getBroken();

            if (pr == N - 1 && pc == M - 1)
            {
                ans = plen;
                break;
            }

            for (int i = 0; i < 4; i++)
            {
                int nr = pr + dr[i];
                int nc = pc + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (passed[nr][nc] <= pbroken) continue;
                // 새로 확인 하는 곳이 벽이 아닌 경우
                if (map[nr][nc] == 0)
                {
                    passed[nr][nc] = pbroken;
                    q.offer(new Node(nr, nc, plen + 1, pbroken));
                }
                // 새로 확인 하는 곳이 벽인 경우
                else // (map[nr][nc] == 1)
                {
                    if (pbroken == 0)
                    {
                        passed[nr][nc] = pbroken + 1;
                        q.offer(new Node(nr, nc, plen +1, pbroken + 1));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        passed = new int[N][M];

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine(), "01", true);
            for (int m = 0; m < M; m++)
            {
                map[n][m] = Integer.parseInt(sta.nextToken());
                passed[n][m] = 2;
            }
        }

        bfs(0, 0);

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else
            System.out.println(ans);
    }
}
