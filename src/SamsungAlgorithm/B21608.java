package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21608 {
    public static int N; // 교실 가로 세로 줄 수 => 제곱하면 학생 수
    public static int[][] ditto; // 좋아하는 학생 번호 받을 배열 // [a][b] a => 배치 우선 순위 b =>
    public static int[][] classroom; // 자리 배정해줄 교실 배열

    //상하좌우
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        classroom = new int[N + 1][N + 1]; // 번호대로 행 열에 반영하기 위해 +1
        ditto = new int[N * N + 1][5]; // 마찬가지 2번째 인덱스의 0번에는 해당 학생 번호 // 나머지 1~4는 해당 학생이 좋아하는 학생 번호

        for (int i = 1; i <= N * N; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int a = 0; a <= 4; a++)
                ditto[i][a] = Integer.parseInt(st.nextToken());
        }

        // 맞게 들어갔나 확인
        // System.out.println(Arrays.deepToString(ditto));

        classroom[2][2] = ditto[1][0]; // 무조건 2,2에 첫 학생 배정
        // 2, 2 => 모두 빈 공간일 때 가장 많은 인접한 빈칸(4칸)을 확보하면서 가장 작은 행과 열값을 가짐

        for (int i = 2; i <= N * N; i++) // 첫 학생 이후부터 순서대로 배정
        {
            int priorR = N; // 최종 배정 행,열
            int priorC = N;
            int maxcnt = -1; // 인접한 곳 중 좋아하는 학생이 있는 곳 갯수 최댓값
            int priorZ = -1; // 인접한 곳 중 빈 공간 수의 최대값
            for (int r = 1; r <= N; r++)
            {
                for (int c = 1; c <= N; c++)
                {
                    if (classroom[r][c] != 0) // 빈공간이 아니면 배치 자체가 불가능하므로 pass
                        continue;
                    int cnt = 0; // 각 위치에서 인접한 곳들 중 좋아하는 학생이 있는 갯수
                    int zcnt = 0; // 각 위치에서 인접한 곳들 중 빈 공간의 갯수
                    for (int e = 0; e < 4; e++) //상하좌우 탐색
                    {
                        int nr = r + dr[e];
                        int nc = c + dc[e];

                        if (nr < 1 || nc < 1 || nr > N || nc > N) continue; // 범위 벗어나면 pass
                        for (int d = 1; d <= 4; d++) // 좋아하는 학생 체크
                        {
                            if (classroom[nr][nc] == ditto[i][d])
                                cnt++;
                        }
                        if (classroom[nr][nc] == 0) // 빈공간 수 체크
                            zcnt++;
                    }

                    if (maxcnt < cnt) // 우선순위 1) 인접한 좋아하는 학생 수
                    {
                        maxcnt = cnt;
                        priorR = r;
                        priorC = c;
                        priorZ = zcnt;
                    }
                    else if (maxcnt == cnt)
                    {
                        if (priorZ < zcnt) // 우선순위 2) 인접한 빈 공간 수
                        {
                            priorR = r;
                            priorC = c;
                            priorZ = zcnt;
                        }
                        else if (priorZ == zcnt)
                        {
                            if (priorR > r) // 우선순위 3) 더 작은 행 값
                            {
                                priorR = r;
                                priorC = c;
                            }
                            else if (priorR == r)
                            {
                                if (priorC > c) // 우선순위 4) 더 작은 열 값
                                    priorC = c;
                            }
                        }
                    }
                }
            }
            classroom[priorR][priorC] = ditto[i][0]; // 우선순위 1등 자리에 해당학생 배정
        }

        //맞게 배정됐는 지 확인
        //System.out.println(Arrays.deepToString(classroom));

        int ans = 0; // 최종 값
        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
            {
                int stunum = classroom[r][c]; // 학생번호
                int stuorder = 0;
                for (int t = 1; t <= N * N; t++)
                {
                    if (stunum == ditto[t][0])
                    {
                        stuorder = t;
                        break;
                    }
                }
                int sat = 0;
                int cnt = 0;
                for (int e = 0; e < 4; e++)
                {
                    int nr = r + dr[e];
                    int nc = c + dc[e];

                    if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
                    for (int d = 1; d <= 4; d++)
                    {
                        if (classroom[nr][nc] == ditto[stuorder][d])
                            cnt++;
                    }
                }
                // 만족도 배정
                if (cnt == 1)
                    sat = 1;
                else if (cnt == 2)
                    sat = 10;
                else if (cnt == 3)
                    sat = 100;
                else if (cnt == 4)
                    sat = 1000;
                ans += sat;
            }
        }
        System.out.println(ans);
    }
}