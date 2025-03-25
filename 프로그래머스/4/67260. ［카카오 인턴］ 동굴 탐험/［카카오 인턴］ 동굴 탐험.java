import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;

        ArrayList<Integer>[] graph = new ArrayList[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
        for (int[] i : path) {
            graph[i[0]].add(i[1]);
            graph[i[1]].add(i[0]);
        }
        // 자식 인덱스에 부모 번호
        for (int[] i : order) parent[ i[1] ] = i[0];

        // i 번째 방문 시 다음 방문 할 점 번호
        int next[] = new int[n];
        boolean visit[] = new boolean[n];
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(0);
        int cnt = 0; // 지나간 점의 갯수
        while (!q.isEmpty()) {
            int cur = q.poll();
            // System.out.println("-----------------");
            // System.out.println(cur + " 도착");
            // 부모가 자기 자신이 아니고(부모가 있고), 아직 부모가 방문 전이면
            if ( cur != parent[cur] && !visit[ parent[cur] ]) {
                // 현재 부모 방문 시에 해금되는 값을 자신으로 설정
                next[ parent[cur] ] = cur;
                // System.out.println(cur + "은 " + parent[cur] + "가 아직 방문 전이라 못 가!");
                // System.out.println("그래서 " + cur + "은 방문 처리 아직 안할거야!");
                continue;
            }
            // 자기 방문 처리 및 갯수 세기
            visit[cur] = true;
            // System.out.println(cur + " 방문 완!");
            // System.out.println("-----------------");
            cnt++;

            // 만약 자신 이후에 확인해야 할 번호가 있다면 큐에 넣어주기
            if (next[cur] != 0) {
                // System.out.println("그러니깐 이제 " + next[cur] + " 방문 해볼까?");
                q.add(next[cur]);
            }
            // 자기 자신에 인접한 애들 중 방문 전인 애들 큐에 넣어주기
            for (int i : graph[cur]) {
                if(!visit[i]) q.add(i);
            }
        }
        answer = cnt == n ? true : false;
        return answer;
    }
}