import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[][] ditto;
    public static int[][] classroom;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        classroom = new int[N + 1][N + 1];
        ditto = new int[N * N + 1][5];

        for (int i = 1; i <= N * N; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int a = 0; a <= 4; a++)
                ditto[i][a] = Integer.parseInt(st.nextToken());
        }
        // 맞게 들어갔나 확인
        // System.out.println(Arrays.deepToString(ditto));

        classroom[2][2] = ditto[1][0];
        for (int i = 2; i <= N * N; i++)
        {
            int priorR = N;
            int priorC = N;
            int maxcnt = -1;
            int priorZ = -1;
            for (int r = 1; r <= N; r++)
            {
                for (int c = 1; c <= N; c++)
                {
                    if (classroom[r][c] != 0)
                        continue;
                    int cnt = 0;
                    int zcnt = 0;
                    for (int e = 0; e < 4; e++)
                    {
                        int nr = r + dr[e];
                        int nc = c + dc[e];

                        if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
                        for (int d = 1; d <= 4; d++)
                        {
                            if (classroom[nr][nc] == ditto[i][d])
                                cnt++;
                        }
                        if (classroom[nr][nc] == 0)
                            zcnt++;
                    }

                    if (maxcnt < cnt)
                    {
                        maxcnt = cnt;
                        priorR = r;
                        priorC = c;
                        priorZ = zcnt;
                    }
                    else if (maxcnt == cnt)
                    {
                        if (priorZ < zcnt)
                        {
                            priorR = r;
                            priorC = c;
                            priorZ = zcnt;
                        }
                        else if (priorZ == zcnt)
                        {
                            if (priorR > r)
                            {
                                priorR = r;
                                priorC = c;
                            }
                            else if (priorR == r)
                            {
                                if (priorC > c)
                                    priorC = c;
                            }
                        }
                    }
                }
            }
            classroom[priorR][priorC] = ditto[i][0];
        }

        //System.out.println(Arrays.deepToString(classroom));

        int ans = 0;
        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
            {
                int stunum = classroom[r][c];
                int stuorder = 0;
                for (int t = 1; t <= N * N; t++)
                {
                    if (stunum == ditto[t][0])
                    {
                        stuorder = t;
                        break;
                    }
                }
                int satisfaction = 0;
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
                if (cnt == 1)
                    satisfaction = 1;
                else if (cnt == 2)
                    satisfaction = 10;
                else if (cnt == 3)
                    satisfaction = 100;
                else if (cnt == 4)
                    satisfaction = 1000;
                ans += satisfaction;
            }
        }
        System.out.println(ans);
    }
}
