import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class country
{
    private int r;
    private int c;

    country(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Main {

    public static int N, L, R, ans;
    public static boolean movable = false;
    public static int[][] A;
    public static int[][] C;
    public static int[][] Cinit;
    public static int[][] union;
    public static int[][] init;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static void initunion()
    {
        for (int r = 0; r < N; r++)
            union[r] = init[r].clone();
    }
    public static void initC()
    {
        for (int i = 1; i < N * N + 1; i++)
            C[i] = Cinit[i].clone();
    }

    private static void BFS ()
    {
        boolean moved = false;
        while (moved == false) {
            int uninum = 1;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (union[r][c] == 0) {
                        Queue<country> united = new LinkedList<>();
                        united.offer(new country(r, c));
                        while (!united.isEmpty()) {
                            country tmp = united.poll();
                            int tr = tmp.getR();
                            int tc = tmp.getC();
                            union[tr][tc] = uninum;
                            C[uninum][0] += A[tr][tc];
                            C[uninum][1] += 1;
                            for (int i = 0; i < 4; i++) {
                                int nr = tr + dr[i];
                                int nc = tc + dc[i];

                                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                                if (union[nr][nc] != 0) continue;
                                int gap = Math.abs(A[tr][tc] - A[nr][nc]);
                                if (gap >= L && gap <= R) {
                                    moved = true;
                                    union[nr][nc] = uninum;
                                    united.offer(new country(nr, nc));
                                }
                            }
                        }
                        uninum++;
                    }
                }
            }

            if (moved == true)
            {
                moved = false;
                for (int i = 1; i < uninum; i++)
                {
                    if (C[i][1] > 1)
                    {
                        for (int r = 0; r < N; r++) {
                            for (int c = 0; c < N; c++) {
                                if (union[r][c] == i)
                                    A[r][c] = ((C[i][0]) / (C[i][1]));
                            }
                        }
                    }
                }
            }
            else
                break;
            ans++;
            // 바뀌는 과정이 보고 싶으면
            // System.out.println(ans);
            // System.out.println(Arrays.deepToString(A));
            initunion();
            initC();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        C = new int[N*N+1][2];
        Cinit = new int[N*N+1][2];
        union = new int[N][N];
        init = new int[N][N];
        ans = 0;

        for (int r = 0; r < N; r++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++)
                A[r][c] = Integer.parseInt(sta.nextToken());
        }
        // 잘 들어갔는 지 확인
        // System.out.println(Arrays.deepToString(A));
        BFS();
        System.out.println(ans);
    }
}