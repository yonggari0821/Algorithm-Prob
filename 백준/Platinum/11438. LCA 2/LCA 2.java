import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] depth;
    static boolean[] visited;
    static ArrayList<Integer>[] nums;
    static int[][] DP;
    // DP[K][V] = DP[K-1][DP[K-1][V]];
    static StringBuilder ans;

    static void dfs(int current, int dep)
    {
        int dest;
        visited[current] = true;
        depth[current] = dep;
        for (int i = 0; i < nums[current].size(); i++)
        {
            dest = nums[current].get(i);
            if (!visited[dest])
            {
                DP[0][dest] = current;
                dfs(dest, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        depth = new int[N+1];
        visited= new boolean[N+1];
        nums = new ArrayList[N+1];
        for (int i = 0; i <= N; i++)
            nums[i] = new ArrayList<>();
        int S = (int) (Math.ceil((Math.log10(N) / Math.log10(2)))); // 총 깊이 = log N ~ log N + 1;
        DP = new int[S+1][N+1];
        ans = new StringBuilder();

        for (int n = 1; n <= N-1 ; n++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nums[a].add(b);
            nums[b].add(a);
        }

        dfs(1,0); // 왼쪽 값은 루트 노드가 될 노드 번호 // 오른쪽 값은 루트 노드의 깊이

        // 1 ~ 깊이 // 0 ~ 전체 수 // 최소 공통 조상 DP 점화식
        for (int i = 1; i <= S; i++)
        {
            for (int j = 0; j <= N ; j++)
            {
                int tmp = DP[i-1][j];
                DP[i][j] = DP[i-1][tmp];
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int m = 1; m <= M ; m++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int deep = depth[a] >= depth[b] ? a : b; // 더 깊은 값
            int shallow = depth[a] >= depth[b] ? b : a; // 더 얕은 값
            // deep == shallow 이면 오류!!
            // 두 깊이가 같으면 먼저 들어온 값이 deep

            if (depth[deep] != depth[shallow]) // 두 정점의 깊이가 다르면
            {
                while (depth[deep] != depth[shallow])
                    deep = DP[(int)(Math.log10(depth[deep] - depth[shallow]) / Math.log10(2))][deep];
            }
            // 두 정점의 깊이가 같아진 상태
            if (deep != shallow) // 깊이가 같으면
            {
                int dg = (int)(Math.log10(depth[deep]) / Math.log10(2)); // log 두 정점의 깊이 => 여기서 지수의 값
                while(dg >= 0) // 따라서 지수가 0일 때까지 봐야함! (2의 0승 == 1)
                {
                    if (DP[dg][deep] != DP[dg][shallow]) // 조상이 다르면 => 공통 조상이 아닌 곳까지 내려왔음!
                    {
                        // 거기까진 올려놔도 무방함
                        deep = DP[dg][deep]; // deep 올리기
                        shallow = DP[dg][shallow]; // shallow 올리기
                        dg = (int)(Math.log10(depth[deep]) / Math.log10(2));
                    }
                    else
                        dg--; // 조상이 같다 = 지수를 더 내려봐도 된다.
                }
                // 최종적으로는 최소 공통 조상 아래에 위치한 상태이므로 그 바로 위 조상 == 최소 공통 조상
                ans.append(DP[0][deep]).append('\n');
            }
            else
                ans.append(deep).append('\n');
        }
        System.out.println(ans.toString());
    }
}