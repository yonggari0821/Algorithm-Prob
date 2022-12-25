package DFSIIBFS.B7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int[][] tomatogrid;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        tomatogrid = new int[N][M];

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
            {
                tomatogrid[n][m] = Integer.parseInt(sta.nextToken());
            }
        }

        for (int u = 1; u < N * M; u++)
        {
            int cnt = 0;
            for (int r = 0; r < N; r++)
            {
                for (int c = 0; c < M; c++)
                {
                    if (tomatogrid[r][c] == u)
                    {
                        for (int i = 0; i < 4; i++)
                        {
                            int nr = r + dr[i];
                            int nc = c + dc[i];

                            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                                continue;
                            if (tomatogrid[nr][nc] == -1)
                                continue;
                            if (tomatogrid[nr][nc] == 0)
                            {
                                tomatogrid[nr][nc] = u + 1;
                                cnt++;
                            }
                        }
                    }
                }
            }
            if (cnt == 0)
                break;
        }

        int max = -1;
        int zerocnt = 0;
        for (int n = 0; n < N; n++)
        {
            for (int m = 0; m < M; m++)
            {
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
