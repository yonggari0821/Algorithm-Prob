import java.util.*;

/*
    승하차 위치는 오류 없음
    오류를 최소한으로 수정하여 정확한 이동 경로를 구하고 싶음
    
    거점 간 이동 시
    1) 간선이 있어야 하며
    2) 한 거점에 머무를 수 있고
    3) 왔던 길을 되돌아 갈수도 있음
    4) 간선은 별도의 방향이 없는 양방향 도로

    n 거점 갯수 <= 200
    m 간선 갯수 <= 10000
    edge_list[][0] => 점 1
    edge_list[][1] => 점 2
    k 택시가 들렀다는 거점 갯수
    gps_log[i] => i번째에 들른 거점 번호
*/

class Solution {
    
    static final int INF = 555555;
    static int start;
    // static int end;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;
        
        // n의 범위가 최대 200이므로
        // 인접 행렬을 구현해도 부담이 없음!
        boolean[][] edges = new boolean[n + 1][n + 1];
        for (int i = 0; i < edge_list.length; i++) {
            int nodeA = edge_list[i][0];
            int nodeB = edge_list[i][1];
            edges[nodeA][nodeB] = true;
            edges[nodeB][nodeA] = true;
        }
        
        /*
            이제 여기서 위의 인접행렬을 이용해서 구해야 하는 것은
            gps_log의 i번째 거점을 a로 바꿨을 때, i 번째까지 다른 횟수
            dp[i][a]
            이 값은 최소가 되야 하므로 애초에 Integer.MAX_VALUE를 채워두고
            최소값으로 리뉴얼!
        */
        
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            for (int a = 0; a <= n; a++) {
                dp[i][a] = INF;
            }
        }
        
        start = gps_log[0];
        // end = gps_log[k - 1];
        
        // 시작 위치는 다를 수 없으므로 0으로 두고 시작
        dp[0][start] = 0;
        
        for (int i = 1; i < k; i++) {
            for (int a = 1; a <= n; a++) {
                // 이동을 하지 않는 경우에는 틀릴 수가 없으므로 그 전의 값 가져오기
                dp[i][a]
                    = Math.min(dp[i][a], dp[i - 1][a]);
                
                for (int next = 1; next <= n; next++) {
                    // 연결된 곳이라면
                    // 값 리뉴얼
                    if (edges[a][next]) {
                        dp[i][a] = Math.min(dp[i][a], dp[i - 1][next]);
                    }
                }
                
                // 다른 점이라면 ++
                if (a != gps_log[i]) dp[i][a]++;
            }
        }
        
        
        answer 
            = dp[k - 1][gps_log[k - 1]] >= INF
            ? -1 
            : dp[k - 1][gps_log[k - 1]];
        return answer;
    }
}