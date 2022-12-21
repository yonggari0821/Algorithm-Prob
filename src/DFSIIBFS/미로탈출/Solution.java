package 미로탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node // 각각의 칸을 하나의 클래스로 생각하기위해 Node라는 클래스를 정의
{
    private int x;
    private int y;

    public Node (int x, int y) // x좌표와 y좌표를 받으면 하나의 Node가 생기는 것!
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
}

public class Solution {

    public static int N, M;
    public static int[][] graph = new int[500][500];

    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};
    // 이 둘을 조합해서 nx, ny를 설정하고 그 nx와 ny를 가지고 범위 판단 후에 상하좌우 이동!

    public static int BFS(int x, int y)
    {
        Queue<Node> q= new LinkedList<>();
        q.offer(new Node(x, y)); // 매개변수로 받은 x와 y좌표로 시작점인 Node 하나 생성
        // 그리고 생성한 노드는 큐에 넣기!

        while (!q.isEmpty())
        {
            Node node = q.poll();
            x = node.getX();
            y = node.getY();

            for (int i = 0; i < 4; i++)
            {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (graph[nx][ny] == 0)
                    continue;
                if (graph[nx][ny] == 1) // 미방문 + 괴물이 없는 경우에만!
                {
                    graph[nx][ny] = graph[x][y] + 1;
                    q.offer(new Node(nx, ny)); // while 문이 계속되게(경로가 계속 이어지게) 다음 노드 넣어줌.
                }
            }
        }
        return graph[N-1][M-1];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int n = 0;
        while (n < N)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = 0;
            while(m < M)
            {
                int a = Integer.parseInt(st.nextToken());
                graph[n][m] = a;
                m++;
            }
            n++;
        }
        System.out.println(BFS(0,0) - 1);
        br.close();
    }
}
