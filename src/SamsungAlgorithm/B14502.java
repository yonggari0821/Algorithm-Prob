package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class node {
    int r;
    int c;

    node(int r, int c)
    {
        this.r = r;
        this.c = c;
    }
}

public class B14502 {
    public static int N, M, safe; // 행열 길이 및 안전지역 수
    public static int[] dr = {-1, 1, 0, 0}; // 상하
    public static int[] dc = {0, 0, -1, 1}; // 좌우

    public static void BFS(node n, int[][] nowlab) // 바이러스 감염
    {
        Queue<node> q = new LinkedList<>();
        q.offer(n);
        while (!q.isEmpty())
        {
            node tmp = q.poll();
            int tr = tmp.r;
            int tc = tmp.c;

            for (int i = 0; i < 4; i++)
            {
                int nr = tr + dr[i];
                int nc = tc + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue; // 범위 벗어나면 pass
                if (nowlab[nr][nc] == 1 || nowlab[nr][nc] == 2) continue; // 벽이나 다른 바이러스 근원지면 pass
                nowlab[nr][nc] = 2; // 이동가능한 곳 감염시키기
                q.offer(new node(nr, nc));
            }
        }
    }

    public static void Brute(int wall, int[][] nowlab, boolean[][] nowchecked)
    {
        if (wall == 3)
        {
            int tcnt = 0; // 안전지역 셀 변수
            int[][] tmp = new int[N][M]; // 바이러스 감염용 임시 배열
            for (int r = 0; r < N; r++)
                tmp[r] = nowlab[r].clone();
            for (int r = 0; r < N; r++)
            {
                for (int c = 0; c < M; c++)
                {
                    if (nowlab[r][c] == 2) // 감염 근원지에 대해 감염 실시 // tmp가 아니라 nowlab에서 2인 곳으로 해야 근원지에 대해서만 감염 시작 가능!
                        BFS(new node(r, c), tmp);
                }
            }
            for (int r = 0; r < N; r++)
            {
                for (int c = 0; c < M; c++)
                {
                    if (tmp[r][c] == 0) // 감염 안된 곳 count
                        tcnt++;
                }
            }
            safe = safe > tcnt ? safe : tcnt;
            return ;
        }
        else
        {
            for (int r = 0; r < N; r++)
            {
                for (int c = 0; c < M; c++)
                {
                    if (nowlab[r][c] == 0 && nowchecked[r][c] == false) // 아직 벽 X / 감염 X / 체크 X
                    {
                        nowlab[r][c] = 1;
                        nowchecked[r][c] = true;
                        Brute(wall + 1, nowlab, nowchecked);
                        nowlab[r][c] = 0;
                        nowchecked[r][c] = false;
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
        int[][] lab = new int[N][M]; // 연구소
        boolean[][] checked = new boolean[N][M]; // 중복체크

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                lab[n][m] = Integer.parseInt(sta.nextToken());
        }
        Brute(0, lab, checked);
        System.out.println(safe);
    }
}
