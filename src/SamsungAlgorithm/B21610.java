package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B21610 {

    public static int N, M, ans;
    public static int[][] grid;
    public static int[][] diaval; // 대각선에 물이 있는 바구니 수
    public static int[][] refgrid;
    public static int[][] cloud; // 구름 위치
    public static int[][] refcloud;
    public static int[] d;
    public static int[] s;
    public static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] diar = {0, -1, -1, 1, 1};
    public static int[] diac = {0, -1, 1, 1, -1};
    public static void cloudinit()
    {
        for (int cl = 0; cl < cloud.length; cl++)
            cloud[cl] = refcloud[cl].clone();
    }
    public static void diavalinit()
    {
        for (int r = 1; r <= N; r++)
        {
            diaval[r] = refgrid[r].clone();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N+1][N+1];
        diaval = new int[N+1][N+1];
        refgrid = new int[N+1][N+1];
        cloud = new int[N*N][2];
        refcloud = new int[N*N][2];
        d = new int[M+1];
        s = new int[M+1];
        ans = 0;

        for (int r = 1; r <= N; r++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++)
                grid[r][c] = Integer.parseInt(sta.nextToken());
        }

        for (int m = 1; m <= M; m++)
        {
            StringTokenizer stb = new StringTokenizer(br.readLine());
            d[m] = Integer.parseInt(stb.nextToken());
            s[m] = Integer.parseInt(stb.nextToken());
        }

        // cloud[n][0] 구름위치행값 // cloud[n][1] 구름위치열값
        // 아래 8줄은 처음 구름 위치 세팅
        cloud[0][0] = N;
        cloud[0][1] = 1;
        cloud[1][0] = N;
        cloud[1][1] = 2;
        cloud[2][0] = N-1;
        cloud[2][1] = 1;
        cloud[3][0] = N-1;
        cloud[3][1] = 2;

        for (int i = 1; i <= M; i++)
        {
            int cc = 0; // cloudcount
            for (int cl = 0; cl < cloud.length; cl++)
            {
                if (cloud[cl][0] != 0 && cloud[cl][1] != 0)
                {
                    cc++;
                    int tr = cloud[cl][0];
                    int tc = cloud[cl][1];
                    int nr = tr + (dr[d[i]] * s[i]);
                    int nc = tc + (dc[d[i]] * s[i]);
                    // 이 부분이 nr과 nc가 격자 밖으로 벗어나는 경우 다시 재배치 해주는 로직
                    if (nr > N)
                    {
                        if (nr % N == 0)
                            nr = N;
                        else
                            nr = nr % N;
                    }
                    else if (nr < 1)
                        nr = N - (nr * -1 % N);
                    if (nc > N)
                    {
                        if (nc % N == 0)
                            nc = N;
                        else
                            nc = nc % N;
                    }
                    else if (nc < 1)
                        nc = N - (nc * -1 % N);
                    cloud[cl][0] = nr;
                    cloud[cl][1] = nc;
                    grid[nr][nc]++;
                }
            }
            boolean[][] clouded = new boolean[N+1][N+1]; // 구름이 사라졌던 위치 표시용 불리언 배열
            for (int cl = 0; cl < cc; cl++)
            {
                int tr = cloud[cl][0];
                int tc = cloud[cl][1];
                clouded[tr][tc] = true;

                int cnt = 0;
                for (int a = 1; a <= 4; a++)
                {
                    int ur = tr + diar[a];
                    int uc = tc + diac[a];

                    if (ur < 1 || uc < 1 || ur > N || uc > N) continue; // 격자 밖으로 벗어난 경우
                    if (grid[ur][uc] > 0) cnt++;
                }
                diaval[tr][tc] = cnt;
            }
            for (int cl = 0; cl < cc; cl++)
            {
                int tr = cloud[cl][0];
                int tc = cloud[cl][1];
                grid[tr][tc] += diaval[tr][tc];
            }
            diavalinit();
            cloudinit();
            cc = 0;
            for (int r = 1; r <= N; r++)
            {
                for (int c = 1; c <= N; c++)
                {
                    if (clouded[r][c] == true) continue; // 구름이 사라졌던 곳이면 pass
                    if (grid[r][c] < 2) continue; // 남은 물이 2 미만이면 pass
                    cloud[cc][0] = r;
                    cloud[cc][1] = c;
                    cc++;
                    grid[r][c] -= 2;
                }
            }
            // i번 이동 후 구름 위치 나타내기
            // System.out.println("---<" + i + ">---");
            // System.out.println(Arrays.deepToString(cloud)); => 둘 다 0인 것이 나오기 전까지가 구름 위치들
        }

        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
                ans += grid[r][c];
        }
        System.out.println(ans);
    }
}