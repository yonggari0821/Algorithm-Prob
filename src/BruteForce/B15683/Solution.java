package BruteForce.B15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node // 각 점
{
    private int r; // 행
    private int c; // 열

    public Node (int r, int c) // 생성자
    {
        this.r = r;
        this.c = c;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Solution {

    public static int N, M, K; // N = 행 길이 // M = 열 길이 // K = cctv 총 갯수
    public static int[][] office; // 사무실 배열
    public static ArrayList<Node> CCTV; // CCTV 위치 Node 저장할 리스트

    // 모델별 가짓수
    // 1) 4 가지 ↑ ↓ ← →
    // 2) 2 가지 ↕ ↔
    // 3) 4 가지 └ ┌ ┐ ┘
    // 4) 4 가지 ㅗ ㅏ ㅜ ㅓ
    // 5) 1 가지 +

    // 위 방향 r : -1 // c : 0
    // 아래 방향 r : 1 // c : 0
    // 왼쪽 방향 r : 0 // c : -1
    // 오른쪽 방향 r : 0 // c : 1

    public static int[][] drc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // model1용

    public static int[][] drc3 = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};  // model3용
    public static int[][][] drc4 ={ {{0, -1},{-1, 0},{0,1}} , {{-1, 0},{0, 1},{1, 0}} , {{0, -1},{1,0},{0,1}} , {{1, 0},{0, -1},{-1, 0}} };  // model4용
    public static int ans; // 최종 답 (사각지대 최소값)

    public static void supervise(int k) // cctv 돌려서 배열의 값을 바꿔주고 cctv 갯수만큼 다 봤으면 최소값 리뉴얼 해주고 배열 리뉴얼 해주는 방식
    {
        if (k == K) // cctv 갯수 만큼 감시했으면 // k = 0으로 시작하므로 cctv가 하나도 없어 K가 0이어도 상관 X
        {
            int cnt = 0; // 사각지대 확인용 임시 값
            for (int a = 0; a < N; a++)
            {
                for (int b = 0; b < M; b++)
                {
                    if (office[a][b] == 0)
                        cnt++;
                }
            }
            ans = ans < cnt ? ans : cnt; // 기존까지의 최소값과 비교하여 사각지대 최소값 리뉴얼
            return ;
        }

        int[][] tmpoffice = new int[N][M]; // 감시 후에 같은 모델 다른 방향 감시를 try 하려고 할 때 감시상태 초기화 위한 임시 배열
        // 기본형을 담은 1차원 배열만 값이 복사되기 때문에 2차원 이상의 경우 1차원배열 하나씩 clone하며 기존 배열값 복사해두기!!
        for (int i = 0; i < tmpoffice.length; i++)
            tmpoffice[i] = office[i].clone();

        if (k < K) // cctv 총 갯수 만큼 감시하기 전까지
        {
            Node cctv = CCTV.get(k); // k번째 cctv
            int r = cctv.getR();
            int c = cctv.getC();
            int model = office[r][c]; // 해당 cctv의 모델 확인

            if (model == 1) // 1) 4 가지 ↑ ↓ ← →
            {
                for (int i = 0; i < 4; i++)
                {
                    int nr = r + drc[i][0];
                    int nc = c + drc[i][1];
                    while (nr >= 0 && nc >= 0 && nr < N && nc < M && office[nr][nc] < 6)
                    {
                        if (office[nr][nc] == 0)
                            office[nr][nc] = office[r][c];
                        nr += drc[i][0];
                        nc += drc[i][1];
                    }
                    supervise(k+1);
                    // 감시상태 초기화
                    for (int a = 0; a < office.length; a++)
                        office[a] = tmpoffice[a].clone();
                }
            }
            // 2) 2 가지 ↕ ↔
            else if (model == 2)
            {
                // ↕
                int nr1 = r + drc[0][0];
                int nr2 = r + drc[1][0];
                while (nr1 >= 0 && nr1 < N &&  office[nr1][c] < 6)
                {
                    if (office[nr1][c] == 0)
                        office[nr1][c] = office[r][c];
                    nr1 += drc[0][0];
                }
                while (nr2 >= 0 && nr2 < N &&  office[nr2][c] < 6)
                {
                    if (office[nr2][c] == 0)
                        office[nr2][c] = office[r][c];
                    nr2 += drc[1][0];
                }
                supervise(k+1); // 감시상태 초기화
                for (int i = 0; i < office.length; i++)
                    office[i] = tmpoffice[i].clone();
                // ↔
                int nc1 = c + drc[2][1];
                int nc2 = c + drc[3][1];
                while (nc1 >= 0 && nc1 < M && office[r][nc1] < 6)
                {
                    if (office[r][nc1] == 0)
                        office[r][nc1] = office[r][c];
                    nc1 += drc[2][1];
                }
                while (nc2 >= 0 && nc2 < M && office[r][nc2] < 6)
                {
                    if (office[r][nc2] == 0)
                        office[r][nc2] = office[r][c];
                    nc2 += drc[3][1];
                }
                supervise(k+1);
                // 감시상태 초기화
                for (int i = 0; i < office.length; i++)
                    office[i] = tmpoffice[i].clone();
            }
            // 3) 4 가지 └ ┌ ┐ ┘
            else if (model == 3)
            {
                for (int i = 0; i < 4; i++)
                {
                    int nr = r + drc3[i][0];
                    int nc = c + drc3[i][1];
                    while (nr >= 0 && nr < N && office[nr][c] < 6)
                    {
                        if (office[nr][c] == 0)
                            office[nr][c] = office[r][c];
                        nr += drc3[i][0];
                    }
                    while (nc >= 0 && nc < M && office[r][nc] < 6)
                    {
                        if (office[r][nc] == 0)
                            office[r][nc] = office[r][c];
                        nc += drc3[i][1];
                    }
                    supervise(k+1);
                    // 감시상태 초기화
                    for (int a = 0; a < office.length; a++)
                        office[a] = tmpoffice[a].clone();
                }
            }
            // 4) 4 가지 ㅗ ㅏ ㅜ ㅓ
            else if (model == 4)
            {
                for (int i = 0; i < 4; i++)
                {
                    int nr1 = r + drc4[i][0][0];
                    int nc1 = c + drc4[i][0][1];
                    while (nr1 >= 0 && nc1 >= 0 && nr1 < N && nc1 < M && office[nr1][nc1] < 6)
                    {
                        if (office[nr1][nc1] == 0)
                            office[nr1][nc1] = office[r][c];
                        nr1 += drc4[i][0][0];
                        nc1 += drc4[i][0][1];
                    }
                    int nr2 = r + drc4[i][1][0];
                    int nc2 = c + drc4[i][1][1];
                    while (nr2 >= 0 && nc2 >= 0 && nr2 < N && nc2 < M && office[nr2][nc2] < 6)
                    {
                        if (office[nr2][nc2] == 0)
                            office[nr2][nc2] = office[r][c];
                        nr2 += drc4[i][1][0];
                        nc2 += drc4[i][1][1];
                    }
                    int nr3 = r + drc4[i][2][0];
                    int nc3 = c + drc4[i][2][1];
                    while (nr3 >= 0 && nc3 >= 0 && nr3 < N && nc3 < M && office[nr3][nc3] < 6)
                    {
                        if (office[nr3][nc3] == 0)
                            office[nr3][nc3] = office[r][c];
                        nr3 += drc4[i][2][0];
                        nc3 += drc4[i][2][1];
                    }
                    supervise(k+1);
                    // 감시상태 초기화
                    for (int a = 0; a < office.length; a++)
                        office[a] = tmpoffice[a].clone();
                }
            }
            // 5) 1 가지 +
            else // (model == 5)
            {
                for (int i = 0; i < 4; i++)
                {
                    int nr = r + drc[i][0];
                    int nc = c + drc[i][1];
                    while (nr >= 0 && nc >= 0 && nr < N && nc < M && office[nr][nc] < 6)
                    {
                        if (office[nr][nc] == 0)
                            office[nr][nc] = office[r][c];
                        nr += drc[i][0];
                        nc += drc[i][1];
                    }
                }
                supervise(k+1);
                // 감시상태 초기화
                for (int a = 0; a < office.length; a++)
                    office[a] = tmpoffice[a].clone();
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = 0;
        office = new int[N][M];
        CCTV = new ArrayList<>();
        ans = Integer.MAX_VALUE;

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
            {
                office[n][m] = Integer.parseInt(sta.nextToken());
                if (office[n][m] > 0 && office[n][m] < 6) // 벽이나 빈공간이 아니라 cctv라면
                {
                    CCTV.add(new Node(n, m));
                    K++; // cctv 총 갯수++
                }
            }
        }

        supervise(0); // 감시 시작
        System.out.println(ans); // 감시 후의 ans값 출력
    }
}
