import java.util.*;
/*
    모든 지역 유일 번호
    지역 간 길이 모두 1
    최단 시간으로 부대 복귀
    임무 시작때와 다르게 되돌아오는 경로가 없어지는 경우도 있음
    복귀 불가능한 경우 -1
    
    sources 배열에 있는 지역으로부터 roads 배열을 참고해서 destination까지 가는 최단 거리를 result 배열에 넣어서 반환하기
    roads 배열 => 인접 리스트 형태로 바꾸고
    최단 거리 구해야 하므로 bfs기반 다익스트라 활용
*/

class Node {
    int val;
    int cnt;
    
    Node (int val, int cnt){
        this.val = val;
        this.cnt = cnt;
    }
}

class Solution {
    
    private static int min = Integer.MAX_VALUE;
    private static List<Integer>[] graph;
    private static int[] sources;
    private static boolean[] visited;
    private static int destination;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 초기화
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) answer[i] = -1;
        graph = new ArrayList[n + 1];
        this.sources = sources;
        this.destination = destination;
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<Integer>();
        for (int r = 0; r < roads.length; r++) {
            int from = roads[r][0];
            int to = roads[r][1];
            graph[from].add(to);
            graph[to].add(from);
        }
        
        for (int i = 0; i < sources.length; i++) {
            int cnt = 0;
            Queue<Node> q = new LinkedList<>();
            visited = new boolean[n + 1];
            q.offer(new Node(sources[i], 0));
            visited[sources[i]] = true;
            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur.val == destination) {
                    answer[i] = cur.cnt;
                    break;
                }
                for (int j = 0; j < graph[cur.val].size(); j++) {
                    int next = graph[cur.val].get(j);
                    if (visited[next]) continue;
                    visited[next] = true;
                    if (next == destination) {
                        answer[i] = cur.cnt + 1;
                        q.clear();
                        break;
                    }
                    q.offer(new Node(next, cur.cnt + 1));
                }
            }
            
            answer[i] = answer[i] == -1 ? -1 : answer[i];
        }
        
        return answer;
    }
}