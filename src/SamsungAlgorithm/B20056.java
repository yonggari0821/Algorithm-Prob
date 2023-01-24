package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class fb
{
    int r;
    int c;
    int m;
    int d;
    int s;

    public fb(int r, int c, int m, int s, int d)
    {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}


public class B20056 {

    public static int N, M, K, ans;
    public static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        if (M == 0)
        {
            System.out.println(ans);
            return ;
        }
        Queue<fb> ballsA = new LinkedList<>();

        for (int i = 1; i <= M; i++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(sta.nextToken());
            int c = Integer.parseInt(sta.nextToken());
            int m = Integer.parseInt(sta.nextToken());
            int s = Integer.parseInt(sta.nextToken());
            int d = Integer.parseInt(sta.nextToken());
            ballsA.offer(new fb(r, c, m, s, d));
        }

        int k = 0;
        while (k < K)
        {
            k++;
            int[][] grid = new int[N+1][N+1]; // 갯수
            int[][] mm = new int[N+1][N+1]; // 질량
            int[][] dd = new int[N+1][N+1]; // 방향
            boolean[][] ddodd = new boolean[N+1][N+1];
            boolean[][] ddeven = new boolean[N+1][N+1];
            int[][] ss = new int[N+1][N+1]; // 속력
            while(!ballsA.isEmpty())
            {
                fb tmp = ballsA.poll();
                int tr = tmp.r;
                int tc = tmp.c;
                int tm = tmp.m;
                int ts = tmp.s;
                int td = tmp.d;

                int nr = tr + (dr[td] * ts);
                int nc = tc + (dc[td] * ts);

                if (nr < 1)
                    nr = N - ((-1 * nr) % N);
                else if (nr > N)
                {
                    if (nr % N == 0)
                        nr = N;
                    else
                        nr = nr % N;
                }
                if (nc < 1)
                    nc = N - ((-1 * nc) % N);
                else if (nc > N)
                {
                    if (nc % N == 0)
                        nc = N;
                    else
                        nc = nc % N;
                }
                mm[nr][nc] += tm;
                ss[nr][nc] += ts;
                dd[nr][nc] += td;
                grid[nr][nc]++;
                if (td % 2 == 1)
                    ddodd[nr][nc] = true;
                else
                    ddeven[nr][nc] = true;
                // System.out.println(tr + "," + tc + " => " + nr + "," + nc );
            }

            for (int r = 1; r <= N; r++)
            {
                for (int c = 1; c <= N; c++)
                {
                    if (grid[r][c] == 1)
                    {
                        ballsA.offer(new fb(r, c, mm[r][c], ss[r][c], dd[r][c]));
                    }
                    else if (grid[r][c] > 1)
                    {
                        int hm = (mm[r][c] / 5);
                        if (k == K) // 마지막의 경우 나눠진 질량을 다시 전체 질량으로 더해놔줌
                            mm[r][c] = hm * 4;
                        if (hm == 0) continue;
                        int hs = ss[r][c] / grid[r][c];
                        if ((ddodd[r][c] == true && ddeven[r][c] == false) || (ddodd[r][c] == false && ddeven[r][c] == true))
                        {
                            for (int a = 0; a < 8; a += 2)
                                ballsA.offer(new fb(r, c, hm, hs, a));
                        }
                        else
                        {
                            for (int b = 1; b < 8; b += 2)
                                ballsA.offer(new fb(r, c, hm, hs, b));
                        }
                    }
                }
            }

            if (k == K)
            {
                for (int r = 1; r <= N; r++) {
                    for (int c = 1; c <= N; c++) {
                        if (mm[r][c] != 0)
                            ans += mm[r][c];
                    }
                }
                break;
            }
        }
        System.out.println(ans);
    }
}
