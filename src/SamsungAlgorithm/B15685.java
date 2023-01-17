package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15685 {

    public static int N, ans; // N 드래곤 커브 갯수 // ans 최종 답
    public static int[][] DragonCurb; // 드래곤 커브 관련 값들 저장
    public static boolean[][] Frame = new boolean[101][101]; // 격자 배열
    public static int[] dy = {0, -1, 0, 1}; // 방향값 0,1,2,3에 따른 x,y 이동방향
    public static int[] dx = {1, 0, -1, 0};
    public static int[] cy = {0, 1, 1}; // 4 꼭지점 체크용 x, y 이동방향
    public static int[] cx = {1, 0, 1};
    public static int next(int dir) // 방향
    {
        if (dir < 3)
            return dir + 1;
        else
            return 0;
    }

    public static int log(int num) // 로그 2 값 + 1 리턴해주는 함수
    {
        /*
        int cnt = 0;
        while (num > 0)
        {
            num /= 2;
            cnt++;
        }
        return cnt;
        이렇게 해줘도 무방
        */
        if (num == 1)
            return 1;
        else if (num < 4)
            return  2;
        else if (num < 8)
            return  3;
        else if (num < 16)
            return  4;
        else if (num < 32)
            return  5;
        else if (num < 64)
            return  6;
        else if (num < 128)
            return  7;
        else if (num < 256)
            return  8;
        else if (num < 512)
            return  9;
        else
            return  10;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DragonCurb = new int[N+1][4];
        ans = 0;

        for (int n = 1; n <= N; n++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            DragonCurb[n][0] = Integer.parseInt(st.nextToken()); // 0 -> 시작점의 X 좌표
            DragonCurb[n][1] = Integer.parseInt(st.nextToken()); // 1 -> 시작점의 Y 좌표
            DragonCurb[n][2] = Integer.parseInt(st.nextToken()); // 2 -> 시작 방향 - 0, 1, 2, 3
            DragonCurb[n][3] = Integer.parseInt(st.nextToken()); // 3 -> 세대
        }

        for (int n = 1; n <= N; n++)
        {
            int x = DragonCurb[n][0];
            int y = DragonCurb[n][1];
            int dir = DragonCurb[n][2];
            int gen = DragonCurb[n][3];
            int[] dirOrder = new int[(int)Math.pow(2, gen)]; // 2의 세대수 제곱 크기의 배열 for 드래곤 커브 방향
            dirOrder[0] = dir; // 방향 초기값 g 는 항상 0보다 크거나 같으므로 한개는 무조건 있음!!
            for (int g = 1; g < dirOrder.length; g++)
            {
                int lg = log(g);
                int a = (int)(Math.pow(2, lg)) - (g+1);
                // ex) 1번째 방향은 0번째 방향을 next에 대입한 값
                // ex) 2번째 방향은 1번째 방향을 next에 대입한 값
                // ex) 3번째 방향은 0번째 방향을 next에 대입한 값
                // 즉, n 번째 방향은 2의 (n의 로그 2 값 + 1)제곱 값 - n - 1)을 next에 대입한 값
                dirOrder[g] = next(dirOrder[a]);
            }
            //System.out.println(Arrays.toString(dirOrder));
            Frame[y][x] = true; // 시작점 초기값 지난 걸로 처리
            for (int i = 0; i < dirOrder.length; i++)
            {
                int tdir = dirOrder[i];
                int ny = y + dy[tdir];
                int nx = x + dx[tdir];
                Frame[ny][nx] = true;
                y = ny;
                x = nx;
                // y와 x값을 방향에 따라 바꿔가면서 지난 곳으로 처리
            }
        }

        // 모든 세대 드래곤 커브에 따라서 지난 곳 처리 후에
        for (int y = 0; y < 100; y++)
        {
            for (int x = 0; x < 100; x++)
            {
                if (Frame[y][x] == true) // 만약 지난 지점이 있다면 y와 x 값에따라 4 꼭지점 모두 지난 곳인지 체크
                {
                    int tc = 0;
                    for (int i = 0; i < 3; i++)
                    {
                        int uy = y + cy[i];
                        int ux = x + cx[i];
                        if (Frame[uy][ux] == true)
                            tc++;
                    }
                    if (tc == 3) // 4 꼭지점 모두 드래곤 커브가 지나갔다면
                        ans++;
                }
            }
        }
        System.out.println(ans);
    }
}