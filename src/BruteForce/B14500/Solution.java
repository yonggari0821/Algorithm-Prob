package BruteForce.B14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node // 한 점 int N x M grid
{
    private int x;
    private int y;

    public Node (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}
}

public class Solution {

    public static int[][] numgrid;
    public static boolean[][] checked;

    public static int N, M;
    public static int ans;
    public static int[] dx  = {-1, 1, 0, 0};
    public static int[] dy  = {0, 0, -1, 1};
    public static int[] axy0 = {0, -1, -1, 0, 1, 0}; // ㅗ
    public static int[] axy1 = {0, -1, 1, 0, 0, 1}; // ㅏ
    public static int[] axy2 = {-1, 0, 0, 1, 1, 0}; // ㅜ
    public static int[] axy3 = {0, -1, -1, 0, 0, 1}; // ㅓ

    public static int[][] axy = {axy0, axy1, axy2, axy3};
    public static Stack<Node> poliomino; // 한 점에서 출발해서 만들 수 있는 poliomino 모양을 위한 stack


    public static void Tetromino (Node start, int picked)
    {
        // 경우 1) 처음 고른 것일 때만 : ax, ay 구해서 4가지 조합(ㅗ ㅏ ㅜ ㅓ) 중에 가능한 것들 체크
        // 경우 2 - 1) 처음~3개까지 골랐을 때 (picked < 3): nx, ny 구해서 갯수 4개 될 때까지 상하좌우 것들 리스트에 넣었다가
        // 경우 2 - 2) 갯수 4개 되면(picked == 3 이면)참고해서 값 리뉴얼해주고 마지막 Node 방문 초기화 및 스택에서 빼기

        int startx = start.getX();
        int starty = start.getY();

        checked[startx][starty] = true; // 해당 Node 위치 방문처리
        poliomino.push(new Node(startx, starty)); // 해당 Node 스택에 쌓기

        // 경우 2 마무리
        if (picked == 3) // 4개째 골랐으면
        {
            int polioVal2 = 0; // 경우 2 제외 나머지 모양 중 한개에 해당하는 임시 값
            for (int i = 0; i <= 3; i++)
            {
                // 스택에 쌓인 4개 Node 위치의 값들 더해줌
                int ansx = poliomino.get(i).getX();
                int ansy = poliomino.get(i).getY();
                polioVal2 += numgrid[ansx][ansy];
            }
            ans = ans > polioVal2 ? ans : polioVal2; // 더한 값과 그 전까지의 최댓값을 비교해서 더 크면 리뉴얼
            checked[startx][starty] = false; // 해당 Node 위치 방문 초기화
            poliomino.pop(); // 해당 Node 스택에서 빼기
            return ;
        }

        // 경우 1
        if (picked == 0)
        {
            for (int a = 0; a < 4; a++)
            {
                int isPolio = 0; // 폴리오 테트리스가 될 수 있는 지 여부 체크 위한 변수
                int polioVal1 = numgrid[startx][starty]; //  폴리오 테트리스 됐을 때의 임시 값
                for (int b = 0; b < 3; b++)
                {
                    int ax = startx + axy[a][2*b];
                    int ay = starty + axy[a][2*b + 1];
                    if (ax < 0 || ay < 0 || ax >= N || ay >= M)
                        continue;
                    polioVal1 += numgrid[ax][ay];
                    isPolio++;
                }
                if (isPolio == 3)
                    ans = ans > polioVal1 ? ans : polioVal1;
            }
        }

        // 경우 2
        for (int n = 0; n < 4; n++)
        {
            int nx = startx + dx[n];
            int ny = starty + dy[n];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) // 오류 방지 위해 범위 벗어나는 것부터 check
                continue;
            if (checked[nx][ny] == true) // 범위 벗어나지 않아도 중복되는 것인지 check
                continue;
            // 둘 다 통과한 애들(=범위 내의 처음 보는 Node들)만 DFS로 picked == 3 될 때까지 DFS
            Tetromino(new Node(nx, ny), picked + 1);
        }

        // 모든 과정이 끝나면 해당 Node에 관한 것들 초기화!
        checked[startx][starty] = false;
        poliomino.pop();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numgrid = new int[N][M];
        checked = new boolean[N][M];
        poliomino = new Stack<>();
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                numgrid[i][j] = Integer.parseInt(sta.nextToken());
        }

        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < M; c++)
                Tetromino(new Node(r,c), 0);
        }

        System.out.println(ans);
    }
}
