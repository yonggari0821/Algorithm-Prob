import java.io.*;
import java.util.*;

/*
[풀이]
모든 건물을 짓기 위한 최소 시간 구하기
단, 건물들 간 선행 조건이 존재
선행조건 == 간선
건물들 == 정점
=> 위상정렬
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++)
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물의 갯수 (정점의 갯수)
            int K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙의 갯수 (간선의 갯수)
            // 건물당 짓는 데 걸리는 시간 받아두기
            int[] buildingTime = new int[N + 1];
            // 진입차수 배열
            int[] indegrees = new int[N + 1];
            // 해당 건물 짓기 전 까지의 총 걸리는 시간
            int[] DP = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int building = 1; building <= N; building++) {
                buildingTime[building] = Integer.parseInt(st.nextToken());
            }
            // 건물의 선행 조건 받아두기 => 유향 비순환 그래프
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) graph.add(new ArrayList<Integer>());
            for (int i = 0; i < K; i++)
            {
                st = new StringTokenizer(br.readLine());
                int before = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                indegrees[next]++; // 진입차수 ++
                graph.get(before).add(next);
            }
            // 승리하려면 지어야 할 건물의 번호
            int W = Integer.parseInt(br.readLine());
            // 위상정렬 하면서 DP값 리뉴얼
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++)
            {
                // 진입차수가 0인 애들부터 큐에 넣어주기
                if (indegrees[i] == 0) q.offer(i);
            }
            while (!q.isEmpty())
            {
                int cur = q.poll();
                for (int next : graph.get(cur))
                {
                    indegrees[next]--;
                    DP[next] = Math.max((DP[cur] + buildingTime[cur]), DP[next]);
                    if (indegrees[next] == 0) q.offer(next);
                }
            }
            ans.append(DP[W] + buildingTime[W]).append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}