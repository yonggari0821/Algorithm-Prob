import java.io.*;
import java.util.*;

class Fireball
{
    int m;
    int s;
    int d;

    public Fireball(int m, int s, int d) {
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main {
    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵 크기
        M = Integer.parseInt(st.nextToken()); // 파이어볼 갯수
        K = Integer.parseInt(st.nextToken()); // 마법사의 파이어볼 이동 명령 횟수
        Queue<Fireball>[][] map = new LinkedList[N][N]; // 맵
        for (int r = 0; r < N; r++)
        {
        	for (int c = 0; c < N; c++) map[r][c] = new LinkedList<Fireball>();
        }
        for (int fireball = 0; fireball < M; fireball++)
        {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(new Fireball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int k = 0;
        int[][] fbCnt; // 파이어볼 갯수 배열
        int[][] fbWeight; // 파이어볼 질량 배열
        int[][] fbSpeed; // 파이어볼 속력 배열
        int[][][] DirCntAndVal; // 파이어볼 짝수 방향 갯수 배열
        while (k < K)
        {
            // 명령마다 각종 배열 초기화
            fbCnt = new int[N][N];
            fbWeight = new int[N][N];
            fbSpeed = new int[N][N];
            DirCntAndVal = new int[N][N][3];

            // 파이어볼 배열에서 파이어볼 찾아서 하나씩 이동시키면서 각종 배열에 수치화
            for (int r = 0; r < N; r++)
            {
                for (int c = 0; c < N; c++)
                {
                    while(!map[r][c].isEmpty())
                    {
                        Fireball cur = map[r][c].poll();
                        int nr = (r + dr[cur.d] * cur.s) % N;
                        int nc = (c + dc[cur.d] * cur.s) % N;
                        while(nr < 0) nr += N;
                        while(nc < 0) nc += N;
                        fbCnt[nr][nc]++; // 이동한 위치 갯수 + 1
                        fbWeight[nr][nc] += cur.m; // 이동한 위치 질량 + 1
                        fbSpeed[nr][nc] += cur.s; // 이동한 위치 갯수 + 1
                        if ((cur.d & 1) == 0) DirCntAndVal[nr][nc][0]++; // 이동한 위치 짝수 방향 갯수 + 1
                        else DirCntAndVal[nr][nc][1]++; // 이동한 위치 홀수 방향 갯수 + 1
                        DirCntAndVal[nr][nc][2] += cur.d;
                    }
                }
            }
            // 각종 배열에서 파이어볼 합치거나 삭제하거나 그대로 파이어볼 배열로 넣어주기
            for (int r = 0; r < N; r++)
            {
                for (int c = 0; c < N; c++)
                {
                    // 파이어볼이 1개라도 있으면 다시 파이어볼 배열로 넣어줘야 함!
                    if (fbCnt[r][c] > 0)
                    {
                        if (fbCnt[r][c] == 1) // 1개만 있으면 그대로 넣어주면 됨!
                        {
                            map[r][c].add(new Fireball(fbWeight[r][c], fbSpeed[r][c], DirCntAndVal[r][c][2]));
                        }
                        else // 2개 이상 => 합친 후에 질량과 속력과 방향을 정해서 다시 파이어볼 배열로 넣어주기
                        {
                            int cnt = fbCnt[r][c];
                            int weight = fbWeight[r][c];
                            int speed = fbSpeed[r][c];
                            boolean Even = (DirCntAndVal[r][c][0] == 0 || DirCntAndVal[r][c][1] == 0) ? true : false;
                            weight /= 5;
                            if (weight <= 0) continue;
                            speed /= cnt;
                            if (Even)
                            {
                                for (int dir = 0; dir <= 6; dir+=2)
                                {
                                    map[r][c].add(new Fireball(weight, speed, dir));
                                }
                            }
                            else
                            {
                                for (int dir = 1; dir <= 7; dir+=2)
                                {
                                    map[r][c].add(new Fireball(weight, speed, dir));
                                }
                            }
                        }
                    }
                }
            }
            k++;
        }
        int sum = 0;
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                while(!map[r][c].isEmpty())
                {
                    sum += map[r][c].poll().m;
                }
            }
        }
        ans.append(sum);
    	bw.write(ans.toString());
    	br.close();
    	bw.close();
    }
}