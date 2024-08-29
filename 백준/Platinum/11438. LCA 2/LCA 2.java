import java.io.*;
import java.util.*;

/*
백준 11438 LCA2

최소 공통 조상을 구하는 문제이며
문제 조건 상 DP를 활용해서 빠르게 구해야 함!

 */



public class Main {

    static int N, M, S;
    static int[] depths;
    static boolean[] visited;
    static ArrayList<Integer>[] nums;
    static int[][] dp;

    // 루트 노드부터 쭉 깊이 입력해두기 위한 함수
    static void dfs (int cur, int depth)
    {
        int next;
        visited[cur] = true; // 현재 노드 방문처리
        depths[cur] = depth; // 현재 노드 깊이 입력
        // 현재 노드에서 연결된 애들에 대해서
        for (int i = 0; i < nums[cur].size(); i++) {
            // 다음 목적지 노드
            next = nums[cur].get(i);
            // 다음 목적지 노드가 방문 전이라면
            if (!visited[next])
            {
                // 다음 목적지 노드의 직속 부모를 현재 노드로 설정하고
                dp[0][next] = cur;
                // 한 depth 더 들어가기
                dfs(next, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = (int) ( Math.ceil( Math.log10(N) / Math.log10(2) ) );
        depths = new int[N + 1]; // 깊이 저장용 배열
        visited = new boolean[N + 1]; // 방문 처리 배열
        nums = new ArrayList[N + 1]; // 트리 그래프의 인접 리스트
        for (int i = 0; i <= N; i++) nums[i] = new ArrayList<>(); // nums 초기화
        dp = new int[S + 1][N + 1]; // dp[k][v] v의 k번째 조상까지 표기하는 배열

        StringTokenizer st;
        for (int i = 1; i <= N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 무향 그래프이므로 양쪽에 추가
            nums[a].add(b);
            nums[b].add(a);
        }

        // 깊이 입력
        dfs(1, 0);

        // k번째 부모까지 미리 입력
        for (int k = 1; k <= S; k++)
        {
            // 1번부터 모든 노드에 대해서
            for (int node = 0; node <= N ; node++)
            {
                int tmp = dp[k-1][node];
                dp[k][node] = dp[k-1][tmp];
            }
        }

        // LCA를 구하는 질문의 갯수
        M = Integer.parseInt(br.readLine());

        for (int m = 1; m <= M ; m++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 위에서 이미 모든 노드의 깊이를 입력해뒀으므로 비교해뒀으므로 가능!
            int deep = depths[a] >= depths[b] ? a : b; // 더 깊은 값
            int shallow = depths[a] >= depths[b] ? b : a; // 더 얕은 값
            // 둘 중 하나에서라도 등호를 빼면 deep == shallow 인 경우에 오류!!
            // 등호가 있어야 두 깊이가 같을 때 자동으로 먼저 들어온 값이 deep이 되고 나중에 들어온 값이 shallow가 됨


            // 두 정점의 깊이가 다르면 같아 질 때 까지 더 깊은 값을 올려주기!
            while (depths[deep] != depths[shallow]) deep = dp[ (int) (Math.log10(depths[deep] - depths[shallow]) / Math.log10(2)) ][ deep ];
            // (int) (Math.log10(depths[deep] - depths[shallow]) / Math.log10(2)) 번째 조상으로 이동해가기


            // 두 정점의 깊이가 같아진 상태 => 아래의 if 문이 시간복잡도 log N의 핵심!!
            if (deep != shallow) // 깊이가 같고 값이 다르면
            {
                int exp = (int)(Math.log10(depths[deep]) / Math.log10(2)); // log 두 정점의 깊이 즉, 지수의 값

                while(exp >= 0) // 따라서 0일 때까지 봐야함! (루트노드가 0!)
                {
                    // 현재 깊이 만큼에서의 조상이 다르면
                    if (dp[exp][deep] != dp[exp][shallow])
                    {
                        // 거기까지 두 정점을 함께 올려놓기
                        deep = dp[exp][deep]; // deep 올리기
                        shallow = dp[exp][shallow]; // shallow 올리기
                        exp = (int)(Math.log10(depths[deep]) / Math.log10(2));
                    }
                    else exp--; // 조상이 같다 = 지수를 더 내려봐도 된다.
                }
                // 최종적으로는 최소 공통 조상 아래에 위치한 상태이므로 그 바로 위 조상 == 최소 공통 조상
                sb.append(dp[0][deep]).append('\n');
            }

            else sb.append(deep).append('\n'); // 같으면 그 정점이 곧 LCA
        }
        System.out.println(sb.toString());
    }
}

/*
[1]
15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15

2
4
2
1
3
1
 */