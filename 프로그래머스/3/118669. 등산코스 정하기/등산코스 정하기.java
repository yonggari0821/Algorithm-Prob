import java.util.*;

/*
    1. 출입구에서 출발해서 산봉우리 한 곳만 방문하고, 다시 같은 출입구로 돌아와야 한다.
    => 출입구에서 산봉우리까지의 경로만 찾으면 됨. (왕복 동일하게 최단 경로로 오면 되니깐!)
    => 모든 출입구에서 출발하는 경로를 고려
    
    2. intensity == 해당 코스에서 지나야 하는 등산로 중 가장 긴 시간(가중치)
    => 최소가 되어야 함
    
    3. 여러 개라면 산봉우리 번호가 가장 낮은 것
    => 우선순위 따질 때 고려해야 함
    
    4. 등산로(간선)는 양방향이며, 각 간선마다 시간이 다르다
    => 그래프를 양방향으로 만들어야 함.
왕복 경로는 동일
*/

class Node {
    int num;
    int dist;

    Node(int num, int dist) {
        this.num = num;
        this.dist = dist;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 1. 그래프 생성 및 값 설정
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int w = path[2];
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }

        // 2. 산봉우리, 출입구 표시 해두기
        boolean[] isSummit = new boolean[n + 1];
        boolean[] isGate = new boolean[n + 1];
        for (int summit : summits) isSummit[summit] = true;
        for (int gate : gates) isGate[gate] = true;

        // 3. intensity 배열 초기화
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 4. 다익스트라 - 우선순위 큐 활용
        // 거리 작은 거 우선 / 거리가 같으면 번호 낮은 거 우선
        PriorityQueue<Node> pq = new PriorityQueue<>( (a, b) -> {
            if (a.dist == b.dist) return a.num - b.num;
            return a.dist - b.dist;
        });

        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 이미 더 좋은 intensity로 방문했다면 스킵
            if (intensity[cur.num] < cur.dist) continue;
            
            // 산봉우리면 stop => 왔던 길 되돌아가면 됨
            if (isSummit[cur.num]) continue;

            for (Node next : graph.get(cur.num)) {
                // 출입구로 돌아가는 경로만 무시해주면 무조건 쉼터 or 산봉우리로 감
                if (isGate[next.num]) continue;
                
                int newIntensity = Math.max(intensity[cur.num], next.dist);
                if (intensity[next.num] > newIntensity) {
                    intensity[next.num] = newIntensity;
                    pq.offer(new Node(next.num, newIntensity));
                }
            }
        }

        // 5. 산봉우리 중 intensity가 최소인 것 찾기 (여러 개면 번호가 작은 것)
        int minSummit = 0;
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits); // 번호가 작은 것 우선
        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}
