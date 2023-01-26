package SamsungAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class dice // 주사위
{
    int top; // 윗 면
    int right; // 오른쪽 면
    int left; // 왼쪽 면
    int front; // 앞 면
    int back; // 뒷 면
    int bottom; // 아랫 면

    public dice (int top, int right, int left, int front, int back, int bottom)
    {
        this.top = top;
        this.right = right;
        this.left = left;
        this.front = front;
        this.back = back;
        this.bottom = bottom;
    }

}

public class B14499 {
    public static int N, M, x, y, K;
    public static int[][] map; // 지도
    public static int[] move; // 주사위 굴리는 방향
    public static int[] dx = {0, 0, 0, -1, 1}; // 동 서 북 남
    public static int[] dy = {0, 1, -1, 0, 0};
    static void to1(dice d) // 동
    {
        int tmp = d.bottom;
        d.bottom = d.right;
        d.right = d.top;
        d.top = d.left;
        d.left = tmp;
    }
    static void to2(dice d) // 서
    {
        int tmp = d.bottom;
        d.bottom = d.left;
        d.left = d.top;
        d.top = d.right;
        d.right = tmp;
    }
    static void to3(dice d) // 북
    {
        int tmp = d.bottom;
        d.bottom = d.back;
        d.back = d.top;
        d.top = d.front;
        d.front = tmp;
    }
    static void to4(dice d) // 남
    {
        int tmp = d.bottom;
        d.bottom = d.front;
        d.front = d.top;
        d.top = d.back;
        d.back = tmp;
    }
    static void toWhere(int dir, dice d) // 주사위 굴리기
    {
        if (dir == 1)
            to1(d);
        else if (dir == 2)
            to2(d);
        else if (dir == 3)
            to3(d);
        else if (dir == 4)
            to4(d);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        move = new int[K];
        // System.out.println("\n" + N + " " + M + " " + x + " " + y + " " + K);

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                map[n][m] = Integer.parseInt(sta.nextToken());
        }

        StringTokenizer stb = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++)
            move[k] = Integer.parseInt(stb.nextToken());

        dice dd = new dice(0,0,0,0,0, 0); // 주사위 생성

        for (int k = 0; k < K; k++)
        {
            int dir = move[k]; // 굴리는 방향 가져와서
            // 주사위 위치 바꿔주고
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // System.out.println("[" + dir + "] " + nx + " " + ny);
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 범위 벗어난 것들은 제외
            // 안 벗어났으면
            toWhere(dir, dd); // 주사위 굴려서 각 면들 바꿔주고
            // System.out.println(dd.top + " " + dd.right + " " + dd.left + " " + dd.front + " " + dd.back + " " + dd.bottom);
            // 새로운 위치의 값이 0인지 여부에 따라서 처리
            if (map[nx][ny] == 0)
                map[nx][ny] = dd.bottom;
            else
            {
                dd.bottom = map[nx][ny];
                map[nx][ny] = 0;
            }
            // 이동 확정
            x = nx;
            y = ny;
            sb.append(dd.top).append("\n"); // 윗면의 값 저장해두기
        }
        System.out.println(sb.toString());
    }
}