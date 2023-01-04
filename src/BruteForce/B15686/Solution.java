package BruteForce.B15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node
{
    private int x;
    private int y;

    Node (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}
}

public class Solution {

    public static int N, M;

    public static int[][] city;

    public static ArrayList<Node> home;
    public static ArrayList<Node> chicken;

    public static int ans;
    public static boolean[] visited;

    public static void DFS(int start, int cnt)
    {
        if (cnt == M)
        {
            int res = 0; // M개의 치킨집으로 구성된 한 조합의 도시 전체의 치킨 거리
            // System.out.println(Arrays.toString(visited));

            for (int i = 0; i < home.size(); i++) // i = 집의 인덱스
            {
                int tmp = Integer.MAX_VALUE; // 각 집에서의 뽑은 치킨집까지의 거리의 최소값 => 집을 기준으로 해야 그 집에서 가장 가까운 치킨집을 찾아서 최소값이 구해짐!

                for (int j = 0; j < chicken.size(); j++) // j = 치킨집의 인덱스
                {
                    if (visited[j]) // 뽑아진 것들만 비교할 것!!)
                    {
                        int dis = Math.abs(home.get(i).getX() - chicken.get(j).getX()) + Math.abs(home.get(i).getY() - chicken.get(j).getY());
                        // 집과 M개 만큼 뽑은 치킨집들과의 거리
                        tmp = (tmp < dis ? tmp : dis);
                    }
                }
                res += tmp;
            }
            ans = ans < res ? ans : res;
            return ;
        }

        for (int i = start; i < chicken.size(); i++)
        {
            visited[i] = true; // i번째 치킨집 뽑기
            DFS(i + 1, cnt + 1); // M개를 뽑을 때 까지 i + 1과 cnt + 1 해주면서 치킨집 뽑기
            // M개를 뽑고 나면 위에 if (cnt == M) 부분에서와 같이
            // 그 치킨집들과의 거리를 집마다 비교해서 최소값을 ans로 리뉴얼
            visited[i] = false; // 한 번 비교한 조합은 피해주기 위해 방문판단 배열값을 다시 false로 처리
            // ex) M = 2이고 chicken.size()가 3이라면 0 1 X / 0 X 2 / X 1 2 식으로
            // 모든 치킨집 조합을 DFS 할 수 있도록!
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++)
        {
            StringTokenizer sta = new StringTokenizer(br.readLine());
             for (int j = 0; j < N; j++)
             {
                 city[i][j] = Integer.parseInt(sta.nextToken());
                 if (city[i][j] == 1)
                     home.add(new Node(i, j));
                 else if (city[i][j] == 2)
                     chicken.add(new Node(i, j));
             }
        }

        ans = Integer.MAX_VALUE;
        visited = new boolean[chicken.size()];

        DFS(0,0);
        System.out.println(ans);
        br.close();
    }
}
