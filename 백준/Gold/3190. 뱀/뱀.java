import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, K, L, ans, ndir;
    public static int[][] board;
    public static int[][] passed;
    public static int[][] direction;
    public static int[][] snake;
    public static int turn(int nowdir, int turndir)
    {
        if (turndir == 0) // L
        {
            if (nowdir > 0)
                nowdir -= 1;
            else
                nowdir = 3;
        }
        else // D
        {
            if (nowdir < 3)
                nowdir += 1;
            else
                nowdir = 0;
        }
        return nowdir;
    }
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        snake = new int[N+1][N+1];
        for (int k = 0; k < K; k++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ar = Integer.parseInt(st.nextToken()); // 행
            int ac = Integer.parseInt(st.nextToken()); // 열
            board[ar][ac] = -1;
        }
        L = Integer.parseInt(br.readLine());
        direction = new int[L][2];
        for (int l = 0; l < L; l++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            direction[l][0] = Integer.parseInt(sta.nextToken()); // 게임 경과 시간(초)
            direction[l][1] = sta.nextToken().charAt(0); // 방향 L - 왼쪽 // D - 오른쪽
        }

        passed = new int[N*N*L+1][2];
        snake[1][1] = 2; // 2 - 머리 1 - 몸
        int hr = 1; // 머리 행
        int hc = 1; // 머리 열
        ndir = 0; // 현재 바라보는 방향 0 1 2 3 순서대로 오른쪽 아래쪽 왼쪽 위쪽
        int time = 0; // 게임 경과 시간
        int last = time; // 꼬리
        passed[time][0] = hr;
        passed[time][1] = hc;
        while(true)
        {
            int nhr = hr + dr[ndir];
            int nhc = hc + dc[ndir];
            if (nhr < 1 || nhc < 1 || nhr > N || nhc > N) break; // 벽에 부딪힌다면
            if (snake[nhr][nhc] > 0) break; // 자신의 몸과 부딪힌다면
            time++;
            if (board[nhr][nhc] == -1) // 사과가 있다면
            {
                board[nhr][nhc] = 0; // 사과 먹고
                snake[nhr][nhc] = 2; // 머리 위치 바꿔주고
                snake[hr][hc] = 1; // 몸통으로 처리
            }
            else
            {
                snake[nhr][nhc] = 2;
                snake[hr][hc] = 1;
                int tr = passed[last][0];
                int tc = passed[last][1];
                snake[tr][tc] = 0;
                last++;
            }
            hr = nhr;
            hc = nhc;
            passed[time][0] = hr;
            passed[time][1] = hc;
            for (int l = 0; l < L; l++)
            {
                if (time == direction[l][0])
                {
                    int tdir;
                    if (direction[l][1] == 'L')
                        tdir = 0;
                    else
                        tdir = 1;
                    ndir = turn(ndir, tdir);
                    break;
                }
            }
        }
        System.out.println(time + 1);
    }
}