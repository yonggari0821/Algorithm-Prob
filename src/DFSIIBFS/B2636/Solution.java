package B2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class cell
{
    private int r;
    private int c;

    public cell (int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Solution {

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static int M, N;
    public static int forouter = 7;
    public static int lastmelting = 0;
    public static boolean flag;

    public static int[][] map;
    public static void outer(int r, int c)
    {
        Queue<cell> q = new LinkedList();
        q.offer(new cell(r, c));
        map[r][c] = forouter;

        while(!(q.isEmpty()))
        {
            cell tmp = q.poll();
            for (int i = 0; i < 4; i++)
            {
                int nr = tmp.getR() + dr[i];
                int nc = tmp.getC() + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] != 0 && map[nr][nc] != forouter - 1) continue;
                if (map[nr][nc] == 0 || map[nr][nc] == forouter - 1)
                {
                    q.offer(new cell(nr, nc));
                    map[nr][nc] = forouter;
                }
            }
        }
    }

    public static void melting()
    {
        Queue<cell> qq = new LinkedList();

        int a = 0;
        int b = 0;
        while (a < N)
        {
            b = 0;
            while (b < M)
            {
                if (map[a][b] == 1)
                    qq.offer(new cell(a, b));
                b++;
            }
            a++;
        }

        if (qq.isEmpty())
        {
            flag = true;
            return ;
        }

        while(!qq.isEmpty())
        {
            cell tmp = qq.poll();
            int cnt = 0;
            for (int i = 0; i < 4; i++)
            {
                int nr = tmp.getR() + dr[i];
                int nc = tmp.getC() + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == forouter) cnt++;
            }
            if (cnt > 0)
                map[tmp.getR()][tmp.getC()] = 5;
        }

        /*
        for (int n = 0; n < N; n++)
        {
            for (int m = 0; m < M; m++)
                System.out.printf("%d ", map[n][m]);
            System.out.println("");
        }
        */

        int meltcnt = 0;
        for (int s = 0; s < N; s++)
        {
            for (int t = 0; t < M; t++)
            {
                if (map[s][t] == 5)
                {
                    meltcnt++;
                    map[s][t] = forouter;
                }
            }
        }
        lastmelting = meltcnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
            {
                map[n][m] = Integer.parseInt(sta.nextToken());
            }
        }



        int times = 0;
        while (flag == false) {
            outer(0,0);
            melting();
            if (flag == true)
                break;
            forouter++;
            times++;
        }

        System.out.println(times);
        System.out.println(lastmelting);

    }
}
