import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int R, C, T, ans;
    public static int[][] room;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R+1][C+1];

        boolean ac = false;
        int uacr = 0;
        int dacr = 0;

        for (int r = 1; r <= R; r++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int c = 1; c<= C; c++)
            {
                room[r][c] = Integer.parseInt(sta.nextToken());
                if (room[r][c] == -1)
                {
                    if (ac == false)
                    {
                        ac = true;
                        uacr = r;
                    }
                    else
                        dacr = r;
                }
            }
        }

        int t = 0;
        while (t < T)
        {
            int[][] diffusion = new int[R+1][C+1];
            for (int r = 1; r <= R; r++)
            {
                for (int c = 1; c<= C; c++)
                {
                    if (room[r][c] > 0)
                    {
                        int cnt = 0;
                        int diff = room[r][c] / 5;
                        for (int i = 0; i < 4; i++)
                        {
                            int nr = r + dr[i];
                            int nc = c + dc[i];

                            if (nr < 1 || nc < 1 || nr > R || nc > C) continue;
                            if (nc == 1 && (nr == uacr || nr == dacr)) continue;
                            diffusion[nr][nc] += diff;
                            cnt++;
                        }
                        diffusion[r][c] -= (cnt * diff);
                    }
                }
            }

            for (int r = 1; r <= R; r++)
            {
                for (int c = 1; c <= C; c++)
                {
                    if (diffusion[r][c] == 0) continue;
                    if (c == 1 && (r == uacr || r == dacr)) continue;
                    room[r][c] += diffusion[r][c];
                }
            }

            for (int r = uacr - 1; r > 1; r--)
                room[r][1] = room[r-1][1];
            for (int c = 1; c < C; c++)
                room[1][c] = room[1][c+1];
            for (int r = 1; r < uacr; r++)
                room[r][C] = room[r+1][C];
            for (int c = C; c > 2; c--)
                room[uacr][c] = room[uacr][c-1];
            room[uacr][2] = 0;

            for (int r = dacr + 1; r < R; r++)
                room[r][1] = room[r+1][1];
            for (int c = 1; c < C; c++)
                room[R][c] = room[R][c+1];
            for (int r = R; r > dacr; r--)
                room[r][C] = room[r-1][C];
            for (int c = C; c > 2; c--)
                room[dacr][c] = room[dacr][c-1];
            room[dacr][2] = 0;
            t++;
        }

        for (int r = 1; r <= R; r++)
        {
            for (int c = 1; c <= C; c++)
            {
                if (room[r][c] > 0)
                    ans += room[r][c];
            }
        }
        System.out.println(ans);
    }
}