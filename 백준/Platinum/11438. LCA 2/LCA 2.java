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
        int S = (int)(Math.log10(N) / Math.log10(2));
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

        dfs(1,0);

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

            int deep = depth[a] >= depth[b] ? a : b;
            int shallow = depth[a] >= depth[b] ? b : a;

            if (depth[deep] != depth[shallow]) // 두 정점의 높이가 다르면
            {
                while (depth[deep] > depth[shallow])
                    deep = DP[(int)(Math.log10(depth[deep] - depth[shallow]) / Math.log10(2))][deep];
            }
            // 두 정점의 높이가 같아진 상태
            if (deep != shallow)
            {
                int dg = (int)(Math.log10(depth[deep]) / Math.log10(2));
                while(dg >= 0)
                {
                    if (DP[dg][deep] != DP[dg][shallow])
                    {
                        deep = DP[dg][deep];
                        shallow = DP[dg][shallow];
                        dg = (int)(Math.log10(depth[deep]) / Math.log10(2));
                    }
                    else
                        dg--;
                }
                ans.append(DP[0][deep]).append('\n');
            }
            else
                ans.append(deep).append('\n');
        }
        System.out.println(ans.toString());
    }
}

/*







 */


/*
if (a == 1)
            {
                depth[b] = 1;
                DP[0][b] = 1;
                DP[1][b] = 0;
            }
            else if (b == 1)
            {
                depth[a] = 1;
                DP[0][a] = 1;
                DP[1][a] = 0;
            }
            else
            {
                if (depth[a] != 0)
                {
                    depth[b] = depth[a] + 1;
                    DP[0][b] = a;
                    DP[1][b] = DP[0][a];
                }
                else if (depth[b] != 0)
                {
                    depth[a] = depth[b] + 1;
                    DP[0][a] = b;
                    DP[1][a] = DP[0][b];
                }
            }
 */