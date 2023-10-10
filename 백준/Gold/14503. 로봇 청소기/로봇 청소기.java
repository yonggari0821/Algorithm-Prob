import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node
{
    private int r;
    private int c;

    public Node (int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getR() {return this.r;}
    public int getC() {return this.c;}
}

public class Main {

    public static int N, M, ans;
    public static int[][] area;
    public static boolean[][] cleaned;
    public static Queue<Node> path;

    public static void cleaning(Node start, int dir)
    {
        path.offer(start); // 시작 점 큐에 넣어주기
        int redir = dir; // 방향
        int rdcnt = 0; // 방향 바꾼 횟수
        while (!path.isEmpty())
        {
            Node tmp = path.poll(); // 현재 위치
            int tr = tmp.getR();
            int tc = tmp.getC();

            if (cleaned[tr][tc] == false && area[tr][tc] == 0) // 아직 청소하지 않았고, 빈 공간이라면 => 청소해줘야 함!
            {
                //System.out.println(tr + " " + tc);
                cleaned[tr][tc] = true; // 청소 했다고 처리
                ans++; // 청소 한 공간 수 ++
                rdcnt = 0; // 방향 바꾼 횟수 초기화
            }

            if (rdcnt == 4) // 4 방향 모두가 이미 청소한 곳이나 벽이라면
            {
                int br = tr, bc = tc; // 뒤쪽의 행과 열 값
                if (redir == 0) // 북쪽(0) 보고 있으면 남쪽(2) 체크
                {
                    br = tr + 1;
                    bc = tc;
                }
                else if (redir == 1) // 동쪽(1) 보고 있으면 서쪽(3) 체크
                {
                    br = tr;
                    bc = tc - 1;
                }
                else if (redir == 2)// 남쪽(2) 보고 있으면 북쪽(0) 체크
                {
                    br = tr - 1;
                    bc = tc;
                }
                else if (redir == 3) // 서쪽(3) 보고 있으면 동쪽(1) 체크
                {
                    br = tr;
                    bc = tc + 1;
                }

                if (area[br][bc] == 1) // 후진 시도 할 위치가 벽이라면 청소 끝!
                    break;
                else
                {
                    rdcnt = 0;
                    path.offer(new Node(br, bc)); // 벽이 아니라면 (즉, 이미 청소는 했지만 빈공간이라면) 방향 유지한 채 그쪽으로 후진
                    continue;
                }
            }

            int nr = tr, nc = tc;
            if (redir == 0) // 북쪽(0) 보고 있으면 서쪽(3) 체크
            {
                nr = tr;
                nc = tc - 1;
                redir = 3;
            }
            else if (redir == 1) // 동쪽(1) 보고 있으면 북쪽(0) 체크
            {
                nr = tr - 1;
                nc = tc;
                redir = 0;
            }
            else if (redir == 2)// 남쪽(2) 보고 있으면 동쪽(1) 체크
            {
                nr = tr;
                nc = tc + 1;
                redir = 1;
            }
            else if (redir == 3) // 서쪽(3) 보고 있으면 남쪽(2) 체크
            {
                nr = tr + 1;
                nc = tc;
                redir = 2;
            }

            if (nr < 1 || nc < 1 || nr >= N - 1 || nc >= M - 1 || area[nr][nc] == 1 || cleaned[nr][nc] == true)
            {
                rdcnt++;
                path.offer(new Node(tr, tc));
            }
            else
            {
                rdcnt = 0;
                path.offer(new Node(nr, nc));
            }
        }
    }
    /*
    1. 왼편 청소 여부 체크 (redir == 0 ? 3 : dir - 1)
    1-1) 안했고 빈공간임 => 청소하고 해당 장소로 이동 (redir 방향으로)
    1-2) 이미했거나 벽임 => 왼쪽으로 회전 후 다시 왼편 체크 (redir == 0 ? 3 : dir - 1)
    1-3) 4 방향 모두 이미 했거나 벽임 => dir 반대로 후진
    1-4) 뒤가 벽이라서 후진도 할 수 없음(area[nr][nc] == 1) => return ;
     */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        area = new int[N][M];
        cleaned = new boolean[N][M];
        path = new LinkedList<>();

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int Rr = Integer.parseInt(st2.nextToken());
        int Rc = Integer.parseInt(st2.nextToken());
        int d = Integer.parseInt(st2.nextToken());

        for (int n = 0; n < N; n++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                area[n][m] = Integer.parseInt(sta.nextToken());
        }

        cleaning(new Node(Rr, Rc), d);
        System.out.println(ans);
    }
}