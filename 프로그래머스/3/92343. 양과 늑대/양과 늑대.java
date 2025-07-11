import java.util.*;

class Solution {
    
    private static int ans = 0;
    private static int[] isWolf;
    private static int[][] graph;
    private static void dfs(int cur, boolean[] visited, int sheep, int wolf) {
        visited[cur] = true;
        if (isWolf[cur] == 0) {
            sheep++;
            if (sheep > ans) ans = sheep;
        }
        else {
            wolf++;
        }
        
        if (sheep <= wolf) return;
        
        for (int[] g : graph) {
            if (visited[g[0]] && !visited[g[1]]) {
                boolean[] nextVisited = new boolean[visited.length];
                for (int i = 0; i < visited.length; i++) {
                    nextVisited[i] = visited[i];
                }
                dfs(g[1], nextVisited, sheep, wolf);
            }
        }
    }
    
    
    public int solution(int[] info, int[][] edges) {
        isWolf = info;
        graph = edges;
        boolean[] visited = new boolean[info.length];
        
        dfs(0, visited, 0, 0);
        
        return ans;
    }
}