import java.util.*;

/*
    어느 등대에서 출발해도 다른 모든 등대까지 이동 가능 => 신장 트리
    그 중 모든 이동 경로에 대해서 from이나 to 중 하나는 켜져 있도록 하는 최소 갯수
    
    
*/



class Solution {

    private static boolean[] visited; // 방문 체크
    private static int[][] nodeStates; // 노드[0]와 상태[1]을 저장(0이면 꺼짐/1이면 켜짐)
    private static ArrayList<Integer>[] graph; // 인접 리스트
    
    private static void dfs(int cur) {
        visited[cur] = true;
        
        nodeStates[cur][0] = 0;
        nodeStates[cur][1] = 1;
        
        for (int i = 0; i < graph[cur].size(); i++) {
            int next = graph[cur].get(i);
            if (visited[next]) continue;
            
            dfs(next);
            
            nodeStates[cur][0] += nodeStates[next][1];
            nodeStates[cur][1] += Math.min(nodeStates[next][0], nodeStates[next][1]);
        }
    }
    
    public int solution(int n, int[][] lighthouse) {
        // 초기화
        visited = new boolean[n + 1];
        nodeStates = new int[n + 1][2];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        
        // 간선 정보로 인접리스트 구성
        for (int r = 0; r < lighthouse.length; r++) {
            int from = lighthouse[r][0];
            int to = lighthouse[r][1];
            graph[from].add(to);
            graph[to].add(from);
        }
        
        dfs(1);
        
        return Math.min(nodeStates[1][0], nodeStates[1][1]);
    }
}