package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class country // 나라
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

public class B16234 {

    public static int N, L, R, ans; // N => N * N이 나라 수 // L 연합 결정용 차이 최소값 // R 연합 결정용 차이 최대값 // ans 최종 답
    public static int[][] A; // 나라의 인구 배열
    public static int[][] C; // 각 연합 별 인구수와 나라수를 저장하기 위한 배열
    public static int[][] Cinit; // C 배열 초기화 위한 초기값 0 인 배열
    public static int[][] union; // 연합
    public static int[][] init; // 연합 배열 초기화 위한 초기값 0인 배열
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static void initunion() // 연합 배열 초기화
    {
        for (int r = 0; r < N; r++)
            union[r] = init[r].clone();
    }
    public static void initC() // C 배열 초기화
    {
        for (int i = 1; i < N * N + 1; i++)
            C[i] = Cinit[i].clone();
    }

    private static void BFS ()
    {
        boolean moved = false; // 인구 이동 지속 여부 판단 위한 불리언 값
        while (moved == false) {
            int uninum = 1; // 연합 번호 // 연합이 안되더라도 즉 1개 국가고 하나의 연합으로 생각하게 됨!
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (union[r][c] == 0) { // 아직 연합 배정이 안된 나라를 발견했다면 BFS로 연합 찾아나감
                        Queue<country> united = new LinkedList<>();
                        united.offer(new country(r, c));
                        while (!united.isEmpty()) {
                            country tmp = united.poll();
                            int tr = tmp.getR();
                            int tc = tmp.getC();
                            union[tr][tc] = uninum; // 연합번호 배정
                            C[uninum][0] += A[tr][tc]; // 연합 인구수
                            C[uninum][1] += 1; // 연합 나라수
                            for (int i = 0; i < 4; i++) {
                                int nr = tr + dr[i];
                                int nc = tc + dc[i];

                                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                                if (union[nr][nc] != 0) continue;
                                int gap = Math.abs(A[tr][tc] - A[nr][nc]);
                                if (gap >= L && gap <= R) {
                                    moved = true; // 인구 이동이 일어날 것임
                                    union[nr][nc] = uninum; // 이때 미리 연합 배정을 안해주면 Queue에 넣고 빼는 순서에 의해 값이 이상해질 수 있음!!
                                    united.offer(new country(nr, nc));
                                }
                            }
                        }
                        uninum++; // 연합 번호 ++
                    }
                }
            }

            if (moved == true) // 인구 이동 일어나야함
            {
                moved = false;
                for (int i = 1; i < uninum; i++)
                {
                    if (C[i][1] > 1) // 진짜 연합인 경우에만 인구 재배정
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
