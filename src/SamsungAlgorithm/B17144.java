package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17144 {

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

        boolean ac = false; // 공기청정기 윗 부분, 아랫 부분 분별해서 받기 위해서
        int uacr = 0; // 윗 부분
        int dacr = 0; // 아랫 부분

        for (int r = 1; r <= R; r++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; c++)
            {
                room[r][c] = Integer.parseInt(sta.nextToken());
                if (room[r][c] == -1) // 공기청정기 등장
                {
                    if (ac == false) // 윗 부분
                    {
                        ac = true;
                        uacr = r;
                    }
                    else // 아랫 부분
                        dacr = r;
                }
            }
        }

        int t = 0; // 경과 시간
        while (t < T)
        {
            int[][] diffusion = new int[R+1][C+1]; // 확산이 동시에 일어나게 만들기 위한 또 다른 배열
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

                            if (nr < 1 || nc < 1 || nr > R || nc > C) continue; // 범위 out
                            if (nc == 1 && (nr == uacr || nr == dacr)) continue; // 공기청정기와 만남
                            diffusion[nr][nc] += diff; // 확산 배열에 확산시킬 값 저장
                            cnt++; // 확산하는 공간 갯수++
                        }
                        diffusion[r][c] -= (cnt * diff); // 확산 시킨 공간 갯수 * (원래 미세먼지량 / 5)만큼 확산 배열 자기 자리에 저장
                    }
                }
            }

            // 확산 by 두 배열의 합
            for (int r = 1; r <= R; r++)
            {
                for (int c = 1; c <= C; c++)
                {
                    if (diffusion[r][c] == 0) continue;
                    if (c == 1 && (r == uacr || r == dacr)) continue;
                    room[r][c] += diffusion[r][c];
                }
            }

            // 공기청정 윗 부분
            for (int r = uacr - 1; r > 1; r--)
                room[r][1] = room[r-1][1];
            for (int c = 1; c < C; c++)
                room[1][c] = room[1][c+1];
            for (int r = 1; r < uacr; r++)
                room[r][C] = room[r+1][C];
            for (int c = C; c > 2; c--)
                room[uacr][c] = room[uacr][c-1];
            room[uacr][2] = 0;
            // 공기청정 아랫 부분
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
        // 미세먼지 량 구하기
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