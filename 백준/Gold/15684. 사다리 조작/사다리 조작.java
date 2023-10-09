import java.io.*;
import java.util.*;

/*
풀이
boolean 2차원 배열로 사다리 배열 만들고'
(1, 1)부터 (M, 1)까지
(1, 2)부터 (M, 2)까지
.
.
.
(1, N)부터 (M, N)까지
가야하며, 그 때 가로선의 최소 갯수를 구해서 그것이 3 이상 또는 불가능한 경우에 -1을 출력하는 문제

내려가는 놈의 위치는 변수 2개(행값, 열값)으로 이동
이 때, 사다리는 자신의 왼쪽에 놓아서 왼쪽으로 가거나 / 그냥 내려가거나 / 오른쪽에 놓아서 오른쪽으로 가거나
왼쪽 오른쪽 내려가려고 할 때는 범위 체크 및 연속 안됨 체크 필수
마지막행(M) 까지가면 그 때까지 늘린 사다리의 수를 체크해서 min값을 반환 (애초에 min값은 Integer.MAX_VALUE로
추가해야 하는 가로선 갯수)
 */

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int minHorizontalLine = Integer.MAX_VALUE;

    static void func(int n, int r, int c, int horizontalLines)
    {
        if (horizontalLines > 3) return ;
//        System.out.println("( " + r + ", " + c + " ) with " + horizontalLines);
        if (n == N && r == H + 1)
        {
            if (c == N) minHorizontalLine = Math.min(minHorizontalLine, horizontalLines);
            return ;
        }
        else if (n != N && r == H + 1)
        {
//            System.out.println("end of " + n + " c == " + c);
            if (c == n) {
//                System.out.println("start of " + (n + 1));
                func(n + 1, 1, n + 1, horizontalLines);
            }
            return ;
        }
        // 이미 오른쪽으로 이어진 사다리가 있으면 가기
        if (ladder[r][c]) func(n, r + 1, c + 1, horizontalLines);
        // 이미 왼쪽으로 이어진 사다리가 있으면 가기
        else if (c > 1 && ladder[r][c - 1]) func(n, r + 1, c - 1, horizontalLines);
        // 둘 다 없으면 사다리 놓을 수 있는 지 검사해보고 사다리 놓고 가거나, 그냥 가기
        else
        {
            if ( c > 1 && !ladder[r][c - 1] )
            {
                if ( c > 2 )
                {
                    if (!ladder[r][c - 2]) {
                        ladder[r][c - 1] = true;
                        func(n, r + 1, c - 1, horizontalLines + 1);
                        ladder[r][c - 1] = false;
                    }
                }
                else
                {
                    ladder[r][c - 1] = true;
                    func(n, r + 1, c - 1, horizontalLines + 1);
                    ladder[r][c - 1] = false;
                }
            }
            // 가운데
//            System.out.println("그대로");
            func(n, r + 1, c, horizontalLines);
            // 오른쪽
            if ( c < N && !ladder[r][c] )
            {
                if ( c == N - 1 )
                {
                    ladder[r][c] = true;
                    func(n, r + 1, c + 1, horizontalLines + 1);
                    ladder[r][c] = false;
                }
                else
                {
                    if (!ladder[r][c + 1]) {
                        ladder[r][c] = true;
                        func(n, r + 1, c + 1, horizontalLines + 1);
                        ladder[r][c] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[H + 1][N + 1];
        for (int m = 0; m < M; m++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true; // 사다리 표시
        }
        if (M == 0)
        {
            System.out.println(0);
            return;
        }
        func(1, 1, 1, 0);
        if (minHorizontalLine > 3) minHorizontalLine = -1;
        ans.append(minHorizontalLine);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}